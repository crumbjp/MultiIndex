package jp.co.rakuten.util.bk;

import java.util.Collection;
import java.util.Iterator;

public interface MultiSet<T> extends Iterable<T> {
	public Iterator<T> descendingIterator();
	public int size();
	public void clear();
	public boolean contains(final Object e);
	public void add(final T e);
	public void addAll(final Collection<? extends T> c);
	public boolean containsAll(final Collection<? extends T> c);
	public boolean isEmpty();
	public void remove(final Iterator<T> it);
	public boolean removeAll(final T e);
	public Iterator<T> equalRange( final T e );
	public Iterator<T> equalRange( final T e1 , final boolean inclusive1, final T e2 , final boolean inclusive2);
}
