package uppgift1.copy;

import java.util.Iterator;

public class DFS {

	private boolean[] marked;
	private int[] edgeTo;
	private final int source;

	public DFS(UndirectedGraph G, char source) {
		marked = new boolean[G.getV()];
		edgeTo = new int[G.getV()];
		this.source = source;
		dfs(G, charIndex(source));
	}

	private void dfs(UndirectedGraph G, int v) {
		marked[v] = true;
		for (Iterator<Character> it = G.getAdj()[v].iterator(); it.hasNext();) {
			int i = it.next();
			if (!marked[i]) {
				edgeTo[i] = v;
				dfs(G, i);
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public Stack<Integer> pathTo(int v)
	{
	if (!hasPathTo(v)) {
		return null;
	}
	Stack<Integer> path = new Stack<Integer>();
	for (int x = v; x != source; x = edgeTo[x])
	path.push(x);
	path.push(source);
	return path;
	}

	public boolean marked(int w) {
		return marked[w];
	}
}
