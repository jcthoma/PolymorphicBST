package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import tree.EmptyTree;
import tree.PolymorphicBST;
import tree.TraversalTask;
import tree.Tree;
import tree.TreeIsEmptyException;

public class PolymorphicBSTTests {
	private PolymorphicBST<Integer, String> bst;

	@Before
	public void setUp() {
		bst = new PolymorphicBST<>();
		bst.put(5, "Apple");
		bst.put(3, "Banana");
		bst.put(8, "Orange");
		bst.put(2, "Mango");
		bst.put(4, "Peach");
		bst.put(7, "Grape");
		bst.put(10, "Cherry");
	}

	@Test
	public void testGet() {
		assertEquals("Apple", bst.get(5));
		assertNull(bst.get(1));
	}

	@Test
	public void testPut() {
		bst.put(6, "Lemon");
		assertEquals("Lemon", bst.get(6));
	}

	@Test
	public void testSize() {
		assertEquals(7, bst.size());
		bst.remove(10);
		assertEquals(6, bst.size());
	}

	@Test
	public void testRemove() {
		bst.remove(4);
		assertNull(bst.get(4));
	}

	@Test
	public void testKeySet() {
		Set<Integer> keys = bst.keySet();
		assertEquals(7, keys.size());
		assertTrue(keys.contains(2));
		assertTrue(keys.contains(3));
		assertTrue(keys.contains(4));
		assertTrue(keys.contains(5));
		assertTrue(keys.contains(7));
		assertTrue(keys.contains(8));
		assertTrue(keys.contains(10));
	}

	@Test
	public void testGetMin() {
		assertEquals(Integer.valueOf(2), bst.getMin());
	}

	@Test
	public void testGetMax() {
		assertEquals(Integer.valueOf(10), bst.getMax());
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetMinEmpty() {
		PolymorphicBST<Integer, String> emptyBST = new PolymorphicBST<>();
		emptyBST.getMin();
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetMaxEmpty() {
		PolymorphicBST<Integer, String> emptyBST = new PolymorphicBST<>();
		emptyBST.getMax();
	}

	@Test
	public void testSubMap() {
		PolymorphicBST<Integer, String> pbst = new PolymorphicBST<>();
	    pbst.put(2, "Two");
	    pbst.put(3, "Three");
	    pbst.put(4, "Four");
	    pbst.put(5, "Five");
	    pbst.put(7, "Seven");
	    pbst.put(8, "Eight");
	    pbst.put(9, "Nine");

	    PolymorphicBST<Integer, String> subMap = pbst.subMap(3, 7);
	    
	    assertEquals("Three", subMap.get(3));
	    assertEquals("Four", subMap.get(4));
	    assertEquals("Five", subMap.get(5));
	    assertEquals("Seven", subMap.get(7));
	    assertNull(subMap.get(2));
	    assertNull(subMap.get(8));
	    assertNull(subMap.get(9));
	}

	@Test
	public void testClear() {
		bst.clear();
		assertEquals(0, bst.size());
	}

	@Test
	public void testHeight() {
		assertEquals(3, bst.height());
		bst.put(11, "Lemon");
		assertEquals(4, bst.height());
	}

	@Test
	public void testInorderTraversal() {
		Set<String> values = new HashSet<>();
		TraversalTask<Integer, String> task = (k, v) -> values.add(v);
		bst.inorderTraversal(task);
		assertEquals(7, values.size());
		assertTrue(values.contains("Mango"));
		assertTrue(values.contains("Banana"));
		assertTrue(values.contains("Peach"));
		assertTrue(values.contains("Apple"));
		assertTrue(values.contains("Grape"));
		assertTrue(values.contains("Orange"));
		assertTrue(values.contains("Cherry"));
	}

	@Test
	public void testRightRootLeftTraversal() {
		Set<String> values = new HashSet<>();
		TraversalTask<Integer, String> task = (k, v) -> values.add(v);
		bst.rightRootLeftTraversal(task);
		assertEquals(7, values.size());
		assertTrue(values.contains("Cherry"));
		assertTrue(values.contains("Orange"));
		assertTrue(values.contains("Grape"));
		assertTrue(values.contains("Apple"));
		assertTrue(values.contains("Peach"));
		assertTrue(values.contains("Banana"));
		assertTrue(values.contains("Mango"));
	}
}
