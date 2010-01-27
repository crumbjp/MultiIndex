package jp.co.rakuten.util.bk;

import java.util.Iterator;

public class ConstIterator<E> implements Iterator<E> {
	Iterator<E> it;
	public ConstIterator(final Iterator<E> it) {
		this. it = it;
	}
	@Override
	public boolean hasNext() {
		return it.hasNext();
	}
	public E next() {
		return it.next();
	};
	public void remove() {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	};
}
