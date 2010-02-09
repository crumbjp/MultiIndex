package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.StdMap;

/**
 * The index is sorted in order of data-type identity (Depends on Comparable<T>).
 * 
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
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
