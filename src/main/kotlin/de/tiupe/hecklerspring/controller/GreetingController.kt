package de.tiupe.hecklerspring.controller

import de.tiupe.hecklerspring.configuration.Greeting
import de.tiupe.hecklerspring.database.StudentRepository
import de.tiupe.hecklerspring.entity.Student
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono



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

    @Autowired
    private lateinit var studentRepository: StudentRepository



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
    @GetMapping("/flux")
    fun getTiupe(): Mono<String> {
        val webClient: WebClient = WebClient.create("http://www.tiupe.de")
        val result = webClient.get().retrieve()
        val x: Mono<String> = result.bodyToMono(String::class.java)
        return x
    }

    @GetMapping("/student/{id}")
    fun getStudent(@PathVariable id: String): Student {
        val student: Student = studentRepository.findById(id).get()
        return student
    }



}