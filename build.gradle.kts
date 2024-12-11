import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
	java
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
	`java-library`
	id("checkstyle")
	jacoco
	id("com.github.johnrengelman.shadow") version "8.1.1"
	id("com.adarshr.test-logger") version "4.0.0"
	id("io.freefair.lombok") version "8.4"
	application
	id("io.sentry.jvm.gradle") version "4.12.0"
}

group = "brocodex"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
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
	// Tests
	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	testImplementation("org.junit.jupiter:junit-jupiter")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.assertj:assertj-core:3.26.3")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Utils
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// DB
	implementation("org.postgresql:postgresql:42.7.3")
	runtimeOnly("com.h2database:h2")
	implementation("org.liquibase:liquibase-core")

	// Spring
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("jakarta.validation:jakarta.validation-api")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	// Spring security
//	implementation("org.springframework.boot:spring-boot-starter-security")
//	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
//	testImplementation("org.springframework.security:spring-security-test")

	// Logger
	implementation("io.sentry:sentry-spring-boot-starter-jakarta:7.15.0")
	implementation("io.sentry:sentry-logback:7.15.0")
	implementation("io.sentry:sentry-log4j2:7.15.0")

	// Mapper
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")

	// Tests
	implementation("net.datafaker:datafaker:2.0.1")
	implementation("org.instancio:instancio-junit:3.3.0")
	testImplementation("net.javacrumbs.json-unit:json-unit-assertj:3.2.2")
	testImplementation ("org.mockito:mockito-core:4.11.0")

	// Spring AMQP
	implementation("org.springframework.boot:spring-boot-starter-amqp")

	// Telegram
	implementation("org.telegram:telegrambots-springboot-longpolling-starter:8.0.0")
	implementation ("org.telegram:telegrambots-client:8.0.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

testlogger {
	showFullStackTraces = true
	theme = ThemeType.MOCHA
}

tasks.jacocoTestReport {
	dependsOn(tasks.withType<Test>()) // tests are required to run before generating the report
	reports {
		xml.required = true
	}
}

application {
	mainClass.set("brocodex.vpn.VpnApplication")
}

checkstyle {
	toolVersion = "10.18.2"
	configFile = file("config/checkstyle/checkstyle.xml")
	isIgnoreFailures = false
	isShowViolations = true
}

tasks.checkstyleMain {
	dependsOn(tasks.compileJava)
	outputs.upToDateWhen { false }
	reports {
		xml.required.set(true)
		html.required.set(true)
	}
}

tasks.checkstyleTest {
	dependsOn(tasks.compileTestJava)
	outputs.upToDateWhen { false }
	reports {
		xml.required.set(true)
		html.required.set(true)
	}
}

if(System.getenv("SPRING_PROFILES_ACTIVE") != null
	&& System.getenv("SPRING_PROFILES_ACTIVE").equals("prod")) {
	sentry {
		includeSourceContext = true

		org = "brocodex"
		projectName = "java-spring-boot"
		authToken = System.getenv("SENTRY_AUTH_TOKEN")
	}
}
