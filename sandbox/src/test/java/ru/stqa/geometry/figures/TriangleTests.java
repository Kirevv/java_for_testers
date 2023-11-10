package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculatePerimeter() {
        var p = new Triangle(3.0, 4.0, 5.0);
        double result = p.perimeter();
        Assertions.assertEquals(12, p.perimeter());
    }

    @Test
    void canCalculateArea() {
        var s = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(6, s.area());
    }
}
