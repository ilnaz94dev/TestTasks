package org.example;

public class Utils {

    public static void drawMatrix(int[][] matrix, String replaceStr)  {
        for (int[] row : matrix) {
            for (int value : row) {
                String str = String.valueOf(value);
                if (replaceStr != null) {
                    str = str.equals("1") ? replaceStr : " ";
                }
                System.out.print(" " + str + " ");
            }
            System.out.println();
        }
    }

    public static void drawMatrix(int[][] matrix) {
        drawMatrix(matrix, null);
    }
}
