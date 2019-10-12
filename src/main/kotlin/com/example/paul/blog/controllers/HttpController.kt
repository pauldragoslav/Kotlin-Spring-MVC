package com.example.paul.blog.controllers

import com.example.paul.blog.repositories.ArticleRepository
import com.example.paul.blog.repositories.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/article")
class ArticleController(private val articleRepo: ArticleRepository) {

    @GetMapping("/")
    fun findAll() = articleRepo.findAllByOrderByAddedAtDesc()

    @GetMapping("/{slug}")
    fun findOne(@PathVariable slug: String) = articleRepo.findBySlug(slug)
            ?: throw IllegalArgumentException("Wrong article slug provided")
}

@RestController
@RequestMapping("/api/user")
class UserController(private val userRepo: UserRepository) {

    @GetMapping("/")
    fun findAll() = userRepo.findAll()

    @GetMapping("/{login}")
    fun findOne(@PathVariable login: String) = userRepo.findByLogin(login)
}
