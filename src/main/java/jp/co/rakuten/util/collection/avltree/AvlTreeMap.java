package jp.co.rakuten.util.collection.avltree;

import java.util.Comparator;

import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdMap;

public class AvlTreeMap<K,V> extends AvlTreeAdapter<Pair<K,V>,K> implements StdMap<K,V>{
	public AvlTreeMap() {
		super(new AvlTree<Pair<K,V>,K>( 
				new Comparator<Pair<K,V>>() {
					public int compare(Pair<K,V> o1, Pair<K,V> o2) {
						return ((Comparable<K>)o1.first).compareTo(o2.first);
					}
				},
				new FindComparator<Pair<K,V>, K>() {
					public int compare(Pair<K,V> o1, K o2) {
						return ((Comparable<K>)o1.first).compareTo(o2);
					}
				}
		));
	}
	public AvlTreeMap( final AvlTree<Pair<K,V>,K> avlTree ) {
		super(avlTree);
	}
	public AvlTreeMap( final Comparator<K> comparator) {
		super(new AvlTree<Pair<K,V>,K>( 
				new Comparator<Pair<K,V>>() {
					public int compare(Pair<K,V> o1, Pair<K,V> o2) {
						return comparator.compare(o1.first, o2.first);
					}
				},
				new FindComparator<Pair<K,V>, K>() {
					public int compare(Pair<K,V> o1, K o2) {
						return comparator.compare(o1.first, o2);
					}
				}
		));
	}
	@Override
	public boolean insert(Pair<K,V> t) {
		return avlTree.insertUnique(t);
	}
	@Override
	public boolean insert(K k ,V v) {
		return avlTree.insertUnique(new Pair<K,V>(k,v));
	}
	@Override
	public boolean insertReplace(Pair<K,V> t) {
		return avlTree.insertReplace(t);
	}
	@Override
	public boolean replace(K k ,V v) {
		return avlTree.insertReplace(new Pair<K,V>(k,v));
	}
	@Override
	public V get(K k) {
		AvlIterator<Pair<K,V>,K> it = avlTree.find(k);
		if ( it.isEnd() ) 
			return null;
		return it.get().second;
	}
}
