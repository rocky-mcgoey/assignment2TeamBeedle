package implementations;

import java.util.Arrays;

import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements ListADT<E>
{
	//constant
	private final int DEFAULT_CAPACITY = 10;
	private final int MULTIPLIER = 2;
	//attributes
	private E[] array;
	private int size;
	
	@SuppressWarnings("unchecked")
	public MyArrayList()
	{
		array = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}

	@Override
	public int size()
	{
				
		return size;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < size; i++)
		{
			array[i] = null;
		}
		// TODO Auto-generated method stub
		size = 0;
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException
	{
		if(toAdd == null)
			throw new NullPointerException();
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		ensureCapacity();
		if(index == size)
			return add(toAdd);
		shiftToRight(index);
		array[index] = toAdd;
		size++;
		
		return true;
	}

	private void shiftToRight(int index)
	{
		for(int i = size; i > index; i--)
		{
			array[i] = array[i-1];
		}
		
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException
	{
		if(toAdd == null)
			throw new NullPointerException();
		ensureCapacity();
		array[size] = toAdd;
		size++;
		
		return true;
	}

	@SuppressWarnings("unchecked")
	private void ensureCapacity()
	{
		if(size == array.length)
		{
			E[] newArray = (E[]) new Object[array.length * MULTIPLIER];
			for(int i = 0; i < array.length; i++)
			{
				newArray[i] = array[i];
			}
			array = newArray;
		}
		
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
		
		
		// TODO Auto-generated method stub
		return changed;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		// TODO Auto-generated method stub
		return array[index];
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		E removed = array[index];
		
		for (int i = index; i < size -1; i++)
		{
			array[i] = array[i + 1];
		}
		array[size - 1] = null;
		size--;
		// TODO Auto-generated method stub
		return removed;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException
	{
		if (toRemove == null)
		{
			throw new NullPointerException();
		}
		
		for (int i =0; i < size; i++)
		{
			if (toRemove.equals(array[i]))
			{
				return remove(i);
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException
	{
		if (toChange == null)
		{
			throw new NullPointerException();
		}
		if ( index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		E old = array[index];
		array[index] = toChange;
		
		// TODO Auto-generated method stub
		return old;
	}

	@Override
	public boolean isEmpty()
	{
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException
	{
		if (toFind == null)
		{
			throw new NullPointerException();
		}
		
		for(int i = 0; i < size; i++)
		{
			if (toFind.equals(array[i]))
			{
				return true;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E[] toArray(E[] toHold) throws NullPointerException
	{
		if (toHold == null)
		{
			throw new NullPointerException();
		}
		
		if(toHold.length < size)
		{
			@SuppressWarnings("unchecked")
			E[] result = (E[]) Arrays.copyOf(array, size, toHold.getClass());
			return result;
		}
		System.arraycopy(array, 0, toHold, 0, size);
		
		if (toHold.length > size)
		{
			toHold[size] = null;
		}
		// TODO Auto-generated method stub
		return toHold;
	}

	@Override
	public Object[] toArray()
	{
		Object[] result = new Object[size];
		
		
		for (int i = 0; i < size; i++)
		{
			result[i] = array[i];
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public Iterator<E> iterator()
	{
		// TODO Auto-generated method stub
		return new MyArrayListIterator<>(array, size);
	}

}
