package jp.co.rakuten.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;


public interface MultiMap<K extends Comparable<K>,V> extends Iterable<ComparablePair<K, V>>{
	public Iterator<ComparablePair<K, V>> descendingIterator();
	public void put(final K k,final V v);
	public void putAll(final Collection<? extends ComparablePair<K,V>> c);
	public boolean contains(final Object e);
	public boolean containsAll(final Collection<? extends ComparablePair<K,V>> c);
	public void clear();
	public int size();
	public boolean isEmpty();
	public void remove(final Iterator<ComparablePair<K, V>> it);
	public void remove(final K e,final V v,final Comparator<V> c);
	public boolean removeAll(final K e);
	public V first();
	public V last();
	public V ceiling(final K e);
	public V floor(final K e);
	public V higher(final K e);
	public V lower(final K e);
	public V pollFirst();
	public V pollLast();
	public Iterator<ComparablePair<K, V>> equalRange( final K e );
	public Iterator<ComparablePair<K, V>> equalRange( final K e1 , final boolean inclusive1 , final K e2 , final boolean inclusive2);
}
