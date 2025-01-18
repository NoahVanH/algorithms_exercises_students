package graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Let's consider a forest represented as a 2D grid.
 * Each cell of the grid can be in one of three states:
 *
 * 0 representing an empty spot.
 * 1 representing a tree.
 * 2 representing a burning tree (indicating a wildfire).
 *
 * The fire spreads from a burning tree to its four neighboring cells (up, down, left, and right) if there's a tree there.
 * Each minute, the trees in the neighboring cells of burning tree catch on fire.
 *
 * Your task is to calculate how many minutes it would take for the fire to spread throughout the forest i.e. to burn all the trees.
 *
 * If there are trees that cannot be reached by the fire (for example, isolated trees with no adjacent burning trees),
 * we consider that the fire will never reach them and -1 is returned.
 *
 * The time-complexity of your algorithm must be O(n) with n the number of cells in the forest.
 */
public class Wildfire {

    static final int EMPTY = 0;
    static final int TREE = 1;
    static final int BURNING = 2;


    /**
     * This method calculates how many minutes it would take for the fire to spread throughout the forest.
     *
     * @param forest
     * @return the number of minutes it would take for the fire to spread throughout the forest,
     *         -1 if the forest cannot be completely burned.
     */
    public int burnForest(int [][] forest) {
        // TODO
        //BFS ?
        /*
        * Marquer tout les noeuds qui brule
        * +1 compteur
        * si all node node marked => -1*/

        int[][] pos = {{1,0},{0,1},{-1,0},{0,-1}};
        int n = forest.length;
        int m = forest[0].length;



        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[n*m];
        int count = 0;

        //ajouter les noeuds qui prennent déjà feu
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(forest[i][j] == BURNING){
                    queue.add(index(i,j,m));
                    marked[index(i,j,m)] = true;
                }

            }

        }

        while (!queue.isEmpty()) {
            int size = queue.size(); // Nombre de nœuds dans le niveau actuel.
            boolean burnedThisLevel = false; // Pour suivre si on brûle des arbres à ce niveau.

            for (int i = 0; i < size; i++) { // Parcourt tous les nœuds du niveau actuel.
                int index = queue.poll();
                int x = RowX(index, m);
                int y = ColY(index, m);

                for (int[] dir : pos) {
                    int neix = x + dir[0];
                    int neiy = y + dir[1];
                    int nei_index = index(neix, neiy, m);

                    if (neix >= 0 && neix < n && neiy >= 0 && neiy < m) { // Vérifie les limites.
                        if (forest[neix][neiy] == TREE && !marked[nei_index]) {
                            forest[neix][neiy] = BURNING;
                            queue.add(nei_index);
                            marked[nei_index] = true;
                            burnedThisLevel = true; // Un arbre a été brûlé à ce niveau.
                        }
                    }
                }
            }

            if (burnedThisLevel) {
                count++; // Incrémente le temps uniquement si un arbre a brûlé à ce niveau.
            }
        }

        if(!isTotallyBurned(forest)){
            //System.out.println(Arrays.deepToString(forest));
            return -1;

        }


        //System.out.println(count);

         return (count);
    }
    public static boolean isTotallyBurned(int[][] forest){
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[0].length; j++) {
                if(forest[i][j] == TREE ){
                    return false;
                }

            }

        }
        return true;

    }

    public static int index(int x, int y, int m){
        return x*m+y;
    }
    public static int RowX(int index,int m){
        return index/m;
    }
    public static int ColY(int index,int m){
        return index%m;
    }
}
















