plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.24"
    id("org.jetbrains.intellij") version "1.17.3"
}

dependencies {
    implementation("io.swagger:swagger-annotations:1.6.14")
    // lombok
    implementation("org.projectlombok:lombok:1.18.32")
    // lombok 依赖的注解处理器
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    // reflections
    implementation("org.reflections:reflections:0.10.2")
    // TODO idea 没有这个依赖 后续再看
    //问题在于运行中可以通过反射拿到这个com.intellij:openapi里面的
    //但是直接运行就不可以，会报错误 没有com.intellij:openapi里面的
//    implementation("com.intellij:openapi:241.17890.1")
}


group = "com.koh"
version = "1.0-SNAPSHOT"

repositories {
//    mavenCentral()
    maven {
        url = uri("https://maven.aliyun.com/repository/public")
    }
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    localPath.set("/Users/zhujiangyong/Applications/IntelliJ IDEA Ultimate.app/Contents")
//    version.set("2023.2.6")
//    type.set("IC") // Target IDE Platform

    plugins.set(listOf(
        "com.intellij.java"
    ))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("242.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
