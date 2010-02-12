package jp.co.rakuten.util.collection.avltree;

import java.util.Comparator;

import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdMultiMap;

public class AvlTreeMultiMap<K,V> extends AvlTreeAdapter<Pair<K,V>,K> implements StdMultiMap<K,V>{
	public AvlTreeMultiMap() {
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
	public AvlTreeMultiMap( final AvlTree<Pair<K,V>,K> avlTree ) {
		super(avlTree);
	}
	public AvlTreeMultiMap( final Comparator<K> comparator) {
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
	public void insert(Pair<K,V> t) {
		avlTree.insert(t);
	}
	@Override
	public void insert(K k ,V v) {
		avlTree.insert(new Pair<K,V>(k,v));
	}
	@Override
	public boolean replace(StdIterator<Pair<K,V>> it , Pair<K,V> t) {
		if ( it.isEnd() )
			return false;
		return avlTree.rawReplace((AvlIterator<Pair<K,V>,K>)it, t);
	}
	@Override
	public boolean replace(StdIterator<Pair<K,V>> it , V v) {
		if ( it.isEnd() )
			return false;
		it.get().second = v;
		return true;
	}
}
