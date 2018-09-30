package uppgift6;

import java.util.ArrayList;

import org.omg.CORBA.Current;

import uppgift3.PairDTO;

public class TextFindST<Key extends Comparable<Key>> {
	private TextFindStack<Integer> valStack = new TextFindStack<Integer>();
	private Node root;
	boolean notInList = true;

	private class Node {
		Node next;
		int length;
		private Key key;
		private Integer val;
		private int N;
		private Node hi;
		private Node lo;

		public Node(Key key, Integer val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}

		public int size(Node node) {
			if (null != node) {
				return N;
			}
			return 0;
		}

		public Integer getValue() {
			return val;
		}

		public Key getKey() {
			return key;
		}
	}
	public Integer get(Key key) {
		if (!isEmpty()) {
			if (key.compareTo(root.key) == 0) {
				return root.val;
			}
			Node current = root;
			while (current != null) {
				int cmp = key.compareTo(current.key);
				if (cmp == 0) {
					return current.val;
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

	public void put(Key key, Integer value) {
		if (isEmpty()) {
			root = new Node(key, value, 1);
			return;
		}
		root = put(key, value, root);
	}
	/**
	 * add another key to the list, if key doesn't exists, adds new node to the subtree
	 * @param key
	 * @param val
	 * @param root
	 * @return
	 */
	public Node put(Key key, Integer val, Node root) {
		
		if (root == null) {
			return new Node(key, val, 1);
		}

		int cmp = key.compareTo(root.key);
		if (cmp > 0) {
			root.hi = put(key, val, root.hi);
		}

		else if (cmp < 0) {
			root.lo = put(key, val, root.lo);
		}

		else {
			root.next = put(key, val, root.next);
		}

		root.N = root.size(root.hi) + root.size(root.lo) + 1;
		return root;
	}

	public Integer[] getWordIndices(Key key) {
		if (isEmpty()) {
			return null;
		}
		traverse(key,root);
		return stackToArray();
	}

	@SuppressWarnings("unchecked")
	private void traverse(Key key, Node root) {
		if(root.getKey() == "The") {
			System.out.println("key");
		}
		if (root.lo != null) {
			if(key.compareTo(root.getKey()) == 0){
//				System.out.println("key"+ key +" currentKey: "+ current.getKey());
				valStack.push(root.getValue());
			}
			traverse(key,root.lo);
		}

		if (root.hi != null) {
			if(key.compareTo(root.getKey()) == 0){
//				System.out.println(current.getValue());
				valStack.push(root.getValue());
			}
			traverse(key,root.hi);
		}
	}
	
	private Integer[] stackToArray() {
		uppgift6.TextFindStack.Node current = valStack.head;
		Integer[] indices = new Integer[valStack.size];
		int i = 0;
		while(valStack.head != null) {
			indices[i] = valStack.pop();
			System.out.println(" : 0"+indices[i]);
			i++;
		}
		return indices;
		
	}

}