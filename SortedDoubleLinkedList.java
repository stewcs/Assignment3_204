import java.util.Comparator;
import java.util.ListIterator;

/**
 * This class extends BasicDoubleLinkedList and implements a special add method to make the doubly linked list sorted.
 * @author Daniel Xu
 * @version 3/17/2023
 */

public class SortedDoubleLinkedList <T> extends BasicDoubleLinkedList<T>{

	private Comparator<T> comparator;

	public SortedDoubleLinkedList(Comparator<T> comparableObject) {
		this.comparator = comparableObject;
	}

	/**
	 * Inserts the specified element at the correct position in the sorted list. 
	 * Notice we can insert the same element several times. 
	 * Your implementation must traverse the list only once in order to perform the insertion.
	 * @return data - the data to be added to the list
	 */
	public void add (T data) {
		Node<T> current = head;

		// If list is empty
		if (size == 0) {
			
			head = new Node<>(data);
			tail = head;
			size++;
		
			return;
		}

		// If it is less than the head
		else if (comparator.compare(data, head.data) < 0) { 
			
			Node<T> newNode = new Node<>(data);
			newNode.next = head;
			newNode.next.prev = newNode;
			newNode.prev = null;
			head = newNode;	
			head.prev = null;
			size++;
			
			return;
		}

		while (current != null) {

			//if size is 1
			if (comparator.compare(data, current.data) >= 0 && size == 1) {
				
				Node<T> newNode = new Node<>(data);

				tail = newNode;
				newNode.prev = head;
				newNode.next = null;
				head.next = newNode;

				size++;

				return;
			}
			// if it is the new tail
			else if (comparator.compare(data, tail.data) >= 0 && size > 1) { 
			  
			    Node<T> newNode = new Node<>(data);
			    
			    tail.next = newNode;
			    newNode.prev = tail;
			    newNode.next = null;
			    tail = newNode;
			    size++;
			    return;
			}

			// if it is greater than, but not at head nor tail (middle)
			else if (comparator.compare(data, current.data) >= 0 && current != head && current != tail) { 
				
				Node<T> newNode = new Node<>(data);
				newNode.next = current.next;
				newNode.prev = current;
				current.next = newNode;
				size++;
				return;
			}

			// less than the tail
			else if (comparator.compare(data, current.data) < 0 && current == tail) {
				
				Node<T> newNode = new Node<>(data);
				
				newNode.prev = tail.prev;
				newNode.next = tail;
			
				tail.prev.next = newNode;
				tail.prev = newNode;
				size++;
				
				
				
				return;
			}
			current = current.next;
		}
		
	}



	/**
	 * Implements the iterator by calling the super class iterator method.
	 * @returns an iterator positioned at the head of the list
	 */
	@Override
	public java.util.ListIterator<T> iterator(){
		return super.iterator();
	}

	/**
	 * Implements the remove operation by calling the super class remove method.
	 * @return a node containing the data or null
	 */
	@Override
	public BasicDoubleLinkedList.Node remove(T data, Comparator<T> comparator){
		return super.remove(data, comparator);

	}


	//This operation is invalid for a sorted list. 
	//An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	public void addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");

	}

	//This operation is invalid for a sorted list. 
	//An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	public void addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

}
