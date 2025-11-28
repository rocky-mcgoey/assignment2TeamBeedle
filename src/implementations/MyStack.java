package implementations;

import java.util.EmptyStackException;

import utilities.Iterator;
import utilities.StackADT;

public class MyStack<E> implements StackADT<E> {

	MyArrayList<E> stackList = new MyArrayList<E>();
	
	@Override
	public void push(E toAdd) throws NullPointerException {
		if (toAdd != null) {
			stackList.add(toAdd);
		}
		else throw new NullPointerException();
	}

	@Override
	public E pop() throws EmptyStackException {
		if (stackList.size() < 1) throw new EmptyStackException();
		E element = stackList.remove(stackList.size() - 1);
		return element;
	}

	@Override
	public E peek() throws EmptyStackException {
		if (stackList.size() < 1) throw new EmptyStackException();
		return stackList.get(stackList.size() - 1);
	}

	@Override
	public void clear() {
		stackList.clear();
	}

	@Override
	public boolean isEmpty() {
		if(stackList.size() > 0) return false;
		return true;
	}

	@Override
	public Object[] toArray() {
		Object[] objectList = stackList.toArray();
		Object[] newObjectList = new Object[objectList.length];
		for (int i = objectList.length - 1, j = 0; i >= 0; i--, j++) {
			newObjectList[j] = objectList[i];
		}
		return newObjectList;
	}

	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		if (holder == null) throw new NullPointerException();
		MyArrayList<E> newList = new MyArrayList<E>();
		for (int i = stackList.size() - 1, j = 0; i >= 0; i--, j++) {
			newList.add(j, stackList.get(i));
		}
		holder = newList.toArray(holder);
		return holder;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) throw new NullPointerException();
		return stackList.contains(toFind);
	}

	@Override
	public int search(E toFind) {
		int index = 1;
		for (int i = stackList.size() - 1; i >= 0; i--) {
			if (stackList.get(i) == toFind) return index;  
			index++;
		}
		return -1;
	}

	@Override
	public Iterator<E> iterator() {
		MyArrayList<E> newList = new MyArrayList<E>();
		for (int i = stackList.size() - 1; i >= 0; i--) newList.add(stackList.get(i));
		Iterator<E> it = newList.iterator();
		return it;
	}

	@Override
	public boolean equals(StackADT<E> that) {
		if (stackList.size() != that.size()) return false;
		Iterator<E> thisIt = this.iterator();
		Iterator<E> thatIt = that.iterator();
		for (int i = 0; i < stackList.size(); i++) {
			if (thisIt.next() != thatIt.next()) return false;
		}
		return true;
	}

	@Override
	public int size() {
		return stackList.size();
	}

	@Override
	public boolean stackOverflow() {
		// TODO Auto-generated method stub
		return false;
	}

}
