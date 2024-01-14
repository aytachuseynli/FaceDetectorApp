package com.aytachuseynli.facedetectorapp.ui.camera

import android.os.CountDownTimer
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.aytachuseynli.facedetectorapp.utils.BaseFragment
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.aytachuseynli.facedetectorapp.databinding.FragmentCameraBinding
import com.aytachuseynli.facedetectorapp.utils.TestInstruction
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CameraFragment : BaseFragment<FragmentCameraBinding>(FragmentCameraBinding::inflate) {
    private val viewModel by viewModels<CameraViewModel>()
    private lateinit var cameraExecutor: ExecutorService

    private var countDownTimer: CountDownTimer? = null

    override fun observeEvents() {

        lifecycleScope.launch {
            viewModel.currentTest.collectLatest {
                binding.instructionTxt.text = it.testName
                countDownTimer?.cancel()
                setTimer(it.test)
            }
        }

    }

    private fun setTimer(currentTest: TestInstruction) {

        val totalMillis = 11000L
        val intervalMillis = 1000L
        countDownTimer = object : CountDownTimer(totalMillis, intervalMillis) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                binding.timer.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                when (currentTest) {
                    TestInstruction.LEFT -> {
                        viewModel.updateCurrentTest(
                            CurrentTestState(
                                "Head to Right",
                                TestInstruction.RIGHT
                            )
                        )
                    }

                    TestInstruction.RIGHT -> {
                        viewModel.updateCurrentTest(CurrentTestState("Smile", TestInstruction.SMILE))
                    }

                    TestInstruction.SMILE -> {
                        viewModel.updateCurrentTest(
                            CurrentTestState(
                                "Neutral",
                                TestInstruction.NEUTRAL
                            )
                        )
                    }

                    TestInstruction.NEUTRAL -> {
                        viewModel.insertResult()
                    }
                }
            }
        }
        (countDownTimer as CountDownTimer).start()
    }

    override fun onCreateFinish() {

        startCamera(binding.cameraPreview)
    }

    private fun startCamera(viewFinder: PreviewView) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.surfaceProvider)
                }

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            val imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_YUV_420_888)
                .build()
                .also { it ->
                    it.setAnalyzer(cameraExecutor) { imageProxy ->
                        lifecycleScope.launch {
                            imageProxy.proxyProcess().collectLatest {face->
                                Log.e("TAG", "startCamera: $face", )
                                viewModel.setTestResult(face)
                            }
                        }

//                        proxyProgress(imageProxy)
                    }
                }

            cameraProvider.bindToLifecycle(
                viewLifecycleOwner,
                cameraSelector,
                preview,
                imageAnalysis
            )

        }, ContextCompat.getMainExecutor(requireContext()))

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    @OptIn(ExperimentalGetImage::class)
    private fun proxyProgress(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(
                mediaImage,
                imageProxy.imageInfo.rotationDegrees
            )

            val options = FaceDetectorOptions.Builder()
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                .build()

            val faceDetector = FaceDetection.getClient(options)

            faceDetector.process(image)
                .addOnSuccessListener { faces ->
                    if (faces.size > 0) {
                        viewModel.setTestResult(faces[0])
                    }
                    Log.e("face", "Face ${faces.size} found.")
                }
                .addOnFailureListener { e ->
                    Log.e("FaceDetection", "Face detection failed", e)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }

    @OptIn(ExperimentalGetImage::class)
    fun ImageProxy.proxyProcess() = callbackFlow<Face> {
        val mediaImage = this@proxyProcess.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(
                mediaImage,
                this@proxyProcess.imageInfo.rotationDegrees
            )

            val options = FaceDetectorOptions.Builder()
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                .build()

            val faceDetector = FaceDetection.getClient(options)

            faceDetector.process(image)
                .addOnSuccessListener { faces ->
                    if (faces.size > 0) {
                        trySend(faces[0])
                    }
                    Log.e("face", "Face ${faces.size} found.")
                }
                .addOnFailureListener { e ->
                    Log.e("FaceDetection", "Face detection failed", e)
                }
                .addOnCompleteListener {
                    this@proxyProcess.close()
                }
            awaitClose {
                this@proxyProcess.close()
            }
        }


    }
}
