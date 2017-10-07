package hellorest

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@RestController
@Service
class HelloController {

    @GetMapping
    fun hello(): Mono<String> = "Hello World!".toMono()

}