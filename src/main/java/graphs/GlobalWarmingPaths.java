package graphs;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Queue;

/**
 * Author Pierre Schaus
 *
 * Assume the following 5x5 matrix that represent a grid surface:
 * int [][] tab = new int[][] {{1,3,3,1,3},
 *                             {4,2,2,4,5},
 *                             {4,4,5,4,2},
 *                             {1,4,2,3,6},
 *                             {1,1,1,6,3}};
 *
 * Given a global water level, all positions in the matrix
 * with a value <= the water level are flooded and therefore unsafe.
 * So, assuming the water level is 3,
 * all safe points are highlighted between parenthesis:
 *
 *   1 , 3 , 3 , 1 , 3
 *  (4), 2 , 2 ,(4),(5)
 *  (4),(4),(5),(4), 2
 *   1 ,(4), 2 , 3 ,(6)
 *   1 , 1 , 1 ,(6), 3}
 *
 * The method you need to implement is
 * a method that find a safe-path between
 * two positions (row,column) on the matrix.
 * The path assume you only make horizontal or vertical moves
 * but not diagonal moves.
 *
 * For a water level of 4, the shortest path
 * between (1,0) and (1,3) is
 * (1,0) -> (2,0) -> (2,1) -> (2,2) -> (2,3) -> (1,3)
 *
 *
 * Complete the code below so that the {@code  shortestPath} method
 * works as expected
 *
 * différence avec Maze : les murs sont les zones <= waterlevel
 */
public class GlobalWarmingPaths {

    int waterLevel;
    int m;
    int [][] altitude;

    public GlobalWarmingPaths(int[][] altitude, int waterLevel) {
        this.waterLevel = waterLevel;
        this.altitude = altitude;
        this.m = altitude[0].length;
        // TODO
    }


    /**
     * Computes the shortest path between point p1 and p2
     * @param p1 the starting point
     * @param p2 the ending point
     * @return the list of the points starting
     *         from p1 and ending in p2 that corresponds
     *         the shortest path.
     *         If no such path, an empty list.
     */
    public List<Point> shortestPath(Point p1, Point p2) {
        // TODO
        // expected time complexity O(n^2)
        int[][] pos = {{1,0},{0,1},{-1,0},{0,-1}};
        if (p1.equals(p2)) {
            LinkedList<Point> path = new LinkedList<>();
            if (altitude[p1.getX()][p1.getY()] > waterLevel) {
                path.add(p1);
                return path;
            }
            return null;
        }
        int n = altitude.length;


        boolean[] marked = new boolean[n*m];
        int[] edgeTo = new int[n*m]; //< 1D index>
        Queue<Point> queue = new LinkedList<>(); // Point

        queue.add(p1); // <Point>
        marked[index(p1.x,p1.y)] = true; //< 1D index>
        boolean found = false;
        while (!queue.isEmpty() && !found){
            Point current = queue.remove();
            int current_index = index(current.x,current.y);
            for (int i = 0; i < 4; i++) {
                int voisin_x = current.x + pos[i][0];
                int voisin_y = current.y + pos[i][1];
                int index = index(voisin_x,voisin_y);
                if (voisin_x >= 0 && voisin_x < n && voisin_y >= 0 && voisin_y < m &&
                        altitude[voisin_x][voisin_y] > waterLevel && !marked[index]) {

                            Point voisin = new Point(voisin_x,voisin_y);
                            marked[index] = true;
                            queue.add(voisin);
                            edgeTo[index] = current_index;
                            if(voisin_x == p2.x && voisin_y == p2.y){
                                found = true;
                            }




                }

            }

        }
        LinkedList<Point> list = new LinkedList<>();
        int dest_index = index(p2.x,p2.y);
        if(!marked[dest_index]) return list;
        Point dest = p2;
        Point source = p1;
        while (!dest.equals(source)){
            list.add(dest);
            int D_dest = edgeTo[dest_index];
            int x = row(D_dest);
            int y = col(D_dest);
            System.out.println("e");

            dest = new Point(x,y);
            dest_index = index(dest.x, dest.y);
        }
        list.add(source);
        Collections.reverse(list);
        for (Point p:list) {
            System.out.println(p.x + " " + p.y);

        }
         return list;

    }
    // Reconstruit le chemin à partir de edgeTo
    private List<Point> reconstructPath(int[] edgeTo, Point p1, Point p2) {
        LinkedList<Point> path = new LinkedList<>();
        int destIndex = index(p2.x, p2.y);

        // Remonter depuis la destination jusqu'à la source
        while (!p2.equals(p1)) {
            path.addFirst(p2);
            destIndex = edgeTo[destIndex];
            p2 = new Point(row(destIndex), col(destIndex));
        }
        path.addFirst(p1); // Ajouter le point de départ
        return path;
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
     * This class represent a point in a 2-dimension discrete plane.
     * This is used to identify the cells of a grid
     * with X = row, Y = column
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
