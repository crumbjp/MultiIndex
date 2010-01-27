package jp.co.rakuten.util.multiindex;

import java.lang.reflect.Field;

public class OrderedNonUniqueIndex<K extends Comparable<K>,T> extends NonUniqueIndex<K,T>{
	Field field;
	public OrderedNonUniqueIndex(final Field field) {
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
