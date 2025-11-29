package implementations;

import exceptions.EmptyQueueException;
import utilities.Iterator;
import utilities.QueueADT;

/**
 * Queue implementation using MyDLL as the underlying data structure.
 * Follows FIFO (First In, First Out) principle.
 * 
 * @param <E> the type of elements stored in this queue
 */
public class MyQueue<E> implements QueueADT<E>
{
	private MyDLL<E> list;
	
	/**
	 * Constructs an empty queue.
	 */
	public MyQueue()
	{
		this.list = new MyDLL<>();
	}

	@Override
	public void enqueue(E toAdd) throws NullPointerException
	{
		if (toAdd == null)
		{
			throw new NullPointerException();
		}
		
		list.add(toAdd);
	}

	@Override
	public E dequeue() throws EmptyQueueException
	{
		if (isEmpty())
		{
			throw new EmptyQueueException("Cannot dequeue from an empty queue.");
		}
		
		return list.remove(0);
	}

	@Override
	public E peek() throws EmptyQueueException
	{
		if (isEmpty())
		{
			throw new EmptyQueueException("Cannot peek at an empty queue.");
		}
		
		return list.get(0);
	}

	@Override
	public void dequeueAll()
	{
		list.clear();
	}

	@Override
	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException
	{
		if (toFind == null)
		{
			throw new NullPointerException();
		}
		
		return list.contains(toFind);
	}

	@Override
	public int search(E toFind)
	{
		if (toFind == null)
		{
			return -1;
		}
		
		int position = 1;
		Iterator<E> it = list.iterator();
		
		while (it.hasNext())
		{
			if (toFind.equals(it.next()))
			{
				return position;
			}
			position++;
		}
		
		return -1;
	}

	@Override
	public Iterator<E> iterator()
	{
		return list.iterator();
	}

	@Override
	public boolean equals(QueueADT<E> that)
	{
		if (that == null)
		{
			return false;
		}
		
		if (this.size() != that.size())
		{
			return false;
		}
		
		Iterator<E> thisIt = this.iterator();
		Iterator<E> thatIt = that.iterator();
		
		while (thisIt.hasNext())
		{
			E thisElement = thisIt.next();
			E thatElement = thatIt.next();
			
			if (!thisElement.equals(thatElement))
			{
				return false;
			}
		}
		
		return true;
	}

	@Override
	public Object[] toArray()
	{
		return list.toArray();
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException
	{
		if (holder == null)
		{
			throw new NullPointerException();
		}
		
		return list.toArray(holder);
	}

	@Override
	public boolean isFull()
	{
		return false; // dynamic, never full
	}

	@Override
	public int size()
	{
		return list.size();
	}
}
