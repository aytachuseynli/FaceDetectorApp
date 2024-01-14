package com.aytachuseynli.facedetectorapp.ui.camera

import com.aytachuseynli.facedetectorapp.utils.TestInstruction

data class CurrentTestState(
    val testName:String,
    val test: TestInstruction
)