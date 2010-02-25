package jp.co.rakuten.util.collection;
/**
 * <h3>Unmodifiable iterator ( wrapped StdIterator ).</h3>
 *  
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @param <T> Target data-type. 
 */
public class ConstStdIterator<T> implements StdIterator<T>{
	private final StdIterator<T> it;
	/**
	 * Instantiate with wrapping iterator.
	 * @param it Wrapping iterator.
	 */
	public ConstStdIterator(StdIterator<T> it) {
		this.it = it;
	}
	@Override
	public T get() {
		return it.get();
	}
	/**
	 * <pre>
	 * Has not been permitted !!
	 *   It'll throw UnsupportedOperationException.
	 * </pre>
	 * @throws UnsupportedOperationException
	 */
//	@Override
//	public boolean replace(T t){
//		throw new UnsupportedOperationException("Operation has not been permitted .");
//	}
	@Override
	public boolean isEnd(){
		return it.isEnd();
	}
	@Override
	public ConstStdIterator<T> next(){
		it.next();
		return this;
	}
	@Override
	public ConstStdIterator<T> prev(){
		it.prev();
		return this;
	}
	/**
	 * <pre>
	 * Has not been permitted !!
	 *   It'll throw UnsupportedOperationException.
	 * </pre>
	 * @throws UnsupportedOperationException
	 */
//	@Override
//	public boolean erase(){
//		throw new UnsupportedOperationException("Operation has not been permitted .");
//	}
	@Override
	public boolean equals(Object obj) {
		ConstStdIterator<T> in = (ConstStdIterator<T>)obj; 
		return it.equals(in.it);
	}
	/**
	 * <pre>
	 * Has not been permitted !!
	 *   It'll throw UnsupportedOperationException.
	 * </pre>
	 * @throws UnsupportedOperationException
	 */
	@Override
	public Object container(){
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
}
