package jp.co.rakuten.util.collection.tree;

import jp.co.rakuten.util.collection.ConstStdIterator;
import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdMap;

public class UnmodifiableAvlTreeMap<K,V> implements StdMap<K,V>{
	protected AvlTree<Pair<K,V>,K> avlTree = null;
	public AvlTree<Pair<K,V>,K> getTree() {
		return avlTree;
	}
	public UnmodifiableAvlTreeMap() {
	}

	public UnmodifiableAvlTreeMap( final AvlTree<Pair<K,V>,K> avlTree ) {
		this.avlTree = avlTree;
	}
	@Override
	public void clear() {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public long size(){
		return avlTree.size();
	}
	@Override
	public boolean insert(Pair<K,V> t) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public boolean insert(K k ,V v) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public boolean set(Pair<K,V> t) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public boolean set(K k ,V v) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public ConstStdIterator<Pair<K,V>> find(K k) {
		return new ConstStdIterator<Pair<K,V>>(avlTree.find(k));
	}
	@Override
	public V get(K k) {
		StdIterator<Pair<K,V>> it = avlTree.find(k);
		if ( it.isEnd() ) 
			return null;
		return it.get().second;
	}
	@Override
	public ConstStdIterator<Pair<K,V>> begin() {
		return new ConstStdIterator<Pair<K,V>>(avlTree.begin());
	}
	@Override
	public ConstStdIterator<Pair<K,V>> last() {
		return new ConstStdIterator<Pair<K,V>>(avlTree.last());
	}
	@Override
	public StdIterator<Pair<K,V>> end() {
		return avlTree.end();
	}
}
