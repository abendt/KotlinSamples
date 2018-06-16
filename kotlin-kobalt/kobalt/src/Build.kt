import com.beust.kobalt.plugin.application.application
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.project

val p = project {
    name = "kotlin-kobalt"
    group = "com.github.abendt"
    artifactId = name
    version = "0.1"

    val kotlin_version = "1.2.50"

    dependencies {
        compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    }

    dependenciesTest {
        compile("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")

        compile("junit:junit:4.12")
        compile("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

        compile("net.wuerl.kotlin:assertj-core-kotlin:0.2.1")
        compile("org.assertj:assertj-core:3.10.0")
    }

    assemble {
        jar {
        }
    }

    application {
        mainClass = "com.example.MainKt"
    }
}
