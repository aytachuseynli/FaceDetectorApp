buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.6")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
    id("com.google.dagger.hilt.android") version "2.49" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false

}