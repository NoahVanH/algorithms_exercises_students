package sorting;


import java.util.PriorityQueue;

/**
 * Imagine a data structure that supports :
 * - insertion in logarithmic time
 * - the median retrieval in constant time
 * - median deletion in logarithmic time
 *
 * Hint: You can use two heaps to solve this problem.
 *       There is no need to implement your own heap, you can
 *       use the PriorityQueue class from Java.
 *       Think about the clas invariant that you need to maintain
 *       after each insertion and deletion.
 *
 * When the number of stored element is odd, the median value is the value
 * at rank n+1/2 where n is the number of element
 * For example if the values {0, 1, 2, 3, 5} are stored, the median value is 2
 * When the number of stored element is even, you will arbitrarily consider that the median value
 * is the value at rank n/2 + 1
 * For example if the values {0, 1, 3, 5} are stored, the median value is 3
 */
public class MedianHeap {

    PriorityQueue<Integer> maxheap;
    PriorityQueue<Integer> minheap;

    public MedianHeap(int initialSize) {
        assert initialSize > 0;

        // root is the largest of the smaller
        this.maxheap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        // root is the smallest of the larger
        this.minheap = new PriorityQueue<>();
        // TODO
    }

    /**
     * Add a new value in the structure
     * The expected time complexity is O(log(n)) with n the size of the heap
     *
     * @param value the added value
     */
    public void insertion(int value) {
        if(maxheap.isEmpty() || value <= maxheap.peek()){
            maxheap.add(value);
        } else {
            minheap.add(value); // Add to min-heap
        }
        invariant();
        // TODO
    }
    public void invariant(){
        if(maxheap.size()> minheap.size()+1){
            int root_max = maxheap.poll();
            minheap.add(root_max);
        } else if (minheap.size()> maxheap.size()) {
            int root_min = minheap.poll();
            maxheap.add(root_min);

        }
    }


    /**
     * Retrieve the median value of the stored elements
     * Complexity O(1)
     * @return the median
     */
    public int findMedian() {
        // TODO
        if(maxheap.size()>minheap.size()){
            return maxheap.peek();
        }else{
            return minheap.peek();

        }



    }

    /**
     * Delete and return the median value
     * Complexity O(log(n)) with n the number of stored elements
     * @return the median value
     */
    public int deleteMedian() {
        if(maxheap.size()>minheap.size()){
            return maxheap.poll();
        }else{
            return minheap.poll();

        }

    }
}
