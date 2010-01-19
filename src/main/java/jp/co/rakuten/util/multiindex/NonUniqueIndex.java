package jp.co.rakuten.util.multiindex;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Map;
import java.util.List;

import jp.co.rakuten.util.MultiMap;
import jp.co.rakuten.util.UnmodifiableMapWrapper;
import jp.co.rakuten.util.UnmodifiableMultiMapWrapper;


public abstract class NonUniqueIndex<K extends Comparable<K>,T> extends UnmodifiableMultiMapWrapper<K,Container<T>> implements Index<T> , MultiMap<K,Container<T>>{
	private Field field;
	protected abstract MultiMap<K,Container<T>> createContainer(final List<Container<T>> origin,final Integer size); 
	public NonUniqueIndex(final Field field) {
		this.field = field;
	}
	@Override
	public void opInit   (final List<Container<T>> origin,final Integer size){
		this.container = createContainer(origin,size);
	}
	@Override
	public void opClear() {
		this.container.clear();
	}
	@SuppressWarnings("unchecked")
	private K getKey(final T t) {
		try {
			return (K)this.field.get(t);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void opAdd(final Container<T> c) {
		K key = this.getKey(c.pair.second);
		this.container.put(key, c);
	}
	private final Comparator<Container<T>> cmp =new Comparator<Container<T>>(){
		@Override
		public int compare(final Container<T> o1, final Container<T> o2) {
			return o1.pair.first - o2.pair.first; 
		}
	}; 
	@Override
	public void opRemove(final Container<T> c){
		K key = this.getKey(c.pair.second);
		this.container.remove(key,c,cmp);
	}
	@Override
	public void opModify(final Container<T> c, final T t){
		K oldKey = this.getKey(c.pair.second);
		K newKey = this.getKey(t);
		if (! oldKey.equals(newKey) ) {
			this.container.remove(oldKey,c,cmp); 
			this.container.put(newKey,c); 
		}
	}
	@Override
	public boolean opCheckAdd(final T t) {
		return true;
	}
	@Override
	public boolean opCheckModify(final Container<T> c, final T t) {
		K oldKey = this.getKey(c.pair.second);
		K newKey = this.getKey(t);
		if (! oldKey.equals(newKey) ) {
			return ! this.contains(newKey);
		}
		return true;
	}
}
