plugins {
	java
	id("org.springframework.boot") version "3.5.0"	// Spring Boot plugin
	id("io.spring.dependency-management") version "1.1.7"	// Spring Dependency Management plugin
}

group = "com.zacthompson"	// Base package for the project
version = "0.0.1-SNAPSHOT"	// Version of the project SNAPSHOT indicates it's under development

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)	// Use Java 21 for the project
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())	// Extend compileOnly configuration with annotationProcessor
	}
}

repositories {
	mavenCentral()	// Use Maven Central repository for dependencies
}

dependencies {
	// Core Spring Boot dependencies
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")			// JPA (Hibernate) support for DB
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")	// OAuth2 client support
	implementation("org.springframework.boot:spring-boot-starter-security")			// Spring Security framework
	implementation("org.springframework.boot:spring-boot-starter-web")				// REST API & MVC

	// Flyway for schema migrations
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")

	// Development tools
	compileOnly("org.projectlombok:lombok")								// Compile-time only lombok library for reducing boilerplate code
	annotationProcessor("org.projectlombok:lombok")						// Enables @Getter and @Setter annotations at compile time
	developmentOnly("org.springframework.boot:spring-boot-devtools")	// Live reload and development tools

	// Database driver
	runtimeOnly("org.postgresql:postgresql")

	// Testing dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")	// JUUnit + Spring TestContext
	testImplementation("org.springframework.security:spring-security-test")	// Extra test helpers for Spring Security
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")			// JUnit Platform Runner
}

tasks.withType<Test> {
	useJUnitPlatform()	// Ensures that JUnit 5 is used for testing
}
