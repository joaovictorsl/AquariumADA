package edu.aquarium.core

import java.lang.IllegalArgumentException

enum class Capacity(private val max: Int, private val asString: String) {
    SMALL(5, "Pequeno"),
    MEDIUM(10, "Médio"),
    LARGE(15, "Grande"),
    ;
    fun max(): Int {
        return max
    }

    override fun toString(): String {
        return asString
    }

    companion object {
        fun options(): String {
            return """
            1 - $SMALL
            2 - $MEDIUM
            3 - $LARGE
        """.trimIndent()
        }

        fun toCapacity(validChoice: Int): Capacity {
            return when(validChoice) {
                1 -> SMALL
                2 -> MEDIUM
                3 -> LARGE
                else -> throw IllegalArgumentException("A escolha de capacidade não é válida")
            }
        }
    }
}