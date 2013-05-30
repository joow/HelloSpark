package org.hello.spark;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class MathsTest {
    public void testIsSquare() {
        assertTrue(Maths.isSquare(1), "1 is a square");
        assertFalse(Maths.isSquare(2), "2 isn't a square");
        assertFalse(Maths.isSquare(3), "3 isn't a square");
        assertTrue(Maths.isSquare(4), "4 is a square");
        assertFalse(Maths.isSquare(5), "5 isn't a square");
    }

    public void testIsCube() {
        assertTrue(Maths.isCube(1), "1 is a cube");
        assertFalse(Maths.isCube(2), "2 isn't a cube");
        assertFalse(Maths.isCube(3), "3 isn't a cube");
        assertFalse(Maths.isCube(4), "4 isn't a cube");
        assertFalse(Maths.isCube(5), "5 isn't a cube");
        assertFalse(Maths.isCube(6), "6 isn't a cube");
        assertFalse(Maths.isCube(7), "7 isn't a cube");
        assertTrue(Maths.isCube(8), "8 is a cube");
        assertFalse(Maths.isCube(9), "9 isn't a cube");
    }
}
