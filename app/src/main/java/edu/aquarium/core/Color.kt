package edu.aquarium.core

import java.lang.IllegalArgumentException

/**
 * Represent the color of a fish. Available values:
 * RED | YELLOW | GREEN | PURPLE | BLUE | BLACK | WHITE
 */
enum class Color(
    private val asString: String
) {
    RED("Vermelho"),
    YELLOW("Amarelo"),
    GREEN("Verde"),
    PURPLE("Roxo"),
    BLUE("Azul"),
    BLACK("Preto"),
    WHITE("Branco")

    ;

    companion object {
        /**
         * Maps an Int to a Color.
         * @param validChoice Int to be mapped to a Color.
         * @return A color.
         * @throws IllegalArgumentException is thrown if choice is invalid.
         */
        fun toColor(validChoice: Int): Color {
            return when(validChoice) {
                1 -> RED
                2 -> YELLOW
                3 -> GREEN
                4 -> PURPLE
                5 -> BLUE
                6 -> BLACK
                7 -> WHITE
                else -> throw IllegalArgumentException("A escolha de cor não é válida")
            }
        }

        /**
         * Builds a representation for all the available colors.
         * @return String representing a numbered list of available colors.
         */
        fun options(): String {
            return """1 - $RED
                |2 - $YELLOW
                |3 - $GREEN
                |4 - $PURPLE
                |5 - $BLUE
                |6 - $BLACK
                |7 - $WHITE""".trimIndent()
        }
    }

    override fun toString(): String {
        return asString
    }
}
