package com.example.devmuscles.auth_feature.domain.validation

interface EmailPatternValidator {

    fun isValid(email: String): Boolean
}