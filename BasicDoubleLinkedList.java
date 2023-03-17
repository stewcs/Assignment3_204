import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class implements methods for a doubly linked list.
 * @author Daniel Xu
 * @version 3/17/2023
 */

public class BasicDoubleLinkedList<T> implements Iterable<T>{

	protected Node<T> head;
	protected Node<T> tail;
	protected int size;

	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Adds element to the front of the list and updated the size of the list
	 * @param data - the data to be added to the list
	 */
	public void addToFront(T data) {

		Node<T> node = new Node<>(data);

		if(head == null) {
			head = tail = node;
		}else {
			node.next = head;
			node.prev = null;
			head.prev = node;
			head = node;
			tail.next = head;
		}
		size++;
	}

	/**
	 * Adds an element to the end of the list and updated the size of the list
	 * @param data - the data to be added to the list
	 */
	public void addToEnd(T data) {
		Node<T> node = new Node<>(data);
		// 
		if(tail == null) {
			head = tail = node;
		}else {
			node.prev = tail;
			node.next = null;
			tail.next = node;
			tail = node;
			head.prev = tail;
		}
		size++;
	}

	/**
	 * Returns but does not remove the first element from the list.
	 * @return
	 */
	public T getFirst() {
		if (head == null) {
			return null;
		}
		return head.data;
	}

	/**
	 * Returns but does not remove the last element from the list.
	 * @return
	 */
	public T getLast() {
		if (tail == null) {
			return null;
		}
		return tail.data;
	}

	/**
	 * Returns the number of nodes in the list.
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Removes the first instance of the targetData from the list.
	 * @param targetData the data element to be removed
	 * @param comparator the comparator to determine equality of data elements
	 * @return a node containing the targetData or null
	 */
	public BasicDoubleLinkedList.Node remove (T targetData, Comparator<T> comparator){

		// If list is empty
		if (size == 0) {

			size--;
			return null;
		}
		// if list has 1 element
		else if (size == 1) {

			Node<T> returnThis = head;
			head = null;
			tail = null;
			size--;
			return returnThis;
		}
		// If head is the element to be removed
		else if (comparator.compare(targetData,head.data) == 0 && size > 1) {

			Node<T> returnThis = head;
			head = head.next; // set new head

			head.prev = null; // remove old head
			size--;
			return returnThis;
		}
		// if tail is the element to be removed
		else if (comparator.compare(targetData,tail.data) == 0) {
			Node<T> returnThis = tail;
			tail = tail.prev; // set new tail
			tail.next = null; // set old tail to null
			size--;
			return returnThis;
		}
		Node<T> current = head;
		while(current != null) {

			if(comparator.compare(targetData,current.data) == 0) {

				current.next.prev = current.prev; // set the next element's previous to the element before current
				current.prev.next = current.next; // set the previous element's next to the element after current
				Node<T> returnThis = current;
				current = null;
				size--;
				return returnThis;

			}

			current = current.next;
		}
		return null;
	}

	/**
	 * Removes and returns the first element from the list.
	 * @return data element or null
	 */
	public T retrieveFirstElement() {
		T first = null;
		// When there is no elements
		if (head == null) {
			return first;
		}

		// When there is only 1 element
		if (head == tail) {
			first = head.data;
			head = null;
			tail = null;
			size--;
		} else { // More than 1 element
			first = head.data;
			head = head.next;
			head.prev = null;	  
			size--;
		}
		return first;
	}

	/**
	 * Removes and returns the last element from the list. 
	 * @return data element or null
	 */
	public T retrieveLastElement() {
		T last = null;
		// When there is no elements
		if (tail == null) {
			return last;
		}
		// When there is only 1 element
		else if (head == tail) {
			last = head.data;
			head = null;
			tail = null;
		} else { // More than 1 element
			last = getLast();
			tail = tail.prev;
			tail.prev = tail.prev.prev;
		}
		size--;
		return last;
	}

	/**
	 * Returns an arraylist of all the items in the Double Linked list
	 * @return list, the arraylist.
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>(size);
		Node<T> current = head;

		while (current != null) {
			if(size == 1) {
				list.add(head.data);
			} else{
				list.add(current.data);
			}
			current = current.next;
		} 
		return list;
	}

	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}

	// Inner Class 
	protected class Node<T> {
		T data;
		Node<T> prev;
		Node<T> next;

		public Node(T dataNode){
			this.prev = null;
			this.next = null;
			this.data = dataNode;
		}

		public Node(T dataNode, Node<T> prev, Node<T> next) {
			this.data = dataNode;
			this.prev = prev;
			this.next = next;
		}
	}
	
	// Inner Class
	protected class DoubleLinkedListIterator implements ListIterator<T> {


		private Node<T> current; 
		private int index;


		public DoubleLinkedListIterator() {
			current = head;
			index = 0;
		}
		
		/**
		 * Returns the next element in the list and moves the cursor position forward.
		 * @return data element or null
		 */
		@Override
		public T next() {
			T returnThis = null;
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (hasNext()) {

				returnThis = current.data;
				current = current.next;
				index++;
			}

			return returnThis;
		}
		
		/**
		 * Returns true if there is another element next.
		 * @return true if there is another element, false if otherwise.
		 */
		@Override
		public boolean hasNext() {

			return index < size;

		}

		/**
		 * Returns the previous element in the list and moves the cursor position backward.
		 * @return data element or null
		 */
		@Override
		public T previous() {
			T returnThis = null;
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			} else {
				if (current == null) {
					current = tail;
				} else {
					current = current.prev;
				}
				returnThis =  current.data;
				index--;
			}
			return returnThis;
		}

		/**
		 * Returns true if there is another element previous.
		 * @return true if there is another element, false if otherwise.
		 */
		@Override
		public boolean hasPrevious() {
			return index > 0;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void add(T t) {
			throw new UnsupportedOperationException();
		}

		public int nextIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		public int previousIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		public void set(T t) {
			throw new UnsupportedOperationException();
		}
	}

}



