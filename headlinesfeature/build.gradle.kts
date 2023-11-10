import java.util.regex.Pattern.compile

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20-RC"
    id("kotlin-kapt")
}

android {
    namespace = "com.genrikhsalexandr.headlinesfeature"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":core"))

    implementation ("io.reactivex.rxjava3:rxjava:3.0.0")

    ksp("androidx.room:room-compiler:2.6.0")
    kapt("com.google.dagger:dagger-compiler:2.46.1")
    kapt ("com.arello-mobile:moxy-compiler:1.5.5")
    api ("com.arello-mobile:moxy-app-compat:1.5.5")
}