package de.tiupe.hecklerspring.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "confprops")
@ConstructorBinding
data class Greeting(val greetingMorgen: String, val greetingMittag: String, val greetingAbend: String)
