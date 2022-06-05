package de.tiupe.hecklerspring.controller

import de.tiupe.hecklerspring.database.ArticleRepository
import de.tiupe.hecklerspring.entity.Article
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/articles")
class ArticleController(val articleRepo : ArticleRepository) {

    // @RequestMapping
    @GetMapping
    fun getArticles(): Iterable<Article> {
        return this.articleRepo.findAll()
    }

    @GetMapping("/{id}")
    fun getArticle(@PathVariable id: String): Optional<Article> {
        return this.articleRepo.findById(id)
    }


    @PostMapping
    fun postArticle(@RequestBody article: Article): Article {
        return this.articleRepo.save(article)
    }

    @PutMapping("/{id}")
    fun putArticle(@PathVariable id: String, @RequestBody article: Article): ResponseEntity<Article> {
        return if(this.articleRepo.existsById(id)) {
            ResponseEntity.ok(this.articleRepo.save(article))
        } else {
            ResponseEntity(this.articleRepo.save(article), HttpStatus.CREATED)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: String) {
        this.articleRepo.deleteById(id)
    }
}