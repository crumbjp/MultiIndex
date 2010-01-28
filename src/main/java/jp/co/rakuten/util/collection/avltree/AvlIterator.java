package jp.co.rakuten.util.collection.avltree;

import jp.co.rakuten.util.collection.StdIterator;

public class AvlIterator<T,K> implements StdIterator<T>{
	final AvlTree<T,K> tree;
	AvlNode<T> node = null;
	public AvlIterator (final AvlTree<T,K> tree) { 
		this.tree = tree;
	}
	public AvlIterator (final AvlTree<T,K> tree,final AvlNode<T> node) {
		this.tree = tree;
		this.node = node;
	}
	public AvlIterator (final AvlIterator<T,K> t) {
		this.tree = t.tree;
		this.node = t.node;
	}
	@Override
	public T get(){
		if ( node == null ) 
			throw new RuntimeException("Failure : Invalid iterator !");
		return node.t;
	}
	@Override
	public boolean replace(T t) {
		if ( node == null ) 
			throw new RuntimeException("Failure : Invalid iterator !");
		return tree.rawReplace(this, t);
	}
	@Override
	public boolean equals(Object o) {
		AvlIterator<T,K> it = (AvlIterator<T,K>)o;
		if ( tree != it.tree )
			return false;
		if ( node == null && it.node == null)
			return true;
		if ( node == null || it.node == null ) 
			return false;
		return node.equals(it.node);
	}
	@Override
	public boolean isEnd() {
		if ( node == null ) 
			return true;
		return false;
	}
	@Override
	public AvlIterator<T,K> next() {
		if ( node == null ) 
			throw new RuntimeException("Failure : Invalid iterator !");
		node = node.next();
		return this;
	}
	@Override
	public AvlIterator<T,K> prev() {
		if ( node == null ) 
			throw new RuntimeException("Failure : Invalid iterator !");
		node = node.prev();
		return this;
	}
	@Override
	public boolean erase() {
		if ( node == null ) 
			throw new RuntimeException("Failure : Invalid iterator !");
		return tree.erase(this);
	}
	public AvlTree getTree(){
		return tree;
	}
}
