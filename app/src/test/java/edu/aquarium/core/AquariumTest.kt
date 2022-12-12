package edu.aquarium.core

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import java.lang.IllegalArgumentException

class AquariumTest {
    private var a: Aquarium = Aquarium(Capacity.SMALL, 2)

    @Before
    fun setUp() {
        a = Aquarium(Capacity.SMALL, 2)
    }

    @Test
    fun `raises exception when cleaningFactor is negative`() {
        assertThrows(IllegalArgumentException::class.java) {
            a = Aquarium(Capacity.SMALL, -3)
        }
    }

    @Test
    fun testIsClean() {
        val dirtyResponse = "Não é possível adicionar peixes em um aquário sujo. Limpe o aquário e tente novamente."
        assertTrue(a.isClean)
        a.addFish("Jotinha", Color.PURPLE, Size.MEDIUM)
        a.addFish("Mamaya", Color.BLUE, Size.SMALL)
        assertEquals(dirtyResponse, a.addFish("Miau", Color.BLACK, Size.LARGE))
        assertFalse(a.isClean)
        a.clean()
        assertTrue(a.isClean)
        a.addFish("Miau", Color.BLACK, Size.LARGE)
        a.addFish("BLUBLU", Color.WHITE, Size.SMALL)
        assertFalse(a.isClean)
        assertEquals(dirtyResponse, a.addFish("abc", Color.BLACK, Size.LARGE))
        a.clean()
        assertEquals("Peixe adicionado com sucesso", a.addFish("abc", Color.BLACK, Size.LARGE))
        assertEquals("O aquário está cheio", a.addFish("abc", Color.BLACK, Size.LARGE))
    }

    @Test
    fun testAddFish() {
        assertEquals("Limpo | 0/5", a.toString())
        a.addFish("Jotinha", Color.PURPLE, Size.MEDIUM)
        a.addFish("Mamaya", Color.BLUE, Size.SMALL)
        assertEquals("Sujo | 2/5", a.toString())
    }

    @Test
    fun testFeedFish() {
        assertEquals("Não há peixes nesse aquário.", a.feedFish())
        a.addFish("Jotinha", Color.PURPLE, Size.MEDIUM)
        a.addFish("Mamaya", Color.BLUE, Size.SMALL)

        val results = mutableSetOf<String>()
        val expected = setOf("Todos os peixes se alimentaram", "Nenhum peixe se alimentou", "Alguns peixes se alimentaram")

        while(results.size < 3)
            results.add(a.feedFish())

        assertEquals(expected, results.intersect(expected))
    }

    @Test
    fun testToString() {
        assertEquals("Limpo | 0/5", a.toString())
        a.addFish("Jotinha", Color.PURPLE, Size.MEDIUM)
        a.addFish("Mamaya", Color.BLUE, Size.SMALL)
        assertEquals("Sujo | 2/5", a.toString())
        a.clean()
        assertEquals("Limpo | 2/5", a.toString())
    }

    @Test
    fun testUpgradeCapacity() {
        assertEquals(Capacity.SMALL.max(), a.toString().split("/")[1].toInt())
        a.upgradeCapacity()
        assertEquals(Capacity.MEDIUM.max(), a.toString().split("/")[1].toInt())
        a.upgradeCapacity()
        assertEquals(Capacity.LARGE.max(), a.toString().split("/")[1].toInt())
    }
}