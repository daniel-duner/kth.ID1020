package uppgift7;
/**
 * README A linked list where the element is sorted on push
 * @author danielduner
 *
 */
public class LinkedList {
	Node first;
/**
 * A node of the list
 * @author danielduner
 *
 */
	private class Node{
		int element;
		Node next;
		
		public Node(int element) {
			this.element = element;
		}
		
		public int getElement() {
			return this.element;
		}
	}
	/**
	 * Checks if the array is empty;
	 * @return
	 */
	private boolean isEmpty() {
		return first == null;
	}
	/**
	 * @return returns the element on the top of the list without removing it
	 */
	private int peek() {
		return first.getElement();
	}
	
	/**
	 * adds an element to an empty list
	 * @param element to be stored in the list
	 */
	private void sortFirst(int element) {
		first = new Node(element);
	}
	/**
	 * adds an element that is smaller then first element in the list
	 * @param element to be stored in the list
	 */
	private void sortLessThanFirst(int element) {
		Node newNode = new Node(element);
		newNode.next = first;
		first = newNode;
	}

	/**
	 * adds an element in a sorted position
	 * @param element to be stored in the list
	 */
	private void sortIn(int element) {
		Node newNode = new Node(element);
		Node current = first;
		
		while(current.next != null) {
			if(current.next.getElement() > element) {
				newNode.next = current.next;
				current.next = newNode;
				break;
			}
			current = current.next;
		}
		if(current.next == null) {
			current.next = newNode;
		}
	}
	
	/**
	 * adds an element to the list in sorted order
	 * @param element to be stored in the list
	 */
	public void push(int element) {
		
		if(isEmpty()) {
			sortFirst(element);
		} else if(first.getElement()>element) {
			sortLessThanFirst(element);
		} else {
			sortIn(element);
		}	
		printList();
	}
	/**
	 * removes the first element in the list
	 * @return element stored first in the list 
	 */
	public int pop() {
		int r = 0;
		if(isEmpty()) {
			r=first.getElement();
			Node temp = first.next;
			first.next = null;
			first = temp;
		}
		return r;
	}
	
	/**
	 * prints out the elements in a nice format
	 */
	private void printList() {
		Node current = first;
		StringBuilder str = new StringBuilder();
		
		while(current.next!=null) {
			str.append("["+current.getElement()+"],");
			current = current.next;
		}
		str.append("["+current.getElement()+"]");
		System.out.println("List: "+str.toString()+"\n");
	}
	/**
	 * Test runs the push function, pushing 7 ints to the list
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList l = new LinkedList();
		l.push(-2);
		l.push(-1);
		l.push(12);
		l.push(2);
		l.push(41);
		l.push(13);
		l.push(3);
	}
	
	
}
