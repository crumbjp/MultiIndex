package jp.co.rakuten.util.tree;

public class StdIterator<T> {
	AvlNode<T> node = null;
	public StdIterator () { }
	public StdIterator (AvlNode<T> node) {
		this.node = node;
	}
	public StdIterator (StdIterator<T> t) {
		this.node = t.node;
	}
	public T get(){
		return node.t;
	}
	public boolean equals(StdIterator<T> it) {
		if ( isEnd() ){
			return it.isEnd();
		}
		return node.equals(it.node);
	}
	public boolean isEnd() {
		if ( node == null ) {
			return true;
		}
		return false;
	}
	public StdIterator<T> next() {
		if ( node != null ) {
			node = node.next();
		}
		return this;
	}
	public StdIterator<T> prev() {
		if ( node != null ) {
			node = node.prev();
		}
		return this;
	}
}
