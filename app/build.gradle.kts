plugins {
    id(Plugin.AGP.application)
    id(Plugin.Kotlin.android)
    id(Plugin.Kotlin.kapt)
    id(Plugin.NavArgs.navArgs)
    id(Plugin.DaggerHilt.hilt)
}

android {
    namespace = "com.example.note"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.example.note"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

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

    buildFeatures {
        viewBinding = true
    }


}

dependencies {

    implementation(Dependencies.UI.core)
    implementation(Dependencies.UI.appcompat)
    implementation(Dependencies.UI.material)
    implementation(Dependencies.UI.constraintLayout)
    implementation(Dependencies.UI.legacy)
    implementation(Dependencies.UI.lifeCycleLiveData)
    implementation(Dependencies.UI.lifeCycleViewModel)

    //espresso
    testImplementation(Dependencies.EspressoUnit.jUnite)
    androidTestImplementation(Dependencies.EspressoUnit.test)
    androidTestImplementation(Dependencies.EspressoUnit.testEspresso)

    //room
    implementation(Dependencies.Room.roomRuntime)
    implementation(Dependencies.Room.room)
    kapt(Dependencies.Room.roomCompiler)

    //DI Hilt
    implementation(Dependencies.Hilt.hilt)
    annotationProcessor(Dependencies.Hilt.hiltCompiler)

    //Coroutine
    implementation(Dependencies.Coroutine.coroutines)

    // reflection-based flavor
    implementation(Dependencies.ReflectionBasedFlavor.viewBinding)

    //navigation
    implementation(Dependencies.Navigation.navFragment)
    implementation(Dependencies.Navigation.navUI)


}