package edu.aquarium.util

/**
 * Gets valid user input. While user input is not valid, prompt will be executed, errorMsg will be printed and input is going to be read again.
 *
 * @param prompt Executable to run before reading line.
 * @param isInputValid Executable which receives a String argument and returns a boolean. By default it returns true if not specified.
 * @param errorMsg Message to be printed when invalid input is given.
 */
fun getUserInput(prompt: () -> Unit, isInputValid: (String) -> Boolean = {true}, errorMsg: String = "Entrada inválida, tente novamente."): String {
    prompt()
    var input = readln()

    while(!isInputValid(input)) {
        prompt()
        println(errorMsg)
        input = readln()
    }

    return input
}