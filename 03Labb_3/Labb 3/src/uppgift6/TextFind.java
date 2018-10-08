package uppgift6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
/**
 * README
 * A program that looks up a word in a textfile 
 * and returns all indices where you can find the word.
 * Index 
 */
public class TextFind {
	TextFindST<String> st = new TextFindST<String>();
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("src/resources/parsedText.txt"));
		TextFind tf = new TextFind(sc);
		TextFindStack<Integer> indices = tf.wordAt("the");
		
		int i =0;
		int size = indices.size();
		while(i < size) {
			System.out.println((i+1)+". "+indices.pop("fifo"));
			i++;
		}
	}
	/**
	 * Loads the text into the symbol table
	 * @param sc
	 */
	public TextFind(Scanner sc) {
		loadST(sc);
	}
	
	private void loadST(Scanner sc) {
//		int count= 0;
		int i=0;
		while(sc.hasNext()) {
//			String word = sc.next();
			String word = sc.next().toLowerCase();
			st.put(word, i);
			i += word.length()-1;
//			count++;
		}
//		System.out.println("antal ord �r: "+ count);
	}
	/**
	 * returns the indices where the word is found
	 * @param word
	 * @return
	 */
	public TextFindStack<Integer> wordAt(String word) {
		if(!st.contains(word)) {
			return null;
		}
		TextFindStack<Integer> indices = st.getWordIndices(word);
		return indices;
	}
	
	
}
