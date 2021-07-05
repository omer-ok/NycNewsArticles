package com.task.nycnewsarticles.main.model

data class Articles(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)
