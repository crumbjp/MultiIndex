package jp.co.rakuten.util.tree;

public class StdIterator<T> {
	AvlNode<T> h = null;
	public StdIterator () { }
	public StdIterator (AvlNode<T> h) {
		this.h = h;
	}
	public StdIterator (StdIterator<T> t) {
		this.h = t.h;
	}
	public T get(){
		return h.t;
	}
	public boolean equals(StdIterator<T> it) {
		if ( isEnd() ){
			return it.isEnd();
		}
		return h.equals(it.h);
	}
	public boolean isEnd() {
		if ( h == null ) {
			return true;
		}
		return false;
	}
	public StdIterator<T> next() {
		if ( h != null ) {
			h = h.next(true);
		}
		return this;
	}
	public StdIterator<T> prev() {
		if ( h != null ) {
			h = h.prev(true);
		}
		return this;
	}
}
