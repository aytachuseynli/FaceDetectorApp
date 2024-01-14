package com.aytachuseynli.facedetectorapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("result")
data class Result(

    @PrimaryKey(autoGenerate = true)
    val id:Long,
    var left:Boolean=false,
    var right:Boolean=false,
    var smile:Boolean=false,
    var neutral: Boolean=false
)