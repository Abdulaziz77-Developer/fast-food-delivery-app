package com.example.app.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.model.Resource
import com.example.app.domain.repository.IAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// Обязательная модель состояния для экрана
data class AuthState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: IAuthRepository // Проверь, что этот интерфейс создан в Domain
) : ViewModel() {

    private val _state = mutableStateOf(AuthState())
    val state: State<AuthState> = _state

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthState(isLoading = true)

            when (val result = repository.login(email, password)) {
                is Resource.Success -> {
                    _state.value = AuthState(isSuccess = true)
                }
                is Resource.Error -> {
                    _state.value = AuthState(error = result.message ?: "Ошибка авторизации")
                }
                else -> {}
            }
        }
    }
}