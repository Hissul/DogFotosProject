package com.example.dogfotosproject.presentation.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.dogfotosproject.databinding.FragmentMainBinding
import com.example.dogfotosproject.presentation.main.adapter.DogPhotoAdapter
import com.example.dogfotosproject.presentation.main.viewmodel.MainViewModel



class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.layoutManager = layoutManager

        viewModel.photos.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = DogPhotoAdapter(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loadPhotos()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}