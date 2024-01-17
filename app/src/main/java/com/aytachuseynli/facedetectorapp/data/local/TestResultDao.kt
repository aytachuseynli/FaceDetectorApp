package com.aytachuseynli.facedetectorapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aytachuseynli.facedetectorapp.data.model.FaceDetectionResult

@Dao
interface TestResultDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun insertResult(result:FaceDetectionResult)

    @Query("SELECT*FROM result")
    fun getResultData(): LiveData<List<FaceDetectionResult>>
}
