package com.asimodabas.appcent_interview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.asimodabas.appcent_interview.databinding.MainRowBinding
import com.asimodabas.appcent_interview.listener.GameClickListener
import com.asimodabas.appcent_interview.model.Result

class MainRecyclerAdapter(private val listener: GameClickListener) :
    RecyclerView.Adapter<MainRecyclerAdapter.MViewHolder>(), Filterable {

    private var data = ArrayList<Result>()

    class MViewHolder(private val binding: MainRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: GameClickListener, game: Result) {
            binding.onClickListener = listener
            binding.game = game
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MainRowBinding.inflate(layoutInflater, parent, false)
                return MViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MViewHolder.from(parent)

    override fun onBindViewHolder(holder: MViewHolder, position: Int) =
        holder.bind(listener, data[position])

    override fun getItemCount() = data.size

    fun setList(newList: List<Result>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return gameFilter
    }

    private val gameFilter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = arrayListOf<Result>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(data)
            } else {
                val filterPattern = constraint.toString().lowercase().trim()
                for (item in data) {
                    if (item.name!!.lowercase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            val result = p1?.values as ArrayList<Result>
            listener.GameFilter(result.size)
            data.clear()
            data.addAll(result)
            notifyDataSetChanged()
        }
    }
}