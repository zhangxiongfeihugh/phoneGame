package telephoneGame;
/**
 * This class helps to implement DoublyLinkedList, 
 * with functions to get head, get size, insert and remove elements.
 * @author Huiyan Zhang; nicolezhang@brandeis.edu
 *
 * @param <T>
 */
public class DoublyLinkedList<T>{
	public int size;
	private DoublyLinkedNode<T> head;
	private DoublyLinkedNode<T> tail;

	/**
	 * Constructor
	 */
	public DoublyLinkedList() {
		size = 0;
	}
	
	/**
	 * Method to get the head of the DoublyLinkedList
	 * @return the head node of the DoublyLinkedList
	 * The running time is O(1)
	 */
	public DoublyLinkedNode<T> getHead() {
		return head;
	}

	/**
	 * Method to add a new DoublyLinkedNode with the given data at the specified index in the DoublyLinkedList
	 * @param data, the data of the added DoublyLinkedNode
	 * @param index, the position of the added DoublyinkedNode
	 * The running time is O(n)
	 */
	public void insert(T data, int index) {
		DoublyLinkedNode<T> add = new DoublyLinkedNode<T>();
		add.setData(data);
		// check if the required index is existed in the DoublyLinkedList
		if(index>size+1 || index<1) {
			System.out.println("Error: this is an invaild index to insert");
			return;
		}
		// insert if the new DoublyLinkedNode should be added at the front of the DoublyLinkedList
		if(index == 1) {
			// check if the DoublyLinkedList is empty
			if(head == null) {
				head = add;
				tail = head;
			}
			else {
				add.setNext(head);
                head.setPrevious(add);
				head = add;
			}
		}
		// insert if the new DoublyLinkedNode is asked to be added at the end of the DoublyLinkedList
		if(index == size+1){
			//check if the DoublyLinkedList is empty
			if(head == null) {
				head = add;
				tail = head;
			} else {
				tail.setNext(add);
				add.setPrevious(tail);
				tail = add;
			}
		}
		// insert the new DoublyLinkedNode when the index is normal
		if(index<=size && index>1) {
			DoublyLinkedNode<T> ptr = head;
			index = index - 1;
			for(int i=1; i<size; i++) {
				if(i == index) {
					DoublyLinkedNode<T> tmp = ptr.getNext();
					ptr.setNext(add);
					add.setPrevious(ptr);
					add.setNext(tmp);
					tmp.setPrevious(add);
					break;
				}
				ptr = ptr.getNext();
			}		
		}	
		size++;
	}
	
	/**
	 * Method to insert the node to the end of DoublyLinkedList if no index is provided
	 * @param data, the data of the added DoublyLinkedNode
	 * The running time is O(1)
	 */
	public void insert(T data) {
		DoublyLinkedNode<T> add = new DoublyLinkedNode<T>();
		add.setData(data);
		size++;
		//check if the DoublyLinkedList is empty
		if(head == null) {
			head = add;
			tail = head;
		} else {
			tail.setNext(add);
            add.setPrevious(tail);
			tail = add;
		}
	}

	/**
	 * Method to remove and return the node at the given index in the DoublyLinkedList
	 * @param index, the position of the DoublyLinkedNode which is asked to be removed
	 * @return the node that is removed
	 * The running time is O(n)
	 */
	public DoublyLinkedNode<T> remove(int index){     
		DoublyLinkedNode<T> tmp = new DoublyLinkedNode<T>();
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
	        if(head!=null){
                head.setPrevious(null);
	        }
	        size--; 
	        return tmp;
	    }
	    // delete if the last node in the list is required to delete
	    if (index == size) {  //这里index肯定大于1，即不是head
	    	DoublyLinkedNode<T>  lastButOne = tail.getPrevious();
	        tmp = tail;
	        tail = lastButOne;
	        tail.setNext(null);
	        size --;
	        return tmp;
	    }
	    DoublyLinkedNode<T>  ptr = head;
	    DoublyLinkedNode<T>  curr = new DoublyLinkedNode<T>();
	    index = index - 1 ;
	    for (int i = 1; i < size - 1; i++) {
	        if (i == index) {
	            tmp = ptr.getNext();
	            curr = tmp;
	            tmp = tmp.getNext();
	            ptr.setNext(tmp);
	            tmp.setPrevious(ptr);
	            break;
	        }
	        ptr = ptr.getNext();
	    }
	    size--;
	    return curr;
	}    
	
	/**
	 * Method to remove the last element in the DoublyLinkedList if no index is provided
	 * @return, the last Node in the list which is removed
	 * The running time is O(n)
	 */
	public DoublyLinkedNode<T> remove() {
		DoublyLinkedNode<T> tmp = tail;
        //如果只有一个元素或一个也没有
        if(size<2){
            head = null;
            tail = null;
            size = 0;
            return tmp;
        }
		tail = tail.getPrevious();
		tail.setNext(null);
		size--;
		return tmp;
	}

	/**
	 * Method to remove the first instance of a node with the specified data from the DoublyLinkedList
	 * @param data, the characteristic that the removed node should have
	 * The running time is O(n)
	 */
	public void remove(T data) {
		DoublyLinkedNode<T> tmp = head;
		DoublyLinkedNode<T> previous = head;
		// remove the first node in the DoublyLinkedList if the first node satisfied the required data
		if(head.getData() == data ){
			head = head.getNext();
			head.setPrevious(null);
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
                tmp.getNext().setPrevious(previous);
				size--;
				return;
			}
            previous = tmp;
            tmp = tmp.getNext();
		}
		System.out.println("Cannot find and remove the data");
	}

	/**
	 * Method to get the size of the DoublyLinkedList
	 * @return the size of the DoublyLinkedList
	 * The running time is O(1)
	 */
	public int size() {
		return size;
	}

	/**
	 * toString method to return the elements in the DoublyLinkedList
	 * The running time is O(n)
	 */
	public String toString() {
		DoublyLinkedNode<T> tmp = head;
		String answer = "";
		while(tmp != null){
			answer = answer +"->" + tmp.getData();
			tmp = tmp.getNext();
		}
		return answer;
	}


}