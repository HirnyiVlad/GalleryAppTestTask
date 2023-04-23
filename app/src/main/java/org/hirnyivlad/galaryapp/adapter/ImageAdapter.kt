package org.hirnyivlad.galaryapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.hirnyivlad.galaryapp.activities.ImageDetailsActivity
import org.hirnyivlad.galaryapp.databinding.ImageItemBinding
import org.hirnyivlad.galaryapp.model.Image

class ImageAdapter(private val context: Context, list: ArrayList<Image>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    private var list: List<Image> = list

    class ImageViewHolder(binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var imageView: ImageView = binding.ivItem
        val titleTextView: TextView = binding.titleText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImageItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(context).load(list[position].getUrls().getRegular())
            .into(holder.imageView)
        holder.imageView.setOnClickListener {
            val intent = Intent(context, ImageDetailsActivity::class.java)
            intent.putExtra("image", list[position].getUrls().getRegular())
            context.startActivity(intent)
        }
        holder.titleTextView.text = list[position].getUser().getUserName()
    }
}