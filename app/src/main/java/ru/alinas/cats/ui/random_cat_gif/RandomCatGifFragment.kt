package ru.alinas.cats.ui.random_cat_gif

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.alinas.cats.databinding.FragmentRandomCatGifBinding

class RandomCatGifFragment : Fragment() {
    private var _binding: FragmentRandomCatGifBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomCatGifBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetRandomCat.setOnClickListener {
            binding.progressbar.visibility = View.VISIBLE
            binding.ivRandomCatGif.setImageBitmap(null)
            Glide.with(requireActivity())
                .asGif()
                .load("https://cataas.com/cat/gif")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .centerCrop()
                .listener(object : RequestListener<GifDrawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<GifDrawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Toast.makeText(requireContext(), e?.message, Toast.LENGTH_SHORT).show()
                        return false
                    }

                    override fun onResourceReady(
                        resource: GifDrawable?,
                        model: Any?,
                        target: Target<GifDrawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressbar.visibility = View.GONE
                        return false
                    }
                })
                .into(binding.ivRandomCatGif)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}