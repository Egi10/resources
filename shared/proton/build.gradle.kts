import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../../iosApp/Podfile")
        name = "proton"
        framework {
            baseName = "proton"
            isStatic = true

            export(project(":shared:common:resources"))
            export(libs.moko.resources)
        }

        xcodeConfigurationToNativeBuildType["Staging"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["UAT"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["ProdDebug"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["ProdRelease"] = NativeBuildType.RELEASE
    }
    
    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            api(project(":shared:common:resources"))
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.example.resources"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

multiplatformResources {
    resourcesPackage.set("org.example.library.proton.resource") // required
//    resourcesClassName.set("FeederRes") // optional, default MR
}