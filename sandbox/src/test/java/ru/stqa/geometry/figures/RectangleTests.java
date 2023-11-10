package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {
    @Test
    void canCalculateArea() {
        var s = new Rectangle(2.0, 8.0);
        double result = s.rectangleArea();
        Assertions.assertEquals(16, s.rectangleArea());
    }
}
