package program;

/**
 * README
 * This program is a circular linked list which handles generic 
 * types and elements can be removed from the front and the back of the queue
 * @author danielduner
 *
 * @param <T> Type of the elements in the queue
 */
public class CircularLinkedList<T> {
	private Node first;
	private Node last;

	/**
	 * The nodes of the linked list
	 * @author danielduner
	 */
	private class Node {
		private T element;
		private Node next;

		/**
		 * Constructor of a Node, instantiates a node
		 * @param element the element which is saved in the node
		 */
		public Node(T element) {
			this.element = element;
		}

		private T getElement() {
			return element;
		}
	}

	/**
	 * Constructor of CircularLinkedList, instantiates a list
	 */
	public CircularLinkedList() {
		first = null;
	}

	/**
	 * Checks if the list is empty
	 * @return Returns true if the list is empty
	 */
	private boolean isEmpty() {
		return first == null;
	}

	/**
	 * Pushes an element to the first position in the list
	 * @param element the element pushed to the list
	 */
	public void pushToFirst(T element) {
		Node newNode = new Node(element);
		if (isEmpty()) {
			first = newNode;
			first.next = newNode;
			last = newNode;
		} else {
			newNode.next = first;
			first = newNode;
		}
		last.next = first;
	}

	/**
	 * Pushes an element to the last position in the list
	 * @param element the element pushed to the list
	 */
	public void pushLast(T element) {
		Node newNode = new Node(element);
		if (isEmpty()) {
			first = newNode;
			first.next = newNode;
			last = newNode;
		} else {
			last.next = newNode;
			last = newNode;
			last.next = first;
		}
	}

	/**
	 * Removes a element at the first position in the list, 
	 * writes "the list is empty" and returns null if the list is empty
	 * @return returns the element at the first position in the list
	 */
	public T popFirst() {
		T temp;
		if (!isEmpty()) {
			temp = first.getElement();
			if (last == first) {
				first = null;
				last = null;
			} else {
				first = first.next;
				last.next = first;
			}
			return temp;
		} else {
			System.out.println("The list is empty");
			return null;
		}

	}

	/**
	 * Removes the element at the last position in the list,
	 * writes "the list is empty" and returns null if the list is empty
	 * @return returns the element at the last position in the list
	 */
	public T popLast() {
		T temp;
		if (!isEmpty()) {
			temp = last.getElement();
			if (last == first) {
				first = null;
				last = null;
			} else {
				Node current = first;
				while (current.next != last) {
					current = current.next;
				}
				last = current;
				last.next = first;
			}
			return temp;
		} else {
			System.out.println("The list is empty");
			return null;
		}
	}
	
	/**
	 * Converts the list into a string with the design [element],[element]
	 * @return returns the list in string format
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (!isEmpty()) {
			Node current = first;
			do {
				if (current.next != first) {
					str.append("[" + current.getElement() + "],");
				} else {
					str.append("[" + current.getElement() + "]");
				}
				current = current.next;
			} while (current != first);
			return str.toString();
		} else {
			return "";
		}

	}

	/**
	 * Test pushes 4 string variables, then loop through the circle 10 times
	 * and then pop the first, then pop the last and then pop the last 3 times,
	 * the last time is when the list is empty and null will
	 * be returned and an "error message" "the list is empty"
	 * @param arg
	 */
	public static void main(String arg[]) {
		CircularLinkedList<String> cL = new CircularLinkedList<String>();
		CircularLinkedList<String> ccL = new CircularLinkedList<String>();
		cL.pushToFirst("a");
		cL.pushToFirst("b");
		cL.pushToFirst("c");
		cL.pushToFirst("d");
		System.out.println(cL.toString());
		System.out.println("");
		
		System.out.println("go through the list element by element 10 times");
		ccL.first = cL.first;
		for (int i = 0; i < 10;i++) {
			if(i+1 != 10) {				
				System.out.print("["+ccL.first.getElement()+"],");
			}else {
				System.out.print("["+ccL.first.getElement()+"]");				
			}
			ccL.first = ccL.first.next;
		}
		
		
		System.out.println(cL.toString());
		System.out.println("");
		System.out.println("----POP-first--");
		String element = cL.popFirst();
		System.out.println(cL.toString());		
		System.out.println("----returned element was [" + element + "]--");
		System.out.println("");
		System.out.println("----POP-last--");
		element = cL.popLast();
		System.out.println(cL.toString());
		System.out.println("----returned element was [" + element + "]--");
		System.out.println("");
		
		System.out.println("----POP-last--");
		element = cL.popLast();
		System.out.println(cL.toString());
		System.out.println("----returned element was [" + element + "]--");
		System.out.println("");
		
		System.out.println("----POP-last--");
		element = cL.popLast();
		System.out.println("----returned element was [" + element + "]--");
		System.out.println("");
		
		
		System.out.println("----POP last (list is empty)--");
		element = cL.popLast();
		System.out.println("----returned element was [" + element + "]--");

	}

}
