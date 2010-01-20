package jp.co.rakuten.util;

import java.util.Iterator;

public interface StdIterator<E> extends Iterator<E> {
	@Override
	public boolean hasNext();
	public E next();
	public void remove();

	
	
}
