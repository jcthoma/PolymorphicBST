package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import tree.EmptyTree;
import tree.PolymorphicBST;
import tree.TraversalTask;
import tree.NonEmptyTree;
import tree.Tree;
import tree.TreeIsEmptyException;

public class NonEmptyTreeTests extends TestCase {
	private Tree<Integer, String> tree;

	@Test
	public void testSearch() {
		Tree<Integer, String> tree = new NonEmptyTree<>(5, "Apple",
				EmptyTree.getInstance(),
				EmptyTree.getInstance());
		
		assertEquals("Apple", tree.search(5));
		assertNull(tree.search(10));
	}

	@Test
	public void testInsert() {
		Tree<Integer, String> tree = new NonEmptyTree<>(5, "Apple",
				EmptyTree.getInstance(),
				EmptyTree.getInstance());
		
		tree = tree.insert(10, "Orange");
		assertEquals("Orange", tree.search(10));
	}

	@Test
	public void testDelete() {
		Tree<Integer, String> tree = new NonEmptyTree<>(5, "Apple",
				EmptyTree.getInstance(),
				EmptyTree.getInstance());
		
		tree = tree.insert(10, "Orange");
		tree = tree.delete(10);
		assertNull(tree.search(10));
	}

	@Test
	public void testMax() {
		Tree<Integer, String> tree = new NonEmptyTree<>(5, "Apple",
				EmptyTree.getInstance(),
				EmptyTree.getInstance());
		
		try {
			assertEquals(Integer.valueOf(5), tree.max());
		} catch (TreeIsEmptyException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMin() {
		Tree<Integer, String> tree = new NonEmptyTree<>(5, "Apple",
				EmptyTree.getInstance(),
				EmptyTree.getInstance());
		
		try {
			assertEquals(Integer.valueOf(5), tree.min());
		} catch (TreeIsEmptyException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSize() {
		Tree<Integer, String> tree = new NonEmptyTree<>(5, "Apple",
				EmptyTree.getInstance(),
				EmptyTree.getInstance());
		
		assertEquals(1, tree.size());
		tree = tree.insert(10, "Orange");
		assertEquals(2, tree.size());
	}

	@Test
	public void testAddKeysToCollection() {
		Tree<Integer, String> tree = new NonEmptyTree<>(5, "Apple",
				EmptyTree.getInstance(),
				EmptyTree.getInstance());
		
		List<Integer> keys = new ArrayList<>();
		tree.addKeysToCollection(keys);
		assertEquals(1, keys.size());
		assertTrue(keys.contains(5));
	}

	@Test
	public void testSubTree() {
		Tree<Integer, String> tree = new NonEmptyTree<>(5, "Apple",
				EmptyTree.getInstance(),
				EmptyTree.getInstance());
		
		Tree<Integer, String> subTree = tree.subTree(3, 7);
		assertEquals(1, subTree.size());
		assertEquals("Apple", subTree.search(5));
		assertNull(subTree.search(3));
		assertNull(subTree.search(7));
	}

	@Test
	public void testHeight() {
		Tree<Integer, String> tree = new NonEmptyTree<>(5, "Apple",
				EmptyTree.getInstance(),
				EmptyTree.getInstance());
		
		assertEquals(1, tree.height());
		tree = tree.insert(10, "Orange");
		assertEquals(2, tree.height());
	}

	@Test
	public void testInorderTraversal() {
		Tree<Integer, String> tree = new NonEmptyTree<>(5, "Apple",
				EmptyTree.getInstance(),
				EmptyTree.getInstance());
		
		List<String> values = new ArrayList<>();
		TraversalTask<Integer, String> task = (k, v) -> values.add(v);
		tree.inorderTraversal(task);
		assertEquals(1, values.size());
		assertEquals("Apple", values.get(0));
	}

	@Test
	public void testRightRootLeftTraversal() {
		Tree<Integer, String> tree = new NonEmptyTree<>(5, "Apple",
				EmptyTree.getInstance(),
				EmptyTree.getInstance());
		
		List<String> values = new ArrayList<>();
		TraversalTask<Integer, String> task = (k, v) -> values.add(v);
		tree.rightRootLeftTraversal(task);
		assertEquals(1, values.size());
		assertEquals("Apple", values.get(0));
	}
}