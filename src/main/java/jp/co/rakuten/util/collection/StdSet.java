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
public interface StdSet<T> extends StdContainer<T>,StdSequence<T>,StdRandomUnique<T,T>{
	
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
}
