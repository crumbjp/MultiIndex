package jp.co.rakuten.util.collection;

/**
 * Basic interface of associative container.
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
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
}
