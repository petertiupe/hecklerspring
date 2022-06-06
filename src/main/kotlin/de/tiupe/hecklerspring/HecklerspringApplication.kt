package de.tiupe.hecklerspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
// Weist Spring an, nach Configuration - Properties zu suchen
@ConfigurationPropertiesScan
class HecklerspringApplication

fun main(args: Array<String>) {
    runApplication<HecklerspringApplication>(*args)
}
