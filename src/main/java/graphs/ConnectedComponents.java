package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * You are asked to implement the ConnectedComponent class given a graph.
 */
public class ConnectedComponents {

    /**
     * @return the number of connected components in g
     */
    public static int numberOfConnectedComponents(Graph g) {
        // Tableau pour marquer les sommets visités
        boolean[] visited = new boolean[g.V()];
        int count = 0;

        // Parcourir tous les sommets
        for (int v = 0; v < g.V(); v++) {
            // Si le sommet n'a pas été visité, cela signifie qu'on a trouvé un nouveau composant
            if (!visited[v]) {
                // Démarrer une recherche DFS à partir de ce sommet
                dfs(g, v, visited);
                // Incrémenter le compteur de composants connexes
                count++;
            }
        }
        return count;
    }

    // Fonction DFS pour explorer les sommets connectés à partir du sommet 'v'
    private static void dfs(Graph g, int v, boolean[] visited) {
        // Marquer le sommet comme visité
        visited[v] = true;

        // Parcourir tous les voisins du sommet 'v'
        for (int w : g.adj(v)) {
            // Si le voisin n'a pas encore été visité, effectuer une recherche DFS à partir de ce sommet
            if (!visited[w]) {
                dfs(g, w, visited);
            }
        }
    }

    public static class Graph {
        private List<Integer>[] edges; // Liste des arêtes du graphe

        public Graph(int nbNodes) {
            this.edges = (ArrayList<Integer>[]) new ArrayList[nbNodes];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new ArrayList<>();
            }
        }

        /**
         * @return the number of vertices
         */
        public int V() {
            return edges.length;
        }

        /**
         * @return the number of edges
         */
        public int E() {
            int count = 0;
            for (List<Integer> bag : edges) {
                count += bag.size();
            }
            return count / 2; // Parce que le graphe est non orienté
        }

        /**
         * Add edge v-w to this graph
         */
        public void addEdge(int v, int w) {
            edges[v].add(w);
            edges[w].add(v);
        }

        /**
         * @return the vertices adjacent to v
         */
        public Iterable<Integer> adj(int v) {
            return edges[v];
        }
    }

    public static void main(String[] args) {
        // Exemple d'utilisation
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(3, 4);

        System.out.println("Number of connected components: " + numberOfConnectedComponents(g));
    }
}
