package jp.co.rakuten.util.collection;

/**
 * <h3>Random accessable base interface of container.</h3>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @param <T> Target data-type. 
 */
public interface StdRandom<T,K> {
	/**
	 * <pre>
	 * Find lowest data of upper than specifying in this container.
	 * </pre>
	 * 
	 * @param k Specifying search key.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> upperBound(K k);

	/**
	 * <pre>
	 * Find highest data of lower than specifying in this container.
	 * </pre>
	 * 
	 * @param k Specifying search key.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> lowerBound(K k);
}
