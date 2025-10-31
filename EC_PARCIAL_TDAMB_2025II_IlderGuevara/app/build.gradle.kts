plugins {
    alias(libs.plugins.android.application)
    // AÑADE ESTO (Requerido por el examen para SafeArgs)
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.ec_parcial_tdamb_2025ii_ilderguevara"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ec_parcial_tdamb_2025ii_ilderguevara"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // AÑADE ESTO (Para activar ViewBinding)
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    implementation("com.google.android.material:material:1.12.0")
}