package hellorest

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "hello")
class HelloConfiguration {
    lateinit var name : String
}
