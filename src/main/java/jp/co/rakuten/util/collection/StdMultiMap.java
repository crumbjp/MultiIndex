package jp.co.rakuten.util.collection;

import jp.co.rakuten.util.collection.tree.Pair;

public interface StdMultiMap<K,V> {
	public void clear();
	public long size();
	public void insert(Pair<K,V> t);
	public void insert(K k ,V v);
	public Pair<StdIterator<Pair<K,V>>,StdIterator<Pair<K,V>>> equlRange(K k);
	public StdIterator<Pair<K,V>> findFirst(K k);
	public StdIterator<Pair<K,V>> findLast(K k);
	public StdIterator<Pair<K,V>> begin();
	public StdIterator<Pair<K,V>> last();
	public StdIterator<Pair<K,V>> end();

}
