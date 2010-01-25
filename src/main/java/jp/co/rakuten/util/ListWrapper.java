package jp.co.rakuten.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class ListWrapper<T> implements List<T>{
	protected List<T> container;
	@Override
	public boolean add(final T e) {
		return this.container.add(e);
	}
	@Override
	public void add(final int index, final T element) {
		this.container.add(index, element);
	}
	@Override
	public boolean addAll(final Collection<? extends T> c) {
		return this.container.addAll(c);
	}
	@Override
	public boolean addAll(final int index, final Collection<? extends T> c) {
		return this.container.addAll(index, c);
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
	public T get(final int index) {
		return this.container.get(index);
	}
	@Override
	public int indexOf(final Object o) {
		return this.container.indexOf(o);
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
	public int lastIndexOf(final Object o) {
		return this.container.lastIndexOf(o);
	}
	@Override
	public ListIterator<T> listIterator() {
		return this.container.listIterator();
	}
	@Override
	public ListIterator<T> listIterator(final int index) {
		return this.container.listIterator(index);
	}
	@Override
	public boolean remove(final Object o) {
		return this.container.remove(o);
	}
	@Override
	public T remove(int index) {
		return this.container.remove(index);
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
	public T set(final int index, final T element) {
		return this.container.set(index, element);
	}
	@Override
	public int size() {
		return this.container.size();
	}
	@Override
	public List<T> subList(final int fromIndex, final int toIndex) {
		return this.container.subList(fromIndex, toIndex);
	}
	@Override
	public Object[] toArray() {
		return this.container.toArray();
	}
	@Override
	public <T> T[] toArray(T[] a) {
		return this.container.toArray(a);
	};
}