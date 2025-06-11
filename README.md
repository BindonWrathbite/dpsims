# dpsims

This is a repo of my inventory management system I am working on to implement within my school district.
It is going to be used for our music department to manage instruments, uniforms, students, etc.

Dev Dependencies:
	Core Spring Boot dependencies
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")			  // JPA (Hibernate) support for DB
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")	// OAuth2 client support
	implementation("org.springframework.boot:spring-boot-starter-security")			  // Spring Security framework
	implementation("org.springframework.boot:spring-boot-starter-web")				    // REST API & MVC

Flyway for schema migrations
  implementation("org.flywaydb:flyway-core")
  implementation("org.flywaydb:flyway-database-postgresql")

Development tools
  compileOnly("org.projectlombok:lombok")								              // Compile-time only lombok library for reducing boilerplate code
  annotationProcessor("org.projectlombok:lombok")						          // Enables @Getter and @Setter annotations at compile time
  developmentOnly("org.springframework.boot:spring-boot-devtools")	  // Live reload and development tools

Database driver
  runtimeOnly("org.postgresql:postgresql")

Testing dependencies
  testImplementation("org.springframework.boot:spring-boot-starter-test")	  // JUUnit + Spring TestContext
  testImplementation("org.springframework.security:spring-security-test")	  // Extra test helpers for Spring Security
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")			        // JUnit Platform Runner

