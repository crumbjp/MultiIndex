package jp.co.rakuten.util;

import java.util.ListIterator;

public class ConstListIterator<E> implements ListIterator<E> {
	ListIterator<E> it;
	public ConstListIterator(final ListIterator<E> it) {
		this. it = it;
	}
	@Override
	public boolean hasNext() {
		return it.hasNext();
	}
	@Override
	public E next() {
		return it.next();
	};
	@Override
	public void remove() {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	};
	@Override
	public void add(E e) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	};
	@Override
	public E previous() {
		return it.previous();
	}
	@Override
	public boolean hasPrevious() {
		return it.hasPrevious();
	}
	@Override
	public int nextIndex() {
		return it.nextIndex();
	}
	@Override
	public int previousIndex() {
		return it.previousIndex();
	}
	@Override
	public void set(E e) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	};
	
}
