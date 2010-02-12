package jp.co.rakuten.util.collection;

/**
 * Sequence accessable interface of container.
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
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
	/**
	 * <pre>
	 * Remove data from this container. 
	 * </pre>
	 * @param it Iterator of pointing removing data.
	 */
	public void erase(StdIterator<T> it);
}
