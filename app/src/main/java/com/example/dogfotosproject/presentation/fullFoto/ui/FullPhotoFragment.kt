package com.example.dogfotosproject.presentation.fullFoto.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.dogfotosproject.R
import com.example.dogfotosproject.data.local.UserSessionManager
import com.example.dogfotosproject.databinding.FragmentFullPhotoBinding
import com.example.dogfotosproject.presentation.fullFoto.viewmodel.FullPhotoViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class FullPhotoFragment : Fragment() {

    private var _binding: FragmentFullPhotoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FullPhotoViewModel by viewModel()
    private lateinit var imageUrl: String

    private val userSessionManager: UserSessionManager by inject()

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
            val message = if (added) {
                "Добавлено в избранное"
            } else {
                "Уже добавлено"
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    findNavController().navigate(R.id.mainFragment)
                    true
                }
                R.id.action_favorites -> {
                    findNavController().navigate(R.id.favoriteFragment)
                    true
                }
                R.id.action_logout -> {
                    // Очистить сессию пользователя
                    lifecycleScope.launch {
                        userSessionManager.clearUserLogin()
                        findNavController().navigate(R.id.loginFragment)
                    }
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


