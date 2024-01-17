package com.aytachuseynli.facedetectorapp.ui.camera

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aytachuseynli.facedetectorapp.data.model.FaceDetectionResult
import com.aytachuseynli.facedetectorapp.data.repo.AppRepository
import com.aytachuseynli.facedetectorapp.utils.TestInstruction
import com.google.mlkit.vision.face.Face
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(val repo: AppRepository) : ViewModel() {

    private val _currentTest =
        MutableStateFlow(CurrentTestState("Head to Left", TestInstruction.LEFT))
    val currentTest = _currentTest.asStateFlow()

    fun updateCurrentTest(currentTestState: CurrentTestState) {
        _currentTest.value = currentTestState
    }


    var result = FaceDetectionResult(0)


    fun setTestResult(face: Face) {

        val smilingProbability = face.smilingProbability

        val head = face.headEulerAngleY

        when (currentTest.value.test) {
            TestInstruction.LEFT -> {
                if (head.toInt() > 20) {
                    Log.e("FaceDetection", "Head turned left")
                    result.left = true
                    _currentTest.value = CurrentTestState("Head to Right", TestInstruction.RIGHT)
                }
            }

            TestInstruction.RIGHT -> {
                if (head.toInt() < -20) {
                    Log.e("FaceDetection", "Head turned right")
                    result.right = true
                    _currentTest.value = CurrentTestState("Smile", TestInstruction.SMILE)
                }
            }

            TestInstruction.SMILE -> {
                if (smilingProbability!! > 0.7) {
                    result.smile = true
                    Log.e("FaceDetection", "Smiling")
                    _currentTest.value = CurrentTestState("Neutral", TestInstruction.NEUTRAL)
                }
            }

            TestInstruction.NEUTRAL -> {
                if (smilingProbability!! < 0.7) {
                    Log.e("FaceDetection", "Neutral")
                    result.neutral = true
                    result.testFinishTime=System.currentTimeMillis()
                    insertResult()
                    _currentTest.value = CurrentTestState("Head to Left", TestInstruction.LEFT)
                }

            }

        }

    }


    fun insertResult() {
        viewModelScope.launch {
            repo.insertResult(result)
        }
    }


//    fun insertResult() {
//        val currentTimeMillis = System.currentTimeMillis()
//
//        val resultWithTime = FaceDetectionResult(
//            left = result.left,
//            right = result.right,
//            smile = result.smile,
//            neutral = result.neutral,
//            testFinishTime = currentTimeMillis
//        )
//
//        viewModelScope.launch {
//            repo.insertResult(resultWithTime)
//        }
//    }
}