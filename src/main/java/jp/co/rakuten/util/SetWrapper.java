package jp.co.rakuten.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class SetWrapper<T> implements Set<T>{
	protected Set<T> container;
	@Override
	public boolean add(final T e) {
		return this.container.add(e);
	}
	@Override
	public boolean addAll(final Collection<? extends T> c) {
		return this.container.addAll(c);
	}
	@Override
	public void clear() {
		this.container.clear();
	}
	@Override
	public boolean contains(final Object o) {
		return this.container.contains(o);
	}
	@Override
	public boolean containsAll(final Collection<?> c) {
		return this.container.containsAll(c);
	}
	@Override
	public boolean isEmpty() {
		return this.container.isEmpty();
	}
	@Override
	public Iterator<T> iterator() {
		return this.container.iterator();
	}
	@Override
	public boolean remove(final Object o) {
		return this.container.remove(o);
	}
	@Override
	public boolean removeAll(final Collection<?> c) {
		return this.container.removeAll(c);
	}
	@Override
	public boolean retainAll(final Collection<?> c) {
		return this.container.retainAll(c);
	}
	@Override
	public int size() {
		return this.container.size();
	}
	@Override
	public Object[] toArray() {
		return this.container.toArray();
	}
	@Override
	public <T> T[] toArray(final T[] a) {
		return this.container.toArray(a);
	};
}
