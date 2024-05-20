plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation("org.seleniumhq.selenium:selenium-chrome-driver:3.141.59")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.0")
}

kotlin {
    // Usunięcie zbędnego ustawienia wersji JVM
    // jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}

