package uppgift6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
/**
 * README
 * Ett program som visar alla indexeringar som ett visst ord har;
 */
public class TextFind {
	TextFindST<String> st = new TextFindST<String>();
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("src/resources/parsedText.txt"));
		TextFind tf = new TextFind(sc);
		Integer[] indices = tf.wordAt("the");
		for(int i = 0; i < indices.length;i++) {
			System.out.println(indices[i]);
		}
		
		
	}
	
	public TextFind(Scanner sc) {
		loadST(sc);
	}
	
	private void loadST(Scanner sc) {
		
		int i=0;
		while(sc.hasNext()) {
			String word = sc.next();
			st.put(word, i);
			i += word.length()-1;
		}
		
	}
	
	public Integer[] wordAt(String word) {
		if(!st.contains(word)) {
			return null;
		}
		Integer[] indices = st.getWordIndices(word);
		
		return indices;
	}
	
	
}
