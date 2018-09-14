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
	 * the node of the list
	 * @author danielduner
	 *
	 */
	private class Node {
		private T element;
		private Node next;
		private Node previous;
		
		/**
		 * Constructor of the node
		 * @param element the element that is put in the queue (saved in the node)
		 */
		private Node(T element) {
			this.element = element;
		}
		/**
		 * @return returns the element saved in the node
		 */
		private T getElement() {
			return element;
		}
	}
	/**
	 * Checks if the queue is empty
	 * @return returns true if empty and false if there are elements in the queue
	 */
	private boolean isEmpty() {
		return first == null;
	}
	/**
	 * Inserts the a new element in the queue, at the first place
	 * @param element the element to be inserted
	 */
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
 * @param index index is the number of the kth element
 * @return returns the kth element
 */
	public T delete(int index) {
		if(index <= size && index > 0){
			T r;
			if(index == 1) {
				r = first.getElement();
				first = first.next;
				first.previous = null;
				size--;
				return r;
			} else if(index == size){
				r = last.getElement();
				last.previous = last;
				last.next = null;
				size--;
				return r;
			} else {
				Node current = first;
				for (int i = 1; i < index; i++) {
					current = current.next;
				}
				current.previous = current.next;
				current.next = current.previous;
				size--;
				return current.getElement();
			}
		}else {
			System.out.println("the number of items in the queue is "+size+" and the index starts at 1");
			return null;
		}
	}
	/**
	 * Converts the list/queue into a string with the design [element1],[element2] 
	 */
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
/**
 * Test First we insert 4 elements in the queue and then we deletes the first, 
 * the second and the second again. we ask if it's empty and it returns false, 
 * since one element is still in there, then we pop the last element and asks if the element is empty
 * @param arg
 */
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

}
