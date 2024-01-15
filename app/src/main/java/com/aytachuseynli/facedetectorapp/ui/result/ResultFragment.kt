package com.aytachuseynli.facedetectorapp.ui.result

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aytachuseynli.facedetectorapp.databinding.FragmentResultBinding
import com.aytachuseynli.facedetectorapp.ui.adapter.TestResultAdapter
import com.aytachuseynli.facedetectorapp.ui.camera.CameraViewModel.Companion.result
import com.aytachuseynli.facedetectorapp.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ResultFragment : BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate) {
    private val viewModel by viewModels<ResultViewModel>()
    private val adapter = TestResultAdapter()

    override fun observeEvents() {
        viewModel.getResultData().observe(viewLifecycleOwner){result->
            adapter.submitList(result)

        }
    }

    override fun onCreateFinish() {

    }


    override fun setupListeners() {
        binding.fab.setOnClickListener {
            findNavController().navigate(ResultFragmentDirections.toCamera())

        }
        binding.recycleView.adapter =  adapter
    }



}