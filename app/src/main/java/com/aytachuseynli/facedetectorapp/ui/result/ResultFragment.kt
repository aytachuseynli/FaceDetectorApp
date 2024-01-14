package com.aytachuseynli.facedetectorapp.ui.result

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aytachuseynli.facedetectorapp.databinding.FragmentResultBinding
import com.aytachuseynli.facedetectorapp.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ResultFragment : BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate) {
    private val viewModel by viewModels<ResultViewModel>()

    override fun observeEvents() {
        viewModel.getResultData().observe(viewLifecycleOwner){

        }
    }

    override fun onCreateFinish() {

    }


    override fun setupListeners() {
        binding.fab.setOnClickListener {
            findNavController().navigate(ResultFragmentDirections.toCamera())
        }
    }

}