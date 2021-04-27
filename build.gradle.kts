plugins {
  application
  java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.yaml:snakeyaml:1.28")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
  testImplementation("org.junit.jupiter:junit-jupiter-params:5.3.1")
  testImplementation("org.mockito:mockito-core:3.9.0")
  testImplementation("org.mockito:mockito-inline:3.9.0")
}

tasks.getByName<Test>("test") {
  useJUnitPlatform()
  testLogging {
    events("PASSED", "SKIPPED", "FAILED")
  }
}

application  {
  mainClass.set("app.GroceryStore")
}
