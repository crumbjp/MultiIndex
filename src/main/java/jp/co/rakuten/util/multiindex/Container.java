package jp.co.rakuten.util.multiindex;

public class Container <T> {
	private static Integer currentUniqueId = 0;
	final Integer id;
	T        t;
	public Container(final T t) {
		this.id = ++currentUniqueId;
		this.t  = t;
	}
	public T get(){
		return t;
	}
}
