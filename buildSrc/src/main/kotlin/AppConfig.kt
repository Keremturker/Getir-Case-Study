import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object AppConfig {
    const val applicationId = "com.kturker.getircasestudy"
    const val name = "Getir Case Study"

    const val compileSdkVersion = 36
    const val minSdkVersion = 24

    const val targetSdkVersion = 36

    const val versionCode = 1

    private const val versionMajor = 0
    private const val versionMinor = 0
    private const val versionPatch = 1

    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"

    val versionName
        get() = "$versionMajor.$versionMinor.$versionPatch"

    object CompileOptions {
        val javaSourceCompatibility = JavaVersion.VERSION_11
        val kotlinJvmTarget: JvmTarget = JvmTarget.JVM_11
    }
}
