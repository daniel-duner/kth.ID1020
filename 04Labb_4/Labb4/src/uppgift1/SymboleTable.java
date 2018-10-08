package uppgift1;

/**
 * README
 * An ordered symbol table for generic keys and values
 * 
 * @author danielduner
 *
 * @param <String>
 * @param <Integer>
 */
public class SymboleTable {
	private String keys[];
	private Integer values[];
	private int N;

	/**
	 * Constrcutor of the symbol table
	 * 
	 * @param capacity
	 */
	public SymboleTable(int capacity) {
		this.keys = new String[capacity];
		this.values = new Integer[capacity];
		N = 0;
	}

	/**
	 * Adds a pair to the array
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, Integer value) {
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
//		if (i < N && compare(keys[i],key)) {
			values[i]++;
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
	
	private boolean compare(String toCompare, String comparingWith) {
		if((toCompare.charAt(0) == comparingWith.charAt(0)) && (toCompare.charAt(1) == comparingWith.charAt(1))||
				(toCompare.charAt(1) == comparingWith.charAt(0)) && (toCompare.charAt(0) == comparingWith.charAt(1))){
			return true;
		}
		return false;
	}

	/**
	 * Returns a value at given key, if the key doesn't exist 0 will be returned
	 * 
	 * @param key
	 * @return
	 */
	public Integer get(String key) {
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
	public int rank(String key) {
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
	private boolean contains(String key) {
		return get(key) != null;
	}

	/**
	 * resizes the symbol table with the parameter as its new length
	 * 
	 * @param newSize
	 */
	@SuppressWarnings("unchecked")
	private void reSize(int newSize) {
		String newKeys[] = new String[newSize];
		Integer newValues[] = new Integer[newSize];
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
	public Integer[] getValues() {
		return this.values;
	}

	/**
	 * returns the <Key> array
	 * 
	 * @return
	 */
	public String[] getKeys() {
		return this.keys;
	}

	/**
	 * returns the Key of the pair with the largest value
	 * 
	 * @param max
	 * @param lowest
	 * @return
	 */
	public String getMax(String max, Integer lowest) {
		put(max, lowest);

		for (int i = 0; i < size(); i++) {
			String word = keys[i];
			if (get(word).compareTo(get(max)) > 0) {
				max = word;
			}
		}
		return max;
	}
}

