package uppgift6;

public class TextFindStack<T> {
	Node head;
	int size;
	public class Node{
		T element;
		Node next;
		public Node(T element) {
		this.element = element;
		}
	}
	private boolean isEmpty() {
		return null == head;
	}
	
	public void push(T element) {
		if(isEmpty()) {
			head = new Node(element);
		} else {
			Node newNode = new Node(element);
			newNode.next = head;
			newNode = head;
		}
		size++;
	}
	
	public T pop() {
		if(isEmpty()) {
			return null;
		}
		T element = head.element;
		head = head.next;
		size--;
		return element;
	}
	
}	
