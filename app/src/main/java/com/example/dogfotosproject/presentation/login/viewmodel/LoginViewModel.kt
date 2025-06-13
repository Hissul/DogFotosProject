package com.example.dogfotosproject.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogfotosproject.data.db.entity.UserEntity
import com.example.dogfotosproject.domain.usecase.LoginUserUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUserUseCase: LoginUserUseCase) : ViewModel() {

    fun login(login: String, password: String, onSuccess: (UserEntity) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val user = loginUserUseCase(login, password)
                if (user != null) onSuccess(user)
                else onError("Неверный логин или пароль")
            }
            catch (e: Exception) {
                onError("Ошибка: ${e.localizedMessage}")
            }
        }
    }
}