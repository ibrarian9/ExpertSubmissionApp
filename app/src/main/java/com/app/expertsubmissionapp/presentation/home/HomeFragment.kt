package com.app.expertsubmissionapp.presentation.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.expertsubmissionapp.R
import com.app.expertsubmissionapp.core.adapter.ProductsAdapter
import com.app.expertsubmissionapp.core.data.Resource
import com.app.expertsubmissionapp.databinding.FragmentHomeBinding
import com.app.expertsubmissionapp.di.homeModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val bind get() = _binding!!
    private val productsAdapter = ProductsAdapter()
    private lateinit var broadcastReceiver: BroadcastReceiver
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onStart() {
        super.onStart()
        broadcastReceiver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(homeModule)

        bind.apply {

            rv.apply {
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = productsAdapter
            }

            productsAdapter.onItemClick = {
                val bundle = Bundle().apply {
                    putInt("productId", it.id)
                }
                findNavController().navigate(R.id.navigation_detail, bundle)
            }

            homeViewModel.product.observe(viewLifecycleOwner) { produk ->
                 produk.let {
                     when(it) {
                         is Resource.Loading -> progressBar.visibility = View.VISIBLE
                         is Resource.Success<*> -> {
                             progressBar.visibility = View.GONE
                             productsAdapter.submitList(it.data)
                         }
                         is Resource.Error -> {
                             progressBar.visibility = View.GONE
                             viewError.root.visibility = View.VISIBLE
                             viewError.tvError.text = it.message ?: "Something Wrong!"
                         }
                     }
                 }
            }
        }
    }

    private fun broadcastReceiver() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                when (intent.action){
                    Intent.ACTION_POWER_CONNECTED -> {
                        bind.tvPowerStatus.text = getString(R.string.power_connected)
                    }
                    Intent.ACTION_POWER_DISCONNECTED -> {
                        bind.tvPowerStatus.text = getString(R.string.power_disconnected)
                    }
                }
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }

        requireActivity().registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.rv?.adapter = null
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        broadcastReceiver()
    }
}