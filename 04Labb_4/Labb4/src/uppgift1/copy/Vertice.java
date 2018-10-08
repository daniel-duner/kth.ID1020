package uppgift1.copy;

public class Vertice {
	private int value;
	private char c;
	
	public Vertice(char c) {
		this.c = c;
		this.value = (int)c-65;
	}
	
	public int getV() {
		return value;
	}
	
	public char getC() {
		return c;
	}
	
}
