import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id "com.android.application"
    id "kotlin-android"
    id "kotlin-kapt"
}

android {
    compileSdkVersion 30

    defaultConfig {
        applicationId "com.hackerrank.android"
        minSdkVersion 16
        targetSdkVersion 30
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    buildFeatures {
        dataBinding true
    }
    testOptions {
        unitTests {
            includeAndroidResources = true
        }
    }

    lintOptions {
        abortOnError false
    }


    tasks.withType(Test) {
        maxParallelForks = Runtime.runtime.availableProcessors().intdiv(2) ?: 1
        testLogging {
            // set options for log level LIFECYCLE
            events TestLogEvent.FAILED,
                    TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED
            exceptionFormat TestExceptionFormat.FULL
            showExceptions true
            showCauses true
            showStackTraces true

            // set options for log level DEBUG and INFO
            debug {
                events TestLogEvent.STARTED,
                        TestLogEvent.FAILED,
                        TestLogEvent.PASSED,
                        TestLogEvent.SKIPPED
                exceptionFormat TestExceptionFormat.FULL
            }
            info.events = debug.events
            info.exceptionFormat = debug.exceptionFormat

            afterSuite { desc, result ->
                if (!desc.parent) { // will match the outermost suite
                    def output = "Results: ${result.resultType} (${result.testCount} tests, ${result.successfulTestCount} passed, ${result.failedTestCount} failed, ${result.skippedTestCount} skipped)"
                    def startItem = '|  ', endItem = '  |'
                    def repeatLength = startItem.length() + output.length() + endItem.length()
                    println('\n' + ('-' * repeatLength) + '\n' + startItem + output + endItem + '\n' + ('-' * repeatLength))
                }
            }
        }
    }
}

dependencies {
    implementation "androidx.appcompat:appcompat:1.3.1"
    implementation "com.google.android.material:material:1.4.0"
    implementation "androidx.constraintlayout:constraintlayout:2.0.1"
    implementation "com.github.bumptech.glide:glide:4.15.0"
    implementation "io.coil-kt:coil:1.0.0"
    annotationProcessor "com.github.bumptech.glide:compiler:4.15.0"
    // Unit testing dependencies
    def kotlin_version = "1.3.2"
    def buildToolsVersion = "30.0.0"
    def androidxAnnotationVersion = "1.2.0"
    def robolectricVersion = "4.6.1"
    def guavaVersion = "30.1.1-android"
    def extTruthVersion = "1.3.0-rc01"
    def coreVersion = "1.4.1-alpha03"
    def extJUnitVersion = "1.1.4-alpha03"
    def runnerVersion = "1.4.1-alpha03"
    def espressoVersion = "3.5.0-alpha03"
    androidTestImplementation "androidx.test:core:" + coreVersion
    androidTestImplementation "androidx.test:core-ktx:" + coreVersion
    androidTestImplementation "androidx.test.ext:junit:" + extJUnitVersion
    androidTestImplementation "androidx.test.ext:junit-ktx:" + extJUnitVersion
    androidTestImplementation "androidx.test:runner:" + runnerVersion

    testImplementation "androidx.test:core:" + coreVersion;
    testImplementation "androidx.test.ext:junit:" + extJUnitVersion
    testImplementation "junit:junit:4.12"
    testImplementation "org.robolectric:robolectric:" + robolectricVersion
    testImplementation "androidx.test.espresso:espresso-core:" + espressoVersion
    testImplementation "androidx.test.ext:truth:" + extTruthVersion
    testImplementation "androidx.test.espresso:espresso-contrib:3.4.0"
    testImplementation "com.google.truth:truth:1.1.3"
}