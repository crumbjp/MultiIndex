package jp.co.rakuten.util.multiindex;

import java.lang.reflect.Field;

public class OrderedUniqueIndex<K extends Comparable<K>,T> extends UniqueIndex<K,T>{
	Field field;
	public OrderedUniqueIndex(final Field field) {
		this.field = field;
	}
	@Override
	protected K getKey(final T t) {
		try {
			return (K)this.field.get(t);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
