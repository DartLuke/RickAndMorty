package com.danielpasser.rickandmorty.ui.characterslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.danielpasser.rickandmorty.model.Character
import com.danielpasser.rickandmorty.model.Response
import com.danielpasser.rickandmorty.repo.CharacterRepository
import com.danielpasser.rickandmorty.repo.Repository
import com.danielpasser.rickandmorty.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val repository: Repository,
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> get() = _errorMessage

    private lateinit var _charactersFlow: Flow<PagingData<Character>>
    val charactersFlow: Flow<PagingData<Character>>
        get() = _charactersFlow

    init {
        getAllCharacters()
    }



    private fun getAllCharacters() {
        viewModelScope.launch {
            try {
                _charactersFlow = characterRepository.getAllCharacters()

            } catch (ex: Exception) {
               _errorMessage.postValue( ex.message)
            }

        }
    }


}