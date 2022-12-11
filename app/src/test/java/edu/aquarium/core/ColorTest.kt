package edu.aquarium.core

import org.junit.Assert.*

import org.junit.Test
import java.lang.IllegalArgumentException

class ColorTest {

    @Test
    fun testToString() {
        assertEquals("Vermelho", Color.RED.toString())
        assertEquals("Amarelo", Color.YELLOW.toString())
        assertEquals("Verde", Color.GREEN.toString())
        assertEquals("Roxo", Color.PURPLE.toString())
        assertEquals("Azul", Color.BLUE.toString())
        assertEquals("Preto", Color.BLACK.toString())
        assertEquals("Branco", Color.WHITE.toString())
    }

    @Test
    fun toColor() {
        assertEquals(Color.RED, Color.toColor(1))
        assertEquals(Color.YELLOW, Color.toColor(2))
        assertEquals(Color.GREEN, Color.toColor(3))
        assertEquals(Color.PURPLE, Color.toColor(4))
        assertEquals(Color.BLUE, Color.toColor(5))
        assertEquals(Color.BLACK, Color.toColor(6))
        assertEquals(Color.WHITE, Color.toColor(7))
        assertThrows(IllegalArgumentException::class.java) {
        Color.toColor(8)
        }
    }

    @Test
    fun options() {
        val expected = """1 - Vermelho
                |2 - Amarelo
                |3 - Verde
                |4 - Roxo
                |5 - Azul
                |6 - Preto
                |7 - Branco""".trimIndent()
        assertEquals(expected, Color.options())
    }
}