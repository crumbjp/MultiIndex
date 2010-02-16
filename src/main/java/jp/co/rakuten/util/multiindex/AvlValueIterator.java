package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.avltree.AvlIterator;

public class AvlValueIterator<T,K> implements StdIterator<T> {
	private final AvlIterator<Pair<K,T>,K> it;
	public AvlValueIterator(final AvlIterator<Pair<K,T>,K> it) {
		this.it = it;
	}
	@Override
	public Object container() {
		return it.container();
	}

	@Override
	public boolean erase() {
		return it.erase();
	}

	@Override
	public T get() {
		return it.get().second;
	}

	@Override
	public boolean isEnd() {
		return it.isEnd();
	}

	@Override
	public StdIterator<T> next() {
		it.next();
		return this; 
	}

	@Override
	public StdIterator<T> prev() {
		it.prev();
		return this;
	}
	@Override
	public boolean replace(T t) {
		return it.replace(new Pair<K,T>(it.get().first,t));
	}
	@Override
	public boolean equals(Object obj) {
		return it.equals(((AvlValueIterator<T,K>)obj).it);
	}
}
