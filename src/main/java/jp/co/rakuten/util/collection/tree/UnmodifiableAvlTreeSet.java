package jp.co.rakuten.util.collection.tree;

import jp.co.rakuten.util.collection.ConstStdIterator;
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
	public boolean set(T t) {
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public ConstStdIterator<T> find(T t) {
		return new ConstStdIterator<T>(avlTree.find(t));
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
	public StdIterator<T> end() {
		return avlTree.end();
	}
}
