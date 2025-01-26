package com.example.testwithpoetry.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwithpoetry.di.IoDispatcher
import com.example.testwithpoetry.domain.usecases.GetUserUseCase
import com.example.testwithpoetry.domain.usecases.RemoveAllFavoriteAuthorsUseCase
import com.example.testwithpoetry.domain.usecases.RemoveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val removeUserUseCase: RemoveUserUseCase,
    private val removeAllFavoriteAuthorsUseCase: RemoveAllFavoriteAuthorsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _profileState: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState())
    val profileState: StateFlow<ProfileState> = _profileState

    fun onEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.GetUserInfo -> getUserInfo()
            ProfileEvent.SignOutUser -> signOutUser()
        }
    }

    private fun getUserInfo() {
        viewModelScope.launch(dispatcher) {
            val user = getUserUseCase()
            _profileState.update {
                it.copy(
                    user = user!!
                )
            }
        }
    }

    private fun signOutUser() {
        viewModelScope.launch(dispatcher) {
            removeUserUseCase()
            removeAllFavoriteAuthorsUseCase()
        }
    }
}