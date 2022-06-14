object Dependencies {
    object BuildPlugins {
        const val application = "com.android.application"
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
        const val library = "com.android.library"
        const val parcelize = "kotlin-parcelize"
        const val hilt = "dagger.hilt.android.plugin"
        const val kapt = "kotlin-kapt"
        const val jvm = "jvm"
        const val serialization = "plugin.serialization"
    }

    object Gradle {
        const val gradle = "com.android.tools.build:gradle:${Versions.Gradle.gradle}"
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.kotlin}"
    }

    object Jetpack {
        const val core = "androidx.core:core-ktx:${Versions.Jetpack.core}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.Jetpack.appcompat}"
        const val material = "com.google.android.material:material:${Versions.Jetpack.material}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Jetpack.lifecycle}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Jetpack.lifecycle}"
        const val activity = "androidx.activity:activity-ktx:${Versions.Jetpack.activity}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.Jetpack.fragment}"
    }

    object Layout {
        const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.Layout.constraint}"
    }

    object Retrofit2 {
        const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.Retrofit2.retrofit2}"
    }

    object OkHttp {
        const val bom = "com.squareup.okhttp3:okhttp-bom:${Versions.OkHttp.okHttp}"
        const val okhttp = "com.squareup.okhttp3:okhttp"
        const val logging = "com.squareup.okhttp3:logging-interceptor"
    }

    object Serialization {
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-serialization:${Versions.Kotlin.kotlin}"
        const val kotlin = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Serialization.kotlin}"
        const val converter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.Serialization.converter}"
    }

    object Hilt {
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Hilt.hilt}"
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.Hilt.hilt}"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Hilt.hilt}"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.Hilt.compiler}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val junitExt = "androidx.test.ext:junit:${Versions.Test.junitExt}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.Test.espresso}"
    }

    object InnerModules {
        const val data = ":data"
        const val domain = ":domain"
    }
}