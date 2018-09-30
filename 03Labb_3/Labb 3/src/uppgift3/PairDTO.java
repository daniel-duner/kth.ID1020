package uppgift3;

public class PairDTO<Key extends Comparable <Key>, Value extends Comparable<Value>> {
	
	private Key key;
	private Value value;
	
	public PairDTO(Key key, Value value) {
		this.key = key;
		this.value = value;
	}

	public Key getKey(){
		return key;
	}
	
	public Value getValue() {
		return value;
	}
	
	public String toString() {
		return new StringBuilder(key+" was used "+ value+" times").toString();
	}
	
}
