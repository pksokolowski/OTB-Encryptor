// Top-level build file where you can add configuration options common to all sub-projects/modules.
@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.hilt) apply false
    id("com.android.application") version "8.1.0" apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
}