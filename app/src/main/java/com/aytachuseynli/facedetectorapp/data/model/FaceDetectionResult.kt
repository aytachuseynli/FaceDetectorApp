package com.aytachuseynli.facedetectorapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("result")
data class FaceDetectionResult(

    @PrimaryKey(autoGenerate = true)
    val id:Long,

    @ColumnInfo(name = "left")
    var left:Boolean=false,

    @ColumnInfo(name = "right")
    var right:Boolean=false,

    @ColumnInfo(name = "smile")
    var smile:Boolean=false,

    @ColumnInfo(name = "neutral")
    var neutral: Boolean=false,

    @ColumnInfo(name = "time")
    var testFinishTime: Long? = 0


)