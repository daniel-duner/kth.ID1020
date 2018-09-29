package program;
/**
 * README
 * A generalized queue, made up of a double linked list
 * @author danielduner
 *
 * @param <T>
 */
public class Queue<T> {
	private Node first;
	private Node last;
	private int size;

	/**
	 * 
	 * @author danielduner
	 *
	 */
	private class Node {
		private T element;
		private Node next;

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

	public void insert(T element) {
		Node newNode = new Node(element);
		if (isEmpty()) {
			first = newNode;
			last = newNode;
		} else {
			newNode.next = first;
			first.previous = newNode;
			first = newNode;
		}
		size++;
	}

/**
 * returns and deletes the kth element in the queue
 * @param index
 * @return
 */
//	public T delete(int index) {
//		if(index <= size && index > 0){
//			T r;
//			if(index == 1) {
//				r = first.getElement();
//				first = first.next;
//				first.previous = null;
//				size--;
//				return r;
//			} else if(index == size){
//				r = last.getElement();
//				last.previous = last;
//				last.next = null;
//				size--;
//				return r;
//			} else {
//				Node current = first;
//				for (int i = 1; i < index; i++) {
//					current = current.next;
//				}
//				current.previous = current.next;
//				current.next = current.previous;
//				size--;
//				return current.getElement();
//			}
//		}else {
//			System.out.println("the number of items in the queue is "+size+" and the index starts at 1");
//			return null;
//		}
//	}
	public T delete(int index) {
	
	}

	public static void main(String[] arg) {
		Queue<String> q = new Queue<String>();
		q.insert("1");
		q.insert("2");
		q.insert("3");
		q.insert("4");
		System.out.println(q.toString());
		
		
		System.out.println("");
		System.out.println("---POP-1--");
		q.delete(1);
		System.out.println(q.toString());
		System.out.println("");
		
		System.out.println("---POP-2--");
		q.delete(2);
		System.out.println(q.toString());
		System.out.println("");
		
		System.out.println("---POP-2--");
		q.delete(2);
		System.out.println(q.toString());
		System.out.println("");
		
		System.out.println("is the list empty? "+q.isEmpty());
		System.out.println("");
		System.out.println("---POP--1-");
		q.delete(1);
		System.out.println(q.toString());
		System.out.println("is the list empty? "+q.isEmpty());

	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		Node current = first;
		for (int i = 0; i < size; i++) {
			if(i+1 != size) {				
				str.append("["+current.getElement()+"],");
			}else {				
				str.append("["+current.getElement()+"]");
			}
			current = current.next;
		}
		return str.toString();
	}

}
