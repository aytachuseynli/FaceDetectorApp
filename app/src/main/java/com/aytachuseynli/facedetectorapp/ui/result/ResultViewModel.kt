package com.aytachuseynli.facedetectorapp.ui.result

import androidx.lifecycle.ViewModel
import com.aytachuseynli.facedetectorapp.data.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(private val repo: AppRepository): ViewModel(){

    fun getResultData()=repo.getResultData()
}