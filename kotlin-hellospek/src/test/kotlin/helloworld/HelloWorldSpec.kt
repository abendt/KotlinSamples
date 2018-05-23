package helloworld

import org.assertj.core.api.KotlinAssertions
import org.assertj.core.api.KotlinAssertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object SimpleSpec: Spek({
    describe("Hello Spek") {

        beforeGroup {
            println("before each group")
        }

        beforeEachTest {
            println("before each test")
        }

        it("can have simple test") {
            KotlinAssertions.assertThat("a").isEqualTo("a")
        }

        on("nested") {
            it("should return the correct greeting") {
                assertThat(sayHelloTo("World")).isEqualTo("Hello World!")
            }
        }
    }
})

