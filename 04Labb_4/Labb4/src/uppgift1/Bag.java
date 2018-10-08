package uppgift1;

import java.util.Iterator;

public class Bag<T> {
	private Node head;
	int size;
	
	
	public static void main(String[] args) {
		Bag<Integer> b = new Bag<Integer>();
			b.push(1);
			b.push(2);
			b.push(3);
			b.push(4);
			b.push(5);
			b.push(6);
			b.push(7);
			b.push(8);
			b.push(9);
		
		for(Iterator<Integer> it = b.iterator();it.hasNext();) {
			System.out.println(it.next());
		}
	}
	
	
	
	private class Node {
		T element;
		private Node next;
		
		public Node(T element) {
			this.element = element;
		}
		
		public T getEle() {
			return element;
		}
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public T peek() {
		return head.getEle();
	}
	
	public void push(T element) {
		if(isEmpty()) {
			head = new Node(element);
			size++;
			return;
		}
		size++;
		Node newNode = new Node(element);
		newNode.next = head;
		head = newNode;
	}
	
	public T pop() {
		if(isEmpty()) 
			return null;
		T r = null;
		if(head.next == null) {
			r = head.getEle();
			head = null;
			size--;
			return r;
		}
		else {
			r = head.getEle();
			head = head.next;
			size--;
			return r;
		}		
		
			
	}
	
	private class BagIterator <T> implements Iterator<T>{
		private int index;
		private Node current;
		
		public BagIterator() {
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
			if(current == null) {
				index++;
				current = head;
				return (T)current.getEle();
			} else {	
				index++;
				current = current.next;
				return (T) current.getEle();
			}
		}
		
	}
	
	public Iterator<T> iterator(){
		return new BagIterator<T>();
	}
	
}
