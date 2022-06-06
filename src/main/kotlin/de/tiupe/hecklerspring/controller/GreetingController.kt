package de.tiupe.hecklerspring.controller

import de.tiupe.hecklerspring.configuration.Greeting
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping


@RestController
@RequestMapping("/greeting")
class GreetingController {

    // Achtung, in Kotlin muss man das $-Zeichen escapen
    // mit dem Doppelpunkt wird ein Default-Wert abgetrennt

    @Value("\${gruss.morgens: Hallo}")
    private lateinit var  morgengruss: String

    @Value("\${gruss.morgens: Hallo}")
    private lateinit var  mittagsgruss: String

    @Value("\${gruss.morgens: Hallo}")
    private lateinit var  abendgruss: String

    @Value("\${gruss.abhaengigkeit.a-morgens}")
    private lateinit var fruehstueck: String

    @Value("\${gruss.abhaengigkeit.a-mittags}")
    private lateinit var mittagessen: String

    @Value("\${gruss.abhaengigkeit.a-abends}")
    private lateinit var abendbrot: String

    // Die Configuration ist keine Bean, daher sollte das auch so klappen.
    @Autowired
    private lateinit var greeting: Greeting



    @GetMapping("/gruss")
    fun getGruss(): String {
        val now = Clock.System.now()
        val hourOfDay: Int = now.toLocalDateTime(TimeZone.currentSystemDefault()).hour
        if(hourOfDay < 12) {
            return morgengruss
        } else if (hourOfDay in 12..17) {
            return mittagsgruss
        } else {
            return abendgruss
        }
    }

    @GetMapping("/essensgruss")
        fun getGrussMitEssen(): String {
            val now = Clock.System.now()
            val hourOfDay: Int = now.toLocalDateTime(TimeZone.currentSystemDefault()).hour
            if(hourOfDay < 12) {
                return fruehstueck
            } else if (hourOfDay in 12..17) {
                return mittagessen
            } else {
                return abendbrot
            }
        }

    @GetMapping("/configgruss")
    fun getConfigGruss(): String {
        val now = Clock.System.now()
        val hourOfDay: Int = now.toLocalDateTime(TimeZone.currentSystemDefault()).hour
        if(hourOfDay < 12) {
            return greeting.greetingMorgen
        } else if (hourOfDay in 12..17) {
            return greeting.greetingMittag
        } else {
            return greeting.greetingAbend
        }
    }


}