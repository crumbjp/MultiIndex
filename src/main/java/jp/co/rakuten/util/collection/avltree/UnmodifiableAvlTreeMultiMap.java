package jp.co.rakuten.util.collection.avltree;

import jp.co.rakuten.util.collection.ConstStdIterator;
import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdMultiMap;

public class UnmodifiableAvlTreeMultiMap<K,V> implements StdMultiMap<K,V>{
	protected AvlTree<Pair<K,V>,K> avlTree = null;
	public AvlTree<Pair<K,V>,K> getTree() {
		return avlTree;
	}
	public UnmodifiableAvlTreeMultiMap() {
	}
	public UnmodifiableAvlTreeMultiMap( final AvlTree<Pair<K,V>,K> avlTree ) {
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
	public void insert(Pair<K,V> t) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public void insert(K k ,V v) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}

	@Override
	public Pair<StdIterator<Pair<K,V>>,StdIterator<Pair<K,V>>> equlRange(K k) {
		Pair<AvlIterator<Pair<K,V>,K>,AvlIterator<Pair<K,V>,K>> p = avlTree.equalRange(k);
		return new Pair<StdIterator<Pair<K,V>>,StdIterator<Pair<K,V>>>(
				new ConstStdIterator<Pair<K,V>>(p.first),
				new ConstStdIterator<Pair<K,V>>(p.second));
	}
	@Override
	public ConstStdIterator<Pair<K,V>> findFirst(K k) {
		return new ConstStdIterator<Pair<K,V>>(avlTree.findFirst(k));
	}
	@Override
	public ConstStdIterator<Pair<K,V>> findLast(K k) {
		return new ConstStdIterator<Pair<K,V>>(avlTree.findLast(k));
	}
	@Override
	public ConstStdIterator<Pair<K,V>> upperBound(K k) {
		return new ConstStdIterator<Pair<K,V>>(avlTree.upperBound(k));
	}
	@Override
	public ConstStdIterator<Pair<K,V>> lowerBound(K k) {
		return new ConstStdIterator<Pair<K,V>>(avlTree.lowerBound(k));
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
