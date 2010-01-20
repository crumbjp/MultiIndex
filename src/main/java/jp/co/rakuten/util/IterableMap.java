package jp.co.rakuten.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

import jp.co.rakuten.util.tree.Pair;


public interface IterableMap<K,V> extends Iterable<Pair<K, V>>{
//	public Iterator<Pair<K, V>> descendingIterator();
	public Iterator<Pair<K, V>> iterator();
	public boolean put(final K k,final V v);
	public boolean contains(final Object e);
//	public void putAll(final Collection<? extends Pair<K,V>> c);
	public void clear();
	public int size();
	public boolean isEmpty();
	public void remove(final K e);
	public V get( final K e );
/*	
	public void put(final K k,final V v);
	public void putAll(final Collection<? extends ComparablePair<K,V>> c);
	public boolean containsAll(final Collection<? extends ComparablePair<K,V>> c);
	public void remove(final Iterator<ComparablePair<K, V>> it);
	public V first();
	public V last();
	public V ceiling(final K e);
	public V floor(final K e);
	public V higher(final K e);
	public V lower(final K e);
	public V pollFirst();
	public V pollLast();
	public V get( final K e );
	public Iterator<ComparablePair<K, V>> getRange( final K e1 , final boolean inclusive1 , final K e2 , final boolean inclusive2);
	*/
	
}
