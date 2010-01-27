package jp.co.rakuten.util.bk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeSet;

public class MultiTreeSet<T extends Comparable<T> > implements MultiSet<T> {
	static class MultiSetIterator<T extends Comparable<T>> implements Iterator<T> {
		private final MultiTreeSet<T> origin;
		private final Iterator<List<T>> iterator;
		private Integer currentCounter = 0;
		private List<T> currentList = null;
		enum Order{
			ASC,
			DESC
		}
		private final Order order;
		public MultiSetIterator(final MultiTreeSet<T> origin , final Iterator<List<T> > iterator , final Order order) {
			this.origin = origin;
			this.iterator = iterator;
			this.order = order;
		}
		@Override
		public boolean hasNext() {
			if ( order == Order.ASC ) { 
				if ( (currentList != null && ( currentList.size() > (currentCounter+1))) || 
						iterator.hasNext()	){
					return true;
				}
				return false;
			}else {
				if ( (currentList != null && ( currentCounter > 0)) || 
						iterator.hasNext()	){
					return true;
				}
				return false;
			}
		}
		@Override
		public T next() {
			if ( order == Order.ASC ) { 
				if ( currentList != null && ( currentList.size() > (currentCounter+1) ) ) {
					currentCounter++;
				}else {
					currentList = iterator.next();
					currentCounter = 0;
				}
				return currentList.get(currentCounter);
			}else {
				if ( currentList != null && ( currentCounter > 0 )) {
					currentCounter--;
				}else {
					currentList = iterator.next();
					currentCounter = currentList.size() - 1;
				}
				return currentList.get(currentCounter);
			}
		}
		@Override
		public void remove() {
			if ( currentList != null ) {
				currentList.remove(currentCounter.intValue());
				if ( order == Order.ASC) {
					currentCounter--;
				} else { 
					;
				} 
				if ( currentList.size() == 0 ) {
					iterator.remove();
					currentList = null;
					currentCounter = 0;
				}
				origin.size--;
			}
		}
	}
	static class TempList<T extends Comparable<T>> implements List<T>{
		private final T e;
		public TempList(final T e) {
			this.e = e;
		}
		@Override
		public T get(final int index) {
			return e;
		}
		@Override
		public int indexOf(final Object o) {
			return 0;
		}
		@Override
		public int size() {
			return 1;
		}
		@Override
		public int lastIndexOf(final Object o) {
			return 0;
		}
		@Override
		public boolean add(final T e) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public void add(final int index, final T element) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public boolean addAll(final Collection<? extends T> c) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public boolean addAll(final int index, final Collection<? extends T> c) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public void clear() {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public boolean contains(final Object o) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public boolean containsAll(final Collection<?> c) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public boolean isEmpty() {
			return false;
		}
		@Override
		public Iterator<T> iterator() {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public ListIterator<T> listIterator() {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public ListIterator<T> listIterator(final int index) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public boolean remove(final Object o) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public T remove(final int index) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public boolean removeAll(final Collection<?> c) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public boolean retainAll(final Collection<?> c) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public T set(final int index, final T element) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public List<T> subList(final int fromIndex, final int toIndex) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public Object[] toArray() {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		@Override
		public <T> T[] toArray(final T[] a) {
			throw new UnsupportedOperationException("Operation has not been permitted .");
		}
		
	}
	final TreeSet<List<T>> container = new TreeSet<List<T>> (new Comparator<List<T>>(){
		@Override
		public int compare(final List<T> o1, final List<T> o2) {
			return o1.get(0).compareTo(o2.get(0));
		}
	});
	int size = 0;

	@Override
	public Iterator<T> iterator() {
		return new MultiSetIterator<T>(this,container.iterator(),MultiSetIterator.Order.ASC);
	}
	@Override
	public Iterator<T> descendingIterator() {
		return new MultiSetIterator<T>(this,container.descendingIterator(),MultiSetIterator.Order.DESC);
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public void clear() {
		this.container.clear();
		this.size = 0;
	}
	@Override
	public boolean contains(final Object e) {
		return this.container.contains(new TempList<T>((T)e));
	}
	@Override
	public void add(final T e) {
		TempList<T> t = new TempList<T>((T)e);
		List<T> d ;
		if ( this.container.contains(t) ) {
			d = this.container.ceiling(t);
			d.add(e);
		}else {
			d = new ArrayList<T>();
			d.add(e);
			this.container.add(d);
		}
		size++;
	}
	@Override
	public void addAll(final Collection<? extends T> c) {
		// Todo: @@@ more effective !!!
		for ( T e : c ) {
			this.add(e);
		}
	}
	@Override
	public boolean containsAll(final Collection<? extends T> c) {
		// Todo: @@@ more effective !!!
		for ( T e : c ) {
			if ( ! this.contains(e) ) {
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean isEmpty() {
		return this.container.isEmpty();
	}
	@Override
	public void remove(final Iterator<T> it) {
		it.remove();
	}
	@Override
	public boolean removeAll(final T e) {
		TempList<T> t = new TempList<T>((T)e);
		if ( this.container.contains(t) ) {
			List<T> d = this.container.ceiling(t);
			size -= d.size();
			this.container.remove(t);
			return true;
		}
		return false;
	}
	public T first() {
		List<T> d = this.container.first(); 
		return (d == null)?null:d.get(0);
	}

	public T last() {
		List<T> d = this.container.last(); 
		return (d == null)?null:d.get(d.size()-1);
	}
	public T ceiling(final T e) {
		TempList<T> t = new TempList<T>((T)e);
		List<T> d = this.container.ceiling(t);
		return (d == null)?null:d.get(0);
	};
	public T floor(final T e) {
		TempList<T> t = new TempList<T>((T)e);
		List<T> d = this.container.floor(t); 
		return (d == null)?null:d.get(d.size()-1);
	};
	public T higher(final T e) {
		TempList<T> t = new TempList<T>((T)e);
		List<T> d = this.container.higher(t);
		return (d == null)?null:d.get(0);
	};
	public T lower(final T e) {
		TempList<T> t = new TempList<T>((T)e);
		List<T> d = this.container.lower(t);
		return (d == null)?null:d.get(d.size()-1);
	};
	public T pollFirst() {
		List<T> d = this.container.first();
		if (d.size() == 1 ){
			return this.container.pollFirst().get(0);
		} else {
			return d.remove(0);
		}
	}
	public T pollLast() {
		List<T> d = this.container.last();
		if (d.size() == 1 ){
			return this.container.pollLast().get(0);
		} else {
			return d.remove(d.size()-1);
		}
	};
	public Iterator<T> equalRange( final T e ) {
		// Todo: @@@ more effective !!!
		TempList<T> t = new TempList<T>((T)e);
		return new MultiSetIterator<T>(this,this.container.subSet(t,true, t,true).iterator(),MultiSetIterator.Order.ASC);
	}
	public Iterator<T> equalRange( final T e1 , final boolean inclusive1, final T e2 , final boolean inclusive2) {
		TempList<T> t1 = new TempList<T>((T)e1);
		TempList<T> t2 = new TempList<T>((T)e2);
		return new MultiSetIterator<T>(this,this.container.subSet(t1,inclusive1, t2,inclusive2).iterator(),MultiSetIterator.Order.ASC);
	}
	
}
