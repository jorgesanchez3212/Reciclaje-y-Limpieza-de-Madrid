import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.kotlinx.dataframe") version "0.8.1"
    kotlin("plugin.serialization") version "1.7.10"
    id("org.jetbrains.dokka") version "1.7.20"
    application
}

group = ""
version = ""

repositories {
    mavenCentral()

}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.jetbrains.kotlinx:dataframe:0.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.0")
    implementation("ch.qos.logback:logback-classic:1.4.1")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin:3.2.0")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:2.3.0")
    implementation("io.github.pdvrieze.xmlutil:core-jvm:0.84.3")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.84.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")

}

tasks.test {
    useJUnitPlatform()
}
tasks {
    val fatJar = register<Jar>("fatJar") {
        dependsOn.addAll(listOf("compileJava", "compileKotlin", "processResources")) // We need this for Gradle optimization to work
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest { attributes(mapOf("Main-Class" to application.mainClass)) } // Provided we set it up in the application plugin configuration
        val sourcesMain = sourceSets.main.get()
        val contents = configurations.runtimeClasspath.get()
            .map { if (it.isDirectory) it else zipTree(it) } +
                sourcesMain.output
        from(contents)
    }
    build {
        dependsOn(fatJar) // Trigger fat jar creation during build
    }
}
tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
application {
    mainClass.set("MainKt")
}
