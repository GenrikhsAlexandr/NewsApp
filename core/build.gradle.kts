plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}

android {
    namespace = "com.genrikhsaleksandr.core"
    compileSdk = 33

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
}

dependencies {

    api("androidx.core:core-ktx:1.12.0")
    api("androidx.appcompat:appcompat:1.6.1")

    api ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    api ("com.android.databinding:viewbinding:8.1.2")

    api ("androidx.recyclerview:recyclerview:1.3.2")

    api("androidx.navigation:navigation-fragment-ktx:2.7.4")
    api("androidx.navigation:navigation-ui-ktx:2.7.4")

    api("com.google.android.material:material:1.10.0")

    api("junit:junit:4.13.2")
    api("androidx.test.ext:junit:1.1.5")
    api("androidx.test.espresso:espresso-core:3.5.1")

    api("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")

    api("androidx.constraintlayout:constraintlayout:2.1.4")

    kapt("com.google.dagger:dagger-compiler:2.46.1")
    api ("com.google.dagger:dagger:2.46.1")

    api("androidx.room:room-ktx:2.6.0")
    api("androidx.room:room-rxjava3:2.6.0")

    api("com.squareup.okhttp3:okhttp:5.0.0-alpha.10")
    api("com.squareup.okhttp3:logging-interceptor:4.7.2")
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    api("com.squareup.picasso:picasso:2.71828")
}