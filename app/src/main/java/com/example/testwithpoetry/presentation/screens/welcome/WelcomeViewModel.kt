package com.example.testwithpoetry.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwithpoetry.di.IoDispatcher
import com.example.testwithpoetry.domain.models.User
import com.example.testwithpoetry.domain.usecases.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    var welcomeState: MutableStateFlow<WelcomeState> = MutableStateFlow(WelcomeState())
        private set

    fun onEvent(event: WelcomeEvent) {
        when (event) {
            is WelcomeEvent.UserChanged -> userChanged(event.input)
            is WelcomeEvent.EmailChanged -> emailChanged(event.input)
            is WelcomeEvent.BirthdayChanged -> birthdayChanged(event.input)
            is WelcomeEvent.ShowDateModal -> showDateModal(event.visible)
            is WelcomeEvent.SaveUser -> saveUser()
        }
    }

    private fun userChanged(input: String) {
        welcomeState.update {
            it.copy(name = input)
        }
        validateForm()
    }

    private fun emailChanged(input: String) {
        welcomeState.update {
            it.copy(email = input)
        }
        validateForm()
    }

    private fun birthdayChanged(input: Long) {
        welcomeState.update {
            it.copy(birthday = input)
        }
        validateForm()
    }

    private fun validateForm() {
        welcomeState.update {
            val isFormValid = it.name.isNotBlank() && it.email.isNotBlank() && it.birthday > 0L
            it.copy(isFormValid = isFormValid)
        }
    }

    private fun showDateModal(show: Boolean) {
        welcomeState.update {
            it.copy(showModal = show)
        }
    }

    private fun saveUser() {
        viewModelScope.launch(dispatcher) {
            with(welcomeState.value) {
                val user = User(
                    name = name,
                    email = email,
                    birthday = birthday
                )
                saveUserUseCase(user)
            }
        }
    }
}