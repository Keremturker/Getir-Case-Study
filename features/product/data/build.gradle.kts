plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.kturker.feature.product.data"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion

        testInstrumentationRunner = AppConfig.testRunner
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
        sourceCompatibility = AppConfig.CompileOptions.javaSourceCompatibility
        targetCompatibility = AppConfig.CompileOptions.javaSourceCompatibility
    }

    kotlin {
        compilerOptions {
            jvmTarget = AppConfig.CompileOptions.kotlinJvmTarget
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":features:product:domain")))
    implementation(project(mapOf("path" to ":contract")))
    implementation(project(mapOf("path" to ":network")))

    //Dagger - Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.kotlin.serialization.json)

    testImplementation(libs.junit)
}