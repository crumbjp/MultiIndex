package jp.co.rakuten.util.collection.tree;

import jp.co.rakuten.util.collection.StdIterator;

public class AvlIterator<T> implements StdIterator<T>{
	AvlNode<T> node = null;
	AvlTree tree = null;
	public AvlIterator (AvlTree tree) { 
		this.tree = tree;
	}
	public AvlIterator (AvlTree tree,AvlNode<T> node) {
		this.tree = tree;
		this.node = node;
	}
	public AvlIterator (AvlIterator<T> t) {
		this.tree = t.tree;
		this.node = t.node;
	}
	@Override
	public T get(){
		if ( tree == null || node == null ) 
			throw new RuntimeException("Failure : Invalid iterator !");
		return node.t;
	}
	@Override
	public boolean replace(T t) {
		if ( tree == null || node == null ) 
			throw new RuntimeException("Failure : Invalid iterator !");
		return tree.rawReplace(this, t);
	}
	@Override
	public boolean equals(Object o) {
		AvlIterator<T> it = (AvlIterator<T>)o;
		if ( node == null && it.node == null)
			return true;
		if ( tree == null || node == null || it.node == null ) 
			return false;
		return tree == it.tree && node.equals(it.node);
	}
	@Override
	public boolean isEnd() {
		if ( tree == null || node == null ) 
			return true;
		return false;
	}
	@Override
	public AvlIterator<T> next() {
		if ( tree == null || node == null ) 
			throw new RuntimeException("Failure : Invalid iterator !");
		node = node.next();
		return this;
	}
	@Override
	public AvlIterator<T> prev() {
		if ( tree == null || node == null ) 
			throw new RuntimeException("Failure : Invalid iterator !");
		node = node.prev();
		return this;
	}
	@Override
	public boolean erase() {
		if ( tree == null || node == null ) 
			throw new RuntimeException("Failure : Invalid iterator !");
		return tree.erase(this);
	}
	public AvlTree getTree(){
		return tree;
	}
}
