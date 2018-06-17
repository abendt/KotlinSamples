package demo

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import org.assertj.core.api.KotlinAssertions

class HelloKotlinTest : StringSpec({
    "this is my first test" {
        val result = 10 + 10

        result shouldBe 20
    }

    "this is a second test" {
        KotlinAssertions.assertThat("bla").isEqualTo("bla")
    }
})
