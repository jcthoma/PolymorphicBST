package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 * 
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/* Provide whatever instance variables you need */

	/**
	 * Only constructor we need.
	 * 
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */

	private K key;
	private V value;
	private Tree<K, V> leftTree;
	private Tree<K, V> rightTree;

	public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {
		this.key = key;
		this.value = value;
		leftTree = left;
		rightTree = right;
	}

	public V search(K key) {

		/**
		 * Find the value that this key is bound to in this tree.
		 * 
		 * @param key -- Key to search for
		 * @return -- value associated with the key by this Tree, or null if the
		 *         key does not have an association in this tree.
		 */
		int comparison = key.compareTo(this.key);

		if (comparison == 0) {
			return value;
		} else if (comparison < 0) {
			return leftTree.search(key);
		} else {
			return rightTree.search(key);
		}
	}

	public NonEmptyTree<K, V> insert(K key, V value) {
		/**
		 * Insert/update the Tree with a new key:value pair. If the key already
		 * exists in the tree, update the value associated with it. If the key
		 * doesn't exist, insert the new key value pair.
		 * 
		 * This method returns a reference to an Tree that represents the
		 * updated value. In many, but not all cases, the method may just return
		 * a reference to this. This method is annotated @CheckReturnValue
		 * because you have to pay attention to the return value; if you simply
		 * invoke insert on a Tree and ignore the return value, your code is
		 * almost certainly wrong.
		 * 
		 * @param key   -- Key
		 * @param value -- Value that the key maps to
		 * @return -- updated tree
		 */
		int comparison = key.compareTo(this.key);

		if (comparison == 0) {
			this.value = value;
		} else if (comparison < 0) {
			leftTree = leftTree.insert(key, value);
		} else {
			rightTree = rightTree.insert(key, value);
		}

		return this;
	}

	public Tree<K, V> delete(K key) {
		/**
		 * Delete any binding the key has in this tree. If the key isn't bound,
		 * this is a no-op
		 * 
		 * This method returns a reference to an Tree that represents the
		 * updated value. In many, but not all cases, the method may just return
		 * a reference to this. This method is annotated @CheckReturnValue
		 * because you have to pay attention to the return value; if you simply
		 * invoke delete on a Tree and ignore the return value, your code is
		 * almost certainly wrong.
		 * 
		 * @param key -- Key
		 * @return -- updated tree
		 */
		int compare = key.compareTo(this.key);

		if (compare < 0) {
			leftTree = leftTree.delete(key);
		} else if (compare > 0) {
			rightTree = rightTree.delete(key);
		} else {
			K replacementKey;
			V replacementValue;

			try {
				replacementKey = leftTree.max();
			} catch (TreeIsEmptyException e) {
				return rightTree;

			}

//			try {
//				replacementKey = rightTree.min();
//			} catch (TreeIsEmptyException e) {
//				return leftTree;
//
//			}

			replacementValue = search(replacementKey);
			this.key = replacementKey;
			value = replacementValue;
			leftTree = leftTree.delete(replacementKey);
			rightTree = rightTree.delete(replacementKey);
		}

		return this;
	}

	public K max() {
		/**
		 * Return the maximum key in the subtree
		 * 
		 * @return maximum key
		 * @throws TreeIsEmptyException if the tree is empty
		 */
		try {
			return rightTree.max();
		} catch (TreeIsEmptyException e) {
			return key;
		}

	}

	public K min() {
		/**
		 * Return the minimum key in the subtree
		 * 
		 * @return minimum key
		 * @throws TreeIsEmptyException if the tree is empty
		 */
		try {
			return leftTree.min();
		} catch (TreeIsEmptyException e) {
			return key;
		}

	}

	public int size() {
		/**
		 * Return number of keys that are bound in this tree.
		 * 
		 * @return number of keys that are bound in this tree.
		 */
		int leftSize = leftTree.size();
		int rightSize = rightTree.size();
		return leftSize + rightSize + 1;
	}

	public void addKeysToCollection(Collection<K> c) {
		/**
		 * Add all keys bound in this tree to the collection c. The elements can
		 * be added in any order.
		 */
		c.add(key);
		leftTree.addKeysToCollection(c);
		rightTree.addKeysToCollection(c);

	}

	public Tree<K, V> subTree(K fromKey, K toKey) {
		/**
		 * Returns a Tree containing all entries between fromKey and toKey,
		 * inclusive. It may not modify the original tree (a common mistake
		 * while implementing this method).
		 * 
		 * @param fromKey - Lower bound value for keys in subtree
		 * @param toKey   - Upper bound value for keys in subtree
		 * @return Tree containing all entries between fromKey and toKey,
		 *         inclusive
		 */
		int compareFrom = key.compareTo(fromKey);
		int compareTo = toKey.compareTo(key);
		if (compareTo < 0) {
			return leftTree.subTree(fromKey, toKey);
		}
		else if (compareFrom < 0) {
			return rightTree.subTree(fromKey, toKey);
		}

		else {
			Tree<K, V> subTree = new NonEmptyTree<>(key, value,
					leftTree.subTree(fromKey, toKey),
					rightTree.subTree(fromKey, toKey));
			return subTree;
		}
//		} else if (compareTo < 0) {
//			return leftTree.subTree(fromKey, toKey);
//		} else if (compareFrom < 0) {
//			return rightTree.subTree(fromKey, toKey);
		
	}

	public int height() {
		/**
		 * Returns the height (maximum level) in the tree. A tree with only one
		 * entry has a height of 1.
		 * 
		 * @return height of the tree.
		 */
		int leftHeight = leftTree.height();
		int rightHeight = rightTree.height();
		return Math.max(leftHeight, rightHeight) + 1;
	}

	public void inorderTraversal(TraversalTask<K, V> p) {
		/**
		 * Performs the specified task on the tree using an inorder traversal.
		 * 
		 * @param p object defining task
		 */
		leftTree.inorderTraversal(p);
		p.performTask(key, value);
		rightTree.inorderTraversal(p);

	}

	public void rightRootLeftTraversal(TraversalTask<K, V> p) {
		/**
		 * Performs the specified task on the tree using a right tree, root,
		 * left tree traversal.
		 * 
		 * @param p object defining task
		 */
		rightTree.rightRootLeftTraversal(p);
		p.performTask(key, value);
		leftTree.rightRootLeftTraversal(p);
	}
}