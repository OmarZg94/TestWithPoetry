package com.example.testwithpoetry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testwithpoetry.di.IoDispatcher
import com.example.testwithpoetry.domain.models.Author
import com.example.testwithpoetry.domain.usecases.GetAuthorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAuthorsUseCase: GetAuthorsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _authorsState = MutableLiveData<List<Author>>()

    fun action() {
        viewModelScope.launch(dispatcher) {
            try {
                val authors = getAuthorsUseCase()
                _authorsState.postValue(authors)
            } catch (e: Exception) {

            }
        }
    }
}