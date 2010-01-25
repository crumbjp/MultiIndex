package jp.co.rakuten.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class MapWrapper<K,V> implements Map<K, V> {
	protected Map<K,V> container;
	@Override
	public void clear() {
		this.container.clear();
	}
	@Override
	public boolean containsKey(final Object key) {
		return this.container.containsKey(key);
	}
	@Override
	public boolean containsValue(final Object value) {
		return this.container.containsValue(value);
	}
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return this.container.entrySet();
	}
	@Override
	public V get(final Object key) {
		return this.container.get(key);
	}
	@Override
	public boolean isEmpty() {
		return this.container.isEmpty();
	}
	@Override
	public Set<K> keySet() {
		return this.container.keySet();
	}
	@Override
	public V put(final K key, final V value) {
		return this.container.put(key, value);
	}
	@Override
	public void putAll(final Map<? extends K, ? extends V> m) {
		this.container.putAll(m);
	}
	@Override
	public V remove(final Object key) {
		return this.container.remove(key);
	}
	@Override
	public int size() {
		return this.container.size();
	}
	@Override
	public Collection<V> values() {
		return this.container.values();
	}

}