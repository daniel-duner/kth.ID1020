package uppgift2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Symbol table implemented in a Binary search tree
 * 
 * @author danielduner
 *
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value extends Comparable> {
	private Node root;
	private Value maxV;
	private Key maxK;

	/**
	 * Nodes in the tree
	 * 
	 * @author danielduner
	 *
	 */
	private class Node {
		private int N;
		private Key key;
		private Value value;
		private Node hi;
		private Node lo;

		/**
		 * Constructor of a node
		 * 
		 * @param key
		 * @param value
		 * @param n     number of nodes under this node (including this node)
		 */
		public Node(Key key, Value value, int n) {
			this.key = key;
			this.value = value;
			this.N = n;
		}

		/**
		 * total number of nodes in the tree
		 * 
		 * @return
		 */
		public int size() {
			return size(root);
		}

		/**
		 * returns the amount of different pairs in the symbol table if the table is not empty;
		 * and returns 0 if the symbol table is empty and
		 * 
		 * 
		 * @param node
		 * @return
		 */
		private int size(Node node) {
			if (node == null) {
				return 0;
			} else {
				return node.N;
			}
		}

	}
	/**
	 * puts a a new pair into the symbol table
	 * @param key
	 * @param value
	 */
	public void put(Key key, Value value) {
		if (isEmpty()) {
			root = new Node(key, value, 1);
			return;
		}
		root = put(key, value, root);
	}
	/**
	 * finds the right place in the tree and 
	 * @param key
	 * @param value
	 * @param current
	 * @return
	 */
	private Node put(Key key, Value value, Node current) {
		if (current == null) {
			return new Node(key, value, 1);
		}
		// compares the key to the key in the current node
		int cmp = key.compareTo(current.key);
		// if key is a bigger number
		if (cmp > 0) {
			current.hi = put(key, value, current.hi);
		} else if (cmp < 0) {
			current.lo = put(key, value, current.lo);
		} else {
			current.value = value;
		}
		current.N = current.size(current.hi) + current.size(current.lo) + 1;
		return current;
	}

	public Value get(Key key) {
		if (!isEmpty()) {
			if (key.compareTo(root.key) == 0) {
				return root.value;
			}
			Node current = root;
			while (current != null) {
				int cmp = key.compareTo(current.key);
				if (cmp == 0) {
					return current.value;
				} else if (cmp < 0) {
					if (current.lo == null) {
						return null;
					}
					current = current.lo;
				} else if (cmp > 0) {
					if (current.hi == null) {
						return null;
					}
					current = current.hi;
				}

			}
		}
		return null;

	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	private boolean isEmpty() {
		return root == null;
	}

	public String getMax() {
		if (!isEmpty()) {
			traverse(root);
			return "The most frequent word is \"" + this.maxK + "\" and was used " + this.maxV+ " times";
		}
		return null;
	}

	private void traverse(Node current) {
		if (current == root) {
			this.maxV = root.value;
			this.maxK = root.key;
		}
		if (current.lo != null) {
			if (current.value.compareTo(maxV) > 0) {
				this.maxV = current.value;
			}
			traverse(current.lo);
		}
		if (current.hi != null) {
			if (current.value.compareTo(maxV) > 0) {
				this.maxV = current.value;
			}
			traverse(current.hi);
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc;
		sc = new Scanner(new FileReader("src/resources/parsedText.txt"));
		int minlen = 1;
		StopWatch sw = new StopWatch();
		// key-length cutoff
		BST<String, Integer> st = new BST<String, Integer>();

		sw.start();
		while (sc.hasNext()) { // Build symbol table and count frequencies.
			String word = sc.next();
			if (word.length() < minlen)
				continue; // Ignore short keys.
			if (!st.contains(word))
				st.put(word, 1);
			else
				st.put(word, st.get(word) + 1);
		}
		// Find a key with the highest frequency count.
		String max = st.getMax();
		sw.stop();
		System.out.println(max);

	}

}
