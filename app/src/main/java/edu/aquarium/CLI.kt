package edu.aquarium

import edu.aquarium.core.Aquarium
import edu.aquarium.core.Capacity
import edu.aquarium.core.Color
import edu.aquarium.core.Size
import edu.aquarium.util.getUserInput
import edu.aquarium.util.printMenu
import edu.aquarium.util.printSection

/**
 * Class representing the command line interface for ADA's aquarium application.
 */
class CLI {
    private val aquariumList = mutableListOf<Aquarium>()
    private var cleaningFactor = 3

    /**
     * Executes the application.
     */
    fun run() {
        println("Bem-vindo ao sistema de aquários da ADA")

        do {
            val input = getUserInput({ printMenu() },
                {
                it.toIntOrNull() in 0..7
            }).toInt()

            execCommand(input)
        } while(input != 0)
    }

    /**
     * Executes a command desired by the user.
     */
    private fun execCommand(command: Int) {
        when (command) {
            1 -> createAquarium()
            2 -> showAllAquariums()
            3 -> addFish()
            4 -> feedFish()
            5 -> cleanAquarium()
            6 -> updateCleaningFactor()
            7 -> upgradeAquariumCapacity()
        }
    }

    /**
     * Creates an aquarium and adds it to the aquariumList property.
     */
    private fun createAquarium() {
        val capacityAsInt = getUserInput(
            { printSection("Qual o tamanho do aquário?\n${Capacity.options()}")},
            {it.toIntOrNull() in 1..3}
        ).toInt()

        aquariumList.add(Aquarium(Capacity.toCapacity(capacityAsInt), cleaningFactor))
        printSection("Aquário criado")
    }

    /**
     * Shows all aquariums inside aquariumList.
     */
    private fun showAllAquariums() {
        printSection(aquariumList.asString())
    }

    /**
     * Adds a fish to a selected aquarium if selected aquarium is not null.
     */
    private fun addFish() {
        val a = selectAquarium()
        var result = "Sem aquários listados."

        a?.let { aquarium ->
            val name = getUserInput(
                prompt = { printSection("Digite o nome do peixe:")},
                isInputValid = {it.isNotEmpty() && it.isNotBlank() && it.length >= 3},
                "O nome deve ter 3 ou mais caracteres e não pode ser vazio ou branco."
            )

            val colorAsInt = getUserInput(
                prompt = { printSection("""
                |Selecione uma das cores disponíveis:
                |${Color.options()}""".trimMargin())},
                isInputValid = {it.toIntOrNull() in 1..7},
            ).toInt()

            val sizeAsInt = getUserInput(
                prompt = { printSection("""
                |Selecione um dos tamanhos disponíveis:
                |${Size.options()}""".trimMargin())},
                isInputValid = {it.toIntOrNull() in 1..3},
            ).toInt()

            result = aquarium.addFish(name, Color.toColor(colorAsInt), Size.toSize(sizeAsInt))
        }

        printSection(result)
    }

    /**
     * Feeds fish if selected aquarium is not null.
     */
    private fun feedFish() {
        val a = selectAquarium()
        var result = "Sem aquários listados."

        a?.let {
            result = it.feedFish()
        }

        printSection(result)
    }

    /**
     * Cleans a selected aquarium if selected aquarium is not null.
     */
    private fun cleanAquarium() {
        val a = selectAquarium()
        var result = "Sem aquários listados."

        a?.let {
            a.clean()
            result = "Aquário limpado com sucesso."
        }

        printSection(result)
    }

    /**
     * Updates the cleaning factor which is used on aquarium's creation. The new cleaning factor is only
     * applicable to new aquariums created after update.
     */
    private fun updateCleaningFactor() {
        cleaningFactor = getUserInput(
            { printSection("Digite o novo valor para o fator de limpeza:")},
            {if (it.toIntOrNull() == null) false else it.toInt() > 0},
            "O fator de limpeza deve ser um número maior que zero."
        ).toInt()
        printSection("Fator de limpeza atualizado.")
    }

    /**
     * Updates the capacity of an aquarium. If aquarium is already at max capacity it doesn't upgrade.
     */
    private fun upgradeAquariumCapacity() {
        val a = selectAquarium()
        var result = "Sem aquários listados."

        a?.let {
            result = a.upgradeCapacity()
        }

        printSection(result)
    }

    /**
     * Selects an Aquarium and returns it. If aquariumList is empty it returns null.
     */
    private fun selectAquarium(): Aquarium? {
        var result: Aquarium? = null

        if (aquariumList.isNotEmpty()) {
            val selected = getUserInput(
                {printSection("Selecione um aquário:\n${aquariumList.asString()}")},
                {it.toIntOrNull() in 1..aquariumList.size}
            ).toInt() - 1

            result = aquariumList[selected]
        }

        return result
    }

}

/**
 * Represents a MutableList<Aquarium> as a String.
 */
private fun MutableList<Aquarium>.asString(): String {
    var result = if (this.size == 0) "Sem aquários listados." else ""

    for (i in 0 until this.size - 1) {
        val a = this[i]
        result += "${i + 1} - $a\n"
    }

    result += "${this.size} - ${this[this.size - 1]}"

    return result
}
