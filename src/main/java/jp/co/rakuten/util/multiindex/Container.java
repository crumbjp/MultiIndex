package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.tree.Pair;

public class Container <T> {
	private static Integer currentUniqueId = 0;
	final Pair<Integer,T> pair;
	public Container(final T t) {
		this.pair = new Pair<Integer, T>(++currentUniqueId, t);
	}
	public T get(){
		return this.pair.second;
	}
}