package hellorest

import kotlinx.coroutines.experimental.future.await
import kotlinx.coroutines.experimental.future.future
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.SettableListenableFuture
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
class HelloController {

    @Autowired
    lateinit var helloConfiguration: HelloConfiguration

    @RequestMapping("/")
    fun index(): String {
        return "Greetings from Spring Boot!"
    }

    @RequestMapping("/async")
    fun asyncHello(): CompletableFuture<String> {

        val result = CompletableFuture<String>()

        Thread {
            println("Start")

            Thread.sleep(1000)
            result.complete("Async Hello")

            println("done")
        }.start()

        return result
    }

    @RequestMapping("/async2")
    fun deferedHello(): ListenableFuture<String> {

        val result = SettableListenableFuture<String>()

        Thread {
            println("Start")

            Thread.sleep(1000)
            result.set("Async Hello")

            println("done")
        }.start()

        return result
    }

    @RequestMapping("/combineSync")
    fun combineSync(): String {
        val name = api1()
        val greeting = api2(name)
        val motd = api3()

        return "$greeting $motd"
    }

    @RequestMapping("/combineAsync")
    fun combineAsync(): CompletableFuture<String> {
        val greeting = asyncApi1().thenCompose {
            asyncApi2(it)
        }
        val motd = asyncApi3()

        return greeting.thenCombine(motd) { g, m -> "$g $m" }
    }

    fun <T> execAsync(block: () -> T): CompletableFuture<T> {
        val result = CompletableFuture<T>()

        Thread {
            val c = block()
            result.complete(c)
        }.start()

        return result
    }

    fun api1(): String {
        Thread.sleep(1000)
        return "Kotlin"
    }

    fun asyncApi1() = execAsync { api1() }
    fun asyncApi2(name: String) = execAsync { api2(name) }
    fun asyncApi3() = execAsync { api3() }

    fun api2(name: String): String {
        Thread.sleep(1000)
        return "Hello $name"
    }

    fun api3(): String {
        Thread.sleep(1000)
        return "What a nice day!"
    }

    @RequestMapping("/combineCoroutine")
    fun combineCoroutine(): CompletableFuture<String> = future {
        val name = asyncApi1()
        val motd = asyncApi3()
        val greeting = asyncApi2(name.await())

        greeting.await() + " " + motd.await()
    }
}

