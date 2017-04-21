package helloworld

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.Test

class HelloWorldTest {

    @Test
    fun canSayHelloTo() {
        assertThat(sayHelloTo("World")).isEqualTo("Hello World!")
    }
}

