package uppgift2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * README
 * Reads characters until enter is pressed and then saves 
 * them as a String and then pushes each character onto the stack
 * @author danielduner
 *
 */
public class KeyReader{
	BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	Stack stack = new Stack();
	
	/**
	 * A implementation of a FILO stack
	 * @author danielduner
	 */
	private class Stack{
		private char stack[] = new char[20];
		private int size = 0;
		
		private void reSizeStackUp(){
			char newStack[] = new char[2*this.stack.length];
			for(int i = 0; i < this.stack.length; i++) {
				newStack[i] = this.stack[i];
			}
			this.stack = newStack;
		}
		private void reSizeStackDown(){
			char newStack[] = new char[this.stack.length/2];
			for(int i = 0; i < this.stack.length; i++) {
				newStack[i] = this.stack[i];
			}
			this.stack = newStack;
		}
		
		/**
		 * pushes an element onto the stack
		 * @param element the element which is being pushed onto the stack
		 */
		private void push(char element){
			if(size == stack.length-1) {
				reSizeStackUp();
			}
			if(size/3 == stack.length) {
				reSizeStackDown();
			}
			stack[size] = element;
			size++;
		}
		/**
		 * Pops the last element that was put onto the stack from the stack
		 * @return returns the element
		 */
		private char pop(){
			if(!isEmpty()) {
				char last = stack[size-1];
				size--;
				return last;
			}
			return 0;
		}
		
		/**
		 * Checks if the stack is empty
		 * @return returns true if the stack is empty
		 */
		private boolean isEmpty(){
			return size == 0;
		}
		/**
		 * Checks the size of the stack
		 * @return returns the size of the stack
		 */
		private int getSize() {
			return size;
		}
		private char getElement(int i) {
			return stack[i];
		}
		
	}
	/**
	 * A recursive method that reads a character from the stdin 
	 * and puts the character onto the stack until enter is pressed
	 * then the thing written will be returned backwards
	 * @throws <IOException>
	 */
	private void recursiveMethod() throws IOException {
		char key;
			key = (char)r.read();
			if(key != '\n') {
				stack.push(key);
				recursiveMethod();
			}
			if(!stack.isEmpty()) {				
				System.out.print(stack.pop());
			}

		return;
	}
	/**
	 * A loop method that reads a character from the stdin 
	 * and puts the character onto the stack until enter is pressed
	 * then the thing written will be returned backwards
	 * @throws <IOException>
	 */
	private void loopMethod() throws IOException {
		boolean condition = true;
		
		while (condition) {
			char key = (char)r.read();
			if(key == '\n') {
				condition = false;
			} else {
				stack.push(key);
			}
		}
		
		while (!stack.isEmpty()) {
			System.out.print(stack.pop());
		}
	}
	/**
	 * Converts the stack into a String
	 */
	public String toString(){
		StringBuilder str =  new StringBuilder();
		int i = 0;
		while(i < stack.getSize()) {
			if(i+1<stack.getSize()) {				
				str.append("["+stack.getElement(i)+"],");
			} else {
				str.append("["+stack.getElement(i)+"]");
			}
			i++;
		}
		return str.toString();
	}

	/**
	 * Test reads characters from the command line, first the iterative method, then the loop method
	 * @param args
	 * @throws <IOException>
	 */
	public static void main(String[] args) throws IOException {
		KeyReader keyReader = new KeyReader();
		keyReader.recursiveMethod();
		System.out.println("");
		keyReader.loopMethod();
	}
	
}
