plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.todo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.todo"
        minSdk = 24
        targetSdk = 36
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Room 依赖（使用version catalog中的别名）
    implementation(libs.room.runtime)
    implementation(libs.room.common.jvm)
    annotationProcessor(libs.room.compiler)  // Java项目用这个

    // 测试支持
    testImplementation(libs.room.testing)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}