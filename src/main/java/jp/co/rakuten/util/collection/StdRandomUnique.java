package jp.co.rakuten.util.collection;

/**
 * <h3>Random accessable interface of overlapping key is not allowed.</h3>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @param <T> Target data-type. 
 */
public interface StdRandomUnique<T,K>{
	/**
	 * <pre>
	 * Find data by the specifying in this container.
	 * </pre>
	 * @param k specifying data.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> find(K k);
}
