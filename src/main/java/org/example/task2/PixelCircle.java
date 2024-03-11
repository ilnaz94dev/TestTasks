package org.example.task2;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.Point;
import org.example.Utils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class PixelCircle {
    private int lineWidth = 1;
    private boolean drawResult = true;

    public List<Point> create(Point mmCenter, int mmDiameter, int dpiResolution) {
        // Переводим всё в пиксели для дальнешей работы
        Point center = new Point();
        center.setX(mmToPixels(mmCenter.getX(), dpiResolution));
        center.setY(mmToPixels(mmCenter.getY(), dpiResolution));

        int canvasWidth = center.getX() * 2;
        int canvasHeight = center.getX() * 2;

        int circleRadius = mmToPixels(mmDiameter / 2, dpiResolution);

        // Допустимая погрешность расстояния для установки пикселей
        double permissibleError = (double) circleRadius * lineWidth / center.distanceTo(new Point(0, 0));

        // Собираем массив пикселей и список точек
        int[][] matrix = new int[canvasHeight][canvasWidth];
        List<Point> pointList = new ArrayList<>();
        for (int y = 0; y < canvasHeight; y++) {
            for (int x = 0; x < canvasWidth; x++) {
                double distance = center.distanceTo(new Point(x, y));
                if (Math.abs(distance - circleRadius) <= permissibleError) {
                    matrix[y][x] = 1;
                    pointList.add(new Point(x, y));
                } else {
                    matrix[y][x] = 0;
                }
            }
        }
        if (drawResult) {
            Utils.drawMatrix(matrix, "x");
        }
        return pointList;
    }

    public static int mmToPixels(int length, int dpiResolution)  {
        return Math.round(length * dpiResolution / 25.4F);
    }
}
