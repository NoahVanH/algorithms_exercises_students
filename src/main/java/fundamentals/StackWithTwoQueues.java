package fundamentals;

import java.util.ArrayDeque;
import java.util.EmptyStackException;
import java.util.Queue;

/**
 * Author: Pierre Schaus and Auguste Burlats
 * Implement the abstract data type stack using two queues
 * You are not allowed to modify or add the instance variables,
 * only the body of the methods
 */
public class StackWithTwoQueues<E> {

    Queue<E> queue1;
    Queue<E> queue2;

    public StackWithTwoQueues() {
        queue1 = new ArrayDeque<>(); // la main
        queue2 = new ArrayDeque<>(); // auxiliaire
    }

    /**
     * Looks at the object at the top of this stack
     * without removing it from the stack
     */
    public boolean empty() {
        // TO DO
        return queue1.isEmpty();

    }

    /**
     * Returns the first element of the stack, without removing it from the stack
     *
     * @throws EmptyStackException if the stack is empty
     */
    public E peek() throws EmptyStackException {
        if(queue1.isEmpty()) throw new EmptyStackException();
         return queue1.peek();
    }

    /**
     * Remove the first element of the stack and returns it
     *
     * @throws EmptyStackException if the stack is empty
     */
    public E pop() throws EmptyStackException {
        /*
        * Stack :
        *   pop - retire le premier
        *   push - ajoute au dessus
        *
        * Queue :
        *   pop - retire le dernier
        *   push - ajoute au dessus
        *
        *  Stack
        *
        * */
        // retirer dans 1 et mettre dans 2 et puis inverser 1 et 2
        if(queue1.isEmpty()) throw new EmptyStackException();

        while (queue1.size() > 1){
            queue2.add(queue1.remove());
        }
        E top = queue1.remove();

        // Inverser les 2 queues
        Queue<E> buff = queue1;
        queue1 = queue2;
        queue2 = buff;

        return top;

    }

    /**
     * Adds an element to the stack
     *
     * @param item the item to add
     */
    public void push(E item) {
        queue1.add(item);
    }

}
