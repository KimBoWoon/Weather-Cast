import java.io.FileInputStream
import java.util.*
import java.text.*

plugins {
    id(Dependencies.BuildPlugins.application)
    id(Dependencies.BuildPlugins.kotlinAndroid)
    id(Dependencies.BuildPlugins.kapt)
    //hilt
    id(Dependencies.BuildPlugins.hilt)
}

android {
    compileSdk = Config.Application.compileSdk

    defaultConfig {
        applicationId = Config.Application.applicationId
        minSdk = Config.Application.minSdk
        targetSdk = Config.Application.targetSdk
        versionCode = Config.Application.jetpackVersionCode
        versionName = Config.Application.jetpackVersionName

        testInstrumentationRunner = Config.Application.testInstrumentationRunner
    }
    val format = SimpleDateFormat("HHmmss")
    val buildTime = format.format(System.currentTimeMillis())
    setProperty("archivesBaseName", "WeatherCast-v${Config.Application.jetpackVersionName}-$buildTime")

    signingConfigs {
        create("release") {
            storeFile = file(getApiKey("store_file_path"))
            storePassword = getApiKey("store_password")
            keyAlias = getApiKey("key_alias")
            keyPassword = getApiKey("key_password")
        }
    }
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
    flavorDimensions += "flavor"
    productFlavors {
//        create("debug") {
//            applicationIdSuffix = "${Config.Application.jetpackVersionName}.debug"
//            versionNameSuffix = "${Config.Application.jetpackVersionName}-debug"
//        }
//        create("full") {
//            applicationIdSuffix = "${Config.Application.jetpackVersionName}.full"
//            versionNameSuffix = "${Config.Application.jetpackVersionName}-full"
//        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = Config.Application.jvmVersion
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation("com.google.android.play:app-update-ktx:2.0.0") {
        exclude(group = "com.android.support", module = "support-v4")
    }

    arrayOf(
        Dependencies.Layout.constraint,
        Dependencies.Jetpack.core,
        Dependencies.Jetpack.appcompat,
        Dependencies.Jetpack.material,
        Dependencies.Jetpack.viewModel,
        Dependencies.Jetpack.liveData,
        Dependencies.Jetpack.activity,
        Dependencies.Jetpack.fragment,
        // hilt
        Dependencies.Hilt.hiltAndroid,
        Dependencies.Glide.glide,
        project(Dependencies.InnerModules.data),
        project(Dependencies.InnerModules.domain)
    ).forEach {
        implementation(it)
    }

    arrayOf(
        // hilt
        Dependencies.Hilt.hiltAndroidCompiler,
        Dependencies.Hilt.hiltCompiler,
        Dependencies.Glide.glideCompiler
    ).forEach {
        kapt(it)
    }

    arrayOf(
        Dependencies.Test.junit
    ).forEach {
        testImplementation(it)
    }

    arrayOf(
        Dependencies.Test.junitExt,
        Dependencies.Test.espresso,
    ).forEach {
        androidTestImplementation(it)
    }
}
fun getApiKey(propertyKey: String): String {
    val prop = Properties().apply {
        load(FileInputStream(File("./sign", "local.properties")))
    }
    return prop.getProperty(propertyKey)
}