package implementations;

/**
 * Node class for doubly-linked list implementation.
 * 
 * @param <E> the type of element stored as data
 */
public class MyDLLNode<E>
{
	private E data;
	private MyDLLNode<E> next;
	private MyDLLNode<E> prev;
	
	/**
	 * Constructs a new node with the argument data
	 * 
	 * @param data the element to store in this node
	 */
	public MyDLLNode(E data)
	{
		this.data = data;
		this.next = null;
		this.prev = null;
	}
	
	/**
	 * Gets the data stored in this node.
	 * 
	 * @return the data element.
	 */
	public E getData()
	{
		return data;
	}
	
	/**
	 * Sets the data stored in this node.
	 * 
	 * @param data the new data element.
	 */
	public void setData(E data)
	{
		this.data = data;
	}
	
	/**
	 * Gets the next node in the list.
	 * 
	 * @return the next node, or null if this is the final node in the list.
	 */
	public MyDLLNode<E> getNext()
	{
		return next;
	}
	
	/**
	 * Sets the next node in the list.
	 * 
	 * @param next the node to set as next.
	 */
	public void setNext(MyDLLNode<E> next)
	{
		this.next = next;
	}
	
	/**
	 * Gets the previous node in the list.
	 * 
	 * @return the previous node, or null if there is no previous.
	 */
	public MyDLLNode<E> getPrev()
	{
		return prev;
	}
	
	/**
	 * Sets the previous node in the list.
	 * 
	 * @param prev the node to set as previous.
	 */
	public void setPrev(MyDLLNode<E> prev)
	{
		this.prev = prev;
	}
}
