package de.tiupe.hecklerspring.entity

import org.springframework.data.redis.core.RedisHash
import java.io.Serializable
import javax.persistence.Id


@Suppress("unused")
@RedisHash("Student")
class Student(@Id val id: String,
              val name: String,
              val gender: Gender
              ) : Serializable{
        enum class Gender {
            MALE, FEMALE
        }


}