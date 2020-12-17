package com.example;

public class BinarySearchTreePlus<AnyType extends Comparable<? super AnyType>> {


    /**
     * Implements an unbalanced binary search tree. Note that all "matching" is
     * * based on the compareTo method. * * @author Mark Allen Weiss  <br>
     * * version modified by ak to: * remove main test driver <br>
     * * remove generation of UnderflowExceptions <br> *
     * * implement BSTInterface <br> *
     * * rearrange order of methods and fields
     */

    // Basic node stored in unbalanced binary search trees
    private class BinaryNode<AnyType> {
        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        // Constructor

        BinaryNode(AnyType theElement) {
            element = theElement;
            left = null;
            right = null;
        }
    }

    /**
    // * The tree root.     */
    private BinaryNode<AnyType> root;

    /**
     * Construct the tree.
     */
    public BinarySearchTreePlus() {
        root = null;
    }

    //    //      public methods:         //    //

    /**
     * * Insert into the tree; duplicates are ignored.
     * * @param item the item to insert.
     */
    public void insert(AnyType item) {
        root = insert(item, root);
    }

    /**
     * * Remove from the tree. Nothing is done if item is not found.
     * * @param item the item to remove.
     **/

    public void remove(AnyType item) {
        root = remove(item, root);
    }

    /**
     * Find the smallest item in the tree.     *
     * * @return smallest item or null if empty.
     */
    public AnyType findMin() {
        AnyType min = null;
        if (!isEmpty()) {
            min = findMin(root).element;
        }
        return min;
    }

    /**
     * Find the largest item in the tree.     *
     * * @return the largest item or null if empty.
     */
    public AnyType findMax() {
        AnyType max = null;
        if (!isEmpty()) {
            max = findMax(root).element;
        }
        return max;
    }

    /**
     * Find an item in the tree.
     * * @param item the item to search for.
     * * @return true if not found.
     */
    public boolean contains(AnyType item) {
        return contains(item, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     ** @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }
    //    //      private methods:        //    //

    /**
     * * Internal method to insert into a subtree.
     * *     * @param item the item to insert.
     * * @param mode the node that roots the subtree.
     * * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert(AnyType item, BinaryNode<AnyType> node) {
        if (node == null) {
            return new BinaryNode<>(item);
        }
        int compareResult = item.compareTo(node.element);
        if (compareResult < 0) {
            node.left = insert(item, node.left);
        } else if (compareResult > 0) {
            node.right = insert(item, node.right);
        } else {
            ;  // Duplicate:  do nothing
        }
        return node;
    }

    /**
     * Internal method to remove from a subtree.
     * *     * @param item the item to remove.
     * * @param node the node that roots the subtree.
     * * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove(AnyType item, BinaryNode<AnyType> node) {
        if (node == null) {
            return node;   // Item not found; do nothing
        }
        int compareResult = item.compareTo(node.element);
        if (compareResult < 0) {
            node.left = remove(item, node.left);
        } else if (compareResult > 0) {
            node.right = remove(item, node.right);
        } else if (node.left != null && node.right != null) // Two children
        {
            node.element = findMin(node.right).element;
            node.right = remove(node.element, node.right);
        } else {
            node = (node.left != null) ? node.left : node.right;
        }
        return node;
    }

    /**
     * * Internal method to find the smallest item in a subtree.
     * *
     * * @param node the node that roots the subtree.
     * * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     * * @param node the node that roots the subtree.
     * * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> node) {
        if (node != null) {
            while (node.right != null) {
                node = node.right;
            }
        }
        return node;
    }

    /**
     * Internal method to find an item in a subtree.
     * * @param item is item to search for.
     * * @param node the node that roots the subtree.
     * * @return node containing the matched item.
     */
    private boolean contains(AnyType item, BinaryNode<AnyType> node) {
        if (node == null) {
            return false;
        }
        int compareResult = item.compareTo(node.element);
        if (compareResult < 0) {
            return contains(item, node.left);
        } else if (compareResult > 0) {
            return contains(item, node.right);
        } else {
            return true;    // Match
        }
    }

    /**
     * * Internal method to print a subtree in sorted order.
     * * @param node the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> node) {
        if (node != null) {
            printTree(node.left);
            System.out.println(node.element);
            printTree(node.right);
        }
    }
}

