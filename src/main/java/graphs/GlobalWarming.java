package graphs;
import java.util.*;

public class GlobalWarming {
    private final Map<Point, Integer> map;
    private final Set<Point> set;
    private final int[][] altitude;
    private int islandId;

    public GlobalWarming(int[][] altitude, int waterLevel) {
        this.altitude = altitude;
        this.map = new HashMap<>();
        this.set = new HashSet<>();
        this.islandId = 0;

        // Ajouter tous les points non submergés à `set`
        for (int i = 0; i < altitude.length; i++) {
            for (int j = 0; j < altitude[0].length; j++) {
                if (altitude[i][j] > waterLevel) {
                    set.add(new Point(i, j));
                }
            }
        }

        // Trouver les îles
        for (Point p : set) {
            if (!map.containsKey(p)) {
                search(p, islandId);
                islandId++;
            }
        }
    }

    private void search(Point start, int islandId) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        map.put(start, islandId);

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (int[] dir : directions) {
                int nextX = current.x + dir[0];
                int nextY = current.y + dir[1];
                Point nextPoint = new Point(nextX, nextY);

                // Vérifier les limites, si le point est non submergé et non marqué
                if (nextX >= 0 && nextX < altitude.length &&
                        nextY >= 0 && nextY < altitude[0].length &&
                        set.contains(nextPoint) &&
                        !map.containsKey(nextPoint)) {
                    map.put(nextPoint, islandId);
                    queue.add(nextPoint);
                }
            }
        }
    }

    public int nbIslands() {
        return islandId;
    }

    public boolean onSameIsland(Point p1, Point p2) {
        if (!map.containsKey(p1) || !map.containsKey(p2)) {
            return false;
        }
        return Objects.equals(map.get(p1), map.get(p2));
    }

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

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
