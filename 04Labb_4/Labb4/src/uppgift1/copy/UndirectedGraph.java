package uppgift1.copy;

import java.net.*;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;

public class UndirectedGraph {

	@SuppressWarnings({ "resource", "unchecked" })
	public static void main(String[] args) throws IOException {
		URL myURL = new URL("https://introcs.cs.princeton.edu/java/data/contiguous-usa.dat");
		UndirectedGraph n = new UndirectedGraph(myURL);
		System.out.println(n.toString());
		System.out.println(n.getV());
		System.out.println(n.edges);
	}

	private final int CONSTANT = 26;
	@SuppressWarnings("unchecked")
	private String[] vertices = new String[CONSTANT];
	@SuppressWarnings("unchecked")
	private Bag<String>[] adj = new Bag[CONSTANT];
	private int V;
	private int edges;

	@SuppressWarnings("resource")
	public UndirectedGraph(URL url) throws IOException {
		Scanner sc = new Scanner(url.openStream());
		while (sc.hasNext()) {
			String v = sc.next();
			String w = sc.next();
			addEdge(v,w);
			addEdge(w,v);
//			System.out.println("v: "+v+" w: "+w);
		}
	}

	public void addEdge(String v, String w) {
		int i = rank(v);
		if (contains(v)) {
			adj[i].push(w);
			edges++;
			return;
		} 
		if (V + 1 == vertices.length) {
			reSize(vertices.length * 2);
		} else if (V < vertices.length / 3) {
			reSize(vertices.length / 2);
		}
		for (int j = V; j > i; j--) {
			vertices[j] = vertices[j - 1];
			adj[j] = adj[j - 1];
		}
		V++;
		edges++;
		adj[i] = new Bag<String>();
		vertices[i] = v;
		adj[i].push(w);
	
//			System.out.println("vertice: "+vertices[i]);
//			System.out.println("adj: "+ adj[i].peek());

	}

	@SuppressWarnings("unchecked")
	private void reSize(int newSize) {
		String[] vertices = new String[newSize];
		Bag<String>[] adj = new Bag[newSize];
		int i = 0;
		while (i < V) {
			vertices[i] = this.vertices[i];
			adj[i] = this.adj[i];
			i++;
		}
		this.vertices = vertices;
		this.adj = adj;

	}

	private boolean isEmpty() {
		return V == 0;
	}

	public String get(String key) {
		if (!isEmpty()) {
			return vertices[rank(key)];
		}
		return null;
	}

	public boolean contains(String v) {
//		return get(v) != null;
		boolean contains = false;
		for(int i = 0; i < V;i++) {
			if(v.compareTo(vertices[i]) == 0) {
				contains = true;
			}
		}
		return contains;
	}

	public int rank(String key) {
		int hi = V - 1;
		int lo = 0;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(vertices[mid]);
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


	public int getV() {
		return V;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < V;i++){
			str.append(vertices[i]+": ");
			for (Iterator<String> it = adj[i].iterator(); it.hasNext();) {
				str.append("["+it.next()+"] ");
			}
			str.append("\n");	
		}
		return str.toString();

	}

	public Bag[] getAdj() {
		return adj;
	}

	private int charIndex(char letter) {
		return (int) letter - 65;
	}

	private char indexToChar(int charValue) {
		return (char) (charValue + 65);
	}

}
