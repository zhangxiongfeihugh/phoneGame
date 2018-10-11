package telephoneGame;

/**
 * This class helps to implement SinglyLinkedNode
 * @author Huiyan Zhang; nicolezhang@brandies.edu
 *
 * @param <T>
 */
public class SinglyLinkedNode<T>{
	private T data;
	private SinglyLinkedNode<T> next;
	
	/**
	 * Constructor
	 */
	public SinglyLinkedNode() {
	}
	
	/**
	 * Constructor
	 * @param data
	 */
	public SinglyLinkedNode(T data) {
		this.data = data;
	}

	/**
	 * Function to set the data to current Node
	 * @param data
	 * The running time is O(1)
	*/
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Function to get the data from current Node
	 * @return data
	 * The running time is O(1)
	 */
	public T getData() {
		return this.data;
	}
	
	/**
	 * Function to get the next SinglyLinkedNode
	 * @return the next SinglyLinkedNode
	 * The running time is O(1)
	 */
	public SinglyLinkedNode<T> getNext() {
		return this.next;
	}
	
	/**
	 * Function to set the SinglyLinkedNode
	 * @param next, the next SinglyLinkedNode
	 * The running time is O(1)
	 */

	public void setNext(SinglyLinkedNode<T> next) {
		this.next = next;
	}

	/**
	 * toString method to return the data item
	 * The running time is O(1)
	 */
	public String toString() {
		return "" + this.data;
	}

}

