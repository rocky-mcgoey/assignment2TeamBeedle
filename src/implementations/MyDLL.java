package implementations;

import java.util.Arrays;

import utilities.Iterator;
import utilities.ListADT;

/**
 * Doubly-linked list implementation of the ListADT interface.
 * Uses MyDLLNode to store elements with bidirectional links.
 * 
 * @param <E> the type of elements stored in this list
 */
public class MyDLL<E> implements ListADT<E>
{
	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size;
	
	/**
	 * Constructs an empty doubly-linked list.
	 */
	public MyDLL()
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public void clear()
	{
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException
	{
		if (toAdd == null)
		{
			throw new NullPointerException();
		}
		
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
		
		// Insert at beginning
		if (index == 0)
		{
			if (isEmpty())
			{
				head = tail = newNode;
			}
			else
			{
				newNode.setNext(head);
				head.setPrev(newNode);
				head = newNode;
			}
		}
		// Insert at end
		else if (index == size)
		{
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
		// Insert in middle
		else
		{
			MyDLLNode<E> current = getNodeAt(index);
			MyDLLNode<E> previous = current.getPrev();
			
			newNode.setNext(current);
			newNode.setPrev(previous);
			previous.setNext(newNode);
			current.setPrev(newNode);
		}
		
		size++;
		return true;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException
	{
		if (toAdd == null)
		{
			throw new NullPointerException();
		}
		
		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
		
		if (isEmpty())
		{
			head = tail = newNode;
		}
		else
		{
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
		
		size++;
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException
	{
		if (toAdd == null)
		{
			throw new NullPointerException();
		}
		
		boolean changed = false;
		Iterator<? extends E> it = toAdd.iterator();
		
		while (it.hasNext())
		{
			add(it.next());
			changed = true;
		}
		
		return changed;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		return getNodeAt(index).getData();
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		MyDLLNode<E> nodeToRemove = getNodeAt(index);
		E data = nodeToRemove.getData();
		
		// Remove the only node
		if (size == 1)
		{
			head = tail = null;
		}
		// Remove head
		else if (index == 0)
		{
			head = head.getNext();
			head.setPrev(null);
		}
		// Remove tail
		else if (index == size - 1)
		{
			tail = tail.getPrev();
			tail.setNext(null);
		}
		// Remove from middle
		else
		{
			MyDLLNode<E> previous = nodeToRemove.getPrev();
			MyDLLNode<E> next = nodeToRemove.getNext();
			previous.setNext(next);
			next.setPrev(previous);
		}
		
		size--;
		return data;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException
	{
		if (toRemove == null)
		{
			throw new NullPointerException();
		}
		
		MyDLLNode<E> current = head;
		int index = 0;
		
		while (current != null)
		{
			if (toRemove.equals(current.getData()))
			{
				return remove(index);
			}
			current = current.getNext();
			index++;
		}
		
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException
	{
		if (toChange == null)
		{
			throw new NullPointerException();
		}
		
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		MyDLLNode<E> node = getNodeAt(index);
		E oldData = node.getData();
		node.setData(toChange);
		
		return oldData;
	}

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException
	{
		if (toFind == null)
		{
			throw new NullPointerException();
		}
		
		MyDLLNode<E> current = head;
		
		while (current != null)
		{
			if (toFind.equals(current.getData()))
			{
				return true;
			}
			current = current.getNext();
		}
		
		return false;
	}

	@Override
	public E[] toArray(E[] toHold) throws NullPointerException
	{
		if (toHold == null)
		{
			throw new NullPointerException();
		}
		
		if (toHold.length < size)
		{
			@SuppressWarnings("unchecked")
			E[] result = (E[]) Arrays.copyOf(toHold, size, toHold.getClass());
			toHold = result;
		}
		
		MyDLLNode<E> current = head;
		int index = 0;
		
		while (current != null)
		{
			toHold[index++] = current.getData();
			current = current.getNext();
		}
		
		if (toHold.length > size)
		{
			toHold[size] = null;
		}
		
		return toHold;
	}

	@Override
	public Object[] toArray()
	{
		Object[] result = new Object[size];
		MyDLLNode<E> current = head;
		int index = 0;
		
		while (current != null)
		{
			result[index++] = current.getData();
			current = current.getNext();
		}
		
		return result;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new MyDLLIterator<>(head);
	}
	
	/**
	 * Helper method to get the node at the specified index.
	 * 
	 * @param index the index of the node to retrieve
	 * @return the node at the specified index
	 */
	private MyDLLNode<E> getNodeAt(int index)
	{
		MyDLLNode<E> current;
		
		// Optimize by starting from the end if index is in the second half
		if (index < size / 2)
		{
			current = head;
			for (int i = 0; i < index; i++)
			{
				current = current.getNext();
			}
		}
		else
		{
			current = tail;
			for (int i = size - 1; i > index; i--)
			{
				current = current.getPrev();
			}
		}
		
		return current;
	}
}