import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class BSTreeTest {
    private BSTree<Integer> tree;

    @BeforeEach
    public void setUp() {
        tree = new BSTree<>();
    }
    @Test
    public void testConstructor() {
        assertNull(tree.getRoot());
    }

    @Test
    public void testConstructo2() {
        assertEquals(0, tree.getSize());
    }

    @Test
    public void testInsert3() {
        tree.insert(10);
        assertEquals(10, tree.getRoot().getKey());
        assertEquals(1, tree.getSize());
    }

    @Test
    public void getRoot() {
        assertNull(tree.getRoot());
    }
    @Test
    void getRoot2() {
        tree.insert(10);
        assertEquals(10, tree.getRoot().getKey());
    }

    @Test
    void getRoot3(){
        tree.insert(10);
        tree.insert(5);
        tree.insert(12);
        assertEquals(10, tree.getRoot().getKey());
    }

    @Test
    void getSize() {
        assertEquals(0, tree.getSize());
    }

    @Test
    void getSize2(){
        tree.insert(5);
        assertEquals(1, tree.getSize());
    }

    @Test
    void getSize3(){
        tree.insert(10);
        tree.insert(5);
        tree.insert(4);
        assertEquals(3, tree.getSize());
    }


    @Test
    void insert() {
        assertTrue(tree.insert(10));
        tree.insert(11);
        assertEquals(10, tree.getRoot().getKey());
        assertEquals(2, tree.getSize());
    }

    @Test
    void insertDup(){
        tree.insert(10);
        assertFalse(tree.insert(10));
        assertEquals(1, tree.getSize());
    }

    @Test
    void insertNull(){
        assertThrows(NullPointerException.class, () -> {
            tree.insert(null);
        });
    }

    @Test
    void findKey() {
        tree.insert(10);
        assertTrue(tree.findKey(10));
    }

    @Test
    void findKey2(){
        tree.insert(10);
        assertFalse(tree.findKey(15));
    }
    @Test
    public void FindKeyNull() {
        assertThrows(NullPointerException.class, () -> {
            tree.findKey(null);
        });
    }

    @Test
    void insertData() {
        tree.insert(10);
        tree.insertData(10, 20);
        assertEquals(1, tree.findDataList(10).size());
        assertEquals(20, tree.findDataList(10).get(0));
    }

    @Test
    void InsertData2(){
        assertThrows(IllegalArgumentException.class, () -> {
            tree.insertData(10, 20);
        });
    }
    @Test
    void insertData3(){
        assertThrows(NullPointerException.class, () -> {
            tree.insertData(null, 20);
        });
    }

    @Test
    void insertData4(){
        tree.insert(10);
        assertThrows(NullPointerException.class, () -> {
            tree.insertData(10, null);
        });
    }

    @Test
    void findDataList() {
        tree.insert(10);
        tree.insertData(10, 20);
        LinkedList<Integer> dataList = tree.findDataList(10);
        assertEquals(1, dataList.size());
        assertEquals(20, dataList.get(0));
    }

    @Test
    void FindDataList2(){
        assertThrows(IllegalArgumentException.class, () -> {
            tree.findDataList(10);
        });
    }
    @Test
    void FindDataList3(){
        assertThrows(NullPointerException.class, () -> {
            tree.findDataList(null);
        });
    }

    @Test
    void findHeight() {
        assertEquals(-1, tree.findHeight());
    }

    @Test
    void findHeight2(){
        tree.insert(10);
        assertEquals(0, tree.findHeight());
    }

    @Test
    void findHeight3(){
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(2);
        tree.insert(8);
        assertEquals(2, tree.findHeight());
    }
}