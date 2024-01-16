package com.example.devmuscles.auth_feature.domain.validation

interface UserDataValidator {

    fun validateFullName(fullName: String): ValidationResult

    fun validateEmail(email: String): ValidationResult

    fun validatePassword(password: String): ValidationResult

}