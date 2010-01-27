package jp.co.rakuten.util.collection;

public interface StdSet<T> {
	public void clear();
	public long size();
	public boolean insert(T t);
	public boolean set(T t);
	public StdIterator<T> find(T t);
	public StdIterator<T> begin();
	public StdIterator<T> last();
	public StdIterator<T> end();
}
