package com.example.paul.blog.controllers

import com.example.paul.blog.models.User
import com.example.paul.blog.repositories.ArticleRepository
import com.example.paul.blog.repositories.UserRepository
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/article")
class ArticleController(private val repository: ArticleRepository) {

    @GetMapping("/")
    fun findAll() = repository.findAllByOrderByAddedAtDesc()

    @GetMapping("/{slug}")
    fun findOne(@PathVariable slug: String) = repository.findBySlug(slug)
            ?: throw ResponseStatusException(NOT_FOUND, "This article does not exist")

}

@RestController
@RequestMapping("/api/user")
class UserController(private val repository: UserRepository) {

    @GetMapping("/")
    fun findAll(): Iterable<User> = repository.findAll()

    @GetMapping("/{login}")
    fun findOne(@PathVariable login: String) = repository.findByLogin(login)
            ?: throw ResponseStatusException(NOT_FOUND, "This user does not exist")
}
