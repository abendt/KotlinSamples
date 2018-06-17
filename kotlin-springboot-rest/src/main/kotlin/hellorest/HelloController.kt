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
        val name = getUserName()
        val greeting = sayHelloTo(name)
        val motd = getMessageOfTheDay()

        return "$greeting $motd"
    }

    @RequestMapping("/combineAsync")
    fun combineAsync(): CompletableFuture<String> {
        val greeting = asyncGetUserName().thenCompose {
            asyncSayHelloTo(it)
        }
        val motd = asyncGetMessageOfTheDay()

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

    fun getUserName(): String {
        Thread.sleep(1000)
        return "Kotlin"
    }

    fun asyncGetUserName() = execAsync { getUserName() }
    fun asyncSayHelloTo(name: String) = execAsync { sayHelloTo(name) }
    fun asyncGetMessageOfTheDay() = execAsync { getMessageOfTheDay() }

    fun sayHelloTo(name: String): String {
        Thread.sleep(1000)
        return "Hello $name"
    }

    fun getMessageOfTheDay(): String {
        Thread.sleep(1000)
        return "What a nice day!"
    }

    @RequestMapping("/combineCoroutine")
    fun combineCoroutine() = future {
        val name = asyncGetUserName()
        val motd = asyncGetMessageOfTheDay()
        val greeting = asyncSayHelloTo(name.await())

        greeting.await() + " " + motd.await()
    }
}

