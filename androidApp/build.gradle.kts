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
    androidTestImplementation("androidx.test:core-ktx:1.3.0")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.2")
    androidTestImplementation("androidx.test:runner:1.3.0")
}
android {
    compileSdkVersion((gradle as ExtensionAware).extra.get("ANDROID_SDK_COMPILE") as Int)
    defaultConfig {
        applicationId = (gradle as ExtensionAware).extra.get("APP_BUNDLE_GROUP") as String
        minSdkVersion((gradle as ExtensionAware).extra.get("ANDROID_SDK_MIN") as Int)
        targetSdkVersion((gradle as ExtensionAware).extra.get("ANDROID_SDK_TARGET") as Int)
        versionCode = (gradle as ExtensionAware).extra.get("APP_VERSION_CODE") as Int
        versionName = (gradle as ExtensionAware).extra.get("APP_VERSION_NAME") as String
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}