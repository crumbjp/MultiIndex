package jp.co.rakuten.util.collection;
/**
 * <h3>Associative container like std::set of C++ STL.</h3>
 *   This class provides basic I/F of associative container.
 * <pre>
 * <h3>Features:</h3>
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
public interface StdSet<T> extends StdSequence<T>,StdRandom<T,T>,StdRandomUnique<T,T>,StdRandomStartWith<T,T>{

}
