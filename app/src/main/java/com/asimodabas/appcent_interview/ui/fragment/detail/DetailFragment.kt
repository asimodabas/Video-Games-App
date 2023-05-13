package com.asimodabas.appcent_interview.ui.fragment.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.asimodabas.appcent_interview.R
import com.asimodabas.appcent_interview.databinding.FragmentDetailBinding
import com.asimodabas.appcent_interview.model.Detail
import com.asimodabas.appcent_interview.util.Constants.API_KEY
import com.asimodabas.appcent_interview.util.loadImage
import com.asimodabas.appcent_interview.util.toastMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var nowData: Detail

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetail(args.argId, API_KEY)
        observeEvents()
    }

    fun observeEvents() {
        with(viewModel) {
            detailResponse.observe(viewLifecycleOwner) {
                it?.let {
                    with(binding) {
                        myVisibilties(false)
                        nowData = it
                        val sharedPref = requireContext().getSharedPreferences(
                            "sharedPrefs", Context.MODE_PRIVATE
                        )
                        val isGameInFavorites = sharedPref.getBoolean(nowData.name, false)
                        if (isGameInFavorites) {
                            imageViewFavFab.setImageResource(R.drawable.ic_fav)
                            it.favorite = true
                        } else {
                            imageViewFavFab.setImageResource(R.drawable.ic_fav_null)
                            it.favorite = false
                        }
                        imageViewDetail.loadImage(it.imageUrl.toString())
                        textViewGameName.text = it.name
                        textViewReleased.text = it.released
                        textViewMetacritic.text = it.metacritic
                        textViewDescription.text = it.description
                        favClickButton(it)
                    }
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

    private fun favClickButton(detail: Detail) = with(binding) {
        imageViewFavFab.setOnClickListener {
            if (detail.favorite) {
                imageViewFavFab.setImageResource(R.drawable.ic_fav_null)
                detail.favorite = false

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.deleteFav(detail)
                    val sharedPref =
                        requireContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                    sharedPref.edit().remove(detail.name).apply()
                }
            } else {
                imageViewFavFab.setImageResource(R.drawable.ic_fav)
                detail.favorite = true

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.addFav(detail)

                    val sharedPref = requireContext().getSharedPreferences(
                        "sharedPrefs", Context.MODE_PRIVATE
                    )
                    sharedPref.edit().putBoolean(detail.name, true).apply()
                }
            }
        }
    }

    private fun myVisibilties(isLoading: Boolean = false) = with(binding) {
        imageViewDetail.isVisible = !isLoading
        imageViewFavFab.isVisible = !isLoading
        textViewGameName.isVisible = !isLoading
        textViewReleased.isVisible = !isLoading
        textViewMetacritic.isVisible = !isLoading
        textViewDescription.isVisible = !isLoading
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}