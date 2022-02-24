package com.nvqquy98.moneyqq.util.other

import android.util.Patterns
import com.nvqquy98.moneyqq.R


fun validateEmail(email: String?): Int? {
    validateNullOrBlank(email)?.let { return it }
    val isValidateSuccess = Patterns.EMAIL_ADDRESS.matcher(email!!).matches()
    return if (isValidateSuccess) null else ValidateError.EMAIL_INCORRECT_FORMAT
}

fun validatePasswordFormat(password: String?): Int? {
    return validateNullOrBlank(password) ?: validateMinLength(
        password!!,
        MIN_LENGTH_PASSWORD,
        ValidateError.PASSWORD_INCORRECT_FORMAT
    ) ?: validIfMatchRegex(
        password!!,
        SET_PASS_RULE_REGEX,
        ValidateError.PASSWORD_INCORRECT_FORMAT
    ) ?: validIfMatchRegex(
        password!!,
        SET_PASS_ONLY_CONTAIN_CHARS_RULE_REGEX,
        ValidateError.PASSWORD_INCORRECT_FORMAT
    )
}

fun validateConfirmPassword(password: String?, confirmPassword: String?): Int? {
    return validateNullOrBlank(password)
        ?: (if (password == confirmPassword) null else ValidateError.PASSWORD_DO_NOT_MATCHES)
}

fun validateNullOrBlank(text: String?): Int? {
    return if (text.isNullOrBlank()) R.string.filed_not_empty
    else null
}

private fun validateMinLength(text: String, minLength: Int, error: Int): Int? =
    if (text.length < minLength) error else null

private fun validIfMatchRegex(text: String, regex: String, error: Int): Int? =
    if (text.matches(Regex(regex))) null else error

private const val MIN_LENGTH_PASSWORD = 8
private const val SET_PASS_RULE_REGEX =
    "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[ \\\\!\"#\$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~])[A-Za-z\\d \\\\!\"#\$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]{10,255}\$"
private const val SET_PASS_ONLY_CONTAIN_CHARS_RULE_REGEX =
    "^[a-zA-Z0-9 \\\\!\"#\$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]+\$"