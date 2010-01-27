package jp.co.rakuten.util.collection;


public interface StdMultiSet<T> {
	public void clear();
	public long size();
	public void insert(T t);
	public Pair<StdIterator<T>,StdIterator<T>> equlRange(T t);
	public StdIterator<T> findFirst(T t);
	public StdIterator<T> findLast(T t);
	public StdIterator<T> begin();
	public StdIterator<T> last();
	public StdIterator<T> end();
}
