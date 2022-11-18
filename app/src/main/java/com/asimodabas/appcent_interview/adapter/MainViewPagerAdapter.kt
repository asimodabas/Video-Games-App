package com.asimodabas.appcent_interview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.asimodabas.appcent_interview.R
import com.asimodabas.appcent_interview.listener.GameViewPagerListener
import com.asimodabas.appcent_interview.model.Result
import com.bumptech.glide.Glide

class MainViewPagerAdapter(private val listener: GameViewPagerListener) :
    RecyclerView.Adapter<MainViewPagerAdapter.ViewPagerViewHolder>() {

    private var games = ArrayList<Result>()

    class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val gameImage: ImageView = itemView.findViewById(R.id.imageViewPager)

        fun bind(listener: GameViewPagerListener, game: Result) {
            Glide.with(itemView.context)
                .load(game.imageUrl)
                .into(gameImage)

            itemView.setOnClickListener {
                listener.ViewPagerClick(game)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewPagerViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = layoutInflater.inflate(R.layout.viewpager_row, parent, false)
                return ViewPagerViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewPagerViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) =
        holder.bind(listener, games[position])

    override fun getItemCount() = games.size

    fun setList(gamesListForViewPager: ArrayList<Result>) {
        games.clear()
        games.addAll(gamesListForViewPager)
        notifyDataSetChanged()
    }
}