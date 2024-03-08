package com.example.kotlinxserialization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.kotlinxserialization.databinding.ActivityMainBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tv = binding.tv

        val recipes = resources.openRawResource(R.raw.recipes)
            .bufferedReader()
            .readText()

        // kotlinxSerialization(recipes, tv)

        // vs

        // moshi(recipes, tv)

    }
}

private fun kotlinxSerialization(recipes: String, tv: TextView) {
    val deserialized = Json.decodeFromString<RecipesForKotlinx>(recipes)

    deserialized.recipesList.forEach { recipe ->
        println("msg: " + recipe.title)
    }

    val filteredList = deserialized.recipesList.filter {
        it.icons.contains("🌿")
    }

    filteredList.forEach {
        println("msg2: " + it.title)
    }

    val jsonPrettyPrint = Json { prettyPrint = true }

    val serialized = jsonPrettyPrint.encodeToString(filteredList)

    tv.text = serialized
}

private fun moshi(recipes: String, tv: TextView) {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val recipesAdapter = moshi.adapter(Recipes::class.java)

    val deserialized = recipesAdapter.fromJson(recipes)

    deserialized?.recipes?.forEach { recipe ->
        println("msg: " + recipe.title)
    }

    val filteredList = deserialized?.recipes?.filter {
        it.icons.contains("🌿")
    }

    filteredList?.forEach {
        println("msg2: " + it.title)
    }

    val filtered = Recipes(filteredList!!)

    tv.text = recipesAdapter.toJson(filtered)
}

