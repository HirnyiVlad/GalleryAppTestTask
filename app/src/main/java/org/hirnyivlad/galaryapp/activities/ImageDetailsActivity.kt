package org.hirnyivlad.galaryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import org.hirnyivlad.galaryapp.databinding.ActivityImageDetailsBinding

class ImageDetailsActivity : AppCompatActivity() {
    private var binding: ActivityImageDetailsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        Glide.with(this).load(intent.getStringExtra("image"))
            .into(binding?.myZoomageView!!)
    }
}