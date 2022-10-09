import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	id("io.gitlab.arturbosch.detekt").version("1.21.0")
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "br.com"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

val postgreeSQLVersion = "42.5.0"
val flywayVersion = "9.2.2"
val springSecurityVersion = "5.7.3"

repositories {
	mavenCentral()
}

dependencies {
	// Spring
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Jackson and Kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Database
	implementation("org.postgresql:postgresql:$postgreeSQLVersion")
	implementation("org.flywaydb:flyway-core:$flywayVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Detekt
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.21.0")

	// Security
	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.security:spring-security-test")

	// JWT
	implementation("com.auth0:java-jwt:4.0.0")

	// Logging
	implementation("io.github.microutils:kotlin-logging:3.0.0")

	// Validators
	implementation("br.com.colman.simplecpfvalidator:simple-cpf-validator:2.2.0")

	// Feign
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.4")
}

detekt {
	toolVersion = "1.21.0"
	config = files("config/detekt/detekt.yml")
	buildUponDefaultConfig = true
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {

	autoCorrect = true

	baseline.set(file("$projectDir/config/detekt/baseline.xml"))

	reports {
		xml.required.set(false)
		html.required.set(false)
		txt.required.set(false)
		sarif.required.set(false)
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
