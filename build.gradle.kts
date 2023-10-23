import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("org.springframework.boot") version "2.5.13"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("plugin.jpa") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("com.amazonaws:aws-java-sdk-s3:1.12.281")
    compileOnly("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    implementation("mysql:mysql-connector-java:8.0.23")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.21")

    implementation(enforcedPlatform("org.springframework.cloud:spring-cloud-dependencies:2020.0.5"))
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation ("org.springframework.cloud:spring-cloud-starter-loadbalancer")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
