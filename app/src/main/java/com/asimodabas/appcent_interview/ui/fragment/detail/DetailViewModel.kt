package com.asimodabas.appcent_interview.ui.fragment.detail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asimodabas.appcent_interview.NetworkCheck
import com.asimodabas.appcent_interview.model.Detail
import com.asimodabas.appcent_interview.repository.DetailRepository
import com.asimodabas.appcent_interview.room.FavDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(
    private val repository: DetailRepository
) : ViewModel() {

    val detailResponse: MutableLiveData<Detail?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val onError: MutableLiveData<String?> = MutableLiveData()

    fun getDetail(
        id: Int,
        apiKey: String
    ) = viewModelScope.launch {
        isLoading.value = true
        val request = repository.getDetail(id, apiKey)
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

    fun addFav(mContext: Context, detail: Detail) {
        val dao = FavDB.invoke(mContext).myFavGame()
        dao.insert(detail)
    }

    fun deleteFav(mContext: Context, detail: Detail) {
        val dao = FavDB.invoke(mContext).myFavGame()
        dao.delete(detail)
    }
}