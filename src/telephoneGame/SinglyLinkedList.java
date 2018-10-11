package telephoneGame;
/**
 * This class helps to implement SinglyLinkedList, 
 * with functions to get head, get size, insert and remove elements.
 * @author Huiyan Zhang; nicolezhang@brandeis.edu
 *
 * @param <T>
 */
public class SinglyLinkedList<T>{
	public int size;
	private SinglyLinkedNode<T> head;
	private SinglyLinkedNode<T> tail;
	
	/**
	 * Constructor
	 */
	public SinglyLinkedList() {
		size = 0;
	}
	
	/**
	 * Method to get the head of the SinglyLinkedList
	 * @return the head node of the SinglyLinkedList
	 * The running time is O(1)
	 */
	public SinglyLinkedNode<T> getHead() {
		return head;
	}

	/**
	 * Method to add a new SinglyLinkedNode with the given data at the specified index in the SinglyLinkedList
	 * @param data, the data of the added SinglyLinkedNode
	 * @param index, the position of the added SinglyinkedNode
	 * The running time is O(n)
	 */
	public void insert(T data, int index) {
		SinglyLinkedNode<T> add = new SinglyLinkedNode<T>();
		add.setData(data);
		// check if the required index is existed in the SinglyLinkedList
		if(index>size+1 || index<1) {
			System.out.println("Error: this is an invaild index to insert");
			return;
		}
		// insert if the new SinglyLinkedNode should be added at the front of the SinglyLinkedList
		if(index == 1) {
			// check if the SinglyLinkedList is empty
			if(head == null) {
				head = add;
				tail = head;
			}
			else {
				add.setNext(head);
				head = add;
			}
		}
		// insert if the new SinglyLinkedNode is asked to be added at the end of the SinglyLinkedList
		if(index == size+1){
			//check if the SinglyLinkedList is empty
			if(head == null) {
				head = add;
				tail = head;
			} else {
				tail.setNext(add);
				tail = add;
			}
		}
		// insert the new SinglyLinkedNode when the index is normal
		if(index<=size && index>1) {
			SinglyLinkedNode<T> ptr = head;
			index = index - 1;
			for(int i=1; i<size; i++) {
				if(i == index) {
					SinglyLinkedNode<T> tmp = ptr.getNext();
					ptr.setNext(add);
					add.setNext(tmp);
					break;
				}
				ptr = ptr.getNext();
			}		
		}	
		size++;
	}
	
	/**
	 * Method to insert the node to the end of SinglyLinkedList if no index is provided
	 * @param data, the data of the added SinglyLinkedNode
	 * The running time is O(1)
	 */
	public void insert(T data) {
		SinglyLinkedNode<T> add = new SinglyLinkedNode<T>();
		add.setData(data);
		size++;
		//check if the SinglyLinkedList is empty
		if(head == null) {
			head = add;
			tail = head;
		} else {
			tail.setNext(add);
			tail = add;
		}
	}

	/**
	 * Method to remove and return the node at the given index in the SinglyLinkedList
	 * @param index, the position of the SinglyLinkedNode which is asked to be removed
	 * @return the node that is removed
	 * The running time is O(n)
	 */
	public SinglyLinkedNode<T> remove(int index){     
		SinglyLinkedNode<T> tmp = new SinglyLinkedNode<T>();
		// check if the index is invalid
		if(index < 1 || index > size) {
			try {
				throw new Exception();
			}catch(Exception e) {
				System.out.println("Error: this is an invaild index to delete");
                return null;
			}
		}
		// delete if the first node in the list is required to delete
	    if (index == 1) {
	    	tmp = head;
	        head = head.getNext();
	        size--; 
	        return tmp;
	    }
	    // delete if the last node in the list is required to delete
	    if (index == size) {
	    	SinglyLinkedNode<T>  s = head;
	    	SinglyLinkedNode<T>  t = head;
	        while (s != tail){
	            t = s;
	            s = s.getNext();
	        }
	        tmp = tail;
	        tail = t;
	        tail.setNext(null);
	        size --;
	        return tmp;
	    }
	    SinglyLinkedNode<T>  ptr = head;
	    SinglyLinkedNode<T>  curr = new SinglyLinkedNode<T>();
	    index = index - 1 ;
	    for (int i = 1; i < size - 1; i++) {
	        if (i == index) {
	            tmp = ptr.getNext();
	            curr = tmp;
	            tmp = tmp.getNext();
	            ptr.setNext(tmp);
	            break;
	        }
	        ptr = ptr.getNext();
	    }
	    size--;
	    return curr;
	}    
	
	/**
	 * Method to remove the last element in the SinglyLinkedList if no index is provided
	 * @return, the last Node in the list which is removed
	 * The running time is O(n)
	 */
	public SinglyLinkedNode<T> remove() {
		SinglyLinkedNode<T> tmp = head;
		SinglyLinkedNode<T> t = head;
		while(tmp != tail) {
			t = tmp;
			tmp = tmp.getNext();
		}
		tail = t;
		tail.setNext(null);
		size--;
		return tmp;
	}

	/**
	 * Method to remove the first instance of a node with the specified data from the SinglyLinkedList
	 * @param data, the characteristic that the removed node should have
	 * The running time is O(n)
	 */
	public void remove(T data) {
		SinglyLinkedNode<T> tmp = head;
		SinglyLinkedNode<T> previous = head;
		// remove the first node in the SinglyLinkedList if the first node satisfied the required data
		if(head.getData() == data ){
			head = head.getNext();
			size--;
			return;
		}
		// go over the rest of the list to find the node with the required data
		tmp = previous.getNext();
		while(tmp != null) {
			//check if we arrived at the end of the list
			if(tmp == tail) {
				// check if the tail node satisfied the given data
				if(tail.getData() == data) {
					tail = previous;
					tail.setNext(null);
					size--;
					return;
				} else {
					break;
				}
			}
			if(tmp.getData() == data) {
				previous.setNext(tmp.getNext());
				size--;
				return;
			}
            previous = tmp;
            tmp = tmp.getNext();
		}
		System.out.println("Cannot find and remove the data");
	}

	/**
	 * Method to get the size of the SinglyLinkedList
	 * @return the size of the SinglyLinkedList
	 * The running time is O(1)
	 */
	public int size() {
		return size;
	}

	/**
	 * toString method to return the elements in the SinglyLinkedList
	 * The running time is O(n)
	 */
	public String toString() {
		SinglyLinkedNode<T> tmp = head;
		String answer = "";
		while(tmp != null){
			answer = answer +"->" + tmp.getData();
			tmp = tmp.getNext();
		}
		return answer;
	}


}