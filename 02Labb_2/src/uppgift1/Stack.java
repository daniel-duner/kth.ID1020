package uppgift1;

public class Stack<T> {
	Node first;
	Node last;
	
	private class Node{
		T element;
		Node next;
		
		public Node(T element) {
			this.element = element;
		}
		
		public T getElement() {
			return element;
		}	
	}
	
	private boolean isEmpty() {
		return first == null;
	}
	
	public T peek() {
		return first.getElement();
	}
	
	public void push(T element) {
		Node newNode = new Node(element);
		newNode.next = first;
		first = newNode;
	}
	
	public T pop(){
		T popedElement = first.getElement();
		first.next = first;
		return popedElement;
		}
	
	
	
	
	
	
	
	
	
	
	
}