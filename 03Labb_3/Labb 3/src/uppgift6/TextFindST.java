package uppgift6;

import java.util.ArrayList;

import org.omg.CORBA.Current;

import uppgift3.PairDTO;
/**
 * README
 * Symbol table implemented in a binary search tree
 * @author danielduner
 *
 * @param <Key>
 */
public class TextFindST<Key extends Comparable<Key>> {
//	private TextFindStack<Integer> valStack = new TextFindStack<Integer>();
	private Node root;
	boolean notInList = true;

	private class Node {
		private TextFindStack<Integer> vals = new TextFindStack<Integer>();
		private Key key;
		private int N;
		private Node hi;
		private Node lo;

		public Node(Key key, Integer val, int N) {
			this.key = key;
			this.N = N;
			setVal(val);
		}

		public int size(Node node) {
			if (null != node) {
				return N;
			}
			return 0;
		}

		public TextFindStack<Integer> getVal() {
			return vals;
		}

		public Key getKey() {
			return key;
		}
		public void setVal(Integer val) {
			vals.push(val);
	}
	}
	
	/**
	 * Returns the Integer for searched key
	 */
	public TextFindStack<Integer> get(Key key) {
		
		//Compares the key to the root
		if (!isEmpty()) {
			if (key.compareTo(root.key) == 0) {
				return root.vals;
			}
			
			Node current = root;
			while (current != null) {
				int cmp = key.compareTo(current.key);
				if (cmp == 0) {
					return current.vals;
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
		return null == root;
	}


	public void reset() {
		root = null;
	}

	@SuppressWarnings("unused")
	public void put(Key key, Integer value) {
		if (isEmpty()) {
			root = new Node(key, value, 1);
			return;
		}
		root = put(key, value, root);
	}
	/**
	 * add another key to the list, if key doesn't exists, adds new node to the subtree
	 */
	public Node put(Key key, Integer val, Node root) {
		//If root is == null returns new node;
		if (root == null) {
			return new Node(key, val, 1);
		}
		int cmp = key.compareTo(root.key);
		//If key is larger than current.key go higher
		if (cmp > 0) {
			root.hi = put(key, val, root.hi);
		}
		//If key is smaller than current.key go lower
		else if (cmp < 0) {
			root.lo = put(key, val, root.lo);
		}
		//if key is the same instead add to queue
		else {
			root.setVal(val);
		}

		root.N = root.size(root.hi) + root.size(root.lo) + 1;
		return root;
	}

	public TextFindStack<Integer> getWordIndices(Key key) {
		if (isEmpty()) {
			return null;
		}
		return get(key);
		}
		
	}


