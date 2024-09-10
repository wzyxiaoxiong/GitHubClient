pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
//        maven("http://dl.bintray.com/alorma/maven")
    }

}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
//        maven("http://dl.bintray.com/alorma/maven")
    }
}

rootProject.name = "GitHubClient"
include(":app")
//include(":githubsdk")
