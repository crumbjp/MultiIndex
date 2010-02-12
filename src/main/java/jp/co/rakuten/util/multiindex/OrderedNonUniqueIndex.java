package jp.co.rakuten.util.multiindex;

import java.lang.reflect.Field;

import jp.co.rakuten.util.collection.CompatibleIterable;
import jp.co.rakuten.util.collection.CompatibleReverseIterable;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdMultiMap;

/**
 * <h3>The index is sorted in order of specified field.</h3>
 *    Specifying field is allowed overlapping. 
 * <pre>
 * <h3>The case of random access by the key using {@linkplain StdIterator raw iterator}(flexible).</h3> {@code
 * OrderedNonUniqueIndex<Integer,MyData> nonUnique = (OrderedNonUniqueIndex<Integer,MyData>)multiIndex.index(0);
 * Pair<StdIterator<Pair<Integer,Record<MyData>>>,StdIterator<Pair<Integer,Record<MyData>>>> range = nonUnique.equlRange(new Integer(3));
 * for ( Pair<Integer,Record<MyData>> p : new CompatibleIterable<Pair<Integer,Record<MyData>>>(range) ) { 
 *   Record<MyData> r = p.second;
 *   MyData d = r.get();
 *        :  
 * }			
 * }
 * <h3>The case of sequential access using {@link CompatibleIterable} (simple)</h3> {@code
 * OrderedNonUniqueIndex<Integer,MyData> nonUnique = (OrderedNonUniqueIndex<Integer,MyData>)multiIndex.index(0);
 * for ( Pair<Integer,Record<MyData>> p : new CompatibleIterable<Pair<Integer,Record<MyData>>>(nonUnique) ) { 
 *   Record<MyData> r = p.second;
 *   MyData d = r.get();
 *        :  
 * }
 * }
 * </pre>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @see MultiIndex
 * @see StdIterator
 * @see CompatibleIterable
 * @see CompatibleReverseIterable
 * @param <T> Target data-type.
 */
public class OrderedNonUniqueIndex <K extends Comparable<K>,T>	extends NonUniqueIndex<K,T>	implements StdMultiMap<K,Record<T>>{
	private Field field;
	/**
	 * Initializes with field of T.
	 * @param field Sorting field (Must be extends Comparable).
	 */
	public OrderedNonUniqueIndex(final Field field) {
		this.field = field;
	}
	@Override
	protected K getKey(final T t) {
		try {
			return (K)this.field.get(t);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
