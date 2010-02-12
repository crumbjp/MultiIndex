package jp.co.rakuten.util.collection;

import java.util.Iterator;

/**
 * Convert I/F from StdSequence to java.lang.Iterable.
 * <pre>
 * == For Example ==
 *   StdSequence<T> container = ...;
 *   for ( T t : new CompatibleIterable<T>(container) )){
 *       ...
 *    }
 * </pre>			
 * 
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @param <T> Target data-type. 
 */
public class CompatibleIterable<T> implements Iterable<T> {
	final private CompatibleIterator<T> it;
	/**
	 * Instantiate with container.
	 * @param sequence target container
	 */
	public CompatibleIterable(StdSequence<T> sequence) {
		it = new CompatibleIterator<T>(sequence.begin(),sequence.end());
	}
	/**
	 * Instantiate with iterator.
	 * @param pair Iterator pair (from,to).
	 */
	public CompatibleIterable(Pair<StdIterator<T>,StdIterator<T>> pair ) {
		it = new CompatibleIterator<T>(pair.first,pair.second);
	}
	/**
	 * Instantiate with iterator.
	 * @param first  Iterator (from).
	 * @param second Iterator (to).
	 */
	public CompatibleIterable(StdIterator<T> first,StdIterator<T> second) {
		it = new CompatibleIterator<T>(first,second);
	}
	@Override
	public Iterator<T> iterator() {
		return it;
	}
}
