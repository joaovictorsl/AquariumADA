package edu.aquarium.core

import java.lang.IllegalArgumentException

/**
 * Represent the size of a fish. Available values:
 * SMALL | MEDIUM | LARGE
 */
enum class Size(private val asString: String) {
    SMALL("Pequeno"),
    MEDIUM("Médio"),
    LARGE("Grande"),

    ;

    companion object {
        /**
         * Maps an Int to a Size.
         * @param validChoice Int to be mapped to a size.
         * @return A size.
         * @throws IllegalArgumentException is thrown if choice is invalid.
         */
        fun toSize(validChoice: Int): Size {
            return when(validChoice) {
                1 -> SMALL
                2 -> MEDIUM
                3 -> LARGE
                else -> throw IllegalArgumentException("A escolha de tamanho não é válida")
            }
        }

        /**
         * Builds a representation for all the available sizes.
         * @return String representing a numbered list of available sizes.
         */
        fun options(): String {
            return """1 - $SMALL
                |2 - $MEDIUM
                |3 - $LARGE
            """.trimMargin()
        }
    }

    override fun toString(): String {
        return asString
    }
}