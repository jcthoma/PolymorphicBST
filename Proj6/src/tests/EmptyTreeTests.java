package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tree.EmptyTree;
import tree.NonEmptyTree;
import tree.TraversalTask;
import tree.Tree;
import tree.TreeIsEmptyException;

public class EmptyTreeTests {
	private Tree<Integer, String> tree;

	@Before
	public void setUp() {
		tree = EmptyTree.getInstance();
	}

	@Test
	public void testSearch() {
		assertNull(tree.search(5));
		assertNull(tree.search(10));
	}

	@Test
	public void testInsert() {
		tree = tree.insert(10, "Orange");
		assertTrue(tree instanceof NonEmptyTree);
	}

	@Test
	public void testDelete() {
		tree = tree.delete(10);
		assertSame(tree, EmptyTree.getInstance());
	}

	@Test(expected = TreeIsEmptyException.class)
	public void testMax() throws TreeIsEmptyException {
		tree.max();
	}

	@Test(expected = TreeIsEmptyException.class)
	public void testMin() throws TreeIsEmptyException {
		tree.min();
	}

	@Test
	public void testSize() {
		assertEquals(0, tree.size());
		tree = tree.insert(10, "Orange");
		assertEquals(1, tree.size());
	}

	@Test
	public void testAddKeysToCollection() {
		List<Integer> keys = new ArrayList<>();
		tree.addKeysToCollection(keys);
		assertTrue(keys.isEmpty());
	}

	@Test
	public void testSubTree() {
		Tree<Integer, String> subTree = tree.subTree(3, 7);
		assertSame(tree, subTree);

	}

	@Test
	public void testHeight() {
		assertEquals(0, tree.height());
		tree = tree.insert(10, "Orange");
		assertEquals(1, tree.height());
	}

	@Test
	public void testInorderTraversal() {
		List<String> values = new ArrayList<>();
		TraversalTask<Integer, String> task = (k, v) -> values.add(v);
		tree.inorderTraversal(task);
		assertTrue(values.isEmpty());
	}

	@Test
	public void testRightRootLeftTraversal() {
		List<String> values = new ArrayList<>();
		TraversalTask<Integer, String> task = (k, v) -> values.add(v);
		tree.rightRootLeftTraversal(task);
		assertTrue(values.isEmpty());
	}
}
