package telephoneGame;

/**
 * This class helps to implement DoublyLinkedNode
 * @author Huiyan Zhang; nicolezhang@brandies.edu
 *
 * @param <T>
 */
public class DoublyLinkedNode<T>{
	private T data;
	private DoublyLinkedNode<T> previous;
	private DoublyLinkedNode<T> next;

	/**
	 * Constructor
	 */
	public DoublyLinkedNode() {
	}

	/**
	 * Constructor
	 * @param data
	 */
	public DoublyLinkedNode(T data) {
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
	 * Function to get the next DoublyLinkedNode
	 * @return the next DoublyLinkedNode
	 * The running time is O(1)
	 */
	public DoublyLinkedNode<T> getNext() {
		return this.next;
	}
	
	/**
	 * Function to set the next DoublyLinkedNode
	 * @param next, the next DoublyLinkedNode
	 * The running time is O(1)
	 */

	public void setNext(DoublyLinkedNode<T> next) {
		this.next = next;
	}

	/**
	 * Function to get the previous DoublyLinkedNode
	 * @return the previous DoublyLinkedNode
	 * The running time is O(1)
	 */
	public DoublyLinkedNode<T> getPrevious() {
		return this.previous;
	}

	/**
	 * Function to set the previous DoublyLinkedNode
	 * @param previous, the previous DoublyLinkedNode
	 * The running time is O(1)
	 */

	public void setPrevious(DoublyLinkedNode<T> previous) {
		this.previous = previous;
	}

	/**
	 * toString method to return the data item
	 * The running time is O(1)
	 */
	public String toString() {
		return "" + this.data;
	}

}

