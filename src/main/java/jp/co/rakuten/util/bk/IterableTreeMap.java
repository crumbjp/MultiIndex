package jp.co.rakuten.util.bk;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import jp.co.rakuten.util.collection.tree.Pair;


public class IterableTreeMap<K extends Comparable<K>,V> implements IterableMap<K,V>{
	TreeSet<ComparablePair<K,V>> container = new TreeSet<ComparablePair<K,V>>(); 

	
	@Override
	public Iterator<Pair<K, V>> iterator() {
		return (Iterator)container.iterator();
	}
//	@Override
//	public Iterator<Pair<K, V>> descendingIterator() {
//		return new IteratorConverter<Pair<K, V>,ComparablePair<K, V>>(container.	descendingIterator());
//	}
	@Override
	public boolean put(final K k,final V v) {
		return container.add(new ComparablePair<K, V>(k, v));
	}
//	@Override
//	public void putAll(final Collection<? extends Pair<K,V>> c) {
//	}
	
	@Override
	public boolean contains(final Object e) {
		return container.contains(e);
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
	public void remove(final K key) {
		container.remove(key);
	}
	public V get(K k) {
		if ( container.contains(k)){
			return container.ceiling(new ComparablePair<K, V>(k, null)).second;
		}
		return null;
	}
	
}
