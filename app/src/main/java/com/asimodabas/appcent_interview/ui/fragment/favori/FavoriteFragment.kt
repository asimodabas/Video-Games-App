package com.asimodabas.appcent_interview.ui.fragment.favori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asimodabas.appcent_interview.adapter.FavoriteRecyclerAdapter
import com.asimodabas.appcent_interview.databinding.FragmentFavoriteBinding
import com.asimodabas.appcent_interview.model.Detail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(), FavoriteRecyclerAdapter.OnClickFavorite {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()
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
        observeEvents()
    }

    override fun onClickItem(id: Int) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(id)
        findNavController().navigate(action)
    }

    fun observeEvents() {
        with(viewModel) {
            favResponse.observe(viewLifecycleOwner) {
                changeRecyclerView(it)
            }
        }
    }

    fun changeRecyclerView(data: List<Detail?>) {
        recyclerAdapter = FavoriteRecyclerAdapter(this)
        binding.favoriteRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        binding.favoriteRecyclerView.adapter = recyclerAdapter
        recyclerAdapter.setList(data)
    }
}