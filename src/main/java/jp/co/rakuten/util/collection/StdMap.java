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
 * @param <V> Value-type. 
 */
public interface StdMap<K,V> extends StdContainer<Pair<K,V>>,StdSequence<Pair<K,V>>,StdRandomUnique<Pair<K,V>, K> {

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
	 * @return Returns value of the data or null.
	 */
	public V get(K k);
}
