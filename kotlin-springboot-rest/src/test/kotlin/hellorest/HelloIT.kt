package hellorest

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import java.net.URL

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloIT {

    @LocalServerPort
    private lateinit var port: String

    lateinit var base: URL

    @Autowired
    lateinit var template: TestRestTemplate

    @Before
    fun setUp() {
        this.base = URL("http://localhost:$port/")
    }

    @Test
    fun getHello() {
        val response = template.getForEntity(base.toString(), String::class.java)
        assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!")
    }
}

