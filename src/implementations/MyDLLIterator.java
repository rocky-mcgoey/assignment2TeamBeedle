package implementations;

import java.util.NoSuchElementException;
import utilities.Iterator;

/**
 * Iterator implementation for MyDLL.
 * Iterates through the DLL
 * 
 * @param <E> the type of elements returned by this iterator
 */
public class MyDLLIterator<E> implements Iterator<E>
{
	private MyDLLNode<E> current;
	
	/**
	 * Constructs an iterator starting at the specified node.
	 * 
	 * @param head the first node to iterate from
	 */
	public MyDLLIterator(MyDLLNode<E> head)
	{
		this.current = head;
	}
	
	@Override
	public boolean hasNext()
	{
		return current != null;
	}
	
	@Override
	public E next() throws NoSuchElementException
	{
		if (!hasNext())
		{
			throw new NoSuchElementException();
		}
		
		E data = current.getData();
		current = current.getNext();
		return data;
	}
}
