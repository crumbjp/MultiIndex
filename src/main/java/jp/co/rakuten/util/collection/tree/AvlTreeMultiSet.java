package jp.co.rakuten.util.collection.tree;

import java.util.Comparator;

import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdMultiSet;

public class AvlTreeMultiSet<T> implements StdMultiSet<T>{
	private AvlTree<T,T> avlTree = null;
	public AvlTree<T,T> getTree() {
		return avlTree;
	}
	public AvlTreeMultiSet( final AvlTree<T,T> avlTree ) {
		this.avlTree = avlTree;
	}
	public AvlTreeMultiSet( Comparator<T> comparator) {
		avlTree = new AvlTree<T,T>(comparator);
	}
	public AvlTreeMultiSet() {
		avlTree = new AvlTree<T,T>();
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
	public void insert(T t) {
		avlTree.insert(t);
	}

	@Override
	public Pair<StdIterator<T>,StdIterator<T>> equlRange(T t) {
		return (Pair)avlTree.equalRange(t);
	}
	@Override
	public AvlIterator<T> findFirst(T t) {
		return avlTree.findFirst(t);
	}
	@Override
	public AvlIterator<T> findLast(T t) {
		return avlTree.findLast(t);
	}
	@Override
	public AvlIterator<T> begin() {
		return avlTree.begin();
	}
	@Override
	public AvlIterator<T> last() {
		return avlTree.last();
	}
	@Override
	public AvlIterator<T> end() {
		return avlTree.end();
	}
	public void erase(AvlIterator<T> it) {
		avlTree.erase((AvlIterator<T>)it);
	}
}
