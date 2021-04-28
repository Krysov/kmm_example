
val appGroup = "kmm.example.app"
val appName = "KMMExample"
val appVersionName = "0.0.5"
val appVersionCode = 5

val androidCompileSdkVersion = 29
val androidTargetSdkVersion = 29
val androidMinSdkVersion = 24

(gradle as ExtensionAware).extra["APP_BUNDLE_GROUP"] = appGroup
(gradle as ExtensionAware).extra["APP_BUNDLE_NAME"] = appName
(gradle as ExtensionAware).extra["APP_VERSION_NAME"] = appVersionName
(gradle as ExtensionAware).extra["APP_VERSION_CODE"] = appVersionCode
(gradle as ExtensionAware).extra["ANDROID_SDK_COMPILE"] = androidCompileSdkVersion
(gradle as ExtensionAware).extra["ANDROID_SDK_TARGET"] = androidTargetSdkVersion
(gradle as ExtensionAware).extra["ANDROID_SDK_MIN"] = androidMinSdkVersion

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
                useModule("com.android.tools.build:gradle:4.0.1")
            }
        }
    }
}
rootProject.name = appName


include(":androidApp")
include(":shared")

