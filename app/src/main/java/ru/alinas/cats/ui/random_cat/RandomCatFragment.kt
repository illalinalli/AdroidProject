package ru.alinas.cats.ui.random_cat

import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.alinas.cats.databinding.FragmentRandomCatBinding

@AndroidEntryPoint
class RandomCatFragment : Fragment() {
    private var _binding: FragmentRandomCatBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RandomCatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomCatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivRandomCat.setOnClickListener {
            if (binding.ivRandomCat.drawable != null) {
                binding.ivLike.alpha = 0.7f
                (binding.ivLike.drawable as AnimatedVectorDrawable).start()
                viewModel.saveCat(binding.ivRandomCat.drawable, onError = {
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), "Failed to save cat", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
                    onSuccess = {
                        requireActivity().runOnUiThread {
                            Toast.makeText(
                                requireContext(),
                                "Cat saved successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }
        }

        binding.btnGetRandomCat.setOnClickListener {
            binding.progressbar.visibility = View.VISIBLE
            binding.ivRandomCat.setImageBitmap(null)
            Glide.with(requireActivity())
                .load("https://cataas.com/cat")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .centerCrop()
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        p0: GlideException?,
                        p1: Any?,
                        p2: Target<Drawable>?,
                        p3: Boolean
                    ): Boolean {
                        Toast.makeText(requireContext(), p0?.message, Toast.LENGTH_SHORT).show()
                        return false
                    }

                    override fun onResourceReady(
                        p0: Drawable?,
                        p1: Any?,
                        p2: Target<Drawable>?,
                        p3: DataSource?,
                        p4: Boolean
                    ): Boolean {
                        binding.progressbar.visibility = View.GONE
                        return false
                    }
                })
                .into(binding.ivRandomCat)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}