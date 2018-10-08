package uppgift5;

public class HashStack<T> {
	private Node head;
	private int size;
	
	public static void main(String[] args) {
		HashStack<Integer> stack = new HashStack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
	}
	
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
	
	public boolean isEmpty() {
		return head == null;
	}
	public T peek() {
		return head.element;
	}
	public void push(T element) {
		if(isEmpty()) {
			head = new Node(element);
			size++;
			return;
		}
		
		Node newNode = new Node(element);
		newNode.next = head;
		head = newNode;
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
	
	public void reset() {
		this.head = null;
	}
	
	public int size() {
		return size;
	}
	
}
