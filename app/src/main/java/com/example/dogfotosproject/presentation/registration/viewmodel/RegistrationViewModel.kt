package com.example.dogfotosproject.presentation.registration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogfotosproject.data.db.entity.UserEntity
import com.example.dogfotosproject.domain.usecase.RegisterUserUseCase
import kotlinx.coroutines.launch


class RegistrationViewModel(private val registerUserUseCase: RegisterUserUseCase) : ViewModel() {

    fun register(login: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                if (login.isBlank() || password.isBlank()) {
                    onError("Логин и пароль не должны быть пустыми")
                    return@launch
                }

                val user = UserEntity(login = login, password = password)
                registerUserUseCase(user)
                onSuccess()
            }
            catch (e: Exception) {
                onError("Ошибка регистрации: ${e.localizedMessage}")
            }
        }
    }
}