package jp.co.rakuten.util.multiindex;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import java.util.HashMap;

public class HashedUniqueIndex<K,T> extends UniqueIndex<K,T>{
	public HashedUniqueIndex(final Field field) {
		super(field);
	}
	@Override
	protected Map<K, Container<T>> createContainer(final List<Container<T>> origin,final Integer size) {
		return new HashMap<K,Container<T>>();
	}
}
