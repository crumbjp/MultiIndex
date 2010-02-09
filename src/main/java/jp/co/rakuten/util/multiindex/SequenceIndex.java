package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.StdSet;
import jp.co.rakuten.util.collection.avltree.AvlTree;
import jp.co.rakuten.util.collection.avltree.UnmodifiableAvlTreeSet;
/**
 * The index is sorted in order of insertion.
 * 
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @param <T> Target data-type.
 */
public class SequenceIndex<T> extends UnmodifiableAvlTreeSet<Record<T>> implements Index<T> , StdSet<Record<T>> {
	/**
	 * Constructor.
	 */
	public SequenceIndex() {
		// Nothing to do.
	}
	@Override
	public void opInit(final AvlTree<Record<T>,Record<T>> origin, final Integer size) {
		avlTree = origin;
	}
	@Override
	public void opClear() {
		;
	}
	@Override
	public void opAdd(final Record<T> c) {
		;
	}
	@Override
	public void opRemove(final Record<T> c){
		;
	}
	@Override
	public void opModify(final Record<T> c, T t) {
		;
	}
	@Override
	public boolean opCheckAdd(final T t) {
		return true;
	}
	@Override
	public boolean opCheckModify(final Record<T> c, T t) {
		return true;
	}
}
