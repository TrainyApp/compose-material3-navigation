import java.util.Base64

plugins {
  id("com.eygraber.conventions-kotlin-multiplatform")
  id("com.eygraber.conventions-android-library")
  id("com.eygraber.conventions-compose-jetbrains")
  id("com.eygraber.conventions-detekt")
  `maven-publish`
}

android {
  namespace = "com.eygraber.compose.material3.navigation"
}

kotlin {
  defaultKmpTargets(
    project = project,
  )

  sourceSets {
    commonMain.dependencies {
      api(libs.compose.navigation)

      implementation(compose.material3)
      implementation(compose.runtime)

      implementation(libs.kotlinx.coroutines.core)
    }
  }
}

publishing {
  repositories {
    maven("https://europe-west3-maven.pkg.dev/mik-music/trainyapp") {
      credentials {
        username = "_json_key_base64"
        password = System.getenv("GOOGLE_KEY")?.toByteArray()?.let {
          Base64.getEncoder().encodeToString(it)
        }
      }

      authentication {
        create<BasicAuthentication>("basic")
      }
    }
  }
}
