import java.util.Properties

// 1. REGLA DE GRADLE: Los plugins van primero.
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Agregamos los plugins de calidad y cobertura
    id("org.sonarqube") version "7.2.2.6593"
    id("org.jetbrains.kotlinx.kover") version "0.9.7"
}

// 2. Ahora sí, leemos tu local.properties de forma segura
val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}

android {
    namespace = "com.example.loginsimple"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.loginsimple"
        minSdk = 31
        targetSdk = 35
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

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

// 3. Tu configuración de Sonar corregida
sonar {
    properties {
        val token = localProperties.getProperty("SONAR_TOKEN") ?: System.getenv("SONAR_TOKEN") ?: ""
        property("sonar.token", token)
        property("sonar.organization", "carlosarqu2026")
        property("sonar.projectKey", "loginsimple_app") // Un nombre limpio y definitivo
        property("sonar.projectName", "Login Simple")
        property("sonar.host.url", "https://sonarcloud.io")

        // ¡CLAVE! Agregamos la carpeta de Kotlin
        property("sonar.sources", "src/main/java, src/main/kotlin")

        // Forzamos el nombre de la rama para evitar el error de "Empty branch"
       //* property("sonar.branch.name", "main") // Si tu repositorio usa "master", cámbialo a "master"

        property("sonar.coverage.jacoco.xmlReportPaths", "${project.layout.buildDirectory.get()}/reports/kover/report.xml")
        property("sonar.exclusions", "**/*Generated.java, **/R.class, **/BuildConfig.java")
    }
}