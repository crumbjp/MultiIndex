package jp.co.rakuten.util.multiindex;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jp.co.rakuten.util.MultiMap;
import jp.co.rakuten.util.MultiTreeMap;

public class OrderedNonUniqueIndex<K extends Comparable<K>,T> extends NonUniqueIndex<K,T>{
	public OrderedNonUniqueIndex(final Field field) {
		super(field);
	}
	
	@Override
	protected MultiMap<K, Container<T>> createContainer(final List<Container<T>> origin,final Integer size) {
		return new MultiTreeMap<K,Container<T>>();
	}
	
}
