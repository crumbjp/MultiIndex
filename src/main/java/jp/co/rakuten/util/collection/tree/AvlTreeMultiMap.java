package jp.co.rakuten.util.collection.tree;

import java.util.Comparator;

import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdMultiMap;

public class AvlTreeMultiMap<K,V> implements StdMultiMap<K,V>{
	private AvlTree<Pair<K,V>,K> avlTree = null;
	public AvlTree<Pair<K,V>,K> getTree() {
		return avlTree;
	}
	public AvlTreeMultiMap( final AvlTree<Pair<K,V>,K> avlTree ) {
		this.avlTree = avlTree;
	}
	public AvlTreeMultiMap( final Comparator<K> comparator) {
		avlTree = new AvlTree<Pair<K,V>,K>( 
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
		);
	}
	public AvlTreeMultiMap() {
		avlTree = new AvlTree<Pair<K,V>,K>( 
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
			);
	}
	@Override
	public void clear() {
		avlTree.clear();
	}
	@Override
	public long size(){
		return avlTree.size();
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
	public Pair<StdIterator<Pair<K,V>>,StdIterator<Pair<K,V>>> equlRange(K k) {
		return (Pair)avlTree.equalRange(k);
	}
	@Override
	public AvlIterator<Pair<K,V>> findFirst(K k) {
		return avlTree.findFirst(k);
	}
	@Override
	public AvlIterator<Pair<K,V>> findLast(K k) {
		return avlTree.findLast(k);
	}
	@Override
	public AvlIterator<Pair<K,V>> begin() {
		return avlTree.begin();
	}
	@Override
	public AvlIterator<Pair<K,V>> last() {
		return avlTree.last();
	}
	@Override
	public AvlIterator<Pair<K,V>> end() {
		return avlTree.end();
	}

	public boolean replace(AvlIterator<Pair<K,V>> it , Pair<K,V> t) {
		return avlTree.rawReplace((AvlIterator<Pair<K,V>>)it, t);
	}

	public boolean replace(AvlIterator<Pair<K,V>> it , V v) {
		if ( it.isEnd() )
			return false;
		it.get().second = v;
		return true;
	}

	public void erase(AvlIterator<Pair<K,V>> it) {
		avlTree.erase((AvlIterator<Pair<K,V>>)it);
	}
}
