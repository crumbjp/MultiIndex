package jp.co.rakuten.util.collection;


/**
 * Associative container like std::multimap of C++ STL.
 *   This class provides basic I/F of associative container.
 * <pre>
 *  Features:
 *   - This container treats pair-data. The data member of first means the key.
 *   - Entered datas are associated by key.
 *   - Can enter data overlapping key.
 *   - But must distinguish the data in same key group yourself. 
 *   - Always sorted.
 *   - Cannot random access.
 *   - Can flexible iterate accessing (Another means java.lang.iterable).
 * </pre>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @param <K> Key-type.
 * @param <V> Value--type. 
 */
public interface StdMultiMap<K,V> {
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
	 *   If already associated same key in this container.
	 * </pre>
	 *	
	 * @param t specifying pair-data. Key is the first. Value is the second.
	 */
	public void insert(Pair<K,V> t);
	/**
	 * <pre>
	 * Enter the new data.
	 *   New data will be inserted into the last position in the group.
	 *   If already associated same key in this container.
	 * </pre>
	 *	
	 * @param k specifying key (first of pair-data).
	 * @param v specifying value (second of pair-data).
	 */
	public void insert(K k ,V v);
	
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
	 * @param k specifying key.
	 * @return Returns range-iterator.
	 */
	public Pair<StdIterator<Pair<K,V>>,StdIterator<Pair<K,V>>> equlRange(K k);
	
	/**
	 * <pre>
	 * Find first data of specifying.
	 * </pre>
	 * 
	 * @param k specifying key.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<Pair<K,V>> findFirst(K k);

	/**
	 * <pre>
	 * Find last data of specifying.
	 * </pre>
	 * 
	 * @param k specifying key.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<Pair<K,V>> findLast(K k);

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
