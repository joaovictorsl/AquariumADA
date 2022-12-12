package edu.aquarium.core

import java.lang.IllegalArgumentException
import kotlin.random.Random

/**
 * Class which represents an Aquarium. It owns a limit and a cleaningFactor
 *
 * @property aquariumCapacity Capacity of fish inside aquarium.
 * @property cleaningFactor Factor used to define if an aquarium is clean or not. Must be over 0.
 * @property isClean true if aquarium is clean, false otherwise.
 */
class Aquarium(aquariumCapacity: Capacity, cleaningFactor: Int) {
    private var aquariumCapacity: Capacity
    private val fishList = mutableListOf<Fish>()
    private val cleaningFactor: Int
    var isClean = true
        private set

    init {
        if (cleaningFactor <= 0)
            throw IllegalArgumentException("Fator de limpeza deve ser maior que zero.")

        this.aquariumCapacity = aquariumCapacity
        this.cleaningFactor = cleaningFactor
    }

    /**
     * Adds fish to the aquarium.
     *
     * @param name Fish's name. Must be over 3 characters and cannot be empty or blank.
     * @param color Fish's color. It's a value from enum class Color.
     * @param size Fish's size. It's a value from enum class Size.
     * @return String representing status after attempt to add fish to the aquarium.
     */
    fun addFish(name: String, color: Color, size: Size): String {
        var result = "O aquário está cheio"

        if (!isFull() && isClean) {
            fishList.add(Fish(name, color, size))
            updateCleanStatus()
            result = "Peixe adicionado com sucesso"
        } else if (!isClean)
            result = "Não é possível adicionar peixes em um aquário sujo. Limpe o aquário e tente novamente."

        return result
    }

    /**
     * Feed the fish in the aquarium.
     * @return String representing status of attempt to feed.
     */
    fun feedFish(): String {
        var result = "Não há peixes nesse aquário."

        if (fishList.size > 0) {
            val fedFish = Random(System.currentTimeMillis()).nextInt(fishList.size + 1)

            result = when (fedFish) {
                fishList.size -> "Todos os peixes se alimentaram"
                0 -> "Nenhum peixe se alimentou"
                else -> "Alguns peixes se alimentaram"
            }
        }

        return result
    }

    /**
     * isClean property is set to true.
     */
    fun clean() {
        isClean = true
    }

    /**
     * Checks if the new fishList size is a multiple of cleaningFactor, if it is not, isClean is set to true.
     */
    private fun updateCleanStatus() {
        isClean = fishList.size % cleaningFactor != 0
    }

    /**
     * Returns true if full, false otherwise.
     */
    private fun isFull(): Boolean {
        return fishList.size == aquariumCapacity.max()
    }

    override fun toString(): String {
        return "${if(isClean) "Limpo" else "Sujo"} | ${fishList.size}/${aquariumCapacity.max()}"
    }

    fun upgradeCapacity(): String {
        var result = "O aquário já está na capacidade máxima."

        if (aquariumCapacity != Capacity.LARGE) {
            aquariumCapacity = Capacity.values()[aquariumCapacity.ordinal + 1]
            result = "Capacidade foi aprimorada. Agora o aquário pode conter ${aquariumCapacity.max()} peixes."
        }

        return result
    }
}