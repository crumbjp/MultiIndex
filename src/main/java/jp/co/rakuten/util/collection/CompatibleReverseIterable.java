package jp.co.rakuten.util.collection;

import java.util.Iterator;
/**
 * <h3>Convert I/F with reverse to java.lang.Iterable.</h3>
 * <pre>
 *    From following.
 *     - {@link StdSequence}  : StdContainer  
 *     - {@link StdIterator}  : From and To
 * {@code 
 *   StdSequence<T> container = ...;
 *   for ( T t : new CompatibleIterable<T>(container) )){
 *       ...
 *    }
 * }
 * </pre>			
 * 
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @see StdSequence
 * @see StdIterator
 * @see StdSet
 * @see StdMultiSet
 * @see StdMap
 * @see StdMultiMap
 * @param <T> Target data-type. 
 */
public class CompatibleReverseIterable<T> implements Iterable<T> {
	final private CompatibleIterator<T> it;
	/**
	 * Instantiate with container.
	 * @param sequence target container
	 */
	public CompatibleReverseIterable(StdSequence<T> sequence) {
		it = new CompatibleIterator<T>(sequence.last(),sequence.end());
	}
	/**
	 * Instantiate with iterator.
	 * @param pair Iterator pair (from,to)
	 */
	public CompatibleReverseIterable(Pair<StdIterator<T>,StdIterator<T>> pair ) {
		it = new CompatibleIterator<T>(pair.first,pair.second);
	}
	/**
	 * Instantiate with iterator.
	 * @param first  Iterator (from).
	 * @param second Iterator (to).
	 */
	public CompatibleReverseIterable(StdIterator<T> first,StdIterator<T> second) {
		it = new CompatibleIterator<T>(first,second);
	}
	@Override
	public Iterator<T> iterator() {
		return it;
	}
}
