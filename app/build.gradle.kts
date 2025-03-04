plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.setdrive"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.setdrive"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.3"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

android {
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
}

dependencies {

    implementation(libs.androidx.compose.material)
    dependencies {
        val room_version = "2.6.1"

        ksp(libs.androidx.room.compiler)

        implementation(libs.androidx.room.runtime)

        // If this project only uses Java source, use the Java annotationProcessor
        // No additional plugins are necessary
        annotationProcessor(libs.androidx.room.compiler)

        // optional - Kotlin Extensions and Coroutines support for Room
        implementation(libs.androidx.room.ktx)

        // optional - RxJava2 support for Room
        implementation(libs.androidx.room.rxjava2)

        // optional - RxJava3 support for Room
        implementation(libs.androidx.room.rxjava3)

        // optional - Guava support for Room, including Optional and ListenableFuture
        implementation(libs.androidx.room.guava)

        // optional - Test helpers
        testImplementation(libs.androidx.room.testing)

        // optional - Paging 3 Integration
        implementation(libs.androidx.room.paging)

        implementation(libs.androidx.material3.v120)
        implementation(libs.androidx.runtime)
        implementation(libs.androidx.foundation)
        implementation(libs.firebase.auth)
        implementation(platform(libs.firebase.bom))
        implementation(libs.firebase.analytics)
        implementation(libs.accompanist.pager)
        implementation(libs.accompanist.swiperefresh)
        implementation(libs.androidx.material.icons.extended)
        implementation(libs.material3)
        implementation(libs.androidx.material3)
        implementation(libs.ui)
        implementation(libs.androidx.ui)
        implementation(libs.androidx.core.ktx.v1110)
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.navigation.compose)
        implementation(libs.androidx.navigation.runtime.ktx)
        implementation(libs.volley)
        implementation(libs.androidx.runner)
        implementation(libs.androidx.room.common.jvm)
        implementation(libs.androidx.room.common)
        implementation(libs.androidx.room.ktx)
        implementation(libs.support.annotations)
        implementation(libs.androidx.lifecycle.livedata.ktx)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
    }
}