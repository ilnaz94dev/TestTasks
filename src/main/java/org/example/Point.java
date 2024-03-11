package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    private int x;
    private int y;

    public double distanceTo(Point point) {
        int width = Math.abs(this.getX() - point.getX());
        int height = Math.abs(this.getY() - point.getY());
        return Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
    }

    @Override
    public String toString() {
        return String.format("(%s:%s)", x , y);
    }
}
