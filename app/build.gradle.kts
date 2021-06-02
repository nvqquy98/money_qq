import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.androidApp)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinApt)
    id(Plugins.detekt).version(Versions.detekt)
    id(Plugins.parcelize)
}
buildscript {
//    apply(from: "autodimension.gradle")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    buildToolsVersion(Versions.buildToolsVersion)
    defaultConfig {
        applicationId = "com.nvqquy98.moneyqq"
        minSdkVersion(Versions.minSDKVersion)
        targetSdkVersion(Versions.targetSDKVersion)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        renderscriptTargetApi = Versions.targetSDKVersion
        renderscriptNdkModeEnabled = true
        consumerProguardFiles(file("proguard-rules.pro"))
    }
    signingConfigs {

    }

    buildTypes {
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }

        flavorDimensions("default")
        productFlavors {
            create("develop") {
                versionCode = 1
                versionName = "1.0.0"
                applicationIdSuffix = ".dev"
                manifestPlaceholders["applicationName"] = "BuildDebug"
            }
            create("staging") {
                versionCode = 1
                versionName = "1.0.0"
                applicationIdSuffix = ".dev"
                manifestPlaceholders["applicationName"] = "BuildStaging"
            }
            create("production") {
                versionCode = 1
                versionName = "1.0.0"
                applicationIdSuffix = ".dev"
                manifestPlaceholders["applicationName"] = "BuildProduction"
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        tasks.withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
        }

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs = freeCompilerArgs
                .plus("-Xopt-in=kotlin.RequiresOptIn")
                .plus("-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi")
        }
        buildFeatures {
            dataBinding = true
            viewBinding = true
        }
        packagingOptions {
            exclude("META-INF/DEPENDENCIES")
            exclude("META-INF/ASL2.0")
            exclude("META-INF/AL2.0")
            exclude("META-INF/LGPL2.1")
        }
    }
}
tasks {
    withType<io.gitlab.arturbosch.detekt.Detekt> {
        // Target version of the generated JVM bytecode. It is used for type resolution.
        this.jvmTarget = "1.8"
    }
}

detekt {
    config = files("$rootDir/config/detekt/detekt.yml")
    input = files("src/main/java")
    reports {
        html.enabled = true // observe findings in your browser with structure and code snippets
        xml.enabled = false // checkstyle like format mainly for integrations like Jenkins
        txt.enabled =
            false // similar to the console output, contains issue signature to manually edit baseline files
    }
}
dependencies {
    implementation(Deps.activity)
    implementation(Deps.activityKtx)

    implementation(Deps.appcompat)

    implementation(Deps.constraintLayout)

    implementation(Deps.coroutines)

    implementation(Deps.glideRuntime)
    implementation(Deps.glideAnnotations)
    kapt(Deps.glideCompiler)

    implementation(Deps.gSon)

    implementation(Deps.koinAndroidx)
    implementation(Deps.koinExt)
    implementation(Deps.koinWorkManager)

    implementation(Deps.kotlin)
    implementation(Deps.coreKtx)

    implementation(Deps.lifecycleRuntime)
    implementation(Deps.lifecycleExtensions)
    implementation(Deps.lifecycleViewModelKtx)
    implementation(Deps.lifecycleLiveDataKtx)
    implementation(Deps.lifecycleAnnotation)

    implementation(Deps.material)

    implementation(Deps.navigationFragment)
    implementation(Deps.navigationUi)

    implementation(Deps.okHttp3)
    implementation(Deps.interceptor)

    implementation(Deps.retrofit)
    implementation(Deps.retrofitConvertGSon)
    implementation(Deps.retrofitScalars)
    implementation(Deps.retrofitMock)

    testImplementation(Deps.junitTest)
    androidTestImplementation(Deps.junitTestExt)
    androidTestImplementation(Deps.espresso)

    implementation(Deps.timber)
}
