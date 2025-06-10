package com.example.dogfotosproject.di

import com.example.dogfotosproject.presentation.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.dogfotosproject.presentation.registration.viewmodel.RegistrationViewModel

val viewModelModule = module {
    viewModel { RegistrationViewModel(get()) }
    viewModel { LoginViewModel(get())}
}