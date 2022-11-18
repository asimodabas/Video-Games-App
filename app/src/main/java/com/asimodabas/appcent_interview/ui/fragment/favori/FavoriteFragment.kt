package com.asimodabas.appcent_interview.ui.fragment.favori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asimodabas.appcent_interview.adapter.FavoriteRecyclerAdapter
import com.asimodabas.appcent_interview.databinding.FragmentFavoriteBinding
import com.asimodabas.appcent_interview.model.Detail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var recyclerAdapter: FavoriteRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[FavoriteViewModel::class.java]

        viewModel.getFavDB(requireContext())
        observeEvents()
    }

    fun observeEvents() {
        with(viewModel) {
            favResponse.observe(viewLifecycleOwner) {
                it?.let {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.getFavDB(requireContext())
                        binding.favoriteRecyclerView.layoutManager =
                            LinearLayoutManager(requireContext())
                        binding.favoriteRecyclerView.visibility = View.VISIBLE
                        withContext(Dispatchers.Main) {
                            changeRecyclerView(it)
                        }
                    }
                }
            }
        }
    }

    fun changeRecyclerView(data: List<Detail?>) {
        recyclerAdapter = FavoriteRecyclerAdapter()
        binding.favoriteRecyclerView.adapter = recyclerAdapter
        recyclerAdapter.setList(data)
    }
}