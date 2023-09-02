@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.github.pksokolowski.core"
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
    kapt {
        correctErrorTypes = true
    }
}

tasks.withType(type = org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask::class) {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

dependencies {
    implementation(libs.coroutines)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.espresso)
    testImplementation(libs.coroutines.test)
}