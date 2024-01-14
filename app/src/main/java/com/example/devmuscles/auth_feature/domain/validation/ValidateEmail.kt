package com.example.devmuscles.auth_feature.domain.validation

class ValidateEmail() {
    fun validate(email: String): Boolean {
        if(email.isBlank()) return false

        // use regex to validate email
        val regex = Regex("^[\\w!#\$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#\$%&'*+/=?`{|}~^-]+)*@(?:[A-Z0-9-]+\\.)+[A-Z]{2,6}\$", RegexOption.IGNORE_CASE)
        return regex.matches(email)
    }
}
