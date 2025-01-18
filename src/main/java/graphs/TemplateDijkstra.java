import java.util.*;

public class TemplateDijkstra {
    private int[] edgeTo;       // Edge to reach each vertex
    private double[] distTo;    // Distance to each vertex
    private PriorityQueue<VertexDistance> pq;  // Priority queue

    /**
     * Constructor for Dijkstra's algorithm.
     *
     * @param graph The adjacency matrix representing the graph.
     *              graph[u][v] represents the weight of the edge from u to v.
     *              Use Integer.MAX_VALUE for no edge between u and v.
     * @param source The source vertex.
     */
    public TemplateDijkstra(int[][] graph, int source) {
        int n = graph.length; // Number of vertices

        edgeTo = new int[n];
        distTo = new double[n];
        pq = new PriorityQueue<>(Comparator.comparingDouble(vd -> vd.distance));

        // Initialize distances to infinity and edgeTo to -1
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        Arrays.fill(edgeTo, -1);
        distTo[source] = 0.0;

        // Add the source vertex to the queue
        pq.add(new VertexDistance(source, 0.0));

        while (!pq.isEmpty()) {
            VertexDistance current = pq.poll();
            int u = current.vertex;

            // Skip if this entry is outdated
            if (current.distance > distTo[u]) continue;

            // Relax all adjacent vertices
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != Integer.MAX_VALUE) { // Check if there's an edge
                    relax(u, v, graph[u][v]);
                }
            }
        }
    }

    /**
     * Relax an edge from vertex u to vertex v with the given weight.
     */
    private void relax(int u, int v, int weight) {
        if (distTo[v] > distTo[u] + weight) {
            distTo[v] = distTo[u] + weight;
            edgeTo[v] = u;

            // Add the updated distance to the priority queue
            pq.add(new VertexDistance(v, distTo[v]));
        }
    }

    /**
     * Returns the shortest distance to the given vertex.
     */
    public double distTo(int v) {
        return distTo[v];
    }

    /**
     * Checks if there's a path to the given vertex.
     */
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns the shortest path to the given vertex as a list of vertices.
     */
    public List<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        List<Integer> path = new ArrayList<>();
        for (int x = v; x != -1; x = edgeTo[x]) {
            path.add(x);
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Helper class to represent a vertex and its distance in the priority queue.
     */
    private static class VertexDistance {
        int vertex;
        double distance;

        VertexDistance(int vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    // Test the implementation
    public static void main(String[] args) {
        // Example adjacency matrix
        int[][] graph = {
                {0, 10, Integer.MAX_VALUE, 30, 100},
                {Integer.MAX_VALUE, 0, 50, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 10},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 20, 0, 60},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };

        // Source vertex
        int source = 0;

        // Run Dijkstra's algorithm
        TemplateDijkstra dijkstra = new TemplateDijkstra(graph, source);

        // Print the shortest distances and paths
        for (int v = 0; v < graph.length; v++) {
            System.out.println("Distance to vertex " + v + ": " + dijkstra.distTo(v));
            System.out.println("Path: " + dijkstra.pathTo(v));
        }
    }
}
