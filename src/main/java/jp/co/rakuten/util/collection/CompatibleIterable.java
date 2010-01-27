package jp.co.rakuten.util.collection;

import java.util.Iterator;

import jp.co.rakuten.util.collection.tree.AvlTree;

public class CompatibleIterable<T> implements Iterable<T> {
	private AvlTree tree;
	public CompatibleIterable(AvlTree tree) {
		this.tree = tree;
	}
	@Override
	public Iterator<T> iterator() {
		return new CompatibleIterator<T>(tree.begin());
	}
}
