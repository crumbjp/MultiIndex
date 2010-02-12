package jp.co.rakuten.util.collection.avltree;

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
	public ConstStdIterator<Pair<K,V>> end() {
		return new ConstStdIterator<Pair<K,V>>(avlTree.end());
	}
	@Override
	public void erase(StdIterator<Pair<K, V>> it) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public Pair<StdIterator<Pair<K, V>>, StdIterator<Pair<K, V>>> equlRangeStartWith(
			K k, FindComparator<Pair<K, V>, K> startWithComparator) {
		Pair<AvlIterator<Pair<K,V>,K>,AvlIterator<Pair<K,V>,K>> p = avlTree.equalRange(k,startWithComparator);
		return new Pair<StdIterator<Pair<K,V>>,StdIterator<Pair<K,V>>>(
				new ConstStdIterator<Pair<K,V>>(p.first),
				new ConstStdIterator<Pair<K,V>>(p.second));
	}
	@Override
	public ConstStdIterator<Pair<K, V>> findFirstStartWith(K k,
			FindComparator<Pair<K, V>, K> startWithComparator) {
		return new ConstStdIterator<Pair<K,V>>(avlTree.findFirst(k,startWithComparator));
	}
	@Override
	public ConstStdIterator<Pair<K, V>> findLastStartWith(K k,
			FindComparator<Pair<K, V>, K> startWithComparator) {
		return new ConstStdIterator<Pair<K,V>>(avlTree.findLast(k,startWithComparator));
	}
	@Override
	public ConstStdIterator<Pair<K, V>> lowerBoundStartWith(K k,
			FindComparator<Pair<K, V>, K> startWithComparator) {
		return new ConstStdIterator<Pair<K,V>>(avlTree.lowerBound(k,startWithComparator));
	}
	@Override
	public ConstStdIterator<Pair<K, V>> upperBoundStartWith(K k,
			FindComparator<Pair<K, V>, K> startWithComparator) {
		return new ConstStdIterator<Pair<K,V>>(avlTree.upperBound(k,startWithComparator));
	}
}
