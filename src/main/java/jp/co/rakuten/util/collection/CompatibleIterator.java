package jp.co.rakuten.util.collection;

import java.util.Iterator;
/**
 * Convert I/F from StdSequence to java.lang.Iterator.
 * 
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @see CompatibleIterable
 * @param <T> Target data-type. 
 */
public class CompatibleIterator<T> implements Iterator<T> {
	private StdIterator<T> it;
	private boolean hasNext;
	/**
	 * Instantiate with iterator.
	 * @param it target iterator
	 */
	public CompatibleIterator(StdIterator<T> it) {
		this.it = it;
		this.hasNext = true;
	}
	@Override
	public boolean hasNext() {
		if ( ! hasNext ){
			hasNext = true;
			return ! it.next().isEnd();
		}
		return ! it.isEnd();
	}

	@Override
	public T next() {
		if ( hasNext) {
			hasNext = false;
			return it.get();
		}
		hasNext = false;
		return it.next().get();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}

}
