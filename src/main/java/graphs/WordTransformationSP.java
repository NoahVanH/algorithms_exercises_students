package graphs;

import java.util.*;


/**
 * You are asked to implement the WordTransformationSP
 * class which allows to find the shortest path
 * from a string A to another string B
 * (with the certainty that there is a path from A to B).
 * To do this, we define a rotation(x, y) operation that
 * reverses the order of the letters between the x and y positions (not included).
 * For example, with A=``HAMBURGER``, if we do rotation(A, 4, 8), we get HAMBEGRUR.
 * So you can see that the URGE sub-string
 * has been inverted to EGRU and the rest of the string
 * has remained unchanged: HAMB + ECRU + R = HAMBEGRUR.
 * Let's say that a rotation(x, y) has a cost of y-x.
 * For example going from HAMBURGER to HAMBEGRUR costs 8-4 = 4.
 * The question is what is the minimum cost to reach a string B from A?
 * So you need to implement a public static int minimalCost(String A, String B)
 * function that returns the minimum cost to reach String B
 * from A using the rotation operation.
 */
public class WordTransformationSP {

    /**
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     */
    /**
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     *
     * @param s
     * @param start
     * @param end
     * @return rotated string
     */
    public static String rotation(String s, int start, int end) {
        return s.substring(0, start) + new StringBuilder(s.substring(start, end)).reverse().toString() + s.substring(end);
    }

    /**
     * Compute the minimal cost from string "from" to string "to" representing the shortest path
     *
     * @param from
     * @param to
     * @return
     */
    public static int minimalCost(String from, String to) {
        // TODO
        // dijkstra, weight = y-a
        HashMap<String,Integer> distTo = new HashMap<>();
        PriorityQueue<Rotation> pq = new PriorityQueue<>();

        pq.add(new Rotation(from,0));
        distTo.put(from,0);

        while (!pq.isEmpty()){
            Rotation current = pq.poll();
            String string_current = current.value;
            int distance_current = current.distance;
            for (int i = 0; i < string_current.length(); i++) {
                for (int j = i+2; j <= string_current.length(); j++) {
                    String out = rotation(string_current,i,j);
                    //System.out.println(out);
                    int cost = distTo.get(string_current) + (j-i);
                    if(!distTo.containsKey(out) ||  cost < distTo.get(out)){
                        distTo.put(out,cost);
                        pq.add(new Rotation(out,cost));
                    }

                }

            }



        }



         return distTo.get(to);
    }

    private static class Rotation implements Comparable<Rotation>{
        String value;
        int distance;
        Rotation(String value, int distance){
            this.distance = distance;
            this.value=value;
        }

        @Override
        public int compareTo(Rotation o) {
            return Integer.compare(this.distance,o.distance);
        }
    }


}
