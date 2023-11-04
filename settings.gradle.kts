pluginManagement {
    repositories {
        google()
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

rootProject.name = "News App"
include(":app")
include(":core")
include(":favoritesfeature")
include(":detailarticlefeature")
include(":souresfeature")
include(":headlinesfeature")
include(":searchfeature")
include(":filterfeature")
