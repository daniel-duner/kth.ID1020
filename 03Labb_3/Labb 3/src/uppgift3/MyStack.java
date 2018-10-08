package uppgift3;

public class MyStack<Key extends Comparable<Key>, Value extends Comparable<Value>> {
		private Node head;
		private int size;
	private class Node{
		Value value;
		Key key;
		Node next;

		public Node(Key key, Value value){
			this.key = key;
			this.value = value;
		}
		
		public Value getValue(){
			return value;
		}
		public Key getKey(){
			return key;
		}
	}
	
	public void push(Key key, Value value) {
		if(isEmpty()) {
			head = new Node(key, value);
		} else {
			Node newNode = new Node(key, value);
			newNode.next = head;
			head = newNode;
		}
		size++;
	}
	
	public PairDTO pop(){
		if(!isEmpty()) {			
			PairDTO pair = new PairDTO(peekKey(), peekValue());
			head = head.next;
			size--;
			return pair;
		}
		return null;
	}
	
	public void reset() {
		head = null;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int size() {
		return size;
	}
	public Node getHead() {
		return head;
	}

	public Value peekValue() {
		return head.getValue();
	}
	
	public Key peekKey() {
		return head.getKey();
	}
	
	public boolean cmp(Key key){
		Node current = head;
		boolean answer = true;
		while(current != null) {
			if(key.compareTo(current.getKey()) == 0) {
				answer = false;
			}
			current = current.next;
		}	
		return answer;
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		String[] strArr = new String[size];
		int i = size();
		Node current = head;
		
		while(0 != i) {
			String s = (i+". "+ current.getKey().toString()+ " is used "+current.getValue().toString()+" times\n");
			strArr[i-1] = s;
			i--;
			current = current.next;
		}
		i = 0;
		while (i < size) {
			str.append(strArr[i]);
			i++;
		}
		return str.toString();
	}
	
	
}
