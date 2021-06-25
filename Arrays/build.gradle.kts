import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
}

group = "pt.isel.pg"
version = "1.0.0"

repositories {
    mavenCentral()
    flatDir { dirs("../../../libs") }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("pt.isel:CanvasLib-jvm:1.0.1")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "ArraysKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        //println(file.absoluteFile)
        from(zipTree(file.absoluteFile))
    }
}