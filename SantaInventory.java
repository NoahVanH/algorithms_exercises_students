package exam;


/**
 * Santa needs to calculate the median price of gifts he will deliver this year.
 * The gift prices are stored in a unique data structure known as the 'magical Christmas search tree'.
 *
 * Each node in this tree represents a gift price (as the key) and the quantity of gifts at that price (as the value).
 * The goal is to implement two methods:
 * - put (to add gift prices to the tree) and
 * - median (to find the median price of the gifts).
 *
 * For example, consider the following magical Christmas search tree:
 *
 *                               [150, 4]
 *                                /     \
 *                               /       \
 *                              /         \
 *                             /           \
 *                        [100, 10]       [300, 2]
 *                                         /   \
 *                                        /     \
 *                                       /       \
 *                                      /         \
 *                                   [200, 8]     [500, 1]
 *
 * This tree represents a total of 25 gifts. The median price is the 13th price in the sorted list of gift prices.
 * In this example, the sorted list of prices is:
 * 100 (10 times), 150 (4 times), 200 (8 times), 300 (2 times), 500 (once). The 13th price in this list is 150.
 * Thus, the median price of the gifts is 150.
 *
 * Note: It's assumed that the total number of gifts is always an odd number.
 *
 * Hint: you may need to add a size attribute to the Node class to keep track of the total number of gifts in the subtree.
 */

public class SantaInventory {

    private Node root; // root of BST

    private class Node {
        private int toyPrice; // Price of the toy
        private int count; // Number of time a toy with price `toyPrice` has been added in the tree
        private Node left, right; // left and right subtrees
        private int subtreeSize; // Number of toys in the subtree

        Node(int toyPrice, int count) {
            this.toyPrice = toyPrice;
            this.count = count;
            this.subtreeSize = count;
        }
    }

    // Function to print the tree
    public void printTree(Node root, int level) {
        if (root == null) {
            return;
        }

        // Print the right subtree first (to display higher levels first)
        printTree(root.right, level + 1);

        // Print current node value with indentation
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.print(root.toyPrice);
        System.out.print(";");
        System.out.print(root.count);
        System.out.print(";");
        System.out.println(root.subtreeSize);

        // Print the left subtree
        printTree(root.left, level + 1);
    }

    /**
     * Inserts a new toy price into the magical Christmas search tree or updates the count of an existing toy price.
     * This method is part of the implementation of the magical Christmas search tree where each node
     * represents a unique toy price and the number of toys available at that price.
     *
     * If the tree already contains the toy price, the method updates the count of toys at that price.
     * If the toy price does not exist in the tree, a new node with the toy price and count is created.
     *
     * @param toyPrice The price of the toy to be added or updated in the tree.
     * @param count    The number of toys added to the magical tree. If the toy price already exists,
     *                 this count is added to the existing count.
     */
    public void put(int toyPrice, int count) {
        Node current = root;
        if(current == null) {
            root = new Node(toyPrice, count);
            return;
        }
        while(true) {
            if(current.toyPrice == toyPrice) {
                current.count += count;
                break;
            } else if(current.toyPrice > toyPrice) {
                if(current.left == null) {
                    current.left = new Node(toyPrice, count);
                    break;
                } else {
                    current = current.left;
                }
            } else {
                if(current.right == null) {
                    current.right = new Node(toyPrice, count);
                    break;
                } else {
                    current = current.right;
                }
            }
        }
        updateSize(root);
    }

    public int updateSize(Node node) {
        if (node == null) {
            return 0;
        }

        int leftSize = updateSize(node.left);
        int rightSize = updateSize(node.right);

        node.subtreeSize = node.count + leftSize + rightSize;

        return node.subtreeSize;
    }

    /**
     * Calculates the median price of the toys in the magical Christmas search tree.
     *
     * The median is determined by the size of the tree. If the tree is empty, it throws an IllegalArgumentException.
     *
     * Note: The method assumes that the total number of toys (the sum of counts of all prices) is odd.
     * The median is the price at the middle position when all toy prices are listed in sorted order.
     *
     * @return The median price of the toys.
     * @throws IllegalArgumentException if the tree is empty.
     */
    public int median() {
        if(root == null) {
            throw new IllegalArgumentException();
        }
        printTree(root, 0);
        int countMedian = (int)Math.ceil(root.subtreeSize/2.0);
        return median(root, countMedian);
    }

    private int median(Node node, int countMedian){
        if(node.left == null) {
            // either itself or to the right
            if(node.count >= countMedian) {
                return node.toyPrice;
            } else {
                return median(node.right, countMedian - node.count);
            }
        }
        if(node.left.subtreeSize > countMedian){
            // to the left
            return median(node.left, countMedian);
        } else if (countMedian - node.left.subtreeSize <= node.count) {
            return node.toyPrice;
        } else return median(node.right, countMedian - node.count);
    }

    public static void main(String[] args) {
        SantaInventory inventory = new SantaInventory();
        inventory.put(20, 4);
        inventory.put(1, 10);
        inventory.put(35, 2);
        inventory.put(40, 1);
        inventory.put(5, 8);
        inventory.median();
    }
}

