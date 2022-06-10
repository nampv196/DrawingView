package com.raed.rasmsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raed.rasmsample.data.ImageDrawingProvider
import com.raed.rasmsample.databinding.ItemDrawingPhotoBinding

class DrawingPhotoAdapter(
    private val onItemSelected: (photo: Int) -> Unit
) : RecyclerView.Adapter<DrawingPhotoAdapter.DrawingHolder>() {

    private val data = ImageDrawingProvider.imageDrawings

    inner class DrawingHolder(private val binding: ItemDrawingPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var photo = -1

        init {
            binding.root.setOnClickListener {
                if (photo != -1)
                    onItemSelected.invoke(photo)
            }
        }

        fun bind(photo: Int) {
            this.photo = photo
            Glide.with(binding.root).load(photo).into(binding.ivDrawingPhoto)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawingHolder {
        return DrawingHolder(
            ItemDrawingPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DrawingHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}