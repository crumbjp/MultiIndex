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
	private StdSequence<T> sequence;
	/**
	 * Instantiate with container.
	 * @param sequence target container
	 */
	public CompatibleIterable(StdSequence<T> sequence) {
		this.sequence = sequence;
	}
	@Override
	public Iterator<T> iterator() {
		return new CompatibleIterator<T>(sequence.begin());
	}
}
