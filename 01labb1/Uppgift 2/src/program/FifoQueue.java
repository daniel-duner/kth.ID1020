package program;

/**
 * README FIFO Queue - which means that the first element pushed is the first to
 * be poped
 * @author danielduner
 * @param <T> Type of the elements in the queue
 */
public class FifoQueue<T> {
	private Node first;
	private Node last;
	int size;

	/**
	 * The node object of the list, containing each elements and the path
	 * 
	 * @author danielduner
	 *
	 */
	private class Node {
		private T element;
		private Node next;
		private Node previous;

		/**
		 * Constructor for a Node, creates a Node in the list
		 * 
		 * @param element
		 *            the element which is added to the list
		 */
		private Node(T element) {
			this.element = element;
		}

		/**
		 * @return returns the element stored in the Node
		 */
		public T getElement() {
			return this.element;
		}
	}

	/**
	 * Checks if the queue is empty
	 * @return returns true if the queue is empty
	 */
	private boolean isEmpty() {
		return this.first == null;
	}

	/**
	 * Adds an element to the back of the queue
	 * 
	 * @param element
	 *            the element which will be added to the queue
	 */
	public void enqueue(T element) {
		Node newNode = new Node(element);
		if (isEmpty()) {
			last = newNode;
		} else {
			first.previous = newNode;
		}
		newNode.next = first;
		first = newNode;
		size++;
	}

	/**
	 * Removes an element(removes a node) from the front of the queue
	 * 
	 * @return returns the element in the front of the queue
	 */
	public T dequeue() {
		T temp = last.getElement();
		last = last.previous;
		last.next = null;
		size--;
		return temp;
	}

	/**
	 * Converts the list into a string with the design [element],[element]
	 * @return returns the list in string format
	 */
	public String toString() {
		if (first != null) {
			StringBuilder str = new StringBuilder();
			Node node = first;
			while (node.next != null) {
				str.append("[" + node.getElement() + "],");
				node = node.next;
			}
			return str.append("[" + node.getElement() + "]").toString();
		}
		return "The queue is empty";
	}

	/**
	 * Test: Enques 4 elements a, b, c and d and then deques the elements in FIFO
	 * order which means after 3 deques there will only be d leftm which was put
	 * into queue last
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FifoQueue<String> dLL = new FifoQueue<String>();
		System.out.println("--ENQUEUE a--");
		dLL.enqueue("a");
		System.out.println(dLL.toString());
		System.out.println("--ENQUEUE b--");
		dLL.enqueue("b");
		System.out.println(dLL.toString());
		System.out.println("--ENQUEUE c--");
		dLL.enqueue("c");
		System.out.println(dLL.toString());
		System.out.println("--ENQUEUE d--");
		dLL.enqueue("d");
		System.out.println(dLL.toString());
		System.out.println("--DEQUEUE a--");
		dLL.dequeue();
		System.out.println(dLL.toString());
		System.out.println("--DEQUEUE b--");
		dLL.dequeue();
		System.out.println(dLL.toString());
		System.out.println("--DEQUEUE c--");
		dLL.dequeue();
		System.out.println(dLL.toString());

	}

}
