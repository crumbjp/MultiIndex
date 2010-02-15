package jp.co.rakuten.util.collection;

/**
 * <h3>Sequence accessable interface of container.</h3>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @see CompatibleIterable
 * @see CompatibleReverseIterable
 * @param <T> Target data-type. 
 */
public interface StdSequence<T> extends StdContainer<T>{
	
	/**
	 * <pre>
	 * Find lowest data in this container.
	 * </pre>
	 * 
	 * @return Returns iterator of pointing the first data or end of container(when this container is empty).
	 */
	public StdIterator<T> begin();
	/**
	 * <pre>
	 * Find highest data in this container.
	 * </pre>
	 * 
	 * @return Returns iterator of pointing the last data or end of container(when this container is empty).
	 */
	public StdIterator<T> last();
}
