package uppgift1.copy;

import java.util.Iterator;

public class Stack<T> {
	private Node head;
	private int size;

	public class Node {
		T element;
		Node next;

		public Node(T element) {
			this.element = element;
		}

		private T getEle() {
			return element;
		}
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return null == head;
	}

	public void push(T element) {
		if (isEmpty()) {
			head = new Node(element);
		} else {
			Node newNode = new Node(element);
			newNode.next = head;
			head = newNode;
		}
		size++;
	}

	public T pop(String mode) {
		if (isEmpty()) {
			return null;
		}
		if (mode == "filo" || head.next == null) {
			T element = head.element;
			head = head.next;

			return element;
		} else if (mode == "fifo") {
			Node current = head;
			while (current.next.next != null) {
				current = current.next;
			}
			T element = current.next.element;
			current.next = null;
			size--;
			return element;
		}
		return null;

	}

	private class StackIterator<T> implements Iterator<T> {
		private int index;
		private Node current;

		public StackIterator() {
			current = null;
			index = 0;
		}

		@Override
		public boolean hasNext() {
//			System.out.println("index "+index+" size: "+size);
			return index < size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (current == null) {
				index++;
				current = head;
				return (T) current.getEle();
			} else {
				index++;
				current = current.next;
				return (T) current.getEle();
			}
		}

	}

	public Iterator<T> iterator() {
		return new StackIterator<T>();
	}
}
