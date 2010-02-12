package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.CompatibleIterable;
import jp.co.rakuten.util.collection.CompatibleReverseIterable;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdMap;

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
public class IdentityIndex <T extends Comparable<T>> extends UniqueIndex<T,T> implements StdMap<T,Record<T>>{
	/**
	 * Constructor.
	 */
	public IdentityIndex() {
	}
	@Override
	protected T getKey(T t) {
		return t;
	}
}
