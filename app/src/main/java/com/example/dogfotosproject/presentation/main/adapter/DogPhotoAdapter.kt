package com.example.dogfotosproject.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogfotosproject.databinding.ItemDogPhotoBinding


class DogPhotoAdapter(private val items: List<String>,private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<DogPhotoAdapter.DogPhotoViewHolder>() {

    inner class DogPhotoViewHolder(private val binding: ItemDogPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photoUrl: String) {
            Glide.with(binding.root.context)
                .load(photoUrl)
                .into(binding.imageViewDog)

            binding.root.setOnClickListener {
                onClick(photoUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogPhotoViewHolder {
        val binding = ItemDogPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogPhotoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}