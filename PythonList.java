package PythonList_Java;


class Node{
	Node(Object data){
		this.data = data;
	}
	Object data;
	Node next;
//	Node prev;
}


public class PythonList {
	int length = 0;
	Node head = null;
	Node last = null;
	Node dummy = new Node(null);
	
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
	
	public void insert(int index, Object data) {
		// Inserts the obj at given index - such that the list would have obj in index after insertion
		
		// Update length
		length++;
		
		//Create the new Node with the data
		Node newNode = new Node(data);
		
		// If list is empty, create a new Node
		if(head == null) {
			head = dummy.next = last = newNode;
			newNode.next = null;
			return;
		}
		
		// --- INDEX OUT OF BOUND: 
		
		//If index is >= length, add the data to the last, update last
		if(index>=length) {
			last.next = newNode;
			last = newNode;
			return;
		}
		
		// If the index is <= length, add the data to the first, update dummy.next, head
		if(index<=-length || index == 0) {
			newNode.next = head;
			dummy.next = head = newNode;
			return;
		}
		
		// --- INDEX IN BOUND
		
		// If index is negative, change it to positive equivalent
		index = index<0?length+index:index;
		
		// Traverse to the index's previous position, and add the new node
		Node ptr = head;
		for(int i = 0; i<index-1; i++) {
			ptr = ptr.next;
		}
		newNode.next = ptr.next;
		ptr.next = newNode;
		
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
	
	public int index(Object data) {
		
		int index = 0;
		
		Node ptr = head;
		while(ptr!=null) {
			if(ptr.data.equals(data)) {
				return index;
			}
			index++;
			ptr = ptr.next;
		}
		
		System.err.println("ValueError: list.index(x): x not in list");
		return Integer.MIN_VALUE;
	}
	
	public int index(Object data, int start, int end) {
		// (int start = 0, end = len(list)) -> In a range of the list
		Node ptr = head; int i = 0;
		//Iterate to start
		// i already initialised
		for (; i<start && ptr!=null; i++, ptr = ptr.next);;
		
		//Traverse to the end while checking for value
		while(ptr!=null && i<end && i<length) {
			if(ptr.data.equals(data)) {
				return i;
			}
			ptr = ptr.next;
			i++;
		}
		
		System.err.print("ValueError: list.index(x): x not in list ");
		return Integer.MIN_VALUE;
	}
	
	public void reverse() {
		//In-place modification - reverses the list
		Node ptr = head;
		Object[] listElements = new Object[length];
		
		for(int i = 0; ptr!=null; i++, ptr=ptr.next) {
			listElements[i] = ptr.data;
		}
		
		// Changing the data in each node - traverse through the existing linkedlist and change the data only
		// Not creating an entire new linkedList
		ptr = head;
		for(int i = length-1; i>=0; i--, ptr=ptr.next) {
			ptr.data = listElements[i];
		}
	}
	
	public void sort() {
		//Sorts the list in place (modifies the original list). key can be used for custom sorting, and reverse=True sorts in descending order.
		//In-place sorting of list when elements are comparable
		
		// ADD ALL VALUES TO ARRAY TO SORT
		Node ptr = head;
		double[] arr = new double[length];
		int i = 0;
		while (ptr!=null) {
			if(!isNumericType(ptr.data)) {
				System.err.println("TypeError: '<' not supported between instances of 'str' and 'int'");
				return;
				
			}
			arr[i++] = ((Number) ptr.data).doubleValue();
			ptr = ptr.next;
		}
		
		// SORT THE ARRAY in ascending order - Insertion Sort
		// (To be improved with dual-pivot quick sort in the future)
		for(i = 0; i<length; i++) {
			int j = i;
			while(j>0 && arr[j]<arr[j-1]) {
				double temp = arr[j-1]; 
				arr[j-1] = arr[j];
				arr[j] = temp;
				j--;
			}
		}
		
		// ADD ALL VALUES BACK TO LIST
		ptr = head; i = 0;
		
		while(ptr!=null) {
			ptr.data = arr[i++];
			ptr = ptr.next;
		}
		
		//PROBLEM: All the values are converted to double
	}
	
	public void sort(boolean reverse) {
		//If ascending or not - reverse=true -> descending
		//Sorts the list in place (modifies the original list). key can be used for custom sorting, and reverse=True sorts in descending order.
		
		
		// ADD ALL VALUES TO ARRAY TO SORT
		Node ptr = head;
		double[] arr = new double[length];
		int i = 0;
		while (ptr!=null) {
			if(!isNumericType(ptr.data)) {
				System.err.println("TypeError: '<' not supported between instances of 'str' and 'int'");
				return;
				
			}
			arr[i++] = ((Number) ptr.data).doubleValue();
			ptr = ptr.next;
		}
		
		// SORT THE ARRAY in ascending order - Insertion Sort
		// (To be improved with dual-pivot quick sort in the future)
		for(i = 0; i<length; i++) {
			int j = i;
			while(j>0 && arr[j]<arr[j-1]) {
				double temp = arr[j-1]; 
				arr[j-1] = arr[j];
				arr[j] = temp;
				j--;
			}
		}
		
		// ADD ALL VALUES BACK TO LIST
		// If reverse, then add in reverse - for descending order
		if(reverse) {
			ptr = head; i = length-1;
			
			while(ptr!=null) {
				ptr.data = arr[i--];
				ptr = ptr.next;
			}
		}
		else {
			ptr = head; i = 0;
			
			while(ptr!=null) {
				ptr.data = arr[i++];
				ptr = ptr.next;
			}
		}
		
		//PROBLEM: All the values are converted to double
	}
	
	public PythonList sorted(boolean reverse) {
		// implements sorted(list) -> Returns a new sorted list
		//If ascending or not - reverse=true -> descending
		//Sorts the list in place (modifies the original list). key can be used for custom sorting, and reverse=True sorts in descending order.
		
		PythonList newList = new PythonList();
		
		// ADD ALL VALUES TO ARRAY TO SORT
		Node ptr = head;
		double[] arr = new double[length];
		int i = 0;
		while (ptr!=null) {
			if(!isNumericType(ptr.data)) {
				System.err.println("TypeError: '<' not supported between instances of 'str' and 'int'");
				return null;
				
			}
			arr[i++] = ((Number) ptr.data).doubleValue();
			ptr = ptr.next;
		}
		
		// SORT THE ARRAY in ascending order - Insertion Sort
		// (To be improved with dual-pivot quick sort in the future)
		for(i = 0; i<length; i++) {
			int j = i;
			while(j>0 && arr[j]<arr[j-1]) {
				double temp = arr[j-1]; 
				arr[j-1] = arr[j];
				arr[j] = temp;
				j--;
			}
		}
		
		// ADD ALL VALUES TO NEW LIST
		// If reverse, then add in reverse - for descending order
		if(reverse) {
			i = length-1;
			
			while(i>=0) {
				Node newNode = new Node(arr[i--]);
				if(newList.head == null) {
					newList.dummy.next = newList.head = newList.last = newNode;
				}
				else {
					newList.last.next = newNode;
					newList.last = newNode;
				}
			}
		}
		
		else {
			i = 0;
			
			while(i<length) {
				Node newNode = new Node(arr[i++]);
				if(newList.head == null) {
					newList.dummy.next = newList.head = newList.last = newNode;
				}
				else {
					newList.last.next = newNode;
					newList.last = newNode;
				}
			}
		}
		
		//PROBLEM: All the values are converted to double
		return newList;
	}
	
	public PythonList sorted() {
		// implements sorted(list) -> Returns a new sorted list
		//If ascending or not - reverse=true -> descending
		//Sorts the list in place (modifies the original list). key can be used for custom sorting, and reverse=True sorts in descending order.
		
		PythonList newList = new PythonList();
		
		// ADD ALL VALUES TO ARRAY TO SORT
		Node ptr = head;
		double[] arr = new double[length];
		int i = 0;
		while (ptr!=null) {
			if(!isNumericType(ptr.data)) {
				System.err.println("TypeError: '<' not supported between instances of 'str' and 'int'");
				return null;
				
			}
			arr[i++] = ((Number) ptr.data).doubleValue();
			ptr = ptr.next;
		}
		
		// SORT THE ARRAY in ascending order - Insertion Sort
		// (To be improved with dual-pivot quick sort in the future)
		for(i = 0; i<length; i++) {
			int j = i;
			while(j>0 && arr[j]<arr[j-1]) {
				double temp = arr[j-1]; 
				arr[j-1] = arr[j];
				arr[j] = temp;
				j--;
			}
		}
		
		// ADD ALL VALUES TO NEW LIST
		// If reverse, then add in reverse - for descending order
		
		i = 0;
		
		while(i<length) {
			Node newNode = new Node(arr[i++]);
			if(newList.head == null) {
				newList.dummy.next = newList.head = newList.last = newNode;
			}
			else {
				newList.last.next = newNode;
				newList.last = newNode;
			}
		}
		
		//PROBLEM: All the values are converted to double
		return newList;
	}
	
	public PythonList reversed() {
		//  --- WORKING
		// RETURNS a reversed version of the list
		
		// Store values of list in order in array
		Node ptr = head;
		Object[] listElements = new Object[length];
		
		for(int i = 0; ptr!=null; i++, ptr=ptr.next) {
			listElements[i] = ptr.data;
		}
		
		// Create the new list with reversed order
		// Run the loop from the back of the array and enter into newList
		PythonList newList = new PythonList();
		
		for(int i = length-1; i>=0; i--) {
			Node newNode = new Node(listElements[i]);
			System.out.println("Entering "+listElements[i]);
			
			if(newList.head == null) {
				newList.head = newList.last = newNode;
				newList.dummy.next = newList.head;
			}
			
			else {
				newList.last.next = newNode;
				newList.last = newNode;
			}
			newList.length++;
			
		}
		
		return newList;
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
	
	public double max() {
		// implements max(list)
		/*
			Traceback (most recent call last):
			  File "<pyshell#3>", line 1, in <module>
			    max(lst)
			TypeError: '>' not supported between instances of 'str' and 'int'
		 */
		
		Node ptr = head;
		
		// Initialise max value
		double max = Double.MIN_VALUE;
		
		while(ptr!=null) {
			if(!isNumericType(ptr.data)) {
				System.err.println("TypeError: '>' not supported between instances of 'str' and 'int'");
				return Integer.MIN_VALUE;
			}
			
			double doubleValue = ((Number) ptr.data).doubleValue();
			if(doubleValue > max )
				max = doubleValue;
			ptr = ptr.next;

		}
	
		return max;
	}
	
	public double min() {
		// implements min(list)
		
		Node ptr = head;
		
		// Initialise max value
		double min = Double.MAX_VALUE;
		
		while(ptr!=null) {
			if(!isNumericType(ptr.data)) {
				System.err.println("TypeError: '<' not supported between instances of 'str' and 'int'");
				return Integer.MIN_VALUE;
			}
			
			double doubleValue = ((Number) ptr.data).doubleValue();
			if(doubleValue < min)
				min = doubleValue;
			ptr = ptr.next;

		}
	
		return min;
	}
	
	public double sum() {
		// implements sum(list)
		
		Node ptr = head;
		
		// Initialise max value
		double sum = 0;
		
		while(ptr!=null) {
			if(!isNumericType(ptr.data)) {
				System.err.println("TypeError: '>' not supported between instances of 'str' and 'int'");
				return Integer.MIN_VALUE;
			}
			
			double doubleValue = ((Number) ptr.data).doubleValue();
			sum += doubleValue;
			ptr = ptr.next;
		}
	
		return sum;
	}
	

	public int length() {
		return length;
	}
	
	public void clear() {
		// In-place Clears the list - does not return anything
		length = 0;
		head = last = dummy.next = null;
	}
	
	protected boolean isNumericType(Object obj) {
		// Used to find if the object is numeric
		// Used in the methods min, max, sum, sort, sort(boolean).
		return obj instanceof Integer ||
				obj instanceof Double ||
				obj instanceof Float ||
				obj instanceof Long	 ||
				obj instanceof Character;
	}
	
	
	
	// ---------------------------------------------------------------------
	// ------------------------- FUTURE ADDITIONS --------------------------
	// ---------------------------------------------------------------------

	// * Throwing EXCEPTIONS instead of error printing using System.err.print()
	// * Code is not threadsafe. Any concurrent access will cause corrupted state (race conditions, partial updates, etc.).
	// * Implement Doubly Linked linked - efficient back-traversal
	// * [DONE] SORTING with incomparable datatypes
	
	
	private void append(PythonList adding_list) {
		//Shallow Copy - WORKING ON IT
	}
}


/*
 ___MISTAKES I DID ___
 * 
 * using != for wrapper classes in remove() method - use .equals() instead - '!=' checks for reference not value
 */
