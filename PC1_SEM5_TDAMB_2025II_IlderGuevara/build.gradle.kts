// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    // CORRECCIÓN: Plugin de Java y versión 2.9.5 (para que coincida)
    id("androidx.navigation.safeargs") version "2.9.5" apply false
}