package com.example.paul.blog.controllers

import com.example.paul.blog.models.Article
import com.example.paul.blog.models.User
import com.example.paul.blog.repositories.ArticleRepository
import com.example.paul.blog.repositories.UserRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
internal class HttpControllersTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var userRepo: UserRepository

    @MockkBean
    private lateinit var articleRepo: ArticleRepository

    @Test
    fun `List articles`() {
        val spotty = User("spottyg", "Spotty", "Giraffe")
        val firstArticle = Article(
                "Reticulated Giraffe",
                "Giraffa camelopardalis reticulata",
                "It lives in Somalia, southern Ethiopia, and northern Kenya ...",
                "a url",
                spotty)
        val secondArticle = Article(
                "Rothschild's Giraffe",
                "Giraffa camelopardalis rothschildi",
                "It is one of the most endangered distinct populations of giraffe ...",
                "another url",
                spotty)

        every { articleRepo.findAllByOrderByAddedAtDesc() } returns listOf(firstArticle, secondArticle)

        mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].author.login").value(spotty.login))
                .andExpect(jsonPath("\$.[0].slug").value(firstArticle.slug))
                .andExpect(jsonPath("\$.[1].author.login").value(spotty.login))
                .andExpect(jsonPath("\$.[1].slug").value(secondArticle.slug))
    }

    @Test
    fun `List users`() {
        val sparky = User("sparkyg", "Sparky", "Giraffe")
        val spotty = User("spottyg", "Spotty", "Giraffe")
        every { userRepo.findAll() } returns listOf(sparky, spotty)
        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].login").value(sparky.login))
                .andExpect(jsonPath("\$.[1].login").value(spotty.login))
    }
}
