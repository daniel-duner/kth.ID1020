package uppgift2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import uppgift3.PairDTO;
import uppgift3.myStack;

/**
 * Symbol table implemented in a Binary search tree
 * 
 * @author danielduner
 *
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
	private Node root;
	private Value maxV;
	private Key maxK;
	private PairDTO<Key, Value> minV;
	private myStack<Key, Value> maxStack = new myStack<Key, Value>();
	private boolean first = true;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Scanner sc;
		sc = new Scanner(new FileReader("src/resources/parsedText.txt"));
		int minlen = 1;
		PrintWriter writer = new PrintWriter("src/resources/text.txt","UTF-8");
		while(sc.hasNext()) {
			String word = sc.next().toLowerCase();
			writer.print(word+" ");
		}
		sc = new Scanner(new FileReader("src/resources/text.txt"));
		StopWatch sw = new StopWatch();
		// key-length cutoff
		BST<String, Integer> st = new BST<String, Integer>();

		sw.start();
		while (sc.hasNext()) { // Build symbol table and count frequencies.
			String word = sc.next();
			if (word.length() < minlen)
				continue; // Ignore short keys.
			if (!st.contains(word))
				st.put(word, new Integer(1));
			else
				st.put(word, new Integer(st.get(word) + 1));
		}
		// Find a key with the highest frequency count.
//		String max = st.getMax();
		String max = st.getMax(5);
		sw.stop();
		System.out.println(max);
	}
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
		 */
		public Node(Key key, Value value, int n) {this.key = key;this.value = value;this.N = n;}

		/**
		 * total number of nodes in the tree
		 */
		public int size() {return size(root);}

		/**
		 * returns the amount of different pairs in the symbol table if the table is not empty;
		 * and returns 0 if the symbol table is empty and
		 */
		private int size(Node node) {if (node == null) {return 0;} else {return node.N;}}
	}
	/**
	 * puts a a new pair into the symbol table
	 */
	public void put(Key key, Value value) {if (isEmpty()) {root = new Node(key, value, 1);return;}root = put(key, value, root);}
	/**
	 * finds the right place in the tree and 
	 */
	private Node put(Key key, Value value, Node current) {
		if (current == null) {
			return new Node(key, value, 1);
		}
		int cmp = key.compareTo(current.key);
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
	/**
	 * Returns a value paired to a given key, if the key is not in the symbol table null will be returned
	 * @param key
	 * @return
	 */
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
	/**
	 * Returns true if the symbol table contains the searched key
	 * @param key
	 * @return
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}
	/**
	 * Returns true if the symbol table is empty and false if there is a pair in the symbol table
	 * @return
	 */
	private boolean isEmpty() {
		return root == null;
	}
	/**
	 * Searches for the max value in the symbol table and prints out the value and the 
	 * @return
	 */
	public void setMin() {
		if (!isEmpty()) {
			findMin(root);
		}

	}
	public void findMin(Node current) {
		if(first = true) {
			this.maxV = root.value;
			this.maxK = root.key;
			first = false;
		}
		if (current.lo != null) {
			if (current.value.compareTo(maxV) <= 0) {
				minV = new PairDTO<Key, Value>(current.key, current.value);
			}
			findMin(current.lo);
		}
		if (current.hi != null) {
			if (current.value.compareTo(maxV) <= 0) {
				minV = new PairDTO<Key, Value>(current.key, current.value);
			}
			findMin(current.hi);
		}
		first = true;
	}
	@SuppressWarnings("unchecked")
	public String getMax() {
		if (!isEmpty()) {
			setMin();
			this.maxV = (Value) minV.getValue();
			this.maxK = (Key) minV.getKey();
			traverse(root);
			String str = "The most frequent word is \"" + this.maxK + "\" and was used " + this.maxV+ " times";
			this.maxK = null;
			this.maxV = null;
			return str;
		}
		return null;
	}
	
	/**
	 * traverse the tree looking for maximum value
	 * @param current
	 */
	@SuppressWarnings("unchecked")
	private void traverse(Node current) {
		if (current.lo != null) {
			if (current.value.compareTo(maxV) > 0) {
				this.maxV = current.value;
				this.maxK = current.key;
			}
			traverse(current.lo);
		}
		if (current.hi != null) {
			if (current.value.compareTo(maxV) > 0) {
				this.maxV = current.value;
				this.maxK = current.key;
			}
			traverse(current.hi);
		}
	}
	
	/**
	 * returns the n most frequently used word
	 * @param n
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getMax(int n) {
		setMin();
		this.maxV = (Value) minV.getValue();
		this.maxK = (Key) minV.getKey();
		traverse(root);
		maxStack.push(this.maxK, this.maxV);
		for(int i = 0; i < n-1;i++) {
			this.maxV = (Value) minV.getValue();
			this.maxK = (Key) minV.getKey();
			traverseComp(root);
			maxStack.push(this.maxK, this.maxV);
		}
		return maxStack.toString();
		
	}
	
	/**
	 * Traverse nodes and saves the maximum value, that is smaller or equal to the last pair added 
	 * the stack of maximum values
	 * @param current
	 */
	@SuppressWarnings("unchecked")
	private void traverseComp(Node current) {
		if (current.lo != null) {
			if ((current.value.compareTo(maxV) > 0) && maxComp(current.key, current.value)) {
				this.maxV = current.value;
				this.maxK = current.key;
			}
			traverseComp(current.lo);
		}
		if (current.hi != null) {
			if ((current.value.compareTo(maxV) > 0) && maxComp(current.key, current.value)) {
				this.maxV = current.value;
				this.maxK = current.key;
			}
			traverseComp(current.hi);
		}
	}
	/**
	 * Compares keys and values with the top pair in the maxStack, if the key is not the same
	 * and the value is smaller it will return true, else it will return false
	 * @param key
	 * @param value
	 * @return
	 */
	private boolean maxComp(Key key, Value value) {
		int cmpV = value.compareTo((Value) maxStack.peekValue());
		if(maxStack.cmp(key) && (cmpV <= 0)) {
			return true;
		}
		return false;
	}

}

