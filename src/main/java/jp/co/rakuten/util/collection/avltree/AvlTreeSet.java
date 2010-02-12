package jp.co.rakuten.util.collection.avltree;

import java.util.Comparator;

import jp.co.rakuten.util.collection.StdSet;

public class AvlTreeSet<T> extends AvlTreeAdapter<T,T> implements StdSet<T>{
	public AvlTreeSet() {
		super(new AvlTree<T,T>());
	}
	public AvlTreeSet( final AvlTree<T,T> avlTree ) {
		super(avlTree);
	}
	public AvlTreeSet( Comparator<T> comparator) {
		super(new AvlTree<T,T>(comparator));
	}
	@Override
	public boolean insert(T t) {
		return avlTree.insertUnique(t);
	}
	@Override
	public boolean set(T t) {
		return avlTree.insertReplace(t);
	}
}
