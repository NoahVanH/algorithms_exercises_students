package strings;

/**
 * Author: Pierre Schaus
 *
 * You have to implement an incremental hash function on a string represented as an array of char's.
 * This is the hash function that is used by the Rabin-Karp algorithm.
 *
 * We advise you to debug your code using the first two small unit-tests provided with string of length 2 and 3
 */
public class IncrementalHash {


    public static final int R = 31;
    private int M;
    private int RM;
    private int Q;

    /**
     *
     * @param Q is the modulo to apply
     * @param M is the length of the words to hash
     */
    public IncrementalHash(int Q, int M) {
        assert (M > 0);
        assert (Q > 0);
        this.Q = Q;
        this.M = M;
        // Computes (R ^(M-1)) % Q using the Horner's method
        // This precomputed result is useful for implementing nextHash in O(1)
        RM = 1;

        // !! i = 0 !!

        /*
        * La modification ici consiste à démarrer la boucle à partir de i = 0 plutôt que i = 1.
        *  Cela garantit que la première puissance de R (R^0) est incluse dans le calcul de RM.
        * Cela est nécessaire pour prendre en compte le caractère initial dans le calcul du hash.*/


        for (int i = 0; i <= M - 1; i++) {
            RM = (RM * R) % Q;
        }
    }

    /**
     * Computes the hash(t) function on the substring
     * t[from,...,from+M-1] in O(1) defined as
     * hash[t] = (t[from] * R^(M-1) + t[from+1] * R^(M-2) + ... + t[from+M-1]) % Q
     * Remark: ^ is the exponent operator, and % the modulo operator
     *
     * The formula given below can be computed in O(M) but
     * it is possible to compute it in O(1) by using the previousHash = hash(t-1)
     *
     * @param t the input array
     * @param previousHash = hash(t-1) that is the one on t[from-1,...from+M-2]
     * @param from the index of the substring window, must be on the inteveral [1...t.length-M]
     * @return hash[t] = (t[from] * R^(M-1) + t[from+1] * R^(M-2) + ... + t[from+M-1]) % Q
     *
     */

    /**
     * Computes the hash(t) function on the substring
     * t[from,...,from+M-1] in O(1) defined as
     * hash[t] = (t[from] * R^(M-1) + t[from+1] * R^(M-2) + ... + t[from+M-1]) % Q
     * Remark: ^ is the exponent operator, and % the modulo operator
     *
     * The formula given below can be computed in O(M) but
     * it is possible to compute it in O(1) by using the previousHash = hash(t-1)
     *
     * @param t the input array
     * @param previousHash = hash(t-1) that is the one on t[from-1,...from+M-2]
     * @param from the index of the substring window, must be on the interval [1...t.length-M]
     * @return hash[t] = (t[from] * R^(M-1) + t[from+1] * R^(M-2) + ... + t[from+M-1]) % Q
     *
     */
    public int nextHash(char[] t, int previousHash, int from) {
        // Compute hash[t] using the Rabin-Karp algorithm

        //Multiplie le hash précédent par la base R.
        //Cela "déplace" le hash d'un caractère vers la gauche, en retirant la contribution du caractère t[from-1].
        int hash = (previousHash * R) % Q; // Remove the contribution of t[from-1]

        //Retire la contribution du caractère t[from-1] en ajoutant Q - (t[from-1] * RM) % Q.
        // Cela est fait pour éviter les problèmes liés à la gestion des nombres négatifs lors de l'utilisation de l'opération %.
        //Ajoute la contribution du caractère t[from+M-1] au hash résultant.
        hash = (hash + Q - (t[from - 1] * RM) % Q) % Q; // Add the contribution of t[from+M-1]

        //Assure que le hash final est dans la plage de 0 à Q-1 en appliquant le modulo Q.
        hash = (hash + t[from + M - 1]) % Q;

        return hash;
    }


}