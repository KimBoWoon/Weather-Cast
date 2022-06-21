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
        versionCode = Config.Application.versionCode
        versionName = Config.Application.versionName

        testInstrumentationRunner = Config.Application.testInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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