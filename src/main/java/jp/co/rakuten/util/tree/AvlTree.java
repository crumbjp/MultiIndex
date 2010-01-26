package jp.co.rakuten.util.tree;

import java.util.Comparator;

public class AvlTree<T> {
	static final StdIterator itend = new StdIterator();
	AvlNode<T> avl = null;
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

	private AvlNode<T> insert( AvlNode<T> curNode , AvlNode<T> node ) {
		while (true) {
			int cmp = comparator.compare(curNode.t,node.t);
			if ( cmp > 0 ) {
				if ( curNode.left == null){
					node.parent = curNode;
					curNode.left = node;
					curNode.nleft = (short) (1 + ((node.nleft < node.nright)?node.nright:node.nleft));
					break;
				}
				curNode = curNode.left;
			} else {
				if ( curNode.right == null){
					node.parent = curNode;
					curNode.right = node;
					curNode.nright = (short) (1 + ((node.nleft < node.nright)?node.nright:node.nleft));
					break;
				}
				curNode = curNode.right;
			}
		}
		AvlNode<T> parentNode;
		while (true) {
			parentNode = curNode.parent;
			if ( parentNode == null ) {
				return curNode.equilibrium();
			}
			if ( parentNode.left == curNode ) {
				parentNode.left = curNode.equilibrium();
				parentNode.nleft = (short) (1 + ((parentNode.left.nleft < parentNode.left.nright)?parentNode.left.nright:parentNode.left.nleft));
			} else {
				parentNode.right= curNode.equilibrium();
				parentNode.nright = (short) (1 + ((parentNode.right.nleft < parentNode.right.nright)?parentNode.right.nright:parentNode.right.nleft));
			}
			curNode = parentNode;
		}
	}
	public void insert(T t) {
		if ( avl == null ) {
			avl = new AvlNode<T>(t); 
			return;
		}
		avl = insert(avl,new AvlNode<T>(t));
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
	private AvlNode<T> erase(AvlNode<T> targetNode) {
		// parge child
		if ( targetNode.left != null ) {
			targetNode.left.parent = null;
		}
		if ( targetNode.right != null ) {
			targetNode.right.parent = null;
		}
		// is parent (curNode == avl)
		if ( targetNode.parent == null ){
			if ( targetNode.left != null) {
				if ( targetNode.right != null ) {
					return insert(targetNode.left,targetNode.right);
				}
				return targetNode.left;
			} else {
				return targetNode.right;
			}
		}
		// parge self
		if ( targetNode.parent.left == targetNode ) {
			targetNode.parent.left = null;
			targetNode.parent.nleft = 0;
		} else {
			targetNode.parent.right = null;
			targetNode.parent.nright = 0;
		}
		// parent
		AvlNode<T> curNode = targetNode.parent;
		while(true) {
			if ( curNode.parent == null ) {
				break;
			}
			short nest = ((curNode.nleft < curNode.nright)?curNode.nright:curNode.nleft);
			if ( curNode.parent.left == curNode ) {
				curNode.parent.nleft = (short) (nest + 1); 
				if ( curNode.parent.nleft <  curNode.parent.nright ) {
					break;
				}
			} else {
				curNode.parent.nright = (short) (nest + 1); 
				if ( curNode.parent.nright < curNode.parent.nleft) {
					break;
				}
			}
			curNode = curNode.parent;
		}
		
		//
		if ( targetNode.left != null ) {
			if ( targetNode.right != null ) {
				AvlNode<T> merge = insert(targetNode.left,targetNode.right);
				return insert(avl,merge);
			}
			return insert(avl,targetNode.left);
		} else {
			if ( targetNode.right != null ) {
				return insert(avl,targetNode.right);
			}
			return avl;
		}
	}
	public boolean erase(StdIterator<T> it) {
		if ( avl == null ) {
			return false;
		}
		avl = erase(it.node);
		return true;
	}
//*
	public StdIterator<T> find(T t) {
		if ( avl == null ) {
			return itend;
		}
		AvlNode<T> curNode = avl;
		while (true) {
			int cmp = comparator.compare(curNode.t,t);
			if ( cmp > 0 ) {
				if ( curNode.left == null ) {
					return itend;
				}
				curNode = curNode.left;
			} else if ( cmp < 0 ) {
				if ( curNode.right == null ) {
					return itend;
				}
				curNode = curNode.right;
			} else {
				return new StdIterator<T>(curNode);
			}
		}
	}

	public StdIterator<T> findFirst(T t) {
		if ( avl == null ) {
			return itend;
		}
		AvlNode<T> curNode = avl;
		AvlNode<T> retNode = null;
		while (true) {
			int cmp = comparator.compare(curNode.t,t);
			if ( cmp > 0 ) {
				if ( curNode.left == null ) {
					break;
				}
				curNode = curNode.left;
			} else if ( cmp < 0 ) {
				if ( curNode.right == null ) {
					break;
				}
				curNode = curNode.right;
			} else {
				retNode = curNode;
				if ( curNode.left == null ) {
					break;
				}
				curNode = curNode.left;
			}
		}
		if ( retNode == null ) {
			return itend;
		}
		return new StdIterator<T>(retNode);
	}

	public StdIterator<T> findLast(T t) {
		if ( avl == null ) {
			return itend;
		}
		AvlNode<T> curNode = avl;
		AvlNode<T> retNode = null;
		while (true) {
			int cmp = comparator.compare(curNode.t,t);
			if ( cmp > 0 ) {
				if ( curNode.left == null ) {
					break;
				}
				curNode = curNode.left;
			} else if ( cmp < 0 ) {
				if ( curNode.right == null ) {
					break;
				}
				curNode = curNode.right;
			} else {
				retNode = curNode;
				if ( curNode.right == null ) {
					break;
				}
				curNode = curNode.right;
			}
		}
		if ( retNode == null ) {
			return itend;
		}
		return new StdIterator<T>(retNode);
	}

	public Pair<StdIterator<T>,StdIterator<T>> equalRange(T t) {
		if ( avl == null ) {
			return new Pair<StdIterator<T>, StdIterator<T>>(itend, itend);
		}
		AvlNode<T> curNode = avl;
		AvlNode<T> firstNode = null;
		AvlNode<T> secondNode = avl;
		while (true) {
			int cmp = comparator.compare(curNode.t,t);
			if ( cmp > 0 ) {
				if ( curNode.left == null ) {
					break;
				}
				if ( firstNode == null ) {
					secondNode = curNode;
				}
				curNode = curNode.left;
			} else if ( cmp < 0 ) {
				if ( curNode.right == null ) {
					break;
				}
				curNode = curNode.right;
			} else {
				firstNode = curNode;
				if ( curNode.left == null ) {
					break;
				}
				curNode = curNode.left;
			}
		}
		if ( firstNode == null ) {
			return new Pair<StdIterator<T>, StdIterator<T>>(itend, itend);
		}
		curNode = secondNode;
		while (true) {
			int cmp = comparator.compare(curNode.t,t);
			if ( cmp > 0 ) {
				secondNode = curNode;
				if ( curNode.left == null ) {
					break;
				}
				curNode = curNode.left;
			} else if ( cmp < 0 ) {
				if ( curNode.right == null ) {
					break;
				}
				curNode = curNode.right;
			} else {
				if ( curNode.right == null ) {
					break;
				}
				curNode = curNode.right;
			}
		}
		return new Pair<StdIterator<T>, StdIterator<T>>(
				new StdIterator<T>(firstNode),
				new StdIterator<T>(secondNode));
	}
	public StdIterator<T> upperBound(T t) {
		if ( avl == null ) {
			return itend;
		}
		AvlNode<T> curNode = avl;
		AvlNode<T> retNode = null;
		while (true) {
			int cmp = comparator.compare(curNode.t,t);
			if ( cmp > 0 ) {
				retNode = curNode;
				if ( curNode.left == null ) {
					break;
				}
				curNode = curNode.left;
			} else if ( cmp < 0 ) {
				if ( curNode.right == null ) {
					break;
				}
				curNode = curNode.right;
			} else {
				if ( curNode.right == null ) {
					break;
				}
				curNode = curNode.right;
			}
		}
		if ( retNode == null ) {
			return itend;
		}
		return new StdIterator<T>(retNode);
	}

	public StdIterator<T> lowerBound(T t) {
		if ( avl == null ) {
			return itend;
		}
		AvlNode<T> curNode = avl;
		AvlNode<T> retNode = null;
		while (true) {
			int cmp = comparator.compare(curNode.t,t);
			if ( cmp > 0 ) {
				if ( curNode.left == null ) {
					break;
				}
				curNode = curNode.left;
			} else if ( cmp < 0 ) {
				retNode = curNode;
				if ( curNode.right == null ) {
					break;
				}
				curNode = curNode.right;
			} else {
				if ( curNode.left == null ) {
					break;
				}
				curNode = curNode.left;
			}
		}
		if ( retNode == null ) {
			return itend;
		}
		return new StdIterator<T>(retNode);
	}

/*/
	public void findFrame(AvlNode<T> curNode ,T t,FindOperation<T> op) {
		while (true) {
			int cmp = comparator.compare(curNode.t,t);
			if ( cmp > 0 ) {
				curNode = op.doLeft(curNode);
				if ( curNode == null ) {
					break;
				}
			} else if ( cmp < 0 ) {
				curNode = op.doRight(curNode);
				if ( curNode == null ) {
					break;
				}
			} else {
				curNode = op.doEqual(curNode);
				if ( curNode == null ) {
					break;
				}
			}
		}
	}
	private static class FindOperation<T> {
		AvlNode<T> retNode = null;
		AvlNode<T> doLeft( AvlNode<T> curNode){
			return curNode.left;
		}
		AvlNode<T> doRight( AvlNode<T> curNode){
			return curNode.right;
		}
		AvlNode<T> doEqual( AvlNode<T> curNode){
			retNode = curNode;
			return null;
		}
	}
	public StdIterator<T> find(T t) {
		if ( avl == null ) {
			return itend;
		}
		FindOperation<T> op = new FindOperation<T>();
		findFrame(avl,t, op);
		if ( op.retNode == null ) {
			return itend;
		}
		return new StdIterator<T>(op.retNode);
	}
	private static class FindFirstOperation<T> extends FindOperation<T>{
		@Override
		AvlNode<T> doEqual( AvlNode<T> curNode){
			retNode = curNode;
			return curNode.left;
		}
	}
	public StdIterator<T> findFirst(T t) {
		if ( avl == null ) {
			return itend;
		}
		FindOperation<T> op = new FindFirstOperation<T>();
		findFrame(avl,t, op);
		if ( op.retNode == null ) {
			return itend;
		}
		return new StdIterator<T>(op.retNode);
	}
	private static class FindLastOperation<T> extends FindOperation<T>{
		@Override
		AvlNode<T> doEqual( AvlNode<T> curNode){
			retNode = curNode;
			return curNode.right;
		}
	}
	public StdIterator<T> findLast(T t) {
		if ( avl == null ) {
			return itend;
		}
		FindOperation<T> op = new FindLastOperation<T>();
		findFrame(avl,t, op);
		if ( op.retNode == null ) {
			return itend;
		}
		return new StdIterator<T>(op.retNode);
	}
	private static class EqualRangeFirstOperation<T> extends FindOperation<T>{
		AvlNode<T> savePoint = null;
		@Override
		AvlNode<T> doLeft(AvlNode<T> curNode) {
			if ( curNode.left == null )
				return null;
			if ( retNode == null )
				savePoint = curNode;
			return curNode.left;
		}
		@Override
		AvlNode<T> doEqual( AvlNode<T> curNode){
			retNode = curNode;
			return curNode.left;
		}
	}
	public Pair<StdIterator<T>,StdIterator<T>> equalRange(T t) {
		if ( avl == null ) {
			return new Pair<StdIterator<T>, StdIterator<T>>(itend, itend);
		}
		
		EqualRangeFirstOperation<T> firstOp = new EqualRangeFirstOperation<T>();
		findFrame(avl,t, firstOp);
		if ( firstOp.retNode == null ) {
			return new Pair<StdIterator<T>, StdIterator<T>>(itend, itend);
		}
		if ( firstOp.savePoint == null ) {
			firstOp.savePoint = avl;
		}
		UpperBoundOperation<T> secondOp = new UpperBoundOperation<T>();
		findFrame(firstOp.savePoint ,t, secondOp);
		if ( secondOp.retNode == null ) {
			return new Pair<StdIterator<T>, StdIterator<T>>(
					new StdIterator<T>(firstOp.retNode), itend);
		}
		return new Pair<StdIterator<T>, StdIterator<T>>(
				new StdIterator<T>(firstOp.retNode),
				new StdIterator<T>(secondOp.retNode));
	}
	private static class UpperBoundOperation<T> extends FindOperation<T>{
		@Override
		AvlNode<T> doLeft(AvlNode<T> curNode) {
			retNode = curNode;
			return curNode.left;
		}
		@Override
		AvlNode<T> doEqual( AvlNode<T> curNode){
			return curNode.right;
		}
	}
	public StdIterator<T> upperBound(T t) {
		if ( avl == null ) {
			return itend;
		}
		FindOperation<T> op = new UpperBoundOperation<T>();
		findFrame(avl,t, op);
		if ( op.retNode == null ) {
			return itend;
		}
		return new StdIterator<T>(op.retNode);
	}
	private static class LowerBoundOperation<T> extends FindOperation<T>{
		@Override
		AvlNode<T> doRight(AvlNode<T> curNode) {
			retNode = curNode;
			return curNode.right;
		}
		@Override
		AvlNode<T> doEqual( AvlNode<T> curNode){
			return curNode.left;
		}
	}
	public StdIterator<T> lowerBound(T t) {
		if ( avl == null ) {
			return itend;
		}
		FindOperation<T> op = new LowerBoundOperation<T>();
		findFrame(avl,t, op);
		if ( op.retNode == null ) {
			return itend;
		}
		return new StdIterator<T>(op.retNode);
	}
//*/
}
