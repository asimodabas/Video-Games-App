package com.asimodabas.appcent_interview.ui.fragment.favori

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asimodabas.appcent_interview.model.Detail
import com.asimodabas.appcent_interview.room.FavDB

class FavoriteViewModel : ViewModel() {

    val favResponse: MutableLiveData<List<Detail?>> = MutableLiveData()

    private fun showFavGame(favoriteGameList: List<Detail>) {
        favResponse.value = favoriteGameList
    }

    fun getFavDB(context: Context) {
        val dao = FavDB(context).myFavGame()
        val favoriteGames = dao.getDetail()
        showFavGame(favoriteGames)
    }
}
