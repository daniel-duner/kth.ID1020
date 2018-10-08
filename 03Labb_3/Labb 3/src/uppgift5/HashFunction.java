package uppgift5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import uppgift2.BST;
/**
 * README The program takes in our text and remove duplicate words and the turn the 
 * words into hashcode and checks if there is any collisions and prints out the hashcodes
 * that collides and the number of collisions
 * @author danielduner
 *
 * @param <T>
 */
public class HashFunction<T> {
	
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		Scanner sc = new Scanner(new FileReader("src/resources/parsedText.txt"));
		checkHashCollisons(sc);
//		System.out.println("He".hashCode() +" "+ "IF".hashCode());
	}

	
	public static void checkHashCollisons(Scanner sc) throws InterruptedException {
		
		//Processing the text, adding them to a symbol table st
		HashBST<String> st = new HashBST<String>();
		int minlen = 1;
		while(sc.hasNext()) {
			String word = sc.next();
			if (word.length() < minlen)
				continue; // Ignore short keys.
			else
				st.put(word, new Integer(1));
		}
		// adds keys from st and to keys stack
		HashStack<String> keys = st.getKeys();
		HashStack<Integer> hashCodes = new HashStack<Integer>();
		
		//adds all keys (not duplicates) to a list in hashCode format
		while(!keys.isEmpty()) {
			hashCodes.push(keys.pop().hashCode());
		}
		
		int count = 0;
		HashBST<Integer> bst = new HashBST<Integer>();
		while(!hashCodes.isEmpty()) {
			if (!bst.contains(hashCodes.peek())) {				
				bst.put(hashCodes.pop(), new Integer(1));
			}
			else if(bst.contains(hashCodes.peek())) {
				count++;
				System.out.println(count+". "+hashCodes.pop());
			}
		}
		System.out.println("There are: " +count+" collisions");
	}
	
}
