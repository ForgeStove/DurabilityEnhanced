@file:Suppress("SpellCheckingInspection")

plugins {
	idea
	id("net.neoforged.moddev") version "+"
	id("me.modmuss50.mod-publish-plugin") version "0.8.4"
}
fun e(key: String) = extra[key].toString()
repositories {
	mavenLocal()
	mavenCentral()
//	maven("https://maven.createmod.net") // Create, Ponder, Flywheel
//	maven("https://mvn.devos.one/snapshots") // Registrate
	maven("https://maven.shedaniel.me") // Cloth Config API
	maven("https://maven.blamejared.com") // JEI
}
dependencies {
	compileOnly(fileTree(mapOf("dir" to "cache", "include" to listOf("*.jar"))))
//	implementation("com.simibubi.create:create-${e("minecraft_version")}:${e("create_version")}:slim") { isTransitive = false }
//	implementation("net.createmod.ponder:Ponder-${e("upper_loader")}-${e("minecraft_version")}:${e("ponder_version")}") {
//		isTransitive = false
//	}
//	implementation("dev.engine-room.flywheel:flywheel-${e("loader")}-${e("minecraft_version")}:${e("flywheel_version")}")
//	implementation("com.tterrag.registrate:Registrate:${e("registrate_version")}")
	implementation("me.shedaniel.cloth:cloth-config-${e("loader")}:${e("cloth_config_version")}")
	implementation("mezz.jei:jei-${e("minecraft_version")}-${e("loader")}:${e("jei_version")}")
}
tasks.processResources {
	val replace = properties.mapValues { it.value.toString() }
	inputs.properties(replace)
	from("src/main/resources") {
		include("**/*.toml")
		expand(replace)
	}
	into("build/resources/main")
	duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
base.archivesName.set(e("mod_id"))
group = e("mod_group_id")
version = "${e("minecraft_version")}-${e("mod_version")}+${e("upper_loader")}"
java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))
tasks.jar { from("LICENSE") }
idea {
	module {
		isDownloadSources = true
		isDownloadJavadoc = true
	}
}
neoForge {
	version = e("loader_version")
	parchment {
		mappingsVersion.set(e("parchment_version"))
		minecraftVersion.set(e("minecraft_version"))
	}
	runs {
		create("client") { client() }
		create("server") { server(); programArgument("--nogui") }
		configureEach {
			jvmArguments.addAll("-XX:+IgnoreUnrecognizedVMOptions", "-XX:+AllowEnhancedClassRedefinition")
			systemProperty("terminal.jline", "true")
		}
	}
	mods { create(e("mod_id")) { sourceSet(sourceSets["main"]) } }
}
