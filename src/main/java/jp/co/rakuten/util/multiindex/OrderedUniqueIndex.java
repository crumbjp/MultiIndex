package jp.co.rakuten.util.multiindex;

import java.lang.reflect.Field;

import jp.co.rakuten.util.collection.StdMap;

/**
 * The index is sorted in order of specified field.
 * <pre>
 *    Specifying field must be unique. 
 * </pre>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
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
