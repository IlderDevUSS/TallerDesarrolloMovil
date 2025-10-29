plugins {
    alias(libs.plugins.android.application)
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.pc1_sem5_tdamb_2025ii_ilderguevara"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pc1_sem5_tdamb_2025ii_ilderguevara"
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

    // CRÍTICO: Añadimos View Binding como en el proyecto funcional.
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)

    // DEPENDENCIAS DE NAVEGACIÓN CORREGIDAS:
    // Se declara la versión y se usa la sintaxis de cadena para evitar la referencia fallida a 'libs.navigation'
    val nav_version = "2.9.5"
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Dependencias de prueba
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
