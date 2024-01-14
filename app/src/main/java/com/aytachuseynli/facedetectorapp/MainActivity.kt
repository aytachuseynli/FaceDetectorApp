package com.aytachuseynli.facedetectorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aytachuseynli.facedetectorapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutorService

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var  cameraExecutor: ExecutorService
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}