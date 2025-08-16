plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = AppConfig.CompileOptions.javaSourceCompatibility
    targetCompatibility = AppConfig.CompileOptions.javaSourceCompatibility
}

kotlin {
    compilerOptions {
        jvmTarget = AppConfig.CompileOptions.kotlinJvmTarget
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    detektPlugins(libs.bundles.detekt)
}