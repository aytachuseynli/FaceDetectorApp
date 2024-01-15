package com.aytachuseynli.facedetectorapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aytachuseynli.facedetectorapp.data.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface TestResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertResult(result:Result)

    @Query("SELECT*FROM result")
    fun getResultData(): LiveData<List<Result>>
}
