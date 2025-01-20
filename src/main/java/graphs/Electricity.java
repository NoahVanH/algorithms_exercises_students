package graphs;

import java.util.*;

/**
 * Author: Alexis Englebert
 * Context: You are operating a power plant in the new city of Louvain-La-Neuve,
 * but lack plans for the city's electrical network.
 * Your goal is to minimize the cost of electrical wires ensuring the city is connected with just one wire.
 *
 * The method 'minimumSpanningTreeCost' is designed to find the minimum cost to connect all cities in a given electrical network.
 * The network is represented as a graph where the nodes are the buildings, the edges are the possible connections
 * and their associated cost.
 *
 * Example:
 * Given a network with three buildings (nodes) and the cost of wires (edges) between them:
 * 0 - 1 (5), 1 - 2 (10), 0 - 2 (20)
 * The minimum cost to connect all the buildings is 15 (5 + 10).
 *
 * Note: The method assumes that the input graph is connected and the input is valid.
 */
public class Electricity {

    /**
     * @param n       The number of buildings (nodes) in the network.
     * @param edges   A 2D array where each row represents an edge in the form [building1, building2, cost].
     *                The edges are undirected so (building2, building1, cost) is equivalent to (building1, building2, cost).
     * @return       The minimum cost to connect all cities.
     */
    public static int minimumSpanningCost(int n, int[][] edges) {
        // Construire le graphe sous forme de liste d'adjacence
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());

        }
        for (int[] edge:edges) {
            graph.get(edge[0]).add(new int[]{edge[1],edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0],edge[2]});

        }

            

        // Priority Queue pour extraire le nœud avec le coût minimal
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        boolean[] visited = new boolean[n];
        int totalCost = 0;
        int nodesConnected = 0;

        // Commencer avec le bâtiment 0
        pq.add(new int[]{0,0});
 

        while (!pq.isEmpty() && nodesConnected < n) {
            int[] current = pq.poll();
            int node = current[0];
            int cost = current[1];
            if(!visited[node]){
                visited[node] = true;
                totalCost+=cost;
                nodesConnected++;
            }
            for (int[] neigh:graph.get(node)) {
                if(!visited[neigh[0]]){
                    pq.add(new int[]{neigh[0],neigh[1]});
                }

            }
            
        }

        return nodesConnected == n ? totalCost : -1;
    }


    public static class Node{
        int b1;
        int b2;
        int cost;
        Node(int b1,int b2, int cost){
            this.b1 = b1;
            this.b2 = b2;
            this.cost = cost;
        }
    }

}
