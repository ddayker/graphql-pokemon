package com.dayker.graphqlpokemon.presentation.common

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.random.Random
import kotlin.random.nextInt

sealed class PokemonType(val name: String, val color: Color) {

    data object Normal : PokemonType(name = "normal", color = Color(0xFF87CEEB))
    data object Fire : PokemonType(name = "fire", color = Color(0xFFFF6347))
    data object Water : PokemonType(name = "water", color = Color(0xFF00CED1))
    data object Electric : PokemonType(name = "electric", color = Color(0xFFFFD700))
    data object Grass : PokemonType(name = "grass", color = Color(0xFF7CFC00))
    data object Ice : PokemonType(name = "ice", color = Color(0xFF00FFFF))
    data object Fighting : PokemonType(name = "fighting", color = Color(0xFFFF69B4))
    data object Poison : PokemonType(name = "poison", color = Color(0xFFDA70D6))
    data object Ground : PokemonType(name = "ground", color = Color(0xFFD2B48C))
    data object Flying : PokemonType(name = "flying", color = Color(0xFF87CEFA))
    data object Psychic : PokemonType(name = "psychic", color = Color(0xFFFFB6C1))
    data object Bug : PokemonType(name = "bug", color = Color(0xFFADFF2F))
    data object Rock : PokemonType(name = "rock", color = Color(0xFFA52A2A))
    data object Ghost : PokemonType(name = "ghost", color = Color(0xFF9370DB))
    data object Dragon : PokemonType(name = "dragon", color = Color(0xFF9932CC))
    data object Dark : PokemonType(name = "dark", color = Color(0xFF696969))
    data object Steel : PokemonType(name = "steel", color = Color(0xFFC0C0C0))
    data object Fairy : PokemonType(name = "fairy", color = Color(0xFFFFE4E1))

    companion object {
        fun setTypeColor(typeName: String): Color {
            return when (typeName.lowercase()) {
                Normal.name -> Normal.color
                Fire.name -> Fire.color
                Water.name -> Water.color
                Electric.name -> Electric.color
                Grass.name -> Grass.color
                Ice.name -> Ice.color
                Fighting.name -> Fighting.color
                Poison.name -> Poison.color
                Ground.name -> Ground.color
                Flying.name -> Flying.color
                Psychic.name -> Psychic.color
                Bug.name -> Bug.color
                Rock.name -> Rock.color
                Ghost.name -> Ghost.color
                Dragon.name -> Dragon.color
                Dark.name -> Dark.color
                Steel.name -> Steel.color
                Fairy.name -> Fairy.color
                else -> Color.Black
            }
        }

        fun setTypeGradientColorBrush(type: String): Brush {
            val color = setTypeColor(type)
            return Brush.linearGradient(
                colors = listOf(
                    color.copy(alpha = 0.9f),
                    color.copy(alpha = 0.6f),
                    Color(0xFFFAE36B),
                    color.copy(alpha = 0.7f),
                    color.copy(alpha = 1f)
                ),
                start = Offset(Random.nextInt(20..200).toFloat(), Random.nextInt(0..200).toFloat()),
                end = Offset(Random.nextInt(0..300).toFloat(), Random.nextInt(0..300).toFloat())
            )
        }
    }
}