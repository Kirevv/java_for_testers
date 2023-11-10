package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculatePerimeter() {
        var p = new Triangle(3.0, 4.0, 5.0);
        double result = p.perimeter();
        Assertions.assertEquals(12, result);
    }

    @Test
    void canCalculateArea() {
        var s = new Triangle(3.0, 4.0, 5.0);
        double result = s.area();
        Assertions.assertEquals(6, result);
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-5.0, 4.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void cannotCreateTriangleWithTwoSidesSumLessThenThirdSide() {
        try {
            new Triangle(1, 10, 100);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }
}
