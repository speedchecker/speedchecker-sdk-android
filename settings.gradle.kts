import java.net.URI

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url = URI("https://jitpack.io") }
        maven {
            url = URI("https://maven.speedcheckerapi.com/artifactory/libs-release")
            credentials {
                username = "demo"
                password = "AP85qiz6wYEsCttWU2ZckEWSwJKuA6mSYcizEY"
            }
        }
    }
}

rootProject.name = "SpeedCheckerDemo"
include(":app")
 