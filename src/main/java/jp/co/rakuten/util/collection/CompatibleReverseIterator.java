package jp.co.rakuten.util.collection;

import java.util.Iterator;

/**
 * Convert I/F with reverse from StdSequence to java.lang.Iterator.
 * 
 *	
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @see CompatibleReverseIterable
 * @param <T> Target data-type. 
 */
public class CompatibleReverseIterator<T> implements Iterator<T> {
	private StdIterator<T> it;
	private StdIterator<T> itend;
	private boolean hasNext;
	/**
	 * Instantiate with iterator.
	 * @param it target iterator
	 */
	public CompatibleReverseIterator(StdIterator<T> it,StdIterator<T> itend) {
		this.it = it;
		this.itend = itend;
		this.hasNext = true;
	}
	public CompatibleReverseIterator(Pair<StdIterator<T>,StdIterator<T>> pair) {
		this.it = pair.first;
		this.itend = pair.second;
		this.hasNext = true;
	}
	@Override
	public boolean hasNext() {
		if ( ! hasNext ){
			hasNext = true;
			it.prev();
		}
		return ! it.equals(itend);
	}

	@Override
	public T next() {
		if ( ! hasNext) {
			it.prev();
		}
		hasNext = false;
		return it.get();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}

}
