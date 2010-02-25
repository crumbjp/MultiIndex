package jp.co.rakuten.util.collection.avltree;

import jp.co.rakuten.util.collection.ConstStdIterator;
import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdSet;

public class UnmodifiableAvlTreeSet<T> implements StdSet<T>{
	protected AvlTree<T,T> avlTree = null;
	public AvlTree<T,T> getTree() {
		return avlTree;
	}
	public UnmodifiableAvlTreeSet() {
	}	
	public UnmodifiableAvlTreeSet( final AvlTree<T,T> avlTree ) {
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
	public boolean insert(T t) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public boolean insertReplace(T t) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public ConstStdIterator<T> find(T t) {
		return new ConstStdIterator<T>(avlTree.find(t));
	}
	@Override
	public ConstStdIterator<T> upperBound(T t) {
		return new ConstStdIterator<T>(avlTree.upperBound(t));
	}
	@Override
	public ConstStdIterator<T> lowerBound(T t) {
		return new ConstStdIterator<T>(avlTree.lowerBound(t));
	}
	@Override
	public ConstStdIterator<T> begin() {
		return new ConstStdIterator<T>(avlTree.begin());
	}
	@Override
	public ConstStdIterator<T> last() {
		return new ConstStdIterator<T>(avlTree.last());
	}
	@Override
	public ConstStdIterator<T> end() {
		return new ConstStdIterator<T>(avlTree.end());
	}
	@Override
	public void erase(StdIterator<T> it) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public Pair<StdIterator<T>, StdIterator<T>> equlRangeStartWith(T k,
			FindComparator<T, T> startWithComparator) {
		Pair<AvlIterator<T,T>,AvlIterator<T,T>> p = avlTree.equalRange(k,startWithComparator);
		return new Pair<StdIterator<T>,StdIterator<T>>(
				new ConstStdIterator<T>(p.first),
				new ConstStdIterator<T>(p.second));
	}
	@Override
	public ConstStdIterator<T> findFirstStartWith(T k,
			FindComparator<T, T> startWithComparator) {
		return new ConstStdIterator<T>(avlTree.findFirst(k,startWithComparator));
	}
	@Override
	public ConstStdIterator<T> findLastStartWith(T k,
			FindComparator<T, T> startWithComparator) {
		return new ConstStdIterator<T>(avlTree.findLast(k,startWithComparator));
	}
	@Override
	public ConstStdIterator<T> lowerBoundStartWith(T k,
			FindComparator<T, T> startWithComparator) {
		return new ConstStdIterator<T>(avlTree.lowerBound(k,startWithComparator));
	}
	@Override
	public ConstStdIterator<T> upperBoundStartWith(T k,
			FindComparator<T, T> startWithComparator) {
		return new ConstStdIterator<T>(avlTree.upperBound(k,startWithComparator));
	}
	@Override
	public boolean replace(StdIterator<T> it, T t) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
}
