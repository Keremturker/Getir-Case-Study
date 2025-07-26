plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = AppConfig.applicationId
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testRunner

        resValue("string", "app_name", AppConfig.name)
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

    flavorDimensions.add(ProductFlavors.flavorDimensionsApi)

    productFlavors {

        create(ProductFlavors.productFlavorDevelopment) {
            dimension = ProductFlavors.flavorDimensionsApi
            applicationIdSuffix = ProductFlavors.productFlavorDevelopmentSuffix
            versionCode = AppConfig.versionCode
            versionName = AppConfig.versionName

            resValue("string", "app_name", "Dev - ${AppConfig.name}")
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://65c38b5339055e7482c12050.mockapi.io\""
            )
        }

        create(ProductFlavors.productFlavorProduction) {
            dimension = ProductFlavors.flavorDimensionsApi
            applicationIdSuffix = ProductFlavors.productFlavorProductionSuffix
            versionCode = AppConfig.versionCode
            versionName = AppConfig.versionName

            resValue("string", "app_name", AppConfig.name)
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://65c38b5339055e7482c12050.mockapi.io\""
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    includeFeatureModules(":features:product", listOf("contract", "data", "domain", "presentation"))
    includeFeatureModules(":features:cart", listOf("data"))
    includeFeatureModules(":core", listOf("di","domain","presentation"))

    implementation(project(mapOf("path" to ":contract")))
    implementation(project(mapOf("path" to ":database")))
    implementation(project(mapOf("path" to ":language")))
    implementation(project(mapOf("path" to ":navigation")))
    implementation(project(mapOf("path" to ":network")))
    implementation(project(mapOf("path" to ":uikit")))

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
    implementation(libs.androidx.navigation.compose)

    //Dagger - Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)

}

fun DependencyHandler.includeFeatureModules(basePath: String, modules: List<String>) {
    modules.forEach { module ->
        implementation(project(mapOf("path" to "$basePath:$module")))
    }
}