package jp.co.rakuten.util.collection;

/**
 * <h3>Associative container like std::multiset of C++ STL.</h3>
 *   This class provides basic I/F of associative container.
 * <pre>
 * <h3>Features:</h3>
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
public interface StdMultiSet<T> extends StdSequence<T>,StdRandom<T,T>,StdRandomMulti<T,T>,StdRandomStartWith<T,T>{
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

}
