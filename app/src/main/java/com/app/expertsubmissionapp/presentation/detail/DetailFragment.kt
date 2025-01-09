
package com.app.expertsubmissionapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.app.expertsubmissionapp.R
import com.app.expertsubmissionapp.core.domain.model.MyProduct
import com.app.expertsubmissionapp.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    private var binding: FragmentDetailBinding? = null
    private val bind get() = binding!!
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getInt("productId") ?: return

        bind.apply {
            detailViewModel.detailProduct(productId).observe(viewLifecycleOwner) { product ->
                bindProductDetails(product)

                var status = product.isFavorite
                setStatus(status)
                fav.setOnClickListener {
                    status = !status
                    detailViewModel.setFavorite(product, status)
                    setStatus(status)
                }
            }
        }
    }

    private fun setStatus(status: Boolean) {
        val statusFav = if (status) R.drawable.baseline_star_24 else R.drawable.baseline_star_outline_24
        bind.fav.setImageDrawable(ContextCompat.getDrawable(requireActivity(), statusFav))
    }

    private fun bindProductDetails(product: MyProduct) {
        bind.apply {
            judul.text = product.title
            desc.text = product.description
            Glide.with(requireActivity()).load(product.image).into(foto)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
