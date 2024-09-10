plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.alorma.github.sdk"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.alorma.github.sdk"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("com.google.code.gson:gson:2.8.8")
    implementation("com.squareup.retrofit:retrofit:1.9.0")
    implementation("com.squareup.okhttp:okhttp:2.5.0")

    implementation("io.reactivex:rxjava:1.0.16")
    implementation("com.squareup.okhttp:okhttp-urlconnection:2.5.0")

    api("io.reactivex:rxjava:1.0.16")
    api("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation("net.danlew:android.joda:2.8.2")
    implementation("nl.littlerobots.rxlint:rxlint:1.0")




    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}