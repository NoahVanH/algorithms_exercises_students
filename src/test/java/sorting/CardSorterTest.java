package sorting;

import org.javagrader.Grade;
import org.javagrader.CustomGradingResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.*;

@Grade
public class CardSorterTest {


    @Test
    public void testExample() {
        LinkedListImpl l = new LinkedListImpl(new int[]{7, 8, 2, 22, 102, 1});
        CardSorter.sort(l);
        assertTrue(l.isSorted());
    }

    @Test
    public void alreadySorted() {
        LinkedListImpl l = new LinkedListImpl(new int[]{1, 2, 3, 4, 5});
        CardSorter.sort(l);
        assertTrue(l.isSorted());
    }

    @Test
    public void bimonotonous() {
        LinkedListImpl l = new LinkedListImpl(new int[]{5, 4, 3, 2, 1, 2, 3, 4, 5});
        CardSorter.sort(l);
        assertTrue(l.isSorted());
    }

    @Test
    public void random() {
        LinkedListImpl l = new LinkedListImpl(new int[]{3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5});
        CardSorter.sort(l);
        assertTrue(l.isSorted());
    }

    @Test
    public void allSortedButOne() {
        LinkedListImpl l = new LinkedListImpl(new int[]{1, 2, 3, 4, 6, 5});
        CardSorter.sort(l);
        assertTrue(l.isSorted());
    }

    @Test
    public void twoDifferent() {
        LinkedListImpl l = new LinkedListImpl(new int[]{3, 2});
        CardSorter.sort(l);
        assertTrue(l.isSorted());
    }

    @Test
    public void reverseSorted() {
        LinkedListImpl l = new LinkedListImpl(new int[]{5, 4, 3, 2, 1});
        CardSorter.sort(l);
        assertTrue(l.isSorted());
    }


}


