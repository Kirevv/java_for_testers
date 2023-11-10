package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(6, Triangle.trianglePerimeter(1, 2, 3));
    }

    @Test
    void canCalculateArea() {
        Assertions.assertEquals(6, Triangle.triangleArea(3, 4, 5));
    }
}
