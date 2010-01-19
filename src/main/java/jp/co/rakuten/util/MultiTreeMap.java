package jp.co.rakuten.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;


public class MultiTreeMap<K extends Comparable<K>,V> implements MultiMap<K,V>{
	MultiTreeSet<ComparablePair<K, V>> container = new MultiTreeSet<ComparablePair<K, V>>(); 
	
	@Override
	public Iterator<ComparablePair<K, V>> iterator() {
		return container.iterator();
	}
	@Override
	public Iterator<ComparablePair<K, V>> descendingIterator() {
		return container.	descendingIterator();
	}
	@Override
	public void put(final K k,final V v) {
		container.add(new ComparablePair<K, V>(k,v));
	}
	@Override
	public void putAll(final Collection<? extends ComparablePair<K,V>> c) {
		container.addAll(c);
	}
	@Override
	public boolean contains(final Object e) {
		return container.contains(new ComparablePair<K, V>((K)e,null));
	}
	@Override
	public boolean containsAll(final Collection<? extends ComparablePair<K,V>> c) {
		return container.containsAll(c);
	}
	@Override
	public void clear() {
		container.clear();
	}
	@Override
	public int size() {
		return container.size();
	}
	@Override
	public boolean isEmpty() {
		return container.isEmpty();
	}
	@Override
	public void remove(final Iterator<ComparablePair<K, V>> it) {
		it.remove();
	}
	@Override
	public void remove(final K e,final V v,final Comparator<V> c) {
		Iterator<ComparablePair<K, V>> it = container.equalRange(new ComparablePair<K, V>(e,null)); 
		while( it.hasNext()) {
			ComparablePair<K, V> p = it.next();
			if ( c.compare(v, p.second) == 0 ) {
				it.remove();
				break;
			}
		}
	}
	@Override
	public boolean removeAll(final K e) {
		return container.removeAll(new ComparablePair<K, V>((K)e,null));
	}
	@Override
	public V first() {
		return container.first().second;
	}
	@Override
	public V last() {
		return container.last().second;
	}
	@Override
	public V ceiling(final K e) {
		return container.ceiling(new ComparablePair<K, V>((K)e,null)).second;
	};
	@Override
	public V floor(final K e) {
		return container.floor(new ComparablePair<K, V>((K)e,null)).second;
	};
	@Override
	public V higher(final K e) {
		ComparablePair<K, V> d = container.higher(new ComparablePair<K, V>((K)e,null)); 
		return (d == null)?null:d.second;
	};
	@Override
	public V lower(final K e) {
		ComparablePair<K, V> d = container.lower(new ComparablePair<K, V>((K)e,null)); 
		return (d == null)?null:d.second;
	};
	@Override
	public V pollFirst() {
		return container.pollFirst().second;
	}		
	@Override
	public V pollLast() {
		return container.pollLast().second;
	}
	@Override
	public Iterator<ComparablePair<K, V>> equalRange( final K e ) {
		return container.equalRange(new ComparablePair<K, V>((K)e,null));
	}
	@Override
	public Iterator<ComparablePair<K, V>> equalRange( final K e1 , final boolean inclusive1 , final K e2 , final boolean inclusive2) {
		return container.equalRange(new ComparablePair<K, V>((K)e1,null),inclusive1,new ComparablePair<K, V>((K)e2,null),inclusive2);
	}
}
