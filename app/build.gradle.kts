plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.loginandregister"
    // FIX #1: Use a stable Android SDK version. 34 is the latest stable release.
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.loginandregister"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8 // Use 1.8 for broader compatibility
        targetCompatibility = JavaVersion.VERSION_1_8 // Use 1.8 for broader compatibility
    }
}

// FIX #2: Replaced all 'libs' aliases with specific, stable versions.
dependencies {
    // Firebase Bill of Materials (BOM) - Manages all Firebase library versions
    // Using a slightly older, super-stable BOM is safer.
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))

    // Your required Firebase libraries (BOM handles the versions)
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-analytics")

    // Use STABLE and widely-used Android library versions directly
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0") // Crucial for Material Components
    implementation("androidx.activity:activity:1.8.0") // Stable version for Activity
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Testing libraries
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}