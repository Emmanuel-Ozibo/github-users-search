import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.compose.compiler)
}

val secretsPropertiesFile = rootProject.file("secrets.properties")
val properties = Properties()
FileInputStream(secretsPropertiesFile).use { properties.load(it) }

android {
    namespace = "com.example.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "GITHUB_API_BASE_URL", "\"https://api.github.com/\"")
        buildConfigField("String", "GITHUB_TOKEN", "\"${properties.getProperty("GITHUB_TOKEN")}\"")
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

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.gson.converter.factory)


    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.kotlinx.serialization)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

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
}
