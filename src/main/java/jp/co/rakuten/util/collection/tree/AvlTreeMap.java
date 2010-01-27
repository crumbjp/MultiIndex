package jp.co.rakuten.util.collection.tree;

import java.util.Comparator;

import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdMap;

public class AvlTreeMap<K,V> implements StdMap<K,V>{
	private AvlTree<Pair<K,V>,K> avlTree = null;
	public AvlTree<Pair<K,V>,K> getTree() {
		return avlTree;
	}
	public AvlTreeMap( final AvlTree<Pair<K,V>,K> avlTree ) {
		this.avlTree = avlTree;
	}
	public AvlTreeMap( final Comparator<K> comparator) {
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
	public AvlTreeMap() {
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
	public boolean insert(Pair<K,V> t) {
		return avlTree.insertUnique(t);
	}
	@Override
	public boolean insert(K k ,V v) {
		return avlTree.insertUnique(new Pair<K,V>(k,v));
	}
	@Override
	public boolean set(Pair<K,V> t) {
		return avlTree.insertReplace(t);
	}
	@Override
	public boolean set(K k ,V v) {
		return avlTree.insertReplace(new Pair<K,V>(k,v));
	}
	@Override
	public AvlIterator<Pair<K,V>> find(K k) {
		return avlTree.find(k);
	}
	@Override
	public V get(K k) {
		AvlIterator<Pair<K,V>> it = avlTree.find(k);
		if ( it.isEnd() ) 
			return null;
		return it.get().second;
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
	public void erase(AvlIterator<Pair<K,V>> it) {
		avlTree.erase(it);
	}
}
