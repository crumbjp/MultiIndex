package jp.co.rakuten.util.collection.avltree;

import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdContainer;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdRandom;
import jp.co.rakuten.util.collection.StdRandomMulti;
import jp.co.rakuten.util.collection.StdRandomStartWith;
import jp.co.rakuten.util.collection.StdRandomUnique;
import jp.co.rakuten.util.collection.StdSequence;

public class AvlTreeAdapter<T,K> implements StdContainer<T>,StdSequence<T>,StdRandom<T,K>,StdRandomUnique<T,K>,StdRandomMulti<T,K>,StdRandomStartWith<T,K>{
	protected AvlTree<T,K> avlTree = null;

	public AvlTree<T,K> getTree() {
		return avlTree;
	}
	public AvlTreeAdapter( final AvlTree<T,K> avlTree) {
		this.avlTree = avlTree;
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
	public AvlIterator<T,K> find(K k) {
		return avlTree.find(k);
	}
	@Override
	public AvlIterator<T,K> lowerBound(K k) {
		return avlTree.lowerBound(k);
	}
	@Override
	public AvlIterator<T,K> upperBound(K k) {
		return avlTree.upperBound(k);
	}
	@Override
	public AvlIterator<T,K> begin() {
		return avlTree.begin();
	}
	@Override
	public AvlIterator<T,K> last() {
		return avlTree.last();
	}
	@Override
	public AvlIterator<T,K> end() {
		return avlTree.end();
	}
	@Override
	public void erase(StdIterator<T> it) {
		avlTree.erase((AvlIterator<T,K>)it);
	}
	@Override
	public Pair<StdIterator<T>, StdIterator<T>> equlRange(K k) {
		return (Pair)avlTree.equalRange(k);
	}
	@Override
	public AvlIterator<T,K> findFirst(K k) {
		return avlTree.findFirst(k);
	}
	@Override
	public AvlIterator<T,K> findLast(K k) {
		return avlTree.findLast(k);
	}
	
	@Override
	public Pair<StdIterator<T>, StdIterator<T>> equlRangeStartWith(K k,FindComparator<T,K> startWithComparator) {
		return (Pair)avlTree.equalRange(k, startWithComparator);
	}
	@Override
	public StdIterator<T> findFirstStartWith(K k,FindComparator<T,K> startWithComparator) {
		return avlTree.findFirst(k, startWithComparator);
	}
	@Override
	public StdIterator<T> findLastStartWith(K k,FindComparator<T,K> startWithComparator) {
		return avlTree.findLast(k, startWithComparator);
	}
	@Override
	public StdIterator<T> lowerBoundStartWith(K k,FindComparator<T,K> startWithComparator) {
		return avlTree.lowerBound(k, startWithComparator);
	}
	@Override
	public StdIterator<T> upperBoundStartWith(K k,FindComparator<T,K> startWithComparator) {
		return avlTree.upperBound(k, startWithComparator);
	}
	@Override
	public boolean insert(T t) {
		avlTree.insert(t);
		return true;
	}
	@Override
	public boolean replace(StdIterator<T> it, T t) {
		if ( it.isEnd() )
			return false;
		return avlTree.rawReplace((AvlIterator<T,K>)it, t);
	}
	public boolean insertReplace(T t) {
		return avlTree.insertReplace(t);
	};
}
