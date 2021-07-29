plugins {
    java
    id("io.izzel.taboolib") version "1.10"
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
}

taboolib {
    version = "6.0.0-pre5"
    install("common")
    install("platform-bukkit")
    install("module-ui")
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("ink.ptms.core:v11701:11701:mapped")
    compileOnly("ink.ptms.core:v11701:11701:universal")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
    compileOnly("org.jsoup:jsoup:1.14.1")
    compileOnly("com.alibaba:fastjson:1.2.71")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}