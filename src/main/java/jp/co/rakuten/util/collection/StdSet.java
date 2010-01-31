package jp.co.rakuten.util.collection;
/**
 * Associative container like std::set of C++ STL.
 *   This class provides basic I/F of associative container.
 * <pre>
 *  Features:
 *   - Entered datas are associated by identity of itself.
 *   - Cannot enter data overlapping identity.
 *   - Always sorted.
 *   - Can flexible iterate accessing (Another means java.lang.iterable).
 *   
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
	 * @return Returns number of associating. 
	 */
	public long size();
	
	/**
	 * <pre>
	 * Enter the new data.
	 *   Will be failure. If already associated same identity data in this container.
	 * </pre>
	 * 
	 * @param t specifying data.
	 * @return true if succeed. false if failure .
	 */
	public boolean insert(T t);

	/**
	 * <pre>
	 * Enter the new data.
	 *   Will replace by new data. If already associated same identity data in this container.
	 * </pre>
	 * 
	 * @param t specifying data.
	 * @return true if replacing.
	 */
	public boolean set(T t);

	/**
	 * <pre>
	 * Find data by the specifying in this container.
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
