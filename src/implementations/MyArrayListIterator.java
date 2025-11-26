package implementations;

import java.util.NoSuchElementException;
import utilities.Iterator;

public class MyArrayListIterator<E> implements Iterator<E>
{
	private final E[] data;
	private final int size;
	private int current = 0;
	
	public MyArrayListIterator(E[] data, int size)
	{
		this.data = data;
		this.size = size;
	}
	
	@Override
	public boolean hasNext()
	{
		return current < size;
	}
	@Override
	public E next() throws NoSuchElementException
	{
		if (!hasNext())
		{
			throw new NoSuchElementException();
		}
		return data[current++];
	}

}
