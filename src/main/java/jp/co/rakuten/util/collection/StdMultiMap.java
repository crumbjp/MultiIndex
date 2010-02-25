package jp.co.rakuten.util.collection;


/**
 * <h3>Associative container like std::multimap of C++ STL.</h3>
 *   This class provides basic I/F of associative container.
 * <pre>
 * <h3>Features:</h3>
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
 * @param <V> Value-type. 
 */
public interface StdMultiMap<K,V> extends StdSequence<Pair<K,V>>,StdRandom<Pair<K,V>,K>,StdRandomMulti<Pair<K,V>,K>,StdRandomStartWith<Pair<K,V>,K> {
	/**
	 * <pre>
	 * Enter the new data.
	 *   New data will be inserted into the last position in the group.
	 *   If already associated same key in this container.
	 * </pre>
	 *	
	 * @param k Specifying key (first of pair-data).
	 * @param v Specifying value (second of pair-data).
	 */
	public void insert(K k ,V v);
}
