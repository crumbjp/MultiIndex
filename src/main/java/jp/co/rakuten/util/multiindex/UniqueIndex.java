package jp.co.rakuten.util.multiindex;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import jp.co.rakuten.util.UnmodifiableMapWrapper;


public abstract class UniqueIndex<K,T> extends UnmodifiableMapWrapper<K,Container<T>> implements Index<T> , Map<K,Container<T>> , Iterable<Container<T>>{
	private Field field;
	protected abstract Map<K,Container<T>> createContainer(final List<Container<T>> origin,final Integer size); 
	public UniqueIndex(final Field field) {
		this.field = field;
	}
	@Override
	public Iterator<Container<T>> iterator() {
		return new Iterator<Container<T>>() {
			Iterator<Map.Entry<K, Container<T>>> it = container.entrySet().iterator();
			public boolean hasNext() {
				return it.hasNext();
			};
			public Container<T> next() {
				return it.next().getValue();
			};
			public void remove() {
				it.remove();
			};
		};
	}
	@Override
	public void opInit   (final List<Container<T>> origin,final Integer size){
		this.container = createContainer(origin,size);
	}
	@Override
	public void opClear () {
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
		if ( this.container.put(key, c) != null )
			throw new RuntimeException("ADD : Unique-index is specified conflicting key !");
	}
	@Override
	public void opRemove(final Container<T> c){
		K key = this.getKey(c.pair.second);
		this.container.remove(key);
	}
	@Override
	public void opModify(final Container<T> c, final T t){
		K oldKey = this.getKey(c.pair.second);
		K newKey = this.getKey(t);
		if (! oldKey.equals(newKey) ) {
			this.container.remove(oldKey); 
			if ( this.container.put(newKey,c) != null ) 
				throw new RuntimeException("MODIFY : Unique-index is specified conflicting key !");
		}
	}
	@Override
	public boolean opCheckAdd(final T t) {
		K key = this.getKey(t);
		return !this.container.containsKey(key);
	}
	@Override
	public boolean opCheckModify(final Container<T> c, final T t) {
		K oldKey = this.getKey(c.pair.second);
		K newKey = this.getKey(t);
		if (! oldKey.equals(newKey) ) {
			return ! this.containsKey(newKey);
		}
		return true;
	}
}
