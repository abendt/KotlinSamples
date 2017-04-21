package helloworld

import org.assertj.core.api.KotlinAssertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object SimpleSpec: Spek({
    describe("Hello World") {

        on("hello to world") {
            it("should return the correct greeting") {
                assertThat(sayHelloTo("World")).isEqualTo("Hello World!")
            }
        }
    }
})

