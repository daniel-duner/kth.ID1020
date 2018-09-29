package uppgift2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;

/**
 * An ordered symbol table for generic keys and values
 * 
 * @author danielduner
 *
 * @param <Key>
 * @param <Value>
 */
public class ST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
	private Key keys[];
	private Value values[];
	private int N;

	/**
	 * Constrcutor of the symbol table
	 * 
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public ST(int capacity) {
		this.keys = (Key[]) new Comparable[capacity];
		this.values = (Value[]) new Comparable[capacity];
		N = 0;
	}

	/**
	 * Adds a pair to the array
	 * 
	 * @param key
	 * @param value
	 */
	public void put(Key key, Value value) {
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			values[i] = value;
			return;
		}
		if (N + 1 == keys.length) {
			reSize(keys.length * 2);
		} else if (N < keys.length / 3) {
			reSize(keys.length / 2);
		}
		for (int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			values[j] = values[j - 1];
		}
		keys[i] = key;
		values[i] = value;
		N++;
	}

	/**
	 * Returns a value at given key, if the key doesn't exist 0 will be returned
	 * 
	 * @param key
	 * @return
	 */
	public Value get(Key key) {
		if (!isEmpty()) {
			return values[rank(key)];
		}
		return null;
	}

	/**
	 * returns the index of given key or where it fits in
	 * 
	 * @param key
	 * @return
	 */
	public int rank(Key key) {
		int hi = N - 1;
		int lo = 0;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0) {
				hi = mid - 1;
			} else if (cmp > 0) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return lo;
	}

	/**
	 * returns the total amount of different pairs
	 * 
	 * @return
	 */
	private int size() {
		return N;
	}

	/**
	 * returns true if the symbol table is empty and false if there is one or more
	 * items in the symbol table
	 * 
	 * @return
	 */
	private boolean isEmpty() {
		return N == 0;
	}

	/**
	 * returns true if the symbol table contains the searched key
	 * 
	 * @param key
	 * @return
	 */
	private boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 * resizes the symbol table with the parameter as its new length
	 * 
	 * @param newSize
	 */
	@SuppressWarnings("unchecked")
	private void reSize(int newSize) {
		Key newKeys[] = (Key[]) new Comparable[newSize];
		Value newValues[] = (Value[]) new Comparable[newSize];
		int i = 0;
		while (i < N) {
			newKeys[i] = keys[i];
			newValues[i] = values[i];
			i++;
		}
		this.values = newValues;
		this.keys = newKeys;

	}

	/**
	 * returns the symbol table as a <string>
	 * @return
	 */
	public String ToString() {
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < N; i++) {
			str.append(keys[i] + ", " + values[i] + "\n");
		}

		return str.toString();
	}
	/**
	 * returns the <Value> array
	 * @return
	 */
	public Value[] getValues() {
		return this.values;
	}
	/**
	 * returns the <Key> array
	 * @return
	 */
	public Key[] getKeys() {
		return this.keys;
	}
	/**
	 * returns the Key of the pair with the largest value
	 * @param max
	 * @param lowest
	 * @return
	 */
	public Key getMax(Key max, Value lowest) {
		put(max, lowest);

		for (int i = 0; i < size(); i++) {
			Key word = keys[i];
			if (get(word).compareTo(get(max)) > 0) {
				max = word;
			}
		}
		return max;
	}
	/**
	 * The frequency counter takes in a text file and prints out the most 
	 * frequently acquired word and prints out the amount times it acquired
	 * and how long time the search took in ms.
	 * @param args
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc;
		sc = new Scanner(new FileReader("src/resources/parsedText.txt"));
		int minlen = 1;
		StopWatch sw = new StopWatch();
		// key-length cutoff
		ST<String, Integer> st = new ST<String, Integer>(10);

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
		sw.stop();
		String max = st.getMax("max", 0);
		System.out.println("The most frequent word is \""+max + "\" and was used " + st.get(max)+" times");

	}

//	@SuppressWarnings({ "resource", "unchecked", "deprecation" })
//	public static void main(String[] args) throws IOException {
//
//		ST<String, Integer> st = new ST<String, Integer>(10);
//		Scanner sc;
//		sc = new Scanner(new FileReader("src/resources/parsedText.txt"));
//		for (int i = 0; i < 1000; i++) {
//			String key = sc.next();
//			
//			if(st.contains(key)) {
//				st.put(key, (st.get(key).intValue()+1));
//			} else {				
//				st.put(key, 1);
//			}
//		}
//		System.out.println(st.ToString());
//	}
//	

}
