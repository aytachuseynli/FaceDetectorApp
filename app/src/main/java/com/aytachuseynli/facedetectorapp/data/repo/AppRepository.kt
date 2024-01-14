package com.aytachuseynli.facedetectorapp.data.repo

import com.aytachuseynli.facedetectorapp.data.local.TestResultDao
import com.aytachuseynli.facedetectorapp.data.model.Result
import javax.inject.Inject

class AppRepository @Inject constructor(private val db: TestResultDao){


    suspend fun insertResult(result: Result){
        db.insertResult(result)
    }
    fun getResultData()=db.getResultData()
}