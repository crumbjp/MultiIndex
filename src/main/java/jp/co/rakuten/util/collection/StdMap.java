package jp.co.rakuten.util.collection;


public interface StdMap<K,V> {
	public void clear();
	public long size();
	public boolean insert(Pair<K,V> t);
	public boolean insert(K k ,V v);
	public boolean set(Pair<K,V> t);
	public boolean set(K k ,V v);
	public StdIterator<Pair<K,V>> find(K k);
	public V get(K k);
	public StdIterator<Pair<K,V>> begin();
	public StdIterator<Pair<K,V>> last();
	public StdIterator<Pair<K,V>> end();
}
