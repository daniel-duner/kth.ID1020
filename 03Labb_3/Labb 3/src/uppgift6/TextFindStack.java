package uppgift6;
/**
 * stack
 * @author danielduner
 *
 * @param <T>
 */
public class TextFindStack<T> {
	
//	public static void main(String[] args) {
////		TextFindStack<Integer> stack = new TextFindStack<Integer>();
////		stack.push(1);
////		stack.push(2);
////		stack.push(3);
////		while(!stack.isEmpty())
////			System.out.println(stack.pop());
//	}
	
	private Node head;
	private int size;
	public class Node{
		T element;
		Node next;
		public Node(T element) {
		this.element = element;
		}
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return null == head;
	}
	
	public void push(T element) {
		if(isEmpty()) {
			head = new Node(element);
		} else {
			Node newNode = new Node(element);
			newNode.next = head;
			head = newNode;
		}
		size++;
	}
	
	public T pop(String mode) {
		if(isEmpty()) {
			return null;
		}		
			if(mode == "filo"|| head.next == null ) {
				T element = head.element;
				head = head.next;
				
				return element;
			} else if(mode == "fifo") {
				Node current = head;
				while(current.next.next != null) {
					current = current.next;
				}
				T element = current.next.element;
				current.next = null;
				size--;
				return element;			
			}
			return null;
		
	}
	
}	
