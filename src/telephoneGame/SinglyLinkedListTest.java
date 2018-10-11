
package telephoneGame;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Class for JUnit test
 * @author Huiyan Zhang; nicolezhang@brandeis.edu
 */
public class SinglyLinkedListTest {

	/**
	 * Test method for {@link telephoneGame.SinglyLinkedList#getHead()}.
	 */
	@Test
	public void testGetHead() {
		SinglyLinkedList<String> testList = new SinglyLinkedList<String>();
		//check if it is correct when the list is null
		assertEquals(null, testList.getHead());
		testList.insert("This is the head");
		//check if it is correct when the head is the only element in the list
		assertEquals("This is the head",testList.getHead().getData());
		testList.insert("telephone game begin");
		//check if it is correct when there is more than one elements in the list
		assertEquals("This is the head",testList.getHead().getData());		
	}

	/**
	 * Test method for {@link telephoneGame.SinglyLinkedList#insert(java.lang.Object, int)}.
	 */
	@Test
	public void testInsertTInt() {
		SinglyLinkedList<String> testList = new SinglyLinkedList<String>();
		//check if it is correct when the list is empty before inserting
		testList.insert("This is the head",1);
		assertEquals("This is the head",testList.getHead().toString());
		//check if it is correct by checking the size of the list and the new head after inserting
		testList.insert("This is the second sentence");
		testList.insert("This is the last sentence");
		testList.insert("This is an added sentence",1);
		assertEquals(4,testList.size());
		assertEquals("This is an added sentence",testList.getHead().toString());
		//check if it is correct when we insert the node at the end of the list
		testList.insert("This is an added end",5);
		assertEquals(5,testList.size());
		//check if it is correct when the required index is unavailable
		testList.insert("This is an useless add",7);
		assertEquals(5,testList.size());
	}

	/**
	 * Test method for {@link telephoneGame.SinglyLinkedList#insert(java.lang.Object)}.
	 */
	@Test
	public void testInsertT() {
		SinglyLinkedList<String> testList = new SinglyLinkedList<String>();
		//check if it is correct when the list is empty before inserting
		testList.insert("This is the head");
		assertEquals("This is the head",testList.getHead().toString());
		//check if it is correct when the list is not empty before inserting
		testList.insert("This is the second sentence");
		testList.insert("This is the last sentence");
		testList.insert("This is an added sentence");
		assertEquals(4,testList.size());
		assertEquals("This is the head",testList.getHead().toString());
	}

	/**
	 * Test method for {@link telephoneGame.SinglyLinkedList#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		SinglyLinkedList<String> testList = new SinglyLinkedList<String>();
		testList.insert("This is the head");
		testList.insert("This is the second sentence");
		testList.insert("This is the last sentence");
		//check if it is correct when need to remove a middle node
		assertEquals("This is the second sentence",testList.remove(2).toString());
		// check if it is correct when the index is invalid
		assertEquals(null,testList.remove(3));
		//check if it is correct when the first node is required to remove
		assertEquals("This is the head",testList.remove(1).toString());
		assertEquals("This is the last sentence",testList.remove(1).toString());
		//check if it is correct when the list is empty
		assertEquals(null,testList.remove(2));
	}

	/**
	 * Test method for {@link telephoneGame.SinglyLinkedList#remove()}.
	 */
	@Test
	public void testRemove() {
		SinglyLinkedList<String> testList = new SinglyLinkedList<String>();
		testList.insert("This is the head");
		testList.insert("This is the second sentence");
		testList.insert("This is the last sentence");
		assertEquals("This is the last sentence",testList.remove().toString());		
	}

	/**
	 * Test method for {@link telephoneGame.SinglyLinkedList#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveT() {
		SinglyLinkedList<String> testList = new SinglyLinkedList<String>();
		testList.insert("This is the head");
		testList.insert("This is the second sentence");
		testList.insert("This is the last sentence");
		//check if it is correct when the removed data is the head of the list
		testList.remove("This is the head");
		assertEquals(2,testList.size());
		assertEquals("This is the second sentence",testList.getHead().toString());
		//check if it is correct when the removed data is not the head of the list by checking the size
		testList.remove("This is the last sentence");		
		assertEquals(1,testList.size());
		//check if it is correct when the removed data is not in the list
		testList.remove("abc");
		assertEquals(1,testList.size());
	}

	/**
	 * Test method for {@link telephoneGame.SinglyLinkedList#size()}.
	 */
	@Test
	public void testSize() {
		SinglyLinkedList<String> testList = new SinglyLinkedList<String>();
		//check if it is correct when the list is empty
		assertEquals(0,testList.size());
		//check if it is correct when the list is not empty
		testList.insert("This is the second sentence");
		testList.insert("This is the last sentence");
		testList.insert("This is an added sentence");
		assertEquals(3,testList.size());
	}

	/**
	 * Test method for {@link telephoneGame.SinglyLinkedList#toString()}.
	 */
	@Test
	public void testToString() {
		SinglyLinkedList<String> testList = new SinglyLinkedList<String>();
		//check if it is correct when the list is empty
		assertEquals("",testList.toString());
		//check if the toString is correct when the list is not empty
		testList.insert("This is the head");
		testList.insert("This is the second sentence");
		testList.insert("This is the last sentence");
		assertEquals("->This is the head->This is the second sentence->This is the last sentence",testList.toString());
	}

}
