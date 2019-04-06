import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
    }
}

plugins {
    val kotlinVersion = "1.3.21"

    kotlin("jvm") version kotlinVersion
    id("com.adarshr.test-logger") version "1.6.0"
}

testlogger {
    setTheme("mocha")
}

repositories {
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))

    testCompile("io.kotlintest:kotlintest-runner-junit5:3.3.2")

    testCompile("net.wuerl.kotlin:assertj-core-kotlin:0.2.1")
    testCompile("org.assertj:assertj-core:3.12.2")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
 }
