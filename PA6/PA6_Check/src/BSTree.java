/*
 * Name: Hasaam Butt
 * PID: A17784858
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Hasaam Butt
 * @since  5/13/2024
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;

        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.dataList = new LinkedList<>();
            this.key = key;
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setLeft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setRight(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            return this.dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.root = null;
        this.nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        if (findKey(key)) {
            return false; // Key already exists
        }
        root = insert(root, key);
        return true;
    }
    /**
     * Helper method for inserting a key into the BST.
     *
     * @param node The current node.
     * @param key  The key to be inserted.
     * @return The updated node.
     */
    private BSTNode insert(BSTNode node, T key) {
        if (node == null) {
            nelems++;
            return new BSTNode(null, null, key);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key);
        } else if (cmp > 0) {
            node.right = insert(node.right, key);
        }
        return node;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        return findKey(root, key);
    }
    /**
     * Helper method for finding a key in the BST.
     *
     * @param node The current node.
     * @param key  The key to be found.
     * @return True if the key is found, false otherwise.
     */
    private boolean findKey(BSTNode node, T key) {
        if (node == null) {
            return false;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return findKey(node.left, key);
        } else if (cmp > 0) {
            return findKey(node.right, key);
        } else {
            return true;
        }
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If eaither key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null || data == null) {
            throw new NullPointerException("Key or data cannot be null");
        }
        BSTNode node = findNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("Key not found in the BST");
        }
        node.addNewInfo(data);
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        BSTNode node = findNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("Key not found in the BST");
        }
        return node.getDataList();
    }

    private BSTNode findNode(BSTNode node, T key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return findNode(node.left, key);
        } else if (cmp > 0) {
            return findNode(node.right, key);
        } else {
            return node;
        }
    }
    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = findHeightHelper(root.left);
        int rightHeight = findHeightHelper(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {
        public BSTree_Iterator() {
            /* TODO */
        }

        public boolean hasNext() {
            /* TODO */
            return false;
        }

        public T next() {
            /* TODO */
            return null;
        }
    }

    public Iterator<T> iterator() {
        /* TODO */
        return null;
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }

    public T levelMax(int level) {
        /* TODO */
        return null;
    }
}
