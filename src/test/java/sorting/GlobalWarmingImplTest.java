package sorting;

import org.javagrader.Grade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Grade
public class GlobalWarmingImplTest {

    @Test
    @Grade(value=1)
    public void testSimpleAll() {
        int [][] matrix = getSimpleMatrix();
        GlobalWarming warming = new GlobalWarmingImpl(matrix);

        // matrice 5x5 => 25 points
        // niveau d’eau = -1 : aucun point noyé, donc tous sûrs
        //assertEquals(25, warming.nbSafePoints(-1));

        // niveau d’eau = 0 : encore aucun point <= 0, donc toujours 25
        //assertEquals(25, warming.nbSafePoints(0));


        //assertEquals(18, warming.nbSafePoints(1));

        // niveau d’eau = 2 : on noie aussi les points de valeur 2 (il y en a 4)
        // 19 - 4 = 15
        assertEquals(14, warming.nbSafePoints(2));

        // niveau d’eau = 3 : on noie aussi les points de valeur 3 (il y en a 6)
        // 15 - 6 = 9
        assertEquals(9, warming.nbSafePoints(3));

        // niveau d’eau = 4 : on noie aussi les points de valeur 4 (il y en a 6)
        // 9 - 6 = 3
        assertEquals(3, warming.nbSafePoints(4));

        // niveau d’eau = 5 : on noie aussi les points de valeur 5 (il y en a 1)
        // 3 - 1 = 2
        assertEquals(2, warming.nbSafePoints(5));

        // niveau d’eau = 6 : tout est noyé
        assertEquals(0, warming.nbSafePoints(6));
    }

    private int[][] getSimpleMatrix() {
        return new int[][] {
                {1,3,3,1,3},
                {4,2,2,4,5},
                {4,4,1,4,2},
                {1,4,2,3,6},
                {1,1,1,6,3}
        };
    }
}
