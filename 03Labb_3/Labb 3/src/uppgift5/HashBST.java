package uppgift5;

import java.util.ArrayList;

import uppgift3.PairDTO;

@SuppressWarnings("hiding")
public class HashBST<Key extends Comparable<Key>> {
	private ArrayList<Key> keys = new ArrayList<>();
	private ArrayList<PairDTO<Key, Integer>> col = new ArrayList<PairDTO<Key,Integer>>();
	private Node root;
	boolean notInList = true;

	private class Node {
		private Key key;private Integer val;private int N;private Node hi;private Node lo;
		public Node(Key key, Integer val, int N) {this.key = key;this.val = val;this.N = N;}
		public int size(Node node) {if (null != node) {return N;}return 0;}
		public Integer getValue() {return val;}
		public Key getKey() {return key;}
	}

	private boolean isEmpty() {return null == root;}
	public ArrayList<Key> getKeys() {return keys;}
	public void reset() {root = null;keys.clear();}
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
	public void put(Key key, Integer value) {
		if (isEmpty()) {
			root = new Node(key, value, 1);
			keys.add(key);
			return;
		}
		root = put(key, value, root);
		if(notInList) {
			keys.add(key);
		}
		notInList = true;
	}

	public Node put(Key key, Integer val, Node current) {
		if (current == null) {return new Node(key, val, 1);}
		
		int cmp = key.compareTo(current.key);
		if (cmp > 0) {current.hi = put(key, val, current.hi);}
		
		else if (cmp < 0) {current.lo = put(key, val, current.lo);} 
		
		else {current.val = val;notInList = false;}
		
		current.N = current.size(current.hi) + current.size(current.lo) + 1;
		return current;
	}
		
	public ArrayList<PairDTO<Key, Integer>> getCollisons(Integer single) {
		if(isEmpty()) {return null;}
		traverse(root, single);return col;}
	
	@SuppressWarnings("unchecked")
	private void traverse(Node current, Integer single) {
		
		if (current.lo != null) {
			if(current.getValue().compareTo(single) > 0) {
				col.add(new PairDTO<Key, Integer>(current.key,current.val));}
			traverse(current.lo, single);}
		
		if (current.hi != null) {if(current.getValue().compareTo(single) > 0) {
				col.add(new PairDTO<Key, Integer>(current.key,current.val));
			}
			traverse(current.hi, single);}
	}
	
	

}
