package jp.co.rakuten.util.bk;

import jp.co.rakuten.util.collection.tree.Pair;

public class ComparablePair<K extends Comparable<K>,V> extends Pair<K,V> implements Comparable<ComparablePair<K,V>>{
	public ComparablePair(final K k,final V v) {
		super(k,v);
	}
	@Override
	public boolean equals(final Object o) {
		return this.first.equals(((ComparablePair<K,V>)o).first);
	}
	@Override
	public int compareTo(final ComparablePair<K, V> o) {
		return this.first.compareTo(((ComparablePair<K,V>)o).first);
	};
}
