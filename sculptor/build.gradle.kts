plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.sculptor"
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
                "proguard-rules.pro",
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Tests
    implementation(platform(libs.junit.bom))
    testImplementation(libs.junit)
    testImplementation(libs.junit5)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.mockk)
    testImplementation(libs.web.mock.server)
    testImplementation(libs.retrofit)
    testImplementation(libs.okhttp.logging.interceptor)
    testImplementation(libs.okhttp)
    testImplementation(libs.gson)
    testImplementation(libs.gson.converter.factory)
    androidTestImplementation(libs.compose.ui.junit)
    debugImplementation(libs.compose.test.manifest)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso.core)
    testRuntimeOnly(libs.junit.platform.launcher)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.kotlinx.serialization)

    implementation(libs.coil)
    implementation(libs.coil.compose)
}
