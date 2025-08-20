package strings;

/**
 * Author Pierre Schaus
 *
 * We are interested in the Rabin-Karp algorithm.
 * We would like to modify it a bit to determine
 * if a word among a list (!!! all words are of the same length !!!)
 * is present in the text.
 * To do this, you need to modify the Rabin-Karp
 * algorithm which is shown below (page 777 of the book).
 * More precisely, you are asked to modify this class
 * so that it has a constructor of the form:
 * public RabinKarp(String[] pat)
 *
 * Moreover the search function must return
 * the index of the beginning of the first
 * word (among the pat array) found in the text or
 * the size of the text if no word appears in the text.
 *
 * Example: If txt = "Here find interesting
 * exercise for Rabin Karp" and pat={"have", "find", "Karp"}
 * the search function must return 5 because
 * the word "find" present in the text and in the list starts at index 5.
 *
 */
public class RabinKarp {

    private String[] pat; // pattern array
    private int M; // pattern length
    private long Q; // a large prime
    private int R = 256; // alphabet size
    private long[] patHash; // pattern hash values
    private long RM; // R^(M-1) % Q

    public RabinKarp(String[] pat) {
        this.pat = pat;
        this.M = pat[0].length();
        this.Q = 4463;
        this.RM = 1;

        for (int i = 1; i <= M - 1; i++) {

            RM = (R * RM) % Q;
        }

        // Compute hash values for all patterns in the array
        patHash = new long[pat.length];
        for (int i = 0; i < pat.length; i++) {
            patHash[i] = hash(pat[i], M);
        }
    }

    private long hash(String key, int M) {
        // Compute hash for key[0..M-1].
        long h = 0;
        for (int j = 0; j < M; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }

    public int search(String txt) {
        // Search for hash match in text.
        int N = txt.length();
        long txtHash = hash(txt, M);

        for (int i = 0; i <= N - M; i++) {
            // Check against all pattern hash values
            for (int j = 0; j < pat.length; j++) {
                if (patHash[j] == txtHash && check(txt, i, j)) {
                    return i; // match found
                }
            }

            if (i < N - M) {
                // Update the hash value for the next window in the text
                txtHash = (R * (txtHash - txt.charAt(i) * RM) + txt.charAt(i + M)) % Q;
                if (txtHash < 0) txtHash += Q; // make sure it's non-negative
            }
        }

        return N; // no match found
    }

    private boolean check(String txt, int i, int j) {
        // Check if the pattern j matches the substring in txt starting at index i
        for (int k = 0; k < M; k++) {
            if (txt.charAt(i + k) != pat[j].charAt(k)) {
                return false;
            }
        }
        return true;
    }
}
