package com.asimodabas.appcent_interview.ui.fragment.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asimodabas.appcent_interview.model.Detail
import com.asimodabas.appcent_interview.repository.DetailRepository
import com.asimodabas.appcent_interview.repository.FavoriteRepository
import com.asimodabas.appcent_interview.util.NetworkCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(
    private val repository: FavoriteRepository, private val detailRepository: DetailRepository
) : ViewModel() {

    val detailResponse: MutableLiveData<Detail?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val onError: MutableLiveData<String?> = MutableLiveData()

    fun getDetail(
        id: Int, apiKey: String
    ) = viewModelScope.launch {
        isLoading.value = true
        val request = detailRepository.getDetail(id, apiKey)
        when (request) {
            is NetworkCheck.Success -> {
                isLoading.value = false
                detailResponse.value = request.data
            }
            is NetworkCheck.Error -> {
                isLoading.value = false
                onError.value = request.message
            }
        }
    }

    fun addFav(detail: Detail) {
        repository.insertDetail(detail)
    }

    fun deleteFav(detail: Detail) {
        repository.deleteDetail(detail)
    }
}