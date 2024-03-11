package org.example.task3;

import org.example.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PathFinderTest {
    @Test
    public void givenFlat_whenFindPath_thenCheckOccupiedPointsPath() {
        int[][] flat = new int[5][10];
        flat[0] = new int[]{0, 0, 1, 0, 1, 0, 1, 0, 0, 0};
        flat[1] = new int[]{1, 0, 1, 1, 1, 0, 1, 0, 0, 0};
        flat[2] = new int[]{0, 0, 0, 0, 1, 0, 0, 1, 0, 0};
        flat[3] = new int[]{0, 0, 1, 0, 0, 1, 0, 1, 0, 0};
        flat[4] = new int[]{0, 0, 0, 1, 0, 0, 0, 0, 1, 1};

        PathFinder pathFinder = new PathFinder(flat);
        int[][] resultFlat = pathFinder.getResultFlat(new Point(0, 1), new Point(5, 3));

        int[][] expectedFlat = new int[5][10];
        expectedFlat[0] = new int[]{0, 0, 1, 0, 1, 0, 1, 0, 0, 0};
        expectedFlat[1] = new int[]{1, 2, 1, 1, 1, 0, 1, 0, 0, 0};
        expectedFlat[2] = new int[]{0, 0, 0, 0, 1, 0, 0, 1, 0, 0};
        expectedFlat[3] = new int[]{0, 0, 1, 0, 0, 1, 0, 1, 0, 0};
        expectedFlat[4] = new int[]{0, 0, 0, 1, 0, 0, 0, 0, 1, 1};

        for (int i = 0; i < 5; i++) {
            Assertions.assertArrayEquals(expectedFlat[i], resultFlat[i]);
        }
    }

    @Test
    public void givenFlat_whenFindPath_thenCheckUnoccupiedPointsPath() {
        int[][] flat = new int[3][10];
        flat[0] = new int[]{0, 0, 1, 0, 0, 0, 0, 0, 1, 0};
        flat[1] = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        flat[2] = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 0, 0};

        PathFinder pathFinder = new PathFinder(flat);
        int[][] resultFlat = pathFinder.getResultFlat(new Point(0, 1), new Point(9, 1));

        int[][] expectedFlat = new int[3][10];
        expectedFlat[0] = new int[]{0, 0, 1, 0, 0, 2, 2, 2, 1, 0};
        expectedFlat[1] = new int[]{1, 0, 0, 0, 2, 0, 0, 0, 0, 1};
        expectedFlat[2] = new int[]{0, 2, 2, 2, 0, 0, 1, 1, 0, 0};

        for (int i = 0; i < 3; i++) {
            Assertions.assertArrayEquals(expectedFlat[i], resultFlat[i]);
        }
    }

    @Test
    public void givenFlat_whenFindPath_thenCheckWithExpected() {
        int[][] flat = new int[10][10];
        flat[0] = new int[]{0, 0, 1, 0, 1, 0, 1, 0, 0, 0};
        flat[1] = new int[]{0, 0, 0, 0, 1, 0, 1, 0, 0, 0};
        flat[2] = new int[]{0, 0, 1, 0, 1, 0, 0, 0, 0, 0};
        flat[3] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        flat[4] = new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
        flat[5] = new int[]{0, 1, 0, 0, 0, 1, 1, 0, 0, 0};
        flat[6] = new int[]{0, 0, 1, 0, 0, 0, 1, 0, 1, 0};
        flat[7] = new int[]{0, 1, 1, 0, 1, 0, 1, 0, 0, 0};
        flat[8] = new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
        flat[9] = new int[]{0, 0, 1, 0, 0, 0, 1, 0, 0, 0};

        PathFinder pathFinder = new PathFinder(flat);
        int[][] resultFlat = pathFinder.getResultFlat(new Point(2, 2), new Point(6, 8));

        int[][] expectedFlat = new int[10][10];
        expectedFlat[0] = new int[]{0, 0, 1, 0, 1, 0, 1, 0, 0, 0};
        expectedFlat[1] = new int[]{0, 0, 0, 0, 1, 0, 1, 0, 0, 0};
        expectedFlat[2] = new int[]{0, 0, 1, 0, 1, 0, 0, 0, 0, 0};
        expectedFlat[3] = new int[]{0, 0, 2, 0, 0, 0, 0, 0, 0, 0};
        expectedFlat[4] = new int[]{0, 0, 0, 2, 0, 0, 1, 0, 0, 0};
        expectedFlat[5] = new int[]{0, 1, 0, 0, 2, 1, 1, 0, 0, 0};
        expectedFlat[6] = new int[]{0, 0, 1, 0, 0, 0, 1, 0, 1, 0};
        expectedFlat[7] = new int[]{0, 1, 1, 0, 1, 0, 1, 0, 0, 0};
        expectedFlat[8] = new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
        expectedFlat[9] = new int[]{0, 0, 1, 0, 0, 0, 1, 0, 0, 0};

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedFlat[i], resultFlat[i]);
        }
    }
}
