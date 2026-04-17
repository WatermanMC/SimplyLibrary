# SimplyLibrary
**SimplyLibrary** is a lightweight and powerful library aims to simplify **Paper API** to make coding plugins more easier and quicker

---

## Requirements
* **Java 21 and above**<br>
* **PaperMC (and its forks) 1.21+**<br>
* **[Vault plugin](https://www.spigotmc.org/resources/vault.34315/)**

## Key Features
* **Economy:** Simplifies Vault API for payment, deposit and withdraw. Automatically wraps the Vault API so you don't need to. No need Vault API imports or dependency boilerplate!
* **Item Builder:** Build items easily in just one statement of code without any boilerplate code! Returns `ItemStack` so you can use it everywhere!
* **Command Builder:** Easily build commands with full customizability in just one statement of code! No more `plugin.yml` junk and other command registration boilerplate.
* **Command Tab Completer Builder:** Create tab completions easily with full customizability on args, etc.! Supports **ALL** commands, even from other plugin or Minecraft internals.
* **Timers:** Easily create timers in ticks, seconds, and up to days! Repeat timer, cancel and check status of the timers and more! No more BukkitTask, BukkitScheduler junk code.
* **Text Utilities:** Easily send a message whether modern or legacy, send actionbars or titles with duration or convert anything to Component!
* **Skin Utilities:** Grab skin value or signature or set a new skin to a player easily!

---

## For Developers
### Dependency:
[![](https://jitpack.io/v/WatermanMC/SimplyLibrary.svg)](https://jitpack.io/#WatermanMC/SimplyLibrary)

**Maven (pom.xml):**<br>
Repository:
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
Dependency:
```xml
	<dependency>
	    <groupId>com.github.WatermanMC</groupId>
	    <artifactId>SimplyLibrary</artifactId>
	    <version>1.0-BETA</version>
	</dependency>
```

**Gradle (build.gradle.kts):**<br>
Add it in your `settings.gradle.kts` at the end of repositories:
```kotlin
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url = uri("https://jitpack.io") }
		}
	}
```
Add the dependency:
```kotlin
	dependencies {
	        implementation("com.github.WatermanMC:SimplyLibrary:1.0-BETA")
	}
```

**Gradle (build.gradle):**<br>
Add it in your root settings.gradle at the end of repositories:
```groovy
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency:
```groovy
	dependencies {
	        implementation 'com.github.WatermanMC:SimplyLibrary:1.0-BETA'
	}
```

**[Javadocs](https://jitpack.io/com/github/WatermanMC/SimplyLibrary/1.0-BETA/javadoc/)**<br>
**[Example codes using SimplyLibrary](https://github.com/WatermanMC/SimplyLibrary/wiki)**

---

## Links & Additional Information

**Author:** WatermanMC
**License:** GNU General Public License v3

* **GitHub Repository** (Source Code): [https://github.com/WatermanMC/SimplyLibrary](https://github.com/WatermanMC/SimplyLibrary)
* **Example Codes**: [https://github.com/WatermanMC/SimplyLibrary/wiki](https://github.com/WatermanMC/SimplyLibrary/wiki)
* **Discord Support:** [https://discord.gg/Scgqfm5EU4](https://discord.gg/Scgqfm5EU4)