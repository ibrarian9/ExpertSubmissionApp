package com.app.expertsubmissionapp.favorite.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.expertsubmissionapp.R
import com.app.expertsubmissionapp.core.adapter.ProductsAdapter
import com.app.expertsubmissionapp.favorite.databinding.FragmentFavoriteBinding
import com.app.expertsubmissionapp.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private var binding: FragmentFavoriteBinding? = null
    private val bind get() = binding!!
    private val productsAdapter = ProductsAdapter()
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(favoriteModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.apply {

            favoriteViewModel.favoriteProduct.observe(viewLifecycleOwner) {
                productsAdapter.submitList(it)
            }

            rv.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = productsAdapter
            }

            productsAdapter.onItemClick = {
                val bundle =
                    Bundle().apply {
                        putInt("productId", it.id)
                    }
                findNavController().navigate(R.id.navigation_detail, bundle)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.rv?.adapter = null
        binding = null
    }
}