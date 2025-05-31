@file:Suppress("SpellCheckingInspection")

plugins {
	idea
	id("net.neoforged.moddev.legacyforge") version "+"
	id("me.modmuss50.mod-publish-plugin") version "+"
}
base.archivesName.set(e("mod_id"))
group = e("mod_group_id")
version = "${e("minecraft_version")}-${e("mod_version")}+${e("upper_loader")}"
java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))
idea.module {
	isDownloadSources = true
	isDownloadJavadoc = true
}
tasks.processResources {
	val replace = properties.mapValues { it.value.toString() }
	inputs.properties(replace)
	from("src/main/resources") {
		include("**/*.toml")
		include("**/*.json")
		expand(replace)
	}
	into("build/resources/main")
	duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
tasks.jar {
	from("LICENSE")
	manifest { attributes(mapOf("MixinConfigs" to "${e("mod_id")}.mixins.json")) }
}
mixin {
	add(sourceSets.main.get(), "${e("mod_id")}.refmap.json")
	config("${e("mod_id")}.mixins.json")
}
legacyForge {
	version = "${e("minecraft_version")}-${e("loader_version")}"
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
repositories {
	mavenLocal()
	mavenCentral()
}
dependencies {
	annotationProcessor("org.spongepowered:mixin:${e("mixin_version")}:processor")
	compileOnly(annotationProcessor("io.github.llamalad7:mixinextras-common:${e("mixin_extras_version")}")!!)
	implementation(jarJar("io.github.llamalad7:mixinextras-${e("loader")}:${e("mixin_extras_version")}")!!)
}
publishMods {
	file.set(tasks.named("reobfJar").get().outputs.files.singleFile)
	changelog.set(file("CHANGELOG.md").readText())
	type.set(ALPHA)
	version.set(project.version.toString())
	displayName.set("${e("mod_name")} ${e("mod_version")}+${e("minecraft_version")}")
	modLoaders.addAll("Forge", "NeoForge")
	modrinth {
		accessToken.set(providers.environmentVariable("MODRINTH_TOKEN"))
		projectId.set("qSWV0tOk")
		minecraftVersions.add(e("minecraft_version"))
	}
}
fun e(key: String) = extra[key].toString()
