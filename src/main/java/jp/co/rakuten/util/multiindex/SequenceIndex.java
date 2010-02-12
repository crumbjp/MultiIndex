package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.CompatibleIterable;
import jp.co.rakuten.util.collection.CompatibleReverseIterable;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdSet;
import jp.co.rakuten.util.collection.avltree.AvlTree;
import jp.co.rakuten.util.collection.avltree.UnmodifiableAvlTreeSet;
/**
 * <h3>The index is sorted in order of insertion.</h3>
 * <pre>
 * <h3>The case of sequential access using {@link CompatibleIterable} (simple)</h3> {@code
 * SequenceIndex<MyData> sequence = (SequenceIndex<MyData>)multiIndex.index(0);
 * for ( Record<MyData> r : new CompatibleIterable<Record<MyData>>(sequence)) {
 *   MyData d = r.get();
 *        :  
 * }
 * }
 * <h3>The case of sequential access using {@linkplain StdIterator raw iterator}(flexible).</h3> {@code
 * SequenceIndex<MyData> sequence = (SequenceIndex<MyData>)multiIndex.index(0);
 * for ( StdIterator<Record<MyData>> it = sequence.begin(),
 *                                itend = sequence.end();
 *       ! it.equals(itend);
 *       it.next())
 * {
 *   Record<MyData> r = it.get();
 *   MyData d = r.get();
 *            :  
 * }
 * }
 * </pre>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @see MultiIndex
 * @see StdIterator
 * @see CompatibleIterable
 * @see CompatibleReverseIterable
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
