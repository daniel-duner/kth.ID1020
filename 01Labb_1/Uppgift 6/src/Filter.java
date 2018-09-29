import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * This program checks the balance of brackets in a text file.
 * 
 * @author danielduner
 *
 */
public class Filter {
	private Scanner file;
	private char[] fileText;
	private Stack<Character> stack = new Stack<Character>();
	
	/**
	 * Stack first in last out
	 * @author danielduner
	 *
	 * @param <T>
	 */
	private class Stack<T>{
			Node first;
			int size;
		/**
		 * The Node of the list
		 * @author danielduner
		 *
		 */
		private class Node{
			T element;
			Node next;
			public Node(T element) {
				this.element = element;
			}
			
			public T getElement(){
				return element;
			}
		}
		/**
		 * pushes the element onto the stack
		 * @param element element is the element pushed to the stack
		 */
		private void push(T element){
			if(first == null) {
				first = new Node(element);
			} else {
				Node newNode = new Node(element);
				newNode.next = first;
				first = newNode;
			}
			size++;
		}
		
		/**
		 * pops the last element
		 * @return returns the element T
		 */
		private T pop(){
			T r = first.getElement();
			if(first.next != null) {				
				first = first.next;
			}else {
				first = null;
			}
			size--;
			return r;
		}
		/**
		 * @return returns true if empty and false if not
		 */
		private boolean isEmpty(){
			return first == null;
		}
		/**
		 * looks at the last added element on the list;
		 * @return
		 */
		public T peek(){
			if(!isEmpty()) {				
				return first.getElement();
			}else {
				System.out.println("Stack is empty");
				return null;
			}
		}
		/**
		 * Clears the stack
		 */
		public void clear() {
			first = null;
		}
		/**
		 * @return returns the size of the stack
		 */
		public int size() {
			return size;
		}
		
		
	}
	
	

	/**
	 * Saves a file and saves the text in the char array;
	 * 
	 * @param fileName name of the file to open
	 */
	public void openFile(String fileName) {
		try {
			file = new Scanner(new File(fileName + ".txt"));
			convertTextToCharArray();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
	}

	/**
	 * Converts the text in the current file to a char array
	 */
	private void convertTextToCharArray() {
		String text = "";
		while (file.hasNext()) {
			text += file.next();
		}
		fileText = text.toCharArray();
	}

	/**
	 * Checks if the arguments match each other, first argument has to be either
	 * {[(, second argument has to be either )]}
	 * 
	 * @param bracketInText is either )]}
	 * @param bracketOnStack is either ([{
	 * @return returns true if the arguments match or false if they don't match
	 */
	private boolean matchBrackets(char bracketInText, char bracketOnStack) {
		if (bracketOnStack == '(' && bracketInText == ')') {
			return true;
		} else if (bracketOnStack == '[' && bracketInText == ']') {
			return true;
		} else if (bracketOnStack == '{' && bracketInText == '}') {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks the bracket balance of the text file. If there is an opening bracket, {([, it will be pushed to the stack.
	 * If there is a closing bracket, )]}, the function will look on the top of the stack and match the current closing 
	 * bracket with the top bracket and if they don't match it will return false, if they match it will pop the opening 
	 * bracket on the top of the stack and go on 
	 * 
	 * @return returns true if there is a balance between opening and closing
	 *         brackets and returns false if there is something wrong with either
	 *         their placement or if there is an unbalanced amount of any kinds of
	 *         brackets
	 */
	private boolean checkBracketBalance() {
		for (char character : fileText) {
			if (character == '{' || character == '(' || character == '[') {
				stack.push(character);
			} else if (character == '}' || character == ')' || character == ']') {
				if (!stack.isEmpty()) {
					if (matchBrackets(character, stack.peek())) {
						stack.pop();
					} else {
						System.out.println("A " + stack.peek()+" was never closed before another kind of closing bracket appeared in the text");
						stack.clear();
						return false;
					}
				} else {
					System.out.println("There was a " + character + " and no opening bracket before it");
					stack.clear();
					return false;
				}
			}
		}
		if (!stack.isEmpty()) {
			System.out.println(stack.size() + " opening brackets are never closed");
			stack.clear();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Tests 4 files
	 * File 1: (((()	should return false since there is not enough closing brackets
	 * File 2: [[(])]	should return false since there is a closing bracket of the wrong kind before the opening bracket of another kind has been closed 
	 * File 3: )		should return false since there is no opening bracket before the closing bracket
	 * File 4: {[()]}	should return true
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Filter f = new Filter();
//		f.openFile("C:\\Users\\danielduner\\Dropbox\\05KTH\\03Algoritmer och datastrukturer\\Uppgift 6\\bin\\myCode");
		f.openFile("/Users/danielduner/Dropbox/05KTH/03Algoritmer och datastrukturer/Uppgift 6/testparentheses1");
		System.out.println("Is the text file balanced? it is "+f.checkBracketBalance());
		System.out.println("-------------------");
		f.openFile("/Users/danielduner/Dropbox/05KTH/03Algoritmer och datastrukturer/Uppgift 6/testparentheses2");
		System.out.println("Is the text file balanced? it is "+f.checkBracketBalance());
		System.out.println("-------------------");
		f.openFile("/Users/danielduner/Dropbox/05KTH/03Algoritmer och datastrukturer/Uppgift 6/testparentheses3");
		System.out.println("Is the text file balanced? it is "+f.checkBracketBalance());
		System.out.println("-------------------");
		f.openFile("/Users/danielduner/Dropbox/05KTH/03Algoritmer och datastrukturer/Uppgift 6/testparentheses4");
		System.out.println("Is the text file balanced? it is "+f.checkBracketBalance());
	}
}
