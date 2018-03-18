package hellorest

import io.restassured.RestAssured
import io.restassured.RestAssured.get
import org.assertj.core.api.Assertions
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner
import java.net.URL

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class HelloIT {

    @LocalServerPort
    private val port: Int = -1

    lateinit var base: URL

    @Before
    fun setUp() {
        this.base = URL("http://localhost:$port/")

        RestAssured.port = port
    }

    @Test
    fun canGetHelloWorld() {
        get("/").then()
                .statusCode(200)
                .body(containsString("Kotlin"))
    }
}
