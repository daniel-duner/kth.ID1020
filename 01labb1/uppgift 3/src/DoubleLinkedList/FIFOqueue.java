package DoubleLinkedList;

public class FIFOqueue<E> {
	
	private Node head = null;
	private Node tail = null;
	
	private class Node{
		E element;
		Node next = null;
		Node previous = null;
		
		public void addElement(E element) {
			this.element = element;
		}
		
		public E getElement() {
			return this.element;
		}
		
	}
	
	private Node getNext(Node node) {
		
	}
	
	public void add(E element) {
			
		
	}
	
	
	public static void main(String[] args) {
		FIFOqueue queue = new FIFOqueue();
	}
}


