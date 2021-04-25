plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}
group = (gradle as ExtensionAware).extra.get("APP_BUNDLE_GROUP") as String
version = (gradle as ExtensionAware).extra.get("APP_VERSION_NAME") as String

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
}
android {
    compileSdkVersion((gradle as ExtensionAware).extra.get("ANDROID_SDK_COMPILE") as Int)
    defaultConfig {
        applicationId = (gradle as ExtensionAware).extra.get("APP_BUNDLE_GROUP") as String
        minSdkVersion((gradle as ExtensionAware).extra.get("ANDROID_SDK_MIN") as Int)
        targetSdkVersion((gradle as ExtensionAware).extra.get("ANDROID_SDK_TARGET") as Int)
        versionCode = (gradle as ExtensionAware).extra.get("APP_VERSION_CODE") as Int
        versionName = (gradle as ExtensionAware).extra.get("APP_VERSION_NAME") as String
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
}