package jp.co.rakuten.util.collection;

/**
 * <h3>Basic interface of container.</h3>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @param <T> Target data-type. 
 */
public interface StdContainer<T> {
	
	/**
	 * Remove all data from this container.
	 */
	public void clear();
	
	/**
	 * Returns number of data in this container.  
	 * @return Returns number of associating. 
	 */
	public long size();
	/**
	 * Iterator of pointing end of container.
	 *    
	 * @return Returns end of container.
	 */
	public StdIterator<T> end();
	/**
	 * <pre>
	 * Remove data from this container. 
	 * </pre>
	 * @param it Iterator of pointing removing data.
	 */
	public void erase(StdIterator<T> it);
}
