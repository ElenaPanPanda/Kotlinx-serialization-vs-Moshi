package com.example.kotlinxserialization

import kotlinx.serialization.*

@Serializable
data class RecipeForKotlinx(
    val title: String,
    val image: String,
    val ingredients: List<String>,
    val icons: List<String>,
    val instructions: String
)

@Serializable
data class RecipesForKotlinx(
    @SerialName("recipes") val recipesList: List<RecipeForKotlinx>
)