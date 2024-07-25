plugins {
    id("java")
    id("application")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

// Java version.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// Set group and version
group = "com.app.template"
version = "1.0"

// Set Main class for gradlew run
application {
    mainClass.set("com.app.template.Main")
}

dependencies {

}

tasks {
    // Manifest and merge libs for Jar.
    jar {
        manifest {
            attributes["Main-Class"] = "com.app.template.Main"
        }
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    // Clean and delete files in release folder.
    register("clearJar", Delete::class) {
        delete(fileTree("../release").matching {
            include("${project.name}*.jar")
        })
    }
    getByName("clean") {
        dependsOn("clearJar")
    }

    // Make jar and copy to release folder.
    register("makeJar") {
        doLast {
            copy {
                from(fileTree("build/libs").files)
                into("../release")
                include("*.jar")
            }
        }
        dependsOn("jar")
    }
}