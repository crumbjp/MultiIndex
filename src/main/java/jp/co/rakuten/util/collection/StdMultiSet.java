package jp.co.rakuten.util.collection;

/**
 * Associative container like std::multiset of C++ STL.
 *   This class provides basic I/F of associative container.
 * <pre>
 *  Features:
 *   - Entered datas are associated by identity of itself.
 *   - Can enter data overlapping identity.
 *   - But must distinguish the data in same identity group yourself. 
 *   - Always sorted.
 *   - Can flexible iterate accessing (Another means java.lang.iterable).
 * </pre>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @param <T> Target data-type. 
 */
public interface StdMultiSet<T> {
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
	 *   New data will be inserted into the last position in the group.
	 *   If already associated same identity data in this container.
	 * </pre>
	 * 
	 * @param t specifying data.
	 */
	public void insert(T t);

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
	 * @param t specifying data.
	 * @return Returns range-iterator.
	 */
	public Pair<StdIterator<T>,StdIterator<T>> equlRange(T t);

	/**
	 * <pre>
	 * Find first data of specifying.
	 * </pre>
	 * 
	 * @param t specifying data.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> findFirst(T t);

	/**
	 * <pre>
	 * Find last data of specifying.
	 * </pre>
	 * 
	 * @param t specifying data.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> findLast(T t);

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
