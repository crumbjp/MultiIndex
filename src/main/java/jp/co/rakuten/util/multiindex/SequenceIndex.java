package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.StdSet;
import jp.co.rakuten.util.collection.tree.AvlTree;
import jp.co.rakuten.util.collection.tree.UnmodifiableAvlTreeSet;

public class SequenceIndex<T> extends UnmodifiableAvlTreeSet<Container<T>> implements Index<T> , StdSet<Container<T>>{

	public SequenceIndex() {
		// Nothing to do.
	}
	@Override
	public void opInit(final AvlTree<Container<T>,Container<T>> origin, final Integer size) {
		avlTree = origin;
	}
	@Override
	public void opClear() {
		;
	}
	@Override
	public void opAdd(final Container<T> c) {
		;
	}
	@Override
	public void opRemove(final Container<T> c){
		;
	}
	@Override
	public void opModify(final Container<T> c, T t) {
		;
	}
	@Override
	public boolean opCheckAdd(final T t) {
		return true;
	}
	@Override
	public boolean opCheckModify(final Container<T> c, T t) {
		return true;
	}
}
