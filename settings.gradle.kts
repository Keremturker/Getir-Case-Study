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
    }
}

rootProject.name = "Getir Case Study"
include(":app")
include(":contract")
include(":core")
include(":core:di")
include(":core:domain")
include(":core:presentation")
include(":database")
include(":features")
include(":features:cart")
include(":features:cart:contract")
include(":features:cart:data")
include(":features:cart:domain")
include(":features:cart:presentation")
include(":features:product")
include(":features:product:contract")
include(":features:product:data")
include(":features:product:domain")
include(":features:product:presentation")
include(":language")
include(":navigation")
include(":network")
include(":uikit")