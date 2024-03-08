package com.example.kotlinxserialization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class Recipe(
    val title: String,
    val image: String,
    val ingredients: List<String>,
    val icons: List<String>,
    val instructions: String
)


data class Recipes(
    val recipes: List<RecipeForKotlinx>
)
