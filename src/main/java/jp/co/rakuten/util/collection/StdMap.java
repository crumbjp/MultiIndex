package jp.co.rakuten.util.collection;

/**
 * Associative container like std::map of C++ STL.
 *   This class provides basic I/F of associative container.
 * <pre>
 *  Features:
 *   - This container treats pair-data. The data member of first means the key.
 *   - Entered datas are associated by key.
 *   - Cannot enter data overlapping key.
 *   - Always sorted.
 *   - Cannot random access.
 *   - Can flexible iterate accessing (Another means java.lang.iterable).
 * </pre>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @param <K> Key-type.
 * @param <V> Value--type. 
 */
public interface StdMap<K,V> {
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
	 *   Will be failure. If already associated same key in this container.
	 * </pre>
	 *	
	 * @param t specifying pair-data. Key is the first. Value is the second.
	 * @return true if succeed. false if failure .
	 */
	public boolean insert(Pair<K,V> t);

	/**
	 * <pre>
	 * Enter the new data.
	 *   Will be failure. If already associated same key in this container.
	 * </pre>
	 *	
	 * @param k specifying key.
	 * @param v specifying value, associating with key.
	 * @return true if succeed. false if failure .
	 */
	public boolean insert(K k ,V v);

	/**
	 * <pre>
	 * Enter the new data.
	 *   Will replace by new pair-data. If already associated same key in this container.
	 * </pre>
	 * 
	 * @param t specifying pair-data. Key is the first. Value is the second.
	 * @return true if replacing.
	 */
	public boolean set(Pair<K,V> t);

	/**
	 * <pre>
	 * Enter the new data.
	 *   Will replace by new pair-data. If already associated same key in this container.
	 * </pre>
	 * 
	 * @param k specifying key.
	 * @param v specifying value, associating with key.
	 * @return true if replacing.
	 */
	public boolean set(K k ,V v);

	/**
	 * <pre>
	 * Find data of having same key in this container.
	 * </pre>
	 * @param k specifying key.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<Pair<K,V>> find(K k);

	/**
	 * <pre>
	 * Find data of having same key in this container.
	 * </pre>
	 * @param k specifying key.
	 * @return Returns value of the data or null.
	 */
	public V get(K k);

	/**
	 * <pre>
	 * Find lowest data of upper than specifying in this container.
	 * </pre>
	 * 
	 * @param k specifying key.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<Pair<K,V>> upperBound(K k);

	/**
	 * <pre>
	 * Find highest data of lower than specifying in this container.
	 * </pre>
	 * 
	 * @param k specifying key.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<Pair<K,V>> lowerBound(K k);
	
	/**
	 * <pre>
	 * Find lowest data in this container.
	 * </pre>
	 * 
	 * @return Returns iterator of pointing the first data or end of container(when this container is empty).
	 */
	public StdIterator<Pair<K,V>> begin();

	/**
	 * <pre>
	 * Find highest data in this container.
	 * </pre>
	 * 
	 * @return Returns iterator of pointing the last data or end of container(when this container is empty).
	 */
	public StdIterator<Pair<K,V>> last();
	/**
	 * Iterator of pointing end of container.
	 *    
	 * @return Returns end of container.
	 */
	public StdIterator<Pair<K,V>> end();
}
