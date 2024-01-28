val javaVersion = JavaVersion.VERSION_17

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.detekt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.kotlin.serialization)
}

val localProperties = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir)
val weatherApiKey = localProperties.getProperty("WEATHER_API_KEY")

android {
    namespace = "com.ivanovictin.weatherapp"
    compileSdk = libs.versions.configuration.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.ivanovictin.weatherapp"
        minSdk = libs.versions.configuration.minSdk.get().toInt()
        targetSdk = libs.versions.configuration.targetSdk.get().toInt()
        versionCode = libs.versions.configuration.versionCode.get().toInt()
        versionName = libs.versions.configuration.versionName.get().toString()

        buildConfigField("String", "WEATHER_API_KEY", weatherApiKey)
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    detekt {
        config.setFrom("../config/detekt/detekt.yml")
        source.setFrom("../")
        parallel = true
        allRules = true
    }

    dependencies {
        implementation(libs.core.ktx)
        implementation(libs.lifecycle.runtime.compose)
        implementation(platform(libs.compose.bom))
        implementation(libs.ui.text.google.fonts)
        implementation(libs.bundles.compose)
        implementation(libs.bundles.hilt)
        implementation(libs.bundles.networking)
        implementation(libs.timber)
        implementation(libs.glide.compose)
        implementation(libs.kotlin.serialization.json)

        debugImplementation(libs.compose.ui.tooling)

        kapt(libs.hilt.compiler)
        detektPlugins(libs.detekt.formatting)
        detektPlugins(libs.detekt.compose.rules)

        testImplementation (libs.bundles.test)
    }
}
