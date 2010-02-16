package jp.co.rakuten.util.multiindex;

import java.util.Comparator;

import jp.co.rakuten.util.collection.CompatibleIterable;
import jp.co.rakuten.util.collection.CompatibleReverseIterable;
import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdSet;
import jp.co.rakuten.util.collection.avltree.AvlTree;
import jp.co.rakuten.util.collection.avltree.AvlTreeSet;
import jp.co.rakuten.util.collection.avltree.FindComparator;
import jp.co.rakuten.util.collection.avltree.UnmodifiableAvlTreeSet;

/**
 * <h3>The index is sorted in order of data-type identity</h3> ({@code Depends on Comparable<T>}).
 * <pre>
 * <h3>The case of sequential access using {@link CompatibleIterable} (simple)</h3> {@code
 * IdentityIndex<MyData> identity = (IdentityIndex<MyData>)multiIndex.index(0);
 * for ( Pair<MyData,Record<MyData>> p : new CompatibleIterable<Pair<MyData,Record<MyData>>>(identity)){
 *   Record<MyData> r = p.second;
 *   MyData d = r.get();
 *        :  
 * }
 * }
 * <h3>The case of sequential access using {@linkplain StdIterator raw iterator}(flexible).</h3> {@code
 * IdentityIndex<MyData> identity = (IdentityIndex<MyData>)multiIndex.index(0);
 * for ( StdIterator<Pair<MyData,Record<MyData>>> it = identity.begin(),
 *                                             itend = identity.end();
 *       ! it.equals(itend);
 *       it.next())
 * {
 *   Record<MyData> r = it.get().second;
 *   MyData d = r.get();
 *        :  
 * }
 * }
 * </pre>
 *
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @see MultiIndex
 * @see StdIterator
 * @see CompatibleIterable
 * @see CompatibleReverseIterable
 * @param <T> Target data-type.
 */
public class IdentityIndex <T extends Comparable<T>> extends UnmodifiableAvlTreeSet<T> implements Index<T> , StdSet<T> {
	AvlTreeSet<T> container;
	/**
	 * Constructor.
	 */
	public IdentityIndex() {
		super( new AvlTree<T,T>(
					new Comparator<T>() {
						public int compare(T o1, T o2) {
							return o1.compareTo(o2);
						}
					},
					new FindComparator<T,T>() {
						public int compare(T o1, T o2) {
							return o1.compareTo(o2);
						}
					}
				)
		);
		container = new AvlTreeSet<T>(avlTree);
	}
	@Override
	public void opInit(final Integer size) {
	}
	@Override
	public void opClear(){
		this.avlTree.clear();
	}
	@Override
	public void opAdd(final T c,final Integer u) {
		container.insert(c); 
	}
	@Override
	public void opRemove(final T c,final Integer u) {
		Pair<StdIterator<T>,StdIterator<T>> pair = container.equlRange(c);
		for (	StdIterator<T> it = pair.first,
				itend = pair.second;
			!it.equals(itend);
			it.next())
		{
			if ( it.get() == c) {
				it.erase();
				break;
			}
		}
	}
	@Override
	public void opModify(final T c,final Integer u,final T t) {
		if (! (c == t ) ) {
			Pair<StdIterator<T>,StdIterator<T>> pair = container.equlRange(c);
			for (	StdIterator<T> it = pair.first,
					itend = pair.second;
				!it.equals(itend);
				it.next())
			{
				if ( it.get() == c) {
					it.erase();
					break;
				}
			}
			container.insert(c); 
		}
	}
	@Override
	public boolean opCheckAdd(final T t) {
		return true;
	}
	@Override
	public boolean opCheckModify(final T c,final Integer u,final T t) {
		return true;
	}
}
