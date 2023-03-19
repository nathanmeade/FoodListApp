import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.hackerrank.android"
        minSdk = 16
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    buildFeatures {
        dataBinding = true
    }
//    testOptions {
//        unitTests {
//            includeAndroidResources(true)
//        }
//    }

    lintOptions {
        isAbortOnError = false
    }


//    tasks.withType(Test) {
//        maxParallelForks = Runtime.runtime.availableProcessors().intdiv(2) ?: 1
//        testLogging {
//            // set options for log level LIFECYCLE
//            events(TestLogEvent.FAILED,
//                    TestLogEvent.PASSED,
//                    TestLogEvent.SKIPPED)
//            exceptionFormat = TestExceptionFormat.FULL
//            showExceptions = true
//            showCauses = true
//            showStackTraces = true
//
//            // set options for log level DEBUG and INFO
//            debug {
//                events(TestLogEvent.STARTED,
//                        TestLogEvent.FAILED,
//                        TestLogEvent.PASSED,
//                        TestLogEvent.SKIPPED)
//                exceptionFormat = TestExceptionFormat.FULL
//            }
//            info.events = debug.events
//            info.exceptionFormat = debug.exceptionFormat
//
//            afterSuite { desc, result ->
//                if (!desc.parent) { // will match the outermost suite
//                    val output = "Results: ${result.resultType} (${result.testCount} tests, ${result.successfulTestCount} passed, ${result.failedTestCount} failed, ${result.skippedTestCount} skipped)"
//                    val startItem = "|  "
//                    val endItem = "  |"
//                    val repeatLength = startItem.length + output.length + endItem.length
//                    println("\n" + ("-".repeat(repeatLength)) + "\n" + startItem + output + endItem + "\n" + ("-".repeat(repeatLength)))
//                }
//            }
//        }
//    }
}
//val coreVersionFromProjectBuildGradle : String by rootProject.extra
dependencies {
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")
    implementation("com.github.bumptech.glide:glide:4.15.0")
//    implementation("io.coil-kt:coil:" + ext.properties["coilVersion"])
    val coreVersionFromProjectBuildGradle = (rootProject.properties["coreVersion"] ?: "default value").toString()

    println("nathanTestBuildGradlePrintLn" + coreVersionFromProjectBuildGradle)
    implementation("io.coil-kt:coil:" + coreVersionFromProjectBuildGradle)
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.0")
    // Unit testing dependencies
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
    androidTestImplementation("androidx.test:core:" + ext.properties["coreVersion"])
    androidTestImplementation("androidx.test:core-ktx:" + ext.properties[0])
    androidTestImplementation("androidx.test.ext:junit:" + extJUnitVersion)
    androidTestImplementation("androidx.test.ext:junit-ktx:" + extJUnitVersion)
    androidTestImplementation("androidx.test:runner:" + runnerVersion)

    testImplementation("androidx.test:core:" + coreVersion)
    testImplementation("androidx.test.ext:junit:" + extJUnitVersion)
    testImplementation("junit:junit:4.12")
    testImplementation("org.robolectric:robolectric:" + robolectricVersion)
    testImplementation("androidx.test.espresso:espresso-core:" + espressoVersion)
    testImplementation("androidx.test.ext:truth:" + extTruthVersion)
    testImplementation("androidx.test.espresso:espresso-contrib:3.4.0")
    testImplementation("com.google.truth:truth:1.1.3")
}
