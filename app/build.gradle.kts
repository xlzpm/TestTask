plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("plugin.serialization") version "1.9.23"
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.testtask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testtask"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val ktor_version: String by project
    val serialization = "1.6.0"
    val hilt = "2.51.1"
    val nav_version = "2.7.7"
    val accompanistSwipeToRefreshVersion = "0.25.1"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Ktor
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    
    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serialization")

    // Hilt - Dagger
    implementation("com.google.dagger:hilt-android:$hilt")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt("com.google.dagger:hilt-android-compiler:$hilt")
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    implementation("com.google.dagger:dagger:$hilt")
    kapt("com.google.dagger:dagger-compiler:$hilt")


    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Crypto
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha06")
    implementation(libs.androidx.security.crypto.ktx)

    // Paging
    implementation("androidx.paging:paging-runtime-ktx:3.1.0-beta01")
    implementation("androidx.paging:paging-compose:1.0.0-alpha20")

    // Java language implementation
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

    // Jetpack Compose Integration
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Swipe
    implementation("com.google.accompanist:accompanist-placeholder-material:$accompanistSwipeToRefreshVersion")
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanistSwipeToRefreshVersion")
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}