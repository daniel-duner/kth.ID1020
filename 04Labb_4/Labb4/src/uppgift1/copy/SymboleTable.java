package uppgift1.copy;

/**
 * README
 * An ordered symbol table for generic keys and values
 * 
 * @author danielduner
 *
 * @param <String>
 * @param <Integer>
 */
public class SymboleTable<Key extends Comparable<Key>, Value extends Comparable<Value>> {
	private Key[] keys;
	private Bag<Value>[] values;
	private int N;

	/**
	 * Constrcutor of the symbol table
	 * 
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public SymboleTable(int capacity) {
		this.keys = (Key[]) new Comparable[capacity];
		this.values = new Bag[capacity];
		N = 0;
	}

	/**
	 * Adds a pair to the array
	 * 
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public void put(Key key, Value value) {
		int i = rank(key);

		if (i < N && keys[i].compareTo(key) == 0) {
			values[i].push(value);
			return;
		}
//		if (N + 1 == keys.length) {
//			reSize(keys.length * 2);
//		} else if (N < keys.length / 3) {
//			reSize(keys.length / 2);
//		}
		for (int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			values[j] = values[j - 1];
		}
		keys[i] = key;
		values[i].push(value);
		N++;
	}

	/**
	 * Returns a value at given key, if the key doesn't exist 0 will be returned
	 * 
	 * @param key
	 * @return
	 */
	public Bag<Value> get(Key key) {
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
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 * resizes the symbol table with the parameter as its new length
	 * 
	 * @param newSize
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void reSize(int newSize) {
		Key newKeys[] = (Key[]) new Comparable[newSize];
		Bag newValues[] = new Bag[newSize];
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
	 * 
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
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Bag[] getValues() {
		return this.values;
	}

	/**
	 * returns the <Key> array
	 * 
	 * @return
	 */
	public Key[] getKeys() {
		return this.keys;
	}



}
