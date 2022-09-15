package com.profilecreator.ui.login

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.co_win_intergration.R
import com.profilecreator.dataSource.SuccessMessageView
import com.profilecreator.ui.dashboard.DashboardActivity
import com.profilecreator.viewModel.LoginViewModel
import com.profilecreator.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

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

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
        })

        loginViewModel.otpResult.observe(this@LoginActivity, Observer {
            val otpResult = it ?: return@Observer

            //loading.visibility = View.GONE
            if (otpResult.error != null) {
                showLoginFailed(otpResult.error)
            }
            if (otpResult.success != null) {
                updateUiWithUser(otpResult.success)
            }
            setResult(Activity.RESULT_OK)
        })

        loginViewModel.confirmOtpResult.observe(this@LoginActivity, Observer {
            val otpResult = it ?: return@Observer

            //loading.visibility = View.GONE
            if (otpResult.error != null) {
                showLoginFailed(otpResult.error)
            }
            if (otpResult.success != null) {
                navigateToDashboardScreen()
            }
            setResult(Activity.RESULT_OK)
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString()
            )
        }

        login.setOnClickListener {
            // loading.visibility = View.VISIBLE
            loginViewModel.generateOTP(username.text.toString())
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

    private fun updateUiWithUser(successMessageView: SuccessMessageView) {
        confirmOTP(successMessageView.successMessage)
    }

    private fun confirmOTP(txnId : String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("OTP")
        builder.setCancelable(false)
        // Set up the input
        val input = EditText(this)
         // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.hint = "Enter OTP"
        input.inputType = InputType.TYPE_CLASS_NUMBER
        input.filters  = arrayOf(InputFilter.LengthFilter(6))
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            var otp = input.text.toString()

            loginViewModel.confirmOTP(otp, txnId)
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
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
