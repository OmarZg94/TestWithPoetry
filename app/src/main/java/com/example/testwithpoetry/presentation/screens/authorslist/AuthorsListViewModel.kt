package com.example.testwithpoetry.presentation.screens.authorslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwithpoetry.di.IoDispatcher
import com.example.testwithpoetry.domain.models.Author
import com.example.testwithpoetry.domain.usecases.GetAuthorsUseCase
import com.example.testwithpoetry.domain.usecases.GetFavoriteAuthorsUseCase
import com.example.testwithpoetry.domain.usecases.RemoveFavoriteAuthorUseCase
import com.example.testwithpoetry.domain.usecases.SaveFavoriteAuthorUseCase
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
class AuthorsListViewModel @Inject constructor(
    private val getAuthorsUseCase: GetAuthorsUseCase,
    private val getFavoriteAuthorsUseCase: GetFavoriteAuthorsUseCase,
    private val saveFavoriteAuthorUseCase: SaveFavoriteAuthorUseCase,
    private val removeFavoriteAuthorUseCase: RemoveFavoriteAuthorUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _authorsListState: MutableStateFlow<AuthorsListState> = MutableStateFlow(
        AuthorsListState()
    )
    val authorsListState: StateFlow<AuthorsListState> = _authorsListState

    fun onEvent(event: AuthorsListEvent) {
        when (event) {
            AuthorsListEvent.GetAuthorsList -> getAuthorsList()
            is AuthorsListEvent.SaveFavoriteAuthor -> saveFavoriteAuthor(event.author)
            is AuthorsListEvent.RemoveFavoriteAuthor -> removeFavoriteAuthor(event.author)
        }
    }

    private fun getAuthorsList() {
        viewModelScope.launch(dispatcher) {
            _authorsListState.update {
                it.copy(
                    isLoading = true,
                    error = EMPTY
                )
            }
            try {
                val authors = getAuthorsUseCase()
                mergeFavoriteAuthors(authors)
            } catch (e: Exception) {
                _authorsListState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: UNKNOWN_ERROR
                    )
                }
            }
        }
    }

    private suspend fun mergeFavoriteAuthors(authors: List<Author>) {
        val favoriteAuthors = getFavoriteAuthorsUseCase().map { it.name }.toSet()
        val mergedAuthors = authors.map { author ->
            if (favoriteAuthors.contains(author.name)) {
                author.copy(isFavorite = true)
            } else {
                author.copy(isFavorite = false)
            }
        }
        _authorsListState.update {
            it.copy(
                isLoading = false,
                authors = mergedAuthors,
                error = EMPTY
            )
        }
    }

    private fun saveFavoriteAuthor(author: Author) {
        viewModelScope.launch(dispatcher) {
            saveFavoriteAuthorUseCase(author)
            mergeFavoriteAuthors(authorsListState.value.authors!!)
        }
    }

    private fun removeFavoriteAuthor(author: Author) {
        viewModelScope.launch(dispatcher) {
            removeFavoriteAuthorUseCase(author)
            mergeFavoriteAuthors(authorsListState.value.authors!!)
        }
    }
}