package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {
    @Test
    void canCalculateArea() {
        Assertions.assertEquals(16, Rectangle.rectangleArea(2, 8));
    }
}
