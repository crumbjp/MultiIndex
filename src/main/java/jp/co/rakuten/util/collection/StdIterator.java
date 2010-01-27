package jp.co.rakuten.util.collection;

public interface StdIterator<T> {
	public T get();
	public boolean replace(T t);
	public boolean isEnd();
	public StdIterator<T> next();
	public StdIterator<T> prev();
	public boolean erase();
	public boolean equals(Object o);
}
