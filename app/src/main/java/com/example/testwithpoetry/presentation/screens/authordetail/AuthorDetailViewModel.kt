package com.example.testwithpoetry.presentation.screens.authordetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwithpoetry.di.IoDispatcher
import com.example.testwithpoetry.domain.usecases.GetAuthorTitlesUseCase
import com.example.testwithpoetry.utils.EMPTY
import com.example.testwithpoetry.utils.UNKNOWN_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorDetailViewModel @Inject constructor(
    private val getAuthorTitlesUseCase: GetAuthorTitlesUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _authorDetailState: MutableStateFlow<AuthorDetailState> = MutableStateFlow(
        AuthorDetailState()
    )
    val authorDetailState: StateFlow<AuthorDetailState> = _authorDetailState

    fun onEvent(event: AuthorDetailEvent) {
        when (event) {
            is AuthorDetailEvent.GetTitles -> getAuthorTitles(event.authorName)
        }
    }

    private fun getAuthorTitles(authorName: String) {
        viewModelScope.launch(dispatcher) {
            _authorDetailState.update {
                it.copy(
                    isLoading = true,
                    message = EMPTY
                )
            }
            try {
                val titles = getAuthorTitlesUseCase(authorName)
                _authorDetailState.update {
                    it.copy(
                        isLoading = false,
                        titles = titles,
                        message = EMPTY
                    )
                }
            } catch (e: Exception) {
                _authorDetailState.update {
                    it.copy(
                        isLoading = false,
                        message = e.message ?: UNKNOWN_ERROR
                    )
                }
            }
        }
    }
}