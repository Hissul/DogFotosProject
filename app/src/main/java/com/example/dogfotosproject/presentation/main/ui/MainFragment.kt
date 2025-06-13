package com.example.dogfotosproject.presentation.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogfotosproject.R
import com.example.dogfotosproject.data.local.UserSessionManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.dogfotosproject.databinding.FragmentMainBinding
import com.example.dogfotosproject.presentation.main.adapter.DogPhotoAdapter
import com.example.dogfotosproject.presentation.main.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModel()

    private val userSessionManager: UserSessionManager by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.layoutManager = layoutManager

        viewModel.photos.observe(viewLifecycleOwner) { list ->
            binding.recyclerView.adapter = DogPhotoAdapter(list) { url ->
                val bundle = Bundle().apply {
                    putString("imageUrl", url)
                }
                findNavController().navigate(
                    com.example.dogfotosproject.R.id.fullPhotoFragment,
                    bundle
                )
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.bottomProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.loadPhotos()

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount &&
                    firstVisibleItemPosition >= 0 &&
                    !viewModel.isLoading.value!!
                ) {
                    viewModel.loadPhotos()
                }
            }
        })

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    true
                }
                R.id.action_favorites -> {
                    findNavController().navigate(R.id.favoriteFragment)
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