package com.asimodabas.appcent_interview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asimodabas.appcent_interview.databinding.MyfavoriteRowBinding
import com.asimodabas.appcent_interview.model.Detail

class FavoriteRecyclerAdapter() :
    RecyclerView.Adapter<FavoriteRecyclerAdapter.FavoriteViewHolder>() {

    private var games = ArrayList<Detail?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoriteViewHolder.from(parent)

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val currentList = games[position]
        holder.bind(currentList!!)
    }

    class FavoriteViewHolder(private val binding: MyfavoriteRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gameDetail: Detail) {
            binding.game = gameDetail
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FavoriteViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MyfavoriteRowBinding.inflate(layoutInflater, parent, false)
                return FavoriteViewHolder(binding)
            }
        }
    }

    fun setList(newList: List<Detail?>) {
        games.clear()
        games.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = games.size
}