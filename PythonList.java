package PythonList_Java;

class Node{
	Node(Object data){
		this.data = data;
	}
	Object data;
	Node next;
	Node prev;
}


public class PythonList {
	int length = 0;
	Node head = null;
	Node last = null;
	Node dummy = new Node(null);
	
	public Object get(int index) {
		//Empty List
		if(head == null) {
			return Integer.MIN_VALUE;
		}
		
		// Index is bigger than size
		if(index>=length || index<-length) {
			System.err.println("IndexError: PythonList index out of range");
			return Integer.MIN_VALUE;
		}
		
		// Negative Index - Backward Traversal
		if(index<0) {
			Node fast = head;
			Node slow = head;
			for(int i = 0; i<-index; i++) {
				fast = fast.next;
			}
			while(fast!=null) {
				fast = fast.next;
				slow = slow.next;
			}
			return slow.data;
		}
		
		// Positive Index - Normal Traversal
		Node ptr = head;
		for(int i = 0; i<index; i++) {
			ptr = ptr.next;
		}
		return ptr.data;
		
	}
	
	public void append(Object data) {
		
		// Create new Node to add into list
		Node newNode = new Node(data);
		
		if(head == null) {
			head = last = newNode;
			dummy.next = head;
		}
		else {
			last.next = newNode;
			last = newNode;
		}
		
		length++;
		
	}
	
	public void remove(Object data) {
		// Empty PythonList
		if(head == null) {
			System.err.println("NOTHING TO REMOVE");
			return;
		}
		
		length--;
		if(head.data.equals(data)) {
			if(head == last) {
				head = dummy.next = last = null;
				
				return;
			}
			dummy.next = head.next;
			head = dummy.next;
			return;
		}
		
		Node ptr = dummy;
		while(!ptr.next.data.equals(data)) {
			ptr = ptr.next;
			if(ptr.next == null) {
				System.err.println("ValueError: PythonList.remove(x): x not in list");
				return;
			}
		}
		if(ptr.next == last) {
			last = ptr;
			last.next = null;
		}
		else {
			ptr.next = ptr.next.next;
		}
	}
	
	public Object pop(int index) {
		//Empty List, Index is way bigger than size, 
		if(head == null || index>=length || index<-length) {
			return Integer.MIN_VALUE;
		}
		
		if(index == 0 || index == -length) {
			Object return_data = head.data;
			if(head == last) {
				head = last = null;
			}
			else {
				head = head.next;
				dummy.next = head;
			}
			length--;
			return return_data;
		}
		// Negative Index - Backward Traversal
		length--;
		if(index<0) {
			Node fast = head;
			Node slow = dummy;
			for(int i = 0; i<-index; i++) {
				fast = fast.next;
			}
			while(fast!=null) {
				fast = fast.next;
				slow = slow.next;
			}
			if(slow.next == last) {
				last = slow;
			}
			Object return_data = slow.next.data;
			slow.next = slow.next.next;
			return return_data;
		}
		else {
			// Positive Index - Normal Traversal
			Node ptr = dummy;
			for(int i = 0; i<index; i++) {
				ptr = ptr.next;
			}
			if(ptr.next == last) {
				last = ptr;
			}
			Object return_data = ptr.next.data;
			ptr.next = ptr.next.next;
			return return_data;
		}
	}
	
	public Object pop() {
		if(head == null) {
			System.err.println("IndexError: pop from empty PythonList");
			return Integer.MIN_VALUE;
		}
		length--;
		
		// If only one element in PythonList
		if(head.next == null) {
			Object data_stored = head.data;
			head = null; last = null; dummy.next = null;
			return data_stored;
		}
		
		Node ptr = head;
		while(ptr.next!=last) {
			ptr = ptr.next;
		}
		Object data_stored = last.data;
		last = ptr;
		last.next = null;
		return data_stored;
	}
	
	public void extend(PythonList adding_list) {
		//Deep Copy Append
		Node lst = adding_list.head;
		while(lst!=null) {
			
			Node newNode = new Node(lst.data);
			if(head == null) {
				head = last = newNode;
				dummy.next = head;
			}
			else {
				last.next = newNode;
				last = newNode;
			}
			lst = lst.next;
			length++;
		}
		
	}
	
	public int count(Object data){
		// RETURNS the number of occurrences of data
		int count = 0;
		
		Node ptr = head;
		while(ptr!=null) {
			if(ptr.data.equals(data)) {
				count++;
			}
			ptr = ptr.next;
		}
		
		return count;
	}
	
	public PythonList copy() {
		// Deep Copy
		PythonList copy = new PythonList();
		copy.extend(this);
		return copy;
	}
	
	public void print() {
		Node ptr = head;
		System.out.print("[");
		while(ptr!=null) {
			System.out.print(ptr.data);
			ptr = ptr.next;
			if(ptr!=null) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
		
	}

	public int length() {
		return length;
	}
	
	public void clear() {
		// In-place Clears the list - does not return anything
		length = 0;
		head = last = dummy.next = null;
	}
	
	// ---------------------------------------------------------------------
	// ------------------------- FUTURE ADDITIONS --------------------------
	// ---------------------------------------------------------------------

	// * SORTING with incomparable datatypes
	// * Throwing EXCEPTIONS instead of error printing using System.err.print()
	// * Code is not threadsafe. Any concurrent access will cause corrupted state (race conditions, partial updates, etc.).
	// * Implement Doubly Linked linked - efficient back-traversal
	
	

	private int index(Object obj) {
		// Returns the index of the given Object
		return 0;
	}
	
	private void insert(int index, Object obj) {
		// Inserts the obj at given index - such that the list would have obj in index after insertion
	}

	private void reverse() {
		//In-place modification - reverses the list
	}
	
	private PythonList reversed() {
		// RETURNS a reversed version of the list
		return null;
	}
	
	private void sort() {
		//In-place sorting of list when elements are comparable
	}
	
	private void append(PythonList adding_list) {
		//Shallow Copy - WORKING ON IT
	}
}


/*
 ___MISTAKES I DID ___
 * 
 * using != for wrapper classes in remove() method - use .equals() instead - != checks for reference not value
 */
