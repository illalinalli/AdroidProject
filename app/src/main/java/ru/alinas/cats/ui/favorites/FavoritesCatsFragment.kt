package ru.alinas.cats.ui.favorites

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import dagger.hilt.android.AndroidEntryPoint
import ru.alinas.cats.databinding.FragmentFavoritesCatsBinding

@AndroidEntryPoint
class FavoritesCatsFragment : Fragment() {
    private var _binding: FragmentFavoritesCatsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesCatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesCatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCats.layoutManager = LinearLayoutManager(requireContext())

        PagerSnapHelper().attachToRecyclerView(binding.rvCats)

        viewModel.getAllCats(onSuccess = {
            val catAdapter = CatAdapter(it)
            binding.rvCats.adapter = catAdapter
        },
            onError = {
                Toast.makeText(
                    requireContext(),
                    "Error while loading cats",
                    Toast.LENGTH_SHORT
                )
                    .show()
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}