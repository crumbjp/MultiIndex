package jp.co.rakuten.util.collection.avltree;

import java.util.Comparator;

import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdSet;

public class AvlTreeSet<T> implements StdSet<T>{
	private AvlTree<T,T> avlTree = null;
	
	public AvlTree<T,T> getTree() {
		return avlTree;
	}
	public AvlTreeSet( final AvlTree<T,T> avlTree ) {
		this.avlTree = avlTree;
	}
	public AvlTreeSet( Comparator<T> comparator) {
		avlTree = new AvlTree<T,T>(comparator);
	}
	public AvlTreeSet() {
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
	public boolean insert(T t) {
		return avlTree.insertUnique(t);
	}
	@Override
	public boolean set(T t) {
		return avlTree.insertReplace(t);
	}
	@Override
	public AvlIterator<T,T> find(T t) {
		return avlTree.find(t);
	}
	@Override
	public AvlIterator<T,T> upperBound(T t) {
		return avlTree.upperBound(t);
	}
	@Override
	public AvlIterator<T,T> lowerBound(T t) {
		return avlTree.lowerBound(t);
	}
	@Override
	public AvlIterator<T,T> begin() {
		return avlTree.begin();
	}
	@Override
	public AvlIterator<T,T> last() {
		return avlTree.last();
	}
	@Override
	public AvlIterator<T,T> end() {
		return avlTree.end();
	}
	@Override
	public void erase(StdIterator<T> it) {
		avlTree.erase((AvlIterator<T,T>)it);
	}
}
