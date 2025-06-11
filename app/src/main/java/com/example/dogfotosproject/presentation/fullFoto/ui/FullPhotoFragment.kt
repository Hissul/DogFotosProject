package com.example.dogfotosproject.presentation.fullFoto.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.dogfotosproject.databinding.FragmentFullPhotoBinding
import com.example.dogfotosproject.presentation.fullFoto.viewmodel.FullPhotoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FullPhotoFragment : Fragment() {

    private var _binding: FragmentFullPhotoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FullPhotoViewModel by viewModel()
    private lateinit var imageUrl: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFullPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageUrl = arguments?.getString("imageUrl") ?: return

        Glide.with(this).load(imageUrl).into(binding.fullPhotoImageView)

        binding.favoriteButton.setOnClickListener {
            viewModel.addToFavorites(imageUrl)
        }

        viewModel.favoriteState.observe(viewLifecycleOwner) { added ->
            if (added) {
                Toast.makeText(requireContext(), "Добавлено в избранное", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


