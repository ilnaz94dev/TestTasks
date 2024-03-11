package org.example.task3;

import lombok.NonNull;
import org.example.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PathFinder {
    @NonNull
    private final int[][] flat;
    private final int height;
    private final int width;
    private final boolean allowDiagonal;

    public PathFinder(int[][] flat, boolean allowDiagonal) {
        this.flat = flat;
        this.height = flat.length;
        this.width = flat[0].length;
        this.allowDiagonal = allowDiagonal;
    }

    public PathFinder(int[][] flat) {
        this(flat, true);
    }

    public int[][] getResultFlat(Point a, Point b) {
        List<Point> path = findPath(a, b);
        int[][] resultFlat = getCopyOfFlat(flat);

        if (!path.contains(b)) {
            System.out.println("\nНе удалось проложить путь");
            return resultFlat;
        }

        // Заполненение незанятых точек
        for (Point point : path) {
            if (resultFlat[point.getY()][point.getX()] == 0) {
                resultFlat[point.getY()][point.getX()] = 2;
            }
        }
        return resultFlat;
    }

    public int[][] getCopyOfFlat(int[][] flat) {
        int[][] copyFlat = new int[height][width];
        for (int i = 0; i < height; i++) {
            copyFlat[i] = Arrays.copyOf(flat[i], width);
        }
        return copyFlat;
    }

    public List<Point> findPath(Point a, Point b) {
        int[][] validPoints = getValidPointsFlat(a, b);
        return findClosestPath(validPoints, a, b);
    }
    
    /**
     * Построение плоскости валидных точек на основе исходной плоскости:
     * 0 - незанятые доступные точки
     * 1 - занятые точки (несвязанные)
     * 2 - занятые точки, связанные с a или b
     * 3 - незанятые точки, граничащие с несвязанными занятыми точками
     */
    public int[][] getValidPointsFlat(Point a, Point b) {
        int[][] validPoints = getCopyOfFlat(flat);

        fillValidPointsRecursive(validPoints, a);
        if (validPoints[b.getY()][b.getX()] != 2) { // Если a и b не связаны
            fillValidPointsRecursive(validPoints, b);
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (validPoints[y][x] != 0) {
                    continue;
                }
                List<Point> avail = getAvailablePoints(new Point(x, y)).stream()
                        .filter(p -> isBetween(p.getY(), 0, height) && isBetween(p.getX(), 0, width))
                        .collect(Collectors.toList());
                boolean hasOccupied = avail.stream().anyMatch(p -> validPoints[p.getY()][p.getX()] == 1);
                if (hasOccupied) {
                    validPoints[y][x] = 3;
                }
            }
        }
        return validPoints;
    }

    public void fillValidPointsRecursive(int[][] validFlat, Point current) {
        List<Point> availPoints = getAvailablePoints(current).stream()
                .filter(p -> isBetween(p.getY(), 0, height) && isBetween(p.getX(), 0, width))
                .filter(p -> validFlat[p.getY()][p.getX()] != 2) // исключать предыдущее заполненное значение
                .filter(p -> flat[p.getY()][p.getX()] == 1)
                .collect(Collectors.toList());

        validFlat[current.getY()][current.getX()] = 2;
        for (Point point : availPoints) {
            fillValidPointsRecursive(validFlat, point);
        }
    }

    public List<Point> findClosestPath(int[][] flat, Point a, Point b) {
        Point current = new Point(a.getX(), a.getY());
        List<Point> path = new ArrayList<>();
        path.add(current); // Помечаем начальную точку
        while (!current.equals(b)) {
            // Находим доступные для передвижения точки (исключаем выход за границы и занятые точки)
            List<Point> availPoints = getAvailablePoints(current).stream()
                    .filter(p -> isBetween(p.getY(), 0, height) && isBetween(p.getX(), 0, width))
                    .filter(p -> flat[p.getY()][p.getX()] == 0 || flat[p.getY()][p.getX()] == 2)
                    .filter(p -> !path.contains(p))
                    .collect(Collectors.toList());

            // Проходим по доступным точкам в поисках ближайшей к точке назначения
            double minDistance = Double.MAX_VALUE;
            Point closestPoint = null;
            for (Point point : availPoints) {
                double distance = point.distanceTo(b);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPoint = point;
                }
            }
            if (closestPoint == null) {
                // Если вдруг получилось так, что точка окружена занятыми точками
                break;
            }
            current = closestPoint;
            path.add(current);
        }
        return path;
    }

    public List<Point> getAvailablePoints(Point currentPoint) {
        int[] directions = {-1, 0, 1};
        List<Point> list = new ArrayList<>();
        for (int dX : directions) {
            for (int dY : directions) {
                if (dX == 0 && dY == 0 || !allowDiagonal && Math.abs(dX + dY) != 1) {
                    continue;
                }
                list.add(new Point(currentPoint.getX() + dX, currentPoint.getY() + dY));
            }
        }
        return list;
    }

    public static boolean isBetween(int a, int min, int max) {
        return a >= min && a < max;
    }

    public static int[][] generateFlat(int width, int height) {
        int[][] flat = new int[height][width];
        Random random = new Random();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Вероятность получения незанятой точки в 2 раза больше
                flat[y][x] = (random.nextInt(3) > 0) ? 0 : 1;
            }
        }
        return flat;
    }
}
