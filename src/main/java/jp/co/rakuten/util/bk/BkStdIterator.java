package jp.co.rakuten.util.bk;

import java.util.Iterator;

public interface BkStdIterator<E> extends Iterator<E> {
	@Override
	public boolean hasNext();
	public E next();
	public void remove();

	
	
}
