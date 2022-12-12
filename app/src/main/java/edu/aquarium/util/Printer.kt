package edu.aquarium.util

private const val divisor = "\uD80C\uDD9F. ° .• .\uD80C\uDD9D .• ° . \uD80C\uDD9F . ° .• .\uD80C\uDD9E"

private val menu = """
        1 - Criar aquário
        2 - Listar aquários
        3 - Adicionar peixe
        4 - Alimentar peixes
        5 - Limpar aquário
        6 - Atualizar fator de limpeza
        7 - Aprimorar capacidade de um aquário
        0 - Sair
    """.trimIndent()

/**
 * Prints the divisor and the menu right after it.
 */
fun printMenu() {
    printSection(menu)
}

/**
 * Prints an string after the divisor.
 *
 * @param section String to be printed after the divisor.
 */
fun printSection(section: String) {
    println(divisor)
    println(section)
}
