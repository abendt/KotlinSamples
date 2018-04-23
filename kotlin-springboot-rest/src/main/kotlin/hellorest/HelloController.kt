package hellorest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @Autowired
    lateinit var helloConfiguration: HelloConfiguration

    @RequestMapping("/")
    fun index(): String {
        return "Greetings from Spring Boot!" + helloConfiguration.name
    }
}

