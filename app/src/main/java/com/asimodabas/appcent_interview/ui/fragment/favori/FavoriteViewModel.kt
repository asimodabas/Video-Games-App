package com.asimodabas.appcent_interview.ui.fragment.favori

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asimodabas.appcent_interview.model.Detail
import com.asimodabas.appcent_interview.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository,
) : ViewModel() {

    val favResponse: MutableLiveData<List<Detail?>> = MutableLiveData()

    private fun showFavGame(favoriteGameList: List<Detail>) {
        favResponse.value = favoriteGameList
    }

    init {
        getFavorites()
    }

    fun getFavorites() = viewModelScope.launch {
        val result = repository.getDetails()
        favResponse.value = result
    }
}
