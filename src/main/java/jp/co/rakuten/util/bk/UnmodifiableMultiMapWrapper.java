package jp.co.rakuten.util.bk;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public abstract class UnmodifiableMultiMapWrapper<K extends Comparable<K>,V> implements MultiMap<K, V> {
	protected MultiMap<K,V> container;
	@Override
	public void clear() {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public boolean contains(final Object key) {
		return this.container.contains(key);
	}
	@Override
	public boolean containsAll(final Collection<? extends ComparablePair<K, V>> c) {
		return this.container.containsAll(c);
	}
	@Override
	public Iterator<ComparablePair<K,V>> equalRange(final K e) {
		return new ConstIterator<ComparablePair<K,V>>(this.container.equalRange(e));
	}
	@Override
	public Iterator<ComparablePair<K, V>> iterator() {
		return new ConstIterator<ComparablePair<K,V>>(this.container.iterator());
	}
	@Override
	public Iterator<ComparablePair<K, V>> descendingIterator() {
		return new ConstIterator<ComparablePair<K,V>>(this.container.descendingIterator());
	}
	@Override
	public Iterator<ComparablePair<K,V>> equalRange(final K e1,final boolean b1,final K e2,final boolean b2) {
		return new ConstIterator<ComparablePair<K,V>>(this.container.equalRange(e1,b1,e2,b2));
	}
	@Override
	public boolean isEmpty() {
		return this.container.isEmpty();
	}
	@Override
	public void put(final K k, final V v) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public void putAll(final Collection<? extends ComparablePair<K, V>> c) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public void remove(final Iterator<ComparablePair<K, V>> it) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public void remove(final K e,final V v,final Comparator<V> c){
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public boolean removeAll(final K e) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	};
	@Override
	public int size() {
		return this.container.size();
	}
	@Override
	public V ceiling(final K e) {
		return this.container.ceiling(e);
	};
	@Override
	public V floor(final K e) {
		return this.container.floor(e);
	};
	@Override
	public V first() {
		return this.container.first();
	}
	@Override
	public V last() {
		return this.container.last();
	}
	@Override
	public V higher(final K e) {
		return this.container.higher(e);
	};
	@Override
	public V lower(final K e) {
		return this.container.lower(e);
	};
	@Override
	public V pollFirst() {
		return this.container.pollFirst();
	}
	@Override
	public V pollLast() {
		return this.container.pollLast();
	}
	
}
