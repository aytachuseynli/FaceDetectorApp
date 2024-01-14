plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.aytachuseynli.facedetectorapp"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.aytachuseynli.facedetectorapp"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")



    implementation("androidx.cardview:cardview:1.0.0")


    //Face Detection
    implementation ("com.google.android.gms:play-services-mlkit-face-detection:17.1.0")


    //MVVM
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    //Okhttp
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    //Coil
    implementation("io.coil-kt:coil:2.2.2")
    //Room
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.49")


    //Security
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha06")


    //CameraX
    implementation("io.github.shashank02051997:FancyToast:2.0.2")
    implementation("androidx.camera:camera-core:1.3.1")
    implementation("androidx.camera:camera-camera2:1.3.1")
    implementation("androidx.camera:camera-lifecycle:1.3.1")
    implementation("androidx.camera:camera-view:1.4.0-alpha03")
    implementation("androidx.camera:camera-video:1.3.1")
    implementation("androidx.camera:camera-mlkit-vision:1.3.1")
    implementation("androidx.camera:camera-extensions:1.3.1")


    // CameraSource
    implementation("com.google.android.gms:play-services-vision-common:19.1.3")

    //Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")



    // Tensorflow Lite dependencies
    implementation ("org.tensorflow:tensorflow-lite-task-vision-play-services:0.4.2")
    implementation ("com.google.android.gms:play-services-tflite-gpu:16.2.0")
    implementation ("org.tensorflow:tensorflow-lite-task-vision:0.4.0")
    implementation ("org.tensorflow:tensorflow-lite-gpu-delegate-plugin:0.4.0")
    implementation ("org.tensorflow:tensorflow-lite-gpu:2.9.0")

    implementation("com.google.android.material:material:1.11.0")
}