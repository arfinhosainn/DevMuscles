package com.example.devmuscles.auth_feature.data.validation

import android.util.Patterns
import com.example.devmuscles.auth_feature.domain.validation.EmailPatternValidator

class EmailPatternValidatorImpl: EmailPatternValidator {
    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}