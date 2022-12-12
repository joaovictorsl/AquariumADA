package edu.aquarium.core

import org.junit.Assert.*

import org.junit.Test
import java.lang.IllegalArgumentException

class SizeTest {

    @Test
    fun testToString() {
        assertEquals("Pequeno", Size.SMALL.toString())
        assertEquals("Médio", Size.MEDIUM.toString())
        assertEquals("Grande", Size.LARGE.toString())
    }

    @Test
    fun testToSize() {
        assertEquals(Size.SMALL, Size.toSize(1))
        assertEquals(Size.MEDIUM, Size.toSize(2))
        assertEquals(Size.LARGE, Size.toSize(3))
        assertThrows(IllegalArgumentException::class.java) {
            Size.toSize(4)
        }
    }

    @Test
    fun testOptions() {
        val expected = """1 - Pequeno
                |2 - Médio
                |3 - Grande
            """.trimMargin()
        assertEquals(expected, Size.options())
    }
}