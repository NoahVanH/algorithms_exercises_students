package graphs;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * In this exercise, we revisit the GlobalWarming
 * class from the sorting package.
 * You are still given a matrix of altitude in
 * parameter of the constructor, with a water level.
 * All the entries whose altitude is under, or equal to,
 * the water level are submerged while the other constitute small islands.
 *
 * For example let us assume that the water
 * level is 3 and the altitude matrix is the following
 *
 *      | 1 | 3 | 3 | 1 | 3 |
 *      | 4 | 2 | 2 | 4 | 5 |
 *      | 4 | 4 | 1 | 4 | 2 |
 *      | 1 | 4 | 2 | 3 | 6 |
 *      | 1 | 1 | 1 | 6 | 3 |
 *
 * If we replace the submerged entries
 * by _, it gives the following matrix
 *
 *      | _ | _ | _ | _ | _ |
 *      | 4 | _ | _ | 4 | 5 |
 *      | 4 | 4 | _ | 4 | _ |
 *      | _ | 4 | _ | _ | 6 |
 *      | _ | _ | _ | 6 | _ |
 *
 * The goal is to implement two methods that
 * can answer the following questions:
 *      1) Are two entries on the same island?
 *      2) How many islands are there
 *
 * Two entries above the water level are
 * connected if they are next to each other on
 * the same row or the same column. They are
 * **not** connected **in diagonal**.
 * Beware that the methods must run in O(1)
 * time complexity, at the cost of a pre-processing in the constructor.
 * To help you, you'll find a `Point` class
 * in the utils package which identified an entry of the grid.
 * Carefully read the expected time complexity of the different methods.
 */
public class GlobalWarming {

    private int [] components;
    private int nbComponent;
    private int [][] altitude;
    private int waterLevel;
    private int m;
    private int n;

    /**
     * Constructor. The run time of this method is expected to be in
     * O(n x m) with n the number of rows and m the number of columns.
     *
     * @param altitude the matrix of altitude
     * @param waterLevel the water level under which the entries are submerged
     */
    public GlobalWarming(int [][] altitude, int waterLevel) {
        // BEGIN STRIP
        this.altitude = altitude;
        this.waterLevel = waterLevel;
        this.n = altitude.length;
        this.m = n;
        this.components = new int[n*n];
        this.nbComponent = 0;
        // At most n*n components
        Arrays.fill(this.components, (n*n)+1);
        for (int row = 0; row < n; row ++) {
            for (int col = 0; col < n; col ++) {
                detectComponent(row, col);
                // If the starting cell has been updated to the number of component,
                // then it is not under water. We need to increment the number of component
                int index = index(row,col);
                if (this.components[index] == this.nbComponent) {
                    this.nbComponent += 1;
                }
            }
        }
        // END STRIP
    }



    /**
     * Returns the number of islands
     *
     * Expected time complexity O(1)
     */
    public int nbIslands() {
        return this.nbComponent;
    }

    /**
     * Return true if p1 is on the same island as p2, false otherwise
     *
     * Expected time complexity: O(1)
     *
     * @param p1 the first point to compare
     * @param p2 the second point to compare
     */
    public boolean onSameIsland(Point p1, Point p2) {
        int index_p1 = index(p1.x,p1.y);
        int index_p2 = index(p2.x,p2.y);


        return components[index_p1] != -1 && components[index_p1] == components[index_p2];
    }

    private void detectComponent(int row, int col) {
        // Utilisation d'une pile pour simuler la récursion
        int index = index(row, col);
        if (row < 0 || row >= n || col < 0 || col >= n || this.components[index] <= n * n)
            return;

        // Si la cellule est sous l'eau, on l'attribue à -1
        if (this.altitude[row][col] <= this.waterLevel) {
            this.components[index] = -1;
            return;
        }

        // Initialisation pour parcourir les voisins
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int componentId = this.nbComponent; // ID actuel de composant
        this.components[index] = componentId;

        // Pile pour simuler la récursion
        LinkedList<Point> stack = new LinkedList<>();
        stack.push(new Point(row,col));

        while (!stack.isEmpty()) {
            Point current = stack.pop();
            int currentRow = current.x;
            int currentCol = current.y;

            for (int[] dir : directions) {
                int neighborRow = currentRow + dir[0];
                int neighborCol = currentCol + dir[1];
                int neighborIndex = index(neighborRow, neighborCol);

                if (neighborRow >= 0 && neighborRow < n && neighborCol >= 0 && neighborCol < n &&
                        this.components[neighborIndex] > n * n && this.altitude[neighborRow][neighborCol] > this.waterLevel) {
                    this.components[neighborIndex] = componentId;
                    stack.push(new Point(neighborRow,neighborCol));
                }
            }
        }
    }

    public int index(int x, int y){
        return x*m+y;
    }
    public int row(int pos) {
        return pos / m;
    }

    public int col(int pos) {
        return pos % m;
    }

    /**
     * This class represents a point in a 2-dimensional discrete plane.
     * This is used, for instance, to identify cells of a grid.
     */
    static class Point {

        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Point) {
                Point p = (Point) o;
                return p.x == this.x && p.y == this.y;
            }
            return false;
        }
    }
}


