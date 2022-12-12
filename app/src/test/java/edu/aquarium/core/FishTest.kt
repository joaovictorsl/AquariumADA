package edu.aquarium.core

import org.junit.Assert.*

import org.junit.Test
import java.lang.IllegalArgumentException

class FishTest {

    @Test
    fun testInvalidArguments() {
        assertThrows(IllegalArgumentException::class.java) {
            Fish("", Color.RED, Size.SMALL)
        }
        assertThrows(IllegalArgumentException::class.java) {
            Fish("   ", Color.RED, Size.SMALL)
        }
        assertThrows(IllegalArgumentException::class.java) {
            Fish("a   ", Color.RED, Size.SMALL)
        }
        assertThrows(IllegalArgumentException::class.java) {
            Fish("ab   ", Color.RED, Size.SMALL)
        }
        assertThrows(IllegalArgumentException::class.java) {
            Fish("   bc", Color.RED, Size.SMALL)
        }
    }

    @Test
    fun testToString() {
        val a = Fish("abc   ", Color.RED, Size.SMALL)
        val b = Fish("   abc", Color.PURPLE, Size.LARGE)
        assertEquals("abc (Vermelho | Pequeno)", a.toString())
        assertEquals("abc (Roxo | Grande)", b.toString())
    }
}