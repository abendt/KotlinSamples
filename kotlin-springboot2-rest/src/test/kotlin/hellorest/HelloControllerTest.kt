package hellorest

import org.junit.Test
import org.springframework.test.web.reactive.server.WebTestClient

class HelloControllerTest {

    val client = WebTestClient.bindToController(HelloController()).build()

    @Test
    fun canTestHelloWorld() {

        client.get().uri("/")
                .exchange()
                .expectStatus().isOk
    }
}
