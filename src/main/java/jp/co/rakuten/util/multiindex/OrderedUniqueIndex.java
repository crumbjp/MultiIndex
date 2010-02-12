package jp.co.rakuten.util.multiindex;

import java.lang.reflect.Field;

import jp.co.rakuten.util.collection.CompatibleIterable;
import jp.co.rakuten.util.collection.CompatibleReverseIterable;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdMap;

/**
 * <h3>The index is sorted in order of specified field.</h3>
 *    Specifying field must be unique. 
 * <pre>
 * <h3>The case of random access by the key using {@linkplain StdIterator raw iterator}(flexible).</h3> {@code
 * OrderedUniqueIndex<Integer,MyData> unique = (OrderedUniqueIndex<Integer,MyData>)multiIndex.index(0);
 * StdIterator<Pair<Integer,Record<MyData>>> it = unique.find(new Integer(3));
 * Record<MyData> r = it.get().second;
 * MyData d = r.get();
 *      :  
 * }
 * <h3>The case of sequential access using {@link CompatibleIterable} (simple)</h3> {@code
 * OrderedUniqueIndex<Integer,MyData> unique = (OrderedUniqueIndex<Integer,MyData>)multiIndex.index(0);
 * for ( Pair<Integer,Record<MyData>> p : new CompatibleIterable<Pair<Integer,Record<MyData>>>(unique) ) { 
 *   Record<MyData> r = p.second;
 *   MyData d = r.get();
 *        :  
 * }
 * }
 * <h3>The case of range access using {@link CompatibleIterable} (from 3 to 5)</h3> {@code
 * OrderedUniqueIndex<Integer,MyData> unique = (OrderedUniqueIndex<Integer,MyData>)multiIndex.index(0);
 * StdIterator<Pair<Integer,Record<MyData>>> rangeFirst = unique.find(3);
 * StdIterator<Pair<Integer,Record<MyData>>> rangeLast  = unique.upperBound(5);
 * for ( Pair<Integer,Record<MyData>> p : new CompatibleIterable<Pair<Integer,Record<MyData>>>(rangeFirst,rangeLast) ) { 
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
public class OrderedUniqueIndex <K extends Comparable<K>,T> extends UniqueIndex<K,T> implements StdMap<K,Record<T>>{
	private Field field;
	/**
	 * Initializes with field of T.
	 * @param field Sorting field (Must be extends Comparable).
	 */
	public OrderedUniqueIndex(final Field field) {
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
