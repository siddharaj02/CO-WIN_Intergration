package com.profilecreator.viewModel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.co_win_intergration.R
import com.profilecreator.dataSource.Response
import com.profilecreator.dataSource.Result
import com.profilecreator.dataSource.SuccessMessageView
import com.profilecreator.model.LoginFormState
import com.profilecreator.repository.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    val OTP_ERROR= "Error, please get new OTP"
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _otpResult = MutableLiveData<Response>()
    val otpResult: LiveData<Response> = _otpResult

    private val _confirmOtpResult = MutableLiveData<Response>()
    val confirmOtpResult: LiveData<Response> = _confirmOtpResult

    fun generateOTP(mobile: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.generateOTP(mobile)

        if (result is Result.Success) {
            _otpResult.value = Response(
                success = result.data?.txnId?.let { SuccessMessageView(successMessage = it) }
            )
        } else {
            _otpResult.value = Response(error = Resources.getSystem().getString(R.string.generate_otp_failed))
        }
    }

    fun confirmOTP(otp: String, txnId : String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.confirmOTP(otp, txnId)

        if (result is Result.Success) {
            _confirmOtpResult.value = Response(
                success = result.data?.token?.let { SuccessMessageView(successMessage = it) }
            )
        } else {
            _confirmOtpResult.value = Response(error = Resources.getSystem().getString(R.string.otp_failed))
        }
    }

    fun loginDataChanged(username: String) {
        if (!isMobileNumberValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        }  else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    fun otpDataChanged(otp: String) {
        if (!isOTPLengthValid(otp)) {
            _loginForm.value = LoginFormState(otpError = R.string.invalid_otp_length)
        }  else {
            _loginForm.value = LoginFormState(isOTPLengthValid = true)
        }
    }

    // A placeholder mobile number validation check
    private fun isMobileNumberValid(mobileNumber: String): Boolean {
        return mobileNumber.length == 10
    }

    // A placeholder otp validation check
    private fun isOTPLengthValid(mobile: String): Boolean {
        return mobile.length == 6
    }
}
