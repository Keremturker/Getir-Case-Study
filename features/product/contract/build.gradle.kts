plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
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
    implementation(project(mapOf("path" to ":navigation")))

    implementation(libs.kotlin.serialization.json)
}
