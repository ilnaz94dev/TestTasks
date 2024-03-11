package org.example;

import org.example.task1.TreeFinder;
import org.example.task1.TreeNode;
import org.example.task2.PixelCircle;
import org.example.task3.PathFinder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nЗадание 1");
        //checkTask1();
        System.out.println("\nЗадание 2");
        //checkTask2();
        System.out.println("\nЗадание 3");
        checkTask3();
    }

    public static void checkTask1() {
        TreeNode node = new TreeNode(1, List.of(new TreeNode(3), new TreeNode(4)));
        List<TreeNode> list = TreeFinder.findGreater(node, 2);
        System.out.println(list);
    }

    public static void checkTask2() {
        PixelCircle circle = new PixelCircle(1, true);
        List<Point> pointList = circle.create(new Point(8, 8), 10, 100);
        System.out.println("Список кортежей, содержащих координаты точек, принадлежащих окружности");
        System.out.println(pointList);
    }

    public static void checkTask3() {
        int[][] flat = PathFinder.generateFlat(30, 30);
        Point a = new Point(20, 28), b = new Point(10, 20);
        flat[a.getY()][a.getX()] = 1;
        flat[b.getY()][b.getX()] = 1;
        System.out.println("Изначальная плоскость");
        Utils.drawMatrix(flat);

        PathFinder pathFinder = new PathFinder(flat);
        int[][] resultFlat = pathFinder.getResultFlat(a, b);
        System.out.println("Результат");
        Utils.drawMatrix(resultFlat);
    }
}