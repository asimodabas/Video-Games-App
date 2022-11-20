package com.asimodabas.appcent_interview.ui.fragment.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asimodabas.appcent_interview.model.GameDTO
import com.asimodabas.appcent_interview.repository.MainRepository
import com.asimodabas.appcent_interview.util.NetworkCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    val gameResponse: MutableLiveData<GameDTO?> = MutableLiveData()
    val onError: MutableLiveData<String?> = MutableLiveData()

    fun getData(
        apiKey: String
    ) = viewModelScope.launch {
        val request = repository.getData(apiKey)
        when (request) {
            is NetworkCheck.Success -> {
                gameResponse.value = request.data
            }
            is NetworkCheck.Error -> {
                onError.value = request.message
            }
        }
    }
}