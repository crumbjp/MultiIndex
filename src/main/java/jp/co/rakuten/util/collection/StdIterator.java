package jp.co.rakuten.util.collection;
/**
 * Iterator like C++ STL.
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @param <T> Target data-type. 
 */
public interface StdIterator<T> {
	/**
	 * Returns current data.
	 * 
	 * @return Current iterating data.
	 */
	public T get();
	/**
	 * Replace current data.
	 * 
	 * @param t replacing value.
	 * @return true if succeed.
	 */
	public boolean replace(T t);
	/**
	 * <pre>
	 * Returns true if iterator being out of range.
	 *   As major cases.
	 *     When finding data being not found.
	 *     When end of iterating. (next()/prev() calls from last-data). 
	 * </pre>
	 * @return true if iterator being out of range.
	 */
	public boolean isEnd();
	/**
	 * Iterator forwarding .
	 *   
	 * @return new position iterator.
	 */
	public StdIterator<T> next();
	/**
	 * Iterator rewinding .
	 *   
	 * @return new position iterator.
	 */
	public StdIterator<T> prev();
	/**
	 * Erase current data.
	 * @return true if succeed.
	 */
	public boolean erase();
	/**
	 * <pre>
	 * For range iteration.
	 * 
	 * 
	 *   for ( StdIterator it = container.find("A"),
	 *                  itend = container.find("E");
	 *         ! it.equals(itend);
	 *         it.next() ) {
	 *         ...
	 *    }
	 * </pre>
	 * @param o target iterator.
	 * @return true if same position.
	 */
	public boolean equals(Object o);
	/**
	 * <pre>
	 * For implementer.
	 * </pre>
	 * @return Returns container instance.
	 */
	public Object container();
}
