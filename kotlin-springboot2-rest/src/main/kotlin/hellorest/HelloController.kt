package hellorest

import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.p
import kotlinx.html.stream.createHTML
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@RestController
@Service
class HelloController {


    // todo https://github.com/flqw/spring-boot-kotlinx-html
    val text = createHTML(true).div {
        p {
            +"Here is "
            a("http://kotlinlang.org") { +"official Kotlin site" }
        }
    }

    @GetMapping
    fun hello(): Mono<String> = text.toMono()

}
