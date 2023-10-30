plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")


}

android {
    namespace = "com.genrikhsalexandr.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.genrikhsalexandr.newsapp"
        minSdk = 24
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":favoritesfeature"))
    implementation(project(":core"))

    kapt("com.google.dagger:dagger-compiler:2.46.1")

    ksp("androidx.room:room-compiler:2.6.0")

    implementation("com.airbnb.android:lottie:4.2.0")

}