package hellorest

import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import kotlin.system.measureTimeMillis

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun canGetHello() {
        mvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }

    @Test
    fun canGetHelloAsync() {
        val response = mvc.perform(get("/async").accept(MediaType.APPLICATION_JSON))
            .andExpect(request().asyncStarted())
            .andReturn()

        mvc.perform(asyncDispatch(response))
            .andExpect(status().isOk)
            .andExpect(content().string(equalTo("Async Hello")));
    }

    @Test
    fun canGetHelloAsync2() {
        val response = mvc.perform(get("/async2").accept(MediaType.APPLICATION_JSON))
            .andExpect(request().asyncStarted())
            .andReturn()

        mvc.perform(asyncDispatch(response))
            .andExpect(status().isOk)
            .andExpect(content().string(equalTo("Async Hello")))
    }

    @Test
    fun combineSync() {
        val time = measureTimeMillis {
            mvc.perform(get("/combineSync").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().string(equalTo("Hello Kotlin What a nice day!")))
        }

        println(time)
    }

    @Test
    fun combineAsync() {
        val time = measureTimeMillis {
            val response = mvc.perform(get("/combineAsync").accept(MediaType.APPLICATION_JSON))
                .andExpect(request().asyncStarted())
                .andReturn()

            mvc.perform(asyncDispatch(response))
                .andExpect(status().isOk)
                .andExpect(content().string(equalTo("Hello Kotlin What a nice day!")))
        }

        println(time)
    }

    @Test
    fun combineCoroutine() {
        val time = measureTimeMillis {
            val response = mvc.perform(get("/combineCoroutine").accept(MediaType.APPLICATION_JSON))
                .andExpect(request().asyncStarted())
                .andReturn()

            mvc.perform(asyncDispatch(response))
                .andExpect(status().isOk)
                .andExpect(content().string(equalTo("Hello Kotlin What a nice day!")))
        }

        println(time)
    }
}