//import java.util.Arrays;
//
///**
// * In this exercise, we revisit the GlobalWarming
// * class from the sorting package.
// * You are still given a matrix of altitude in
// * parameter of the constructor, with a water level.
// * All the entries whose altitude is under, or equal to,
// * the water level are submerged while the other constitute small islands.
// *
// * For example let us assume that the water
// * level is 3 and the altitude matrix is the following
// *
// *      | 1 | 3 | 3 | 1 | 3 |
// *      | 4 | 2 | 2 | 4 | 5 |
// *      | 4 | 4 | 1 | 4 | 2 |
// *      | 1 | 4 | 2 | 3 | 6 |
// *      | 1 | 1 | 1 | 6 | 3 |
// *
// * If we replace the submerged entries
// * by _, it gives the following matrix
// *
// *      | _ | _ | _ | _ | _ |
// *      | 4 | _ | _ | 4 | 5 |
// *      | 4 | 4 | _ | 4 | _ |
// *      | _ | 4 | _ | _ | 6 |
// *      | _ | _ | _ | 6 | _ |
// *
// * The goal is to implement two methods that
// * can answer the following questions:
// *      1) Are two entries on the same island?
// *      2) How many islands are there
// *
// * Two entries above the water level are
// * connected if they are next to each other on
// * the same row or the same column. They are
// * **not** connected **in diagonal**.
// * Beware that the methods must run in O(1)
// * time complexity, at the cost of a pre-processing in the constructor.
// * To help you, you'll find a Point class
// * in the utils package which identified an entry of the grid.
// * Carefully read the expected time complexity of the different methods.
// */
//public class GlobalWarming {
//
//    private int [] components;
//    private int nbComponent;
//    private int [][] altitude;
//    private int waterLevel;
//    private int m;
//    private int n;
//
//    /**
//     * Constructor. The run time of this method is expected to be in
//     * O(n x m) with n the number of rows and m the number of columns.
//     *
//     * @param altitude the matrix of altitude
//     * @param waterLevel the water level under which the entries are submerged
//     */
//    public GlobalWarming(int [][] altitude, int waterLevel) {
//        // BEGIN STRIP
//        this.altitude = altitude;
//        this.waterLevel = waterLevel;
//        this.n = altitude.length;
//        this.m = n;
//        this.components = new int[n*n];
//        this.nbComponent = 0;
//        // At most n*n components
//        Arrays.fill(this.components, (n*n)+1);
//        for (int row = 0; row < n; row ++) {
//            for (int col = 0; col < n; col ++) {
//                detectComponent(row, col);
//                // If the starting cell has been updated to the number of component,
//                // then it is not under water. We need to increment the number of component
//                int index = index(row,col);
//                if (this.components[index] == this.nbComponent) {
//                    this.nbComponent += 1;
//                }
//            }
//        }
//        // END STRIP
//    }
//
//
//
//    /**
//     * Returns the number of islands
//     *
//     * Expected time complexity O(1)
//     */
//    public int nbIslands() {
//        return this.nbComponent;
//    }
//
//    /**
//     * Return true if p1 is on the same island as p2, false otherwise
//     *
//     * Expected time complexity: O(1)
//     *
//     * @param p1 the first point to compare
//     * @param p2 the second point to compare
//     */
//    public boolean onSameIsland(Point p1, Point p2) {
//        int index_p1 = index(p1.x,p1.y);
//        int index_p2 = index(p2.x,p2.y);
//
//
//        return components[index_p1] != -1 && components[index_p1] == components[index_p2];
//    }
//
//    private void detectComponent(int row, int col) {
//
//        /*
//         * Since this array is initialized with entries
//         * at (n*n)+1 (n*n being the maximum number of
//         * component in an n x n matrix), if an entry
//         * has a component lower than it, it has been visited.
//         * This avoids the need for an array of boolean
//         * to check if a cell has been visited.
//         */
//        int index = index(row,col);
//        if (row < 0 || row >= n || col < 0 || col >= n || this.components[index] <= n*n)
//            return;
//        // Put the entries underwater at component -1
//        if (this.altitude[row][col] <= this.waterLevel) {
//            this.components[index] = -1;
//        } else {
//            // On an island:
//            // Update the component, which is
//            // the current number of component
//            this.components[index] = this.nbComponent;
//            // Rercursively visits the neighbors
//            detectComponent(row+1, col);
//            detectComponent(row-1, col);
//            detectComponent(row, col+1);
//            detectComponent(row, col-1);
//        }
//    }
//    public int index(int x, int y){
//        return x*m+y;
//    }
//    public int row(int pos) {
//        return pos / m;
//    }
//
//    public int col(int pos) {
//        return pos % m;
//    }
//
//    /**
//     * This class represents a point in a 2-dimensional discrete plane.
//     * This is used, for instance, to identify cells of a grid.
//     */
//    static class Point {
//
//        private final int x;
//        private final int y;
//
//        public Point(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        public int getX() {
//            return x;
//        }
//
//        public int getY() {
//            return y;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (o instanceof Point) {
//                Point p = (Point) o;
//                return p.x == this.x && p.y == this.y;
//            }
//            return false;
//        }
//    }
//}