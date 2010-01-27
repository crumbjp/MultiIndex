package jp.co.rakuten.util.collection;

import java.util.Iterator;

import jp.co.rakuten.util.collection.tree.AvlTree;

public class CompatibleReverseIterable<T> implements Iterable<T> {
	AvlTree tree;
	public CompatibleReverseIterable(AvlTree tree) {
		this.tree = tree;
	}
	@Override
	public Iterator<T> iterator() {
		return new CompatibleReverseIterator<T>(tree.last());
	}
}
