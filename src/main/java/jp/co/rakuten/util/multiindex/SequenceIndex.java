package jp.co.rakuten.util.multiindex;

import java.util.Comparator;

import jp.co.rakuten.util.collection.CompatibleIterable;
import jp.co.rakuten.util.collection.CompatibleReverseIterable;
import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdMap;
import jp.co.rakuten.util.collection.StdSequence;
import jp.co.rakuten.util.collection.avltree.AvlIterator;
import jp.co.rakuten.util.collection.avltree.AvlTree;
import jp.co.rakuten.util.collection.avltree.AvlTreeMap;
import jp.co.rakuten.util.collection.avltree.FindComparator;
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
public class SequenceIndex<T> implements Index<T>,StdSequence<T> {
	private AvlTreeMap<Integer,T> container = new AvlTreeMap<Integer, T>(
			new AvlTree<Pair<Integer,T>, Integer>(
					new Comparator<Pair<Integer,T>>() {
						public int compare(Pair<Integer,T> o1, Pair<Integer,T> o2) {
							return o1.first.compareTo(o2.first);
						}
					},
					new FindComparator<Pair<Integer,T>, Integer>() {
						public int compare(Pair<Integer,T> o1, Integer o2) {
							return o1.first.compareTo(o2);
						}
					}
			)
	); 
	/**
	 * Constructor.
	 */
	public SequenceIndex() {
		// Nothing to do.
	}
	@Override
	public void opInit(final Integer size) {
	}
	@Override
	public void opClear(){
		container.clear();
	}
	@Override
	public void opAdd(final T c,final Integer u) {
		container.insert(u,c);
	}
	@Override
	public void opRemove(final T c,final Integer u) {
		container.find(u).erase();
	}
	@Override
	public void opModify(final T c,final Integer u,final T t) {
		container.find(u).get().second = t;
	}
	@Override
	public boolean opCheckAdd(final T t) {
		return true;
	}
	@Override
	public boolean opCheckModify(final T c,final Integer u,final T t) {
		return true;
	}
	@Override
	public StdIterator<T> begin() {
		return new AvlValueIterator<T,Integer>(container.begin());
	}
	@Override
	public StdIterator<T> last() {
		return new AvlValueIterator<T,Integer>(container.last());
	}
	private AvlValueIterator<T,Integer> itend = new AvlValueIterator<T,Integer>(container.end()); 
	@Override
	public StdIterator<T> end() {
		return itend; 
	}
	@Override
	public void clear() {
		container.clear();
	}
	@Override
	public void erase(StdIterator<T> it) {
		it.erase();
	}
	@Override
	public long size() {
		return container.size();
	}
}
