package com.example.paul.blog.config

import com.example.paul.blog.models.Article
import com.example.paul.blog.models.User
import com.example.paul.blog.repositories.ArticleRepository
import com.example.paul.blog.repositories.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepo: UserRepository,
                            articleRepo: ArticleRepository) = ApplicationRunner {
        val sparky = userRepo.save(User("sparkyg", "Sparky", "Giraffe"))
        val spotty = userRepo.save(User("spottyg", "Spotty", "Giraffe"))

        articleRepo.save(Article(
                title = "Reticulated Giraffe",
                headline = "Giraffa camelopardalis reticulata",
                content = "It lives in Somalia, southern Ethiopia, and northern Kenya ...",
                author = sparky
        ))
        articleRepo.save(Article(
                title = "Rothschild's Giraffe",
                headline = "Giraffa camelopardalis rothschildi",
                content = "It is one of the most endangered distinct populations of giraffe ...",
                author = sparky
        ))
        articleRepo.save(Article(
                title = "West African Giraffe",
                headline = "Giraffa camelopardalis peralta",
                content = "It is a subspecies of the giraffe distinguished by its light colored spots ...",
                author = spotty
        ))
    }
}
