package jp.co.rakuten.util.multiindex;

public class IdentityIndex<T extends Comparable<T>> extends UniqueIndex<T,T> {
	
	public IdentityIndex() {
	}
	@Override
	protected T getKey(T t) {
		return t;
	}
}
