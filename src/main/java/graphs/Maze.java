package graphs;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * We are interested in solving a maze represented
 * by a matrix of integers 0-1 of size nxm.
 * This matrix is a two-dimensional array.
 * An entry equal to '1' means that there
 * is a wall and therefore this position is not accessible,
 * while '0' means that the position is free.
 * We ask you to write a Java code to discover
 * the shortest path between two coordinates
 * on this matrix from (x1, y1) to (x2, y2).
 * The moves can only be vertical (up/down) or horizontal (left/right)
 * (not diagonal), one step at a time.
 * The result of the path is an Iterable of
 * coordinates from the origin to the destination.
 * These coordinates are represented by integers
 * between 0 and n * m-1, where an integer 'a'
 * represents the position x =a/m and y=a%m.
 * If the start or end position is a wall
 * or if there is no path, an empty Iterable must be returned.
 * The same applies if there is no path
 * between the origin and the destination.
 */
public class Maze {
    public static Iterable<Integer> shortestPath(int[][] maze, int x1, int y1, int x2, int y2) {
        // TODO
        int start_value = maze[x1][y1];
        int end_value = maze[x2][y2];
        if(start_value == 1 || end_value == 1) return new LinkedList<>();


        int n = maze.length;
        int m = maze[0].length;
        boolean[] marked = new boolean[n*m];
        int[] edgeTo = new int[n*m];

        Queue<Integer> queue = new LinkedList<>();

        int[][] pos = {{1,0},{0,1},{-1,0},{0,-1}};

        int start = ind(x1,y1,m);//m ou n
        int dest = ind(x2,y2,m);
        System.out.println(start);
        queue.add(start);
        marked[start] = true;

        boolean found = false;
        while(!queue.isEmpty() && !found){
            int current = queue.remove(); // 1D
            int current_x = row(current,m); // 2D
            int current_y = col(current,m); // 2D
            for (int i = 0; i < 4; i++) {
                int voisin_x = current_x + pos[i][0];
                int voisin_y = current_y + pos[i][1];

                if (voisin_x < n && voisin_x >= 0 && voisin_y < m && voisin_y >= 0) {

                    int voisin1D = ind(voisin_x,voisin_y,m);

                    if (maze[voisin_x][voisin_y] != 1 && !marked[voisin1D]) {

                        marked[voisin1D] = true;
                        queue.add(voisin1D);
                        edgeTo[voisin1D] = current;
                        if (voisin_x == x2 && voisin_y == y2) {
                            found = true;
                        }
                    }

                }



            }

        }

        LinkedList<Integer> result = new LinkedList<>();
        if(!marked[dest]) return result;
        for (int current = dest; current != start; current = edgeTo[current]) {
            result.addFirst(current);
        }
        result.addFirst(start);

        return result;
    }

    public static int ind(int x, int y, int lg) {
        return x * lg + y;
    }

    public static int row(int pos, int mCols) {
        return pos / mCols;
    }

    public static int col(int pos, int mCols) {
        return pos % mCols;
    }

}
