package hellorest

import io.restassured.RestAssured
import io.restassured.RestAssured.get
import org.assertj.core.api.Assertions
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import java.net.URL

@RunWith(SpringRunner::class)
@SpringBootTest
class HelloIT {

    private val port: Int = 8080

    lateinit var base: URL

    @Before
    fun setUp() {
        this.base = URL("http://localhost:$port/")

        RestAssured.port = 8080
    }

    @Test
    fun canGetHelloWorld() {
        get("/").then()
                .statusCode(200)
                .body(equalTo("Hello World!"))
    }
}