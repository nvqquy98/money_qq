object Versions {
    /* android */
    const val compileSdkVersion = 31
    const val gradlePlugin = "7.0.4"
    const val kotlin = "1.6.10"
    const val minSDKVersion = 24
    const val targetSDKVersion = 31

    /* activity */
    const val activity = "1.4.0"

    /* appcompat */
    const val appcompat = "1.4.1"

    /* Code smell */
    const val detekt = "1.9.1"

    /* ConstrainLayout */
    const val constraintLayout = "2.1.3"

    /* Coroutines */
    const val coroutines = "1.4.2"

    /* Glide */
    const val glide = "4.12.0"

    /* GSon */
    const val gSon = "2.8.6"

    /* Koin */
    const val koin = "3.0.1"

    /* ktx */
    const val core = "1.7.0"

    /* Lifecycle */
    const val lifecycle = "2.4.1"

    /* Material */
    const val material = "1.3.0"

    /* Navigation Component */
    const val navigationComponent = "2.4.1"

    /* OkHttp */
    const val okHttp3 = "4.9.0"

    /* Retrofit */
    const val retrofit = "2.9.0"

    /* Test */
    const val espresso = "3.3.0"
    const val junit = "4.13.2"
    const val junitExt = "1.1.2"
    const val mockitoKotlin = "2.1.0"

    /* Timber */
    const val timber = "4.7.1"
}


object ClassPaths {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationComponent}"
}

@Suppress("unused")
object Plugins {
    const val androidApp = "com.android.application"
    const val androidLib = "com.android.library"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val javaLib = "java-library"
    const val kotlinAndroid = "android"
    const val kotlinApt = "kapt"
    const val parcelize = "kotlin-parcelize"
    const val navigationKotlin = "androidx.navigation.safeargs.kotlin"
}

object Mavens {}

object Deps {
    /* activity */
    const val activity = "androidx.activity:activity:${Versions.activity}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"

    /* app compat */
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    /* ConstraintLayout */
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    /* Coroutines */
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    /* Glide */
    const val glideRuntime = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotations = "com.github.bumptech.glide:annotations:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    /* GSon */
    const val gSon = "com.google.code.gson:gson:${Versions.gSon}"

    /* Koin */
    const val koinAndroidx = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinExt = "io.insert-koin:koin-android-ext:${Versions.koin}"
    const val koinWorkManager = "io.insert-koin:koin-androidx-workmanager:${Versions.koin}"

    /* Kotlin */
    const val coreKtx = "androidx.core:core-ktx:${Versions.core}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    /* Lifecycle */
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    const val lifecycleAnnotation =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val lifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    /* Material */
    const val material = "com.google.android.material:material:${Versions.material}"

    /* Navigation */
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponent}"
    const val navigationUi =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponent}"

    /* OkHttp */
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp3}"
    const val okHttp3 = "com.squareup.okhttp3:okhttp:${Versions.okHttp3}"

    /* Retrofit */
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConvertGSon = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    const val retrofitScalars = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}"

    /* Test */
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val junitTest = "junit:junit:${Versions.junit}"
    const val junitTestExt = "androidx.test.ext:junit:${Versions.junitExt}"

    /* Timber */
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}