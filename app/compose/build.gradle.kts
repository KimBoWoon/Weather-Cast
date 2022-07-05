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
        versionCode = Config.Application.composeVersionCode
        versionName = Config.Application.composeVersionName

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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose.compose
    }
}

dependencies {
    implementation("com.github.skydoves:landscapist-glide:1.5.2")
    implementation("androidx.paging:paging-compose:1.0.0-alpha15")

    arrayOf(
        Dependencies.Layout.constraint,
        // hilt
        Dependencies.Hilt.hiltAndroid,
        Dependencies.Glide.glide,
        Dependencies.Compose.ui,
        Dependencies.Compose.material,
        Dependencies.Compose.uiToolPreview,
        Dependencies.Compose.activity,
        Dependencies.Compose.viewModel,
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
        Dependencies.Compose.uiTest
    ).forEach {
        androidTestImplementation(it)
    }

    arrayOf(
        Dependencies.Compose.uiTooling
    ).forEach {
        debugImplementation(it)
    }
}