buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
        classpath("com.android.tools.build:gradle:4.0.2")
    }
}
group = (gradle as ExtensionAware).extra.get("APP_BUNDLE_GROUP") as String
version = (gradle as ExtensionAware).extra.get("APP_VERSION_NAME") as String

repositories {
    mavenCentral()
}
