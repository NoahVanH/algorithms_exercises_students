package graphs;

//feel free to import anything here


import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * You just bought yourself the latest game from the Majong™
 * studio (recently acquired by Macrosoft™): MineClimb™.
 * In this 3D game, the map is made up of size 1
 * dimensional cube blocks, aligned on a grid,
 * forming vertical columns of cubes.
 * There are no holes in the columns, so the state
 * of the map can be represented as a matrix M of size n⋅m
 * where the entry M_{i,j} is the height of
 * the cube column located at i,j (0 ≤ i < n, 0 ≤ j < m).
 * You can't delete or add cubes, but you do have
 * climbing gear that allows you to move from one column to another
 * (in the usual four directions (north, south, east, west),
 * but not diagonally). The world of MineClimb™ is round:
 * the position north of (0,j) is (n-1,j), the position
 * west of (i,0) is (i,m-1) , and vice versa.
 * <p>
 * The time taken to climb up or down a column depends on
 * the difference in height between the current column and the next one.
 * Precisely, the time taken to go from column (i,j)
 * to column (k,l) is |M_{i,j}-M_{k,l}|
 * <p>
 * Given the map of the mine, an initial position
 * and an end position, what is the minimum time needed
 * to reach the end position from the initial position?
 */
public class MineClimbing {

    static int[]distTo;


    /**
     * Returns the minimum distance between (startX, startY) and (endX, endY), knowing that
     * you can climb from one mine block (a,b) to the other (c,d) with a cost Math.abs(map[a][b] - map[c][d])
     * <p>
     * Do not forget that the world is round: the position (map.length,i) is the same as (0, i), etc.
     * <p>
     * map is a matrix of size n times m, with n,m > 0.
     * <p>
     * 0 <= startX, endX < n
     * 0 <= startY, endY < m
     */
    public static int best_distance(int[][] map, int startX, int startY, int endX, int endY) {
        // TODO
        int[][] pos = {{1,0},{0,1},{-1,0},{0,-1}};

        int n = map.length;
        int m = map[0].length;
        if (n == 1 && m == 1) return 0;

        int index_start = index(startX,startY,m);
        int index_end = index(endX,endY,m);


        distTo = new int[n*m];
        PriorityQueue<Point> pq = new PriorityQueue<>(); // on stock quoi ? Integer ou strucure.dist et structure.index

        //edge case, start = end
        if(index_start == index_end){
            return min_dist(startX,startY,endX,endY,map);
        }
        Arrays.fill(distTo,1000000);
        distTo[index_start] = 0;
        pq.add(new Point(index_start,0));


        while (!pq.isEmpty() ){
            Point currentPoint = pq.poll();
            int currentDistance = currentPoint.distance;
            int currentIndex = currentPoint.index;
            int currentX = Row(currentIndex,m);
            int currentY = Col(currentIndex,m);
            if(currentDistance > distTo[currentIndex]) continue;
            //System.out.println(currentX +" " + currentY);
            for (int v = 0; v < 4; v++) {

                int neix = (currentX + pos[v][0] + n)% n;
                int neiy = (currentY + pos[v][1] + m)%m;
                int neiindex = index(neix,neiy,m);


                //System.out.println("(" + neix +" , "+ neiy+")");
                int weight = min_dist(currentX,currentY,neix,neiy,map);
                if(distTo[neiindex] > distTo[currentIndex] + weight){
                    distTo[neiindex] = distTo[currentIndex] + weight;
                    pq.add(new Point(neiindex,distTo[neiindex]));

                }


            }


        }
        //System.out.println(Arrays.toString(distTo));

        if(distTo[index_end] == 1000000){
            System.out.println("end not reached");
            return -1;
        }


         return distTo[index_end];
    }

    public static int index(int x, int y,int m){
        return x*m+y;

    }
    public static int Col(int index, int nbCol){
        return index % nbCol;
    }
    public static int Row(int index, int nbCol){
        return index / nbCol;
    }
    public static int min_dist(int a, int b, int c, int d,int[][] map){
        return Math.abs(map[a][b] - map[c][d]);
    }
    public static class Point implements Comparable<Point> {
        int index;
        int distance;
        Point(int index, int distance){
            this.index = index;
            this.distance = distance;
        }
        @Override
        public boolean equals(Object o) {
            if (o instanceof Point) {
                Point p = (Point) o;
                return p.index == this.index && p.distance == this.distance;
            }
            return false;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.distance,o.distance);
        }
    }
}
