package de.tiupe.hecklerspring.database

import de.tiupe.hecklerspring.entity.Student
import org.springframework.data.repository.CrudRepository

interface StudentRepository : CrudRepository<Student, String> {}