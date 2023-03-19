buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.2.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
    extra.apply{
        set("coreVersion", "1.0.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
val ktorVersion by extra { "1.3.2" }
ext {
    val kotlin_version = "1.3.2"
    val buildToolsVersion = "30.0.0"
    val androidxAnnotationVersion = "1.2.0"
    val robolectricVersion = "4.6.1"
    val guavaVersion = "30.1.1-android"
    val extTruthVersion = "1.3.0-rc01"
    val coreVersion = "1.4.1-alpha03"
    val extJUnitVersion = "1.1.4-alpha03"
    val runnerVersion = "1.4.1-alpha03"
    val espressoVersion = "3.5.0-alpha03"
    val coilVersion = "1.0.0"
}
