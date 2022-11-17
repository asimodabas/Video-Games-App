package com.asimodabas.appcent_interview.ui.fragment.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.asimodabas.appcent_interview.Constants.API_KEY
import com.asimodabas.appcent_interview.R
import com.asimodabas.appcent_interview.databinding.FragmentDetailBinding
import com.asimodabas.appcent_interview.loadImage
import com.asimodabas.appcent_interview.model.Detail
import com.asimodabas.appcent_interview.toastMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailViewModel
    private lateinit var nowData: Detail
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]

        viewModel.getDetail(args.argId, API_KEY)
        observeEvents()
    }

    fun observeEvents() {
        with(viewModel) {
            detailResponse.observe(viewLifecycleOwner) {
                it?.let {
                    myVisibilties(false)
                    nowData = it
                    val sharedPref =
                        requireContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                    val isGameInFavorites = sharedPref.getBoolean(nowData.name, false)
                    if (isGameInFavorites) {
                        binding.imageViewFavFab.setImageResource(R.drawable.ic_fav)
                        it.favorite = true
                    } else {
                        binding.imageViewFavFab.setImageResource(R.drawable.ic_fav_null)
                        it.favorite = false
                    }
                    binding.imageViewDetail.loadImage(it.imageUrl.toString())
                    binding.textViewGameName.text = it.name
                    binding.textViewReleased.text = it.released
                    binding.textViewMetacritic.text = it.metacritic
                    binding.textViewDescription.text = it.description
                    favClickButton(it)
                }
            }

            isLoading.observe(viewLifecycleOwner) {
                myVisibilties(it)
            }

            onError.observe(viewLifecycleOwner) {
                requireContext().toastMessage(it.toString())
            }
        }
    }

    private fun favClickButton(detail: Detail) {
        binding.imageViewFavFab.setOnClickListener {
            if (detail.favorite) {
                binding.imageViewFavFab.setImageResource(R.drawable.ic_fav_null)
                detail.favorite = false

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.deleteFav(requireContext(), detail)

                    val sharedPref =
                        requireContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                    sharedPref.edit().remove(detail.name).apply()
                }
            } else {
                binding.imageViewFavFab.setImageResource(R.drawable.ic_fav)
                detail.favorite = true

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.addFav(requireContext(), detail)

                    val sharedPref =
                        requireContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                    sharedPref.edit().putBoolean(detail.name, true).apply()
                }
            }
        }
    }

    private fun myVisibilties(isLoading: Boolean = false) {
        binding.imageViewDetail.isVisible = !isLoading
        binding.imageViewFavFab.isVisible = !isLoading
        binding.textViewGameName.isVisible = !isLoading
        binding.textViewReleased.isVisible = !isLoading
        binding.textViewMetacritic.isVisible = !isLoading
        binding.textViewDescription.isVisible = !isLoading
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}