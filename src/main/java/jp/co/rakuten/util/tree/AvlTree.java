package jp.co.rakuten.util.tree;

import java.util.Comparator;

public class AvlTree<T> {
	static final StdIterator itend = new StdIterator();
	AvlNode<T> avl = null;
//	Comparator<T> comparator;
//	public AvlTree(Comparator<T> comparator) {
//		this.comparator = comparator;
//	}
	private final Comparator<T> comparator;
	public AvlTree( Comparator<T> comparator ) {
		this.comparator = comparator;
	}
	public AvlTree() {
		this.comparator = new Comparator<T>() {
			public int compare(T o1, T o2) {
				return ((Comparable<T>)o1).compareTo(o2);
			};
		};
	}
	public StdIterator<T> end(){
		return itend;
	}
	public void clear(){
		avl = null;
	}
	public void insert(T t) {
		if ( avl == null ) {
			avl = new AvlNode<T>(comparator,t); 
			return;
		}
		avl = avl.insert(new AvlNode<T>(comparator,t));
//		avl.find(t, new AvlNode.InsertOp());
	}
	
	
	public StdIterator<T> begin() {
		if ( avl == null ) {
			return itend;
		}
		return  new StdIterator<T>(avl.begin());
	}
	public StdIterator<T> last() {
		if ( avl == null ) {
			return itend;
		}
		return  new StdIterator<T>(avl.last());
	}
	public StdIterator<T> find(T t) {
		if ( avl == null ) {
			return itend;
		}
		return new StdIterator<T>(avl.find(t,new AvlNode.FindOp<T>()));
	}
	public StdIterator<T> findFirst(T t) {
		if ( avl == null ) {
			return itend;
		}
		return new StdIterator<T>(avl.find(t,new AvlNode.FindFirstOp<T>()));
	}
	public StdIterator<T> findLast(T t) {
		if ( avl == null ) {
			return itend;
		}
		return new StdIterator<T>(avl.find(t,new AvlNode.FindLastOp<T>()));
	}
	public Pair<StdIterator<T>,StdIterator<T>> equalRange(T t) {
		if ( avl == null ) {
			return new Pair<StdIterator<T>, StdIterator<T>>( itend,itend);
		}
		AvlNode.EqualRangeOp<T> op = new AvlNode.EqualRangeOp<T>();
		avl.find(t,op);
		return op.p;
	}
	public StdIterator<T> upperBound(T t) {
		if ( avl == null ) {
			return itend;
		}
		return new StdIterator<T>(avl.find(t,new AvlNode.UpperBoundOp<T>()));
	}
	public StdIterator<T> lowerBound(T t) {
		if ( avl == null ) {
			return itend;
		}
		return new StdIterator<T>(avl.find(t,new AvlNode.LowerBoundOp<T>()));
	}

	public boolean erase(StdIterator<T> it) {
		if ( avl == null ) {
			return false;
		}
		if ( it.node == null ) {
			return false;
		}
		avl = it.node.erase(avl);
		return true;
	}
}
