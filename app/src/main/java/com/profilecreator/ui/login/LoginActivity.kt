package com.profilecreator.ui.login

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.co_win_intergration.R
import com.profilecreator.ui.dashboard.DashboardActivity
import com.profilecreator.ui.util.MainApplication
import com.profilecreator.ui.util.Util
import com.profilecreator.viewModel.LoginViewModel
import com.profilecreator.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var txnId : String
    private lateinit var token : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this, ViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid
            confirmOTPButton.isEnabled = loginState.isOTPLengthValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.otpError != null) {
                confirmOTPEdiText.error = getString(loginState.otpError)
            }
        })

        loginViewModel.otpResult.observe(this@LoginActivity, Observer {
            val otpResult = it ?: return@Observer

            if (otpResult.error != null) {
                showLoginFailed(otpResult.error)
            }
            if (otpResult.success != null) {
                txnId = it.success?.successMessage.toString()
                if(txnId.isNotEmpty()) {
                    //updateUiWithOTP()
                    //reset mobile number field
                    login.isEnabled = false
                    username.text.clear()
                } else if(txnId.contains("otp", true)) {
                        showDialog(txnId)
                }
            }
        })

        loginViewModel.confirmOtpResult.observe(this@LoginActivity, Observer {
            val otpResult = it ?: return@Observer


            if (otpResult.error != null) {
                showLoginFailed(otpResult.error)
            }
            if (otpResult.success != null) {
                token = it.success?.successMessage.toString()
                if(token.isNotEmpty()) {
                    navigateToDashboardScreen()
                }
            }
           // setResult(Activity.RESULT_OK)
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString()
            )
        }
        confirmOTPEdiText.afterTextChanged {
            loginViewModel.otpDataChanged(
                confirmOTPEdiText.text.toString()
            )
        }
        login.setOnClickListener {
            //loading.visibility = View.VISIBLE
            loginViewModel.generateOTP(username.text.toString())
        }

        confirmOTPButton.setOnClickListener {
            if(txnId.isEmpty()) {
                showLoginFailed(loginViewModel.OTP_ERROR)
            } else {
               // loading.visibility = View.VISIBLE
                loginViewModel.confirmOTP(confirmOTPEdiText.text.toString(), txnId)
            }

        }
    }

    private fun navigateToDashboardScreen() {
        val intent = Intent(
            this@LoginActivity,
            DashboardActivity::class.java
        )
        startActivity(intent)
        finish()
    }

    fun showDialog(message : String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(MainApplication.applicationContext())
        builder.setTitle("CO-WIN message")
        builder.setCancelable(false)
        // Set up the input
        val input = TextView(MainApplication.applicationContext())
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        //input.hint = "Enter OTP"
        //input.inputType = InputType.TYPE_CLASS_NUMBER
        //input.filters  = arrayOf(InputFilter.LengthFilter(6))
        input.text = message
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            // var otp = input.text.toString()

            //loginViewModel.confirmOTP(otp, txnId)
            dialog.cancel()
        })

        builder.show()
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
