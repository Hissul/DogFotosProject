package com.example.dogfotosproject.presentation.favorite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogfotosproject.R
import com.example.dogfotosproject.databinding.FragmentFavoriteBinding
import com.example.dogfotosproject.presentation.favorite.viewmodel.FavoriteViewModel
import com.example.dogfotosproject.presentation.main.adapter.DogPhotoAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.dogfotosproject.data.local.UserSessionManager
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModel()

    private val userSessionManager: UserSessionManager by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.layoutManager = layoutManager

        viewModel.favorites.observe(viewLifecycleOwner) { list ->
            val photoUrls = list.map { it.imageUrl }

            binding.recyclerView.adapter = DogPhotoAdapter(photoUrls) { url ->
                val bundle = Bundle().apply {
                    putString("imageUrl", url)
                }
                findNavController().navigate(
                    com.example.dogfotosproject.R.id.fullPhotoFragment,
                    bundle
                )
            }
        }

        viewModel.loadFavorites()

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    findNavController().navigate(R.id.mainFragment)
                    true
                }
                R.id.action_favorites -> {
                    true
                }
                R.id.action_logout -> {
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