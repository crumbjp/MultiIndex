package jp.co.rakuten.util.collection;
/**
 * Set like C++ STL.
 * <pre>
 *  Features:
 *   - Associating datas have unique identity each other. 
 *   - Always sorted.
 *   - Cannot random access.
 * </pre>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @param <T> Target data-type. 
 */
public interface StdSet<T> {
	/**
	 * Remove all data from this container.
	 */
	public void clear();
	
	/**
	 * Returns number of data in this container.  
	 * @return
	 */
	public long size();
	
	/**
	 * Associates the date with the identity itself.
	 * 
	 * @param t specified data.
	 * @return true if succeed. false if failure .
	 */
	public boolean insert(T t);
	/**
	 * 
	 * @param t
	 * @return
	 */
	public boolean set(T t);
	/**
	 * 
	 * @param t
	 * @return
	 */
	public StdIterator<T> find(T t);
	/**
	 * 
	 * @param t
	 * @return
	 */
	public StdIterator<T> upperBound(T t);
	/**
	 * 
	 * @param t
	 * @return
	 */
	public StdIterator<T> lowerBound(T t);
	/**
	 * 
	 * @return
	 */
	public StdIterator<T> begin();
	/**
	 * 
	 * @return
	 */
	public StdIterator<T> last();
	/**
	 * 
	 * @return
	 */
	public StdIterator<T> end();
}
