package jp.co.rakuten.util.collection;

import java.util.Iterator;

public class CompatibleReverseIterator<T> implements Iterator<T> {
	private StdIterator<T> it;
	private boolean hasNext;
	public CompatibleReverseIterator(StdIterator<T> it) {
		this.it = it;
		this.hasNext = true;
	}
	@Override
	public boolean hasNext() {
		if ( ! hasNext ){
			hasNext = true;
			return ! it.prev().isEnd();
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
		return it.prev().get();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}

}
