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
	 * <pre>
	 * Associates the date with the identity itself.
	 *   Will be failure. If already associated same identity data in this container.
	 * </pre>
	 * 
	 * @param t specifying data.
	 * @return true if succeed. false if failure .
	 */
	public boolean insert(T t);

	/**
	 * <pre>
	 * Associates the date with the identity itself.
	 *   Will be replace by new data. If already associated same identity data in this container.
	 * </pre>
	 * 
	 * @param t specifying data.
	 * @return true if replacing.
	 */
	public boolean set(T t);

	/**
	 * <pre>
	 * Find data of having same identity in this container.
	 * </pre>
	 * @param t specifying data.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> find(T t);

	/**
	 * <pre>
	 * Find lowest data of upper than specifying in this container.
	 * </pre>
	 * 
	 * @param t specifying data.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> upperBound(T t);

	/**
	 * <pre>
	 * Find highest data of lower than specifying in this container.
	 * </pre>
	 * 
	 * @param t specifying data.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> lowerBound(T t);

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
	 * Iterator of pointing end of container.
	 *    
	 * @return Returns end of container.
	 */
	public StdIterator<T> end();
}
