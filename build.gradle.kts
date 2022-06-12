import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    // Plugin um die no-arg-konstruktoren generieren zu lassen
    id("org.jetbrains.kotlin.plugin.jpa") version "1.6.21"

    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("kapt") version "1.3.70"
}

group = "de.tiupe"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Spring-Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring-JPA
    // H2-Datenbank als User-DB
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2")

    // Kotlinx-Date-Time
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")

    // Spring-Security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // Spring-Dev-Tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Unterstützung bei der Eingabe von Config-Properties
    // Wenn man die Klasse installiert hat, kann die IDE die Properties vorschlagen
    //developmentOnly("org.springframework.boot:spring-boot-configuration-processor")
    // nur mit dem Annotation-Processor bekommt man die Unterstützung in intellij
    // Es gibt einen Pull-Request für den KSP (Kotlin-Symbol-Processor, der war am
    // 6.6.2022 aber noch nicht gelöst.)
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    // stellt den Actuator und der URL /actuator zur Verfügung
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
    // No Argument-Konstruktoren werden automatisch erzeugt.
    noArg {
    annotation("com.my.Annotation")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}