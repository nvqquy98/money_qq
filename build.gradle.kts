// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(ClassPaths.androidGradlePlugin)
        classpath(ClassPaths.kotlinGradlePlugin)
        classpath(ClassPaths.navigationSafeArgs)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
tasks.register<Delete>("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
