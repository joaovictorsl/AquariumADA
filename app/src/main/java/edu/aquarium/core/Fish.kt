package edu.aquarium.core

import java.lang.IllegalArgumentException

/**
 * Class responsible to represent a fish in the aquarium.
 *
 * @param name  Represents the fish's name. Should not be empty or blank and should have at least 3 characters.
 *              If one of the conditions is not met, an **IllegalArgumentException** is thrown.
 * @param color Represents the fish's color. It can be one of the following values:
 *              RED | YELLOW | GREEN | PURPLE | BLUE | BLACK | WHITE
 * @param size Represents the fish's size. It can be one of the following values:
 *             SMALL | MEDIUM | LARGE
 */
class Fish(
    name: String,
    color: Color,
    size: Size,
) {
    private val name: String
    private val color: Color
    private val size: Size

    init {
        validateName(name)
        this.name = name.trim()
        this.color = color
        this.size = size
    }

    private fun validateName(name: String) {
        val trimmedName = name.trim()
        if (trimmedName.isBlank() || trimmedName.isEmpty() || trimmedName.length < 3)
            throw IllegalArgumentException("Nome inválido, o nome de um peixe deve ter no mínimo 3 caracteres não vazios.")
    }

    override fun toString(): String {
        return "$name ($color | $size)"
    }
}
