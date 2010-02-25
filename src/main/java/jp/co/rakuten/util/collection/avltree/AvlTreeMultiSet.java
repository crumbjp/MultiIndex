package jp.co.rakuten.util.collection.avltree;

import java.util.Comparator;

import jp.co.rakuten.util.collection.StdMultiSet;

public class AvlTreeMultiSet<T> extends AvlTreeAdapter<T,T> implements StdMultiSet<T>{

	public AvlTreeMultiSet() {
		super(new AvlTree<T,T>());
	}
	public AvlTreeMultiSet( final AvlTree<T,T> avlTree ) {
		super(avlTree);
	}
	public AvlTreeMultiSet( Comparator<T> comparator) {
		super(new AvlTree<T,T>(comparator));
	}
	@Override
	public boolean insert(T t) {
		avlTree.insert(t);
		return true;
	}
}
