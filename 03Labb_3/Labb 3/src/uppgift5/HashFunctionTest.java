package uppgift5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import uppgift2.BST;

public class HashFunctionTest<T> {
	
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		Scanner sc = new Scanner(new FileReader("src/resources/parsedText.txt"));
		checkHashCollisons(sc);
//		System.out.println("He".hashCode() +" "+ "IF".hashCode());
	}

	
	public static void checkHashCollisons(Scanner sc) throws InterruptedException {
		
		//Processing the text, adding them to a symbol table
		HashBST<String> st = new HashBST<String>();
		while(sc.hasNext()) {
			String word = sc.next();
			st.put(word, new Integer(1));
		}
		
		ArrayList<String> keys = st.getKeys();
		ArrayList<Integer> hashCodes = new ArrayList<Integer>();
		
		//adds all keys (not duplicates) to a list in hashcode format
		for(int i = 0; i < keys.size();i++) {
			hashCodes.add(keys.get(i).hashCode());
		}
		//Moves the hashCoded data in to a symbol table
		HashBST<Integer> bst = new HashBST<Integer>();
		for(int i = 0; i < hashCodes.size();i++) {
			
			if (!bst.contains(hashCodes.get(i)))
				bst.put(hashCodes.get(i), new Integer(1));
			else
				bst.put(hashCodes.get(i), new Integer(bst.get(hashCodes.get(i)) + 1));
		}

		
		printArrayList(bst.getCollisons(new Integer(1)));
		
//		int i = 0;
//		while (i < keys.size()) {
//			System.out.println(i+". "+keys.get(i));
//			i++;
//			if(i == 2000 || i == 4000 || i == 6000) {
////				TimeUnit.SECONDS.sleep(20);
//			}
//		}
		
//		printArrayList(bst.getKeys());
	}
	
	public static void printArrayList(ArrayList arr) throws InterruptedException {
		for(int i = 0; i< arr.size();i++) {
			System.out.println((i+1)+". "+arr.get(i));
//			if(i%2000 == 0) {
//				TimeUnit.SECONDS.sleep(0);
//			}
		}
	}
	
}
