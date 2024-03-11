package org.example.task2;

import org.example.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PixelCircleTest {

    @Test
    public void givenParameters_whenCreateCircle_thenFilledEdges() {
        PixelCircle circle = new PixelCircle(1, false);

        int mmX = 10, mmY = 10, mmDiameter = 8, dpiResolution = 100;
        List<Point> pointList = circle.create(new Point(mmX, mmY), mmDiameter, dpiResolution);

        int circleRadius = PixelCircle.mmToPixels(mmDiameter / 2, dpiResolution);
        int centerX = PixelCircle.mmToPixels(mmX, dpiResolution);
        int centerY = PixelCircle.mmToPixels(mmY, dpiResolution);

        Assertions.assertTrue(pointList.contains(new Point(centerX + circleRadius, centerY)));
        Assertions.assertTrue(pointList.contains(new Point(centerX - circleRadius, centerY)));
        Assertions.assertTrue(pointList.contains(new Point(centerX, centerY + circleRadius)));
        Assertions.assertTrue(pointList.contains(new Point(centerX, centerY - circleRadius)));
    }
}
