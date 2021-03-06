package jp.co.rakuten.util.collection;

/**
 * <h3>Random accessable interface of overlapping key is allowed.</h3>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @param <T> Target data-type. 
 */
public interface StdRandomMulti<T,K>{
	/**
	 * <pre>
	 * Find first data of specifying.
	 * </pre>
	 * 
	 * @param k Specifying search key.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> findFirst(K k);

	/**
	 * <pre>
	 * Find last data of specifying.
	 * </pre>
	 * 
	 * @param k Specifying search key.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> findLast(K k);
	/**
	 * <pre>
	 * Find data by the specifying in this container.
	 *   Returns range-iterator.
	 *   
	 *   Range-iterator's first is a iterator of the first data of specifying.
	 *    This points end of container if not found case.
	 *    
	 *   Range-iterator's second is a iterator of the first data of upper.
	 *    Be careful! This is not pointing the last data of your specifying.
	 * </pre>
	 * @param k Specifying search key.
	 * @return Returns range-iterator.
	 */
	public Pair<StdIterator<T>,StdIterator<T>> equlRange(K k);

}
