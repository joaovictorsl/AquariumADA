package edu.aquarium.core

import org.junit.Assert.*

import org.junit.Test
import java.lang.IllegalArgumentException

class CapacityTest {

    @Test
    fun testMax() {
        assertEquals(5, Capacity.SMALL.max())
        assertEquals(10, Capacity.MEDIUM.max())
        assertEquals(15, Capacity.LARGE.max())
    }

    @Test
    fun testToString() {
        assertEquals("Pequeno", Capacity.SMALL.toString())
        assertEquals("Médio", Capacity.MEDIUM.toString())
        assertEquals("Grande", Capacity.LARGE.toString())
    }

    @Test
    fun testOptions() {
        assertEquals("""
            1 - Pequeno
            2 - Médio
            3 - Grande
        """.trimIndent(), Capacity.options())
    }

    @Test
    fun testToCapacity() {
        assertEquals(Capacity.SMALL, Capacity.toCapacity(1))
        assertEquals(Capacity.MEDIUM, Capacity.toCapacity(2))
        assertEquals(Capacity.LARGE, Capacity.toCapacity(3))
        assertThrows(IllegalArgumentException::class.java) {
            Capacity.toCapacity(4)
        }
    }
}