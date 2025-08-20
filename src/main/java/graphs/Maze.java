package graphs;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

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
        if(maze[x1][y1] == 1 || maze[x2][y2] == 1)return new LinkedList<>();

        final int[][] pos = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};

        LinkedList<Integer> queue = new LinkedList<>();
        int sizeX = maze.length;
        int sizeY = maze[0].length;
        int nb_node = sizeX*sizeY;

        boolean[] marked = new boolean[nb_node]; // retenir qui on a add
        int[] edge_to = new int[nb_node]; // retenir le path

        int start = ind(x1,y1,sizeX);
        int end = ind(x2,y2,sizeY);
        marked[start] = true;
        queue.add(start);


        while (!queue.isEmpty()){
            int current = queue.remove();
            int currX = row(current,sizeY);
            int currY = col(current,sizeY);
            for (int i = 0; i < 4; i++) {
                int x = pos[i][0];
                int y = pos[i][1];

                int voisinX = currX + x;
                int voisinY = currY + y;


                if((0 <= voisinX && voisinX < sizeX) && (0 <= voisinY && voisinY < sizeY) && (maze[voisinX][voisinY] != 1)){
                    int voisinId = ind(voisinX,voisinY,sizeY);
                    if(!marked[voisinId]){
                        marked[voisinId] = true;
                        queue.add(voisinId);
                        edge_to[voisinId] = current;

                    }


                }

            }
        }

        LinkedList<Integer> list = new LinkedList<>();
        if(!marked[end]) return list;
        while(start!=end){
            list.add(end);
            end = edge_to[end];

        }
        list.add(end);


        Collections.reverse(list);
        System.out.println(list);
        // TODO
        return list;
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