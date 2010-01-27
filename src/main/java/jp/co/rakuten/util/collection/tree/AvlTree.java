package jp.co.rakuten.util.collection.tree;

import java.util.Comparator;

public class AvlTree<T,K> {
	static final AvlIterator itend = new AvlIterator((AvlTree)null);
	AvlNode<T> avl = null;
	private final Comparator<T> comparator;
	private final FindComparator<T,K> findComparator;
	long size = 0;
	public AvlTree( final Comparator<T> comparator ) {
		this.comparator = comparator;
		this.findComparator = new FindComparator<T, K>() {
			public int compare(T o1, K o2) {
				return comparator.compare(o1, (T)o2);
			}
		};
	}
	public AvlTree( final Comparator<T> comparator , final FindComparator<T,K> keyComparator) {
		this.comparator = comparator;
		this.findComparator = keyComparator;
	}
	public AvlTree( final FindComparator<T,K> keyComparator) {
		this.comparator = new Comparator<T>() {
			public int compare(T o1, T o2) {
				return ((Comparable<T>)o1).compareTo(o2);
			};
		};
		this.findComparator = keyComparator;
	}

	public AvlTree() {
		this.comparator = new Comparator<T>() {
			public int compare(T o1, T o2) {
				return ((Comparable<T>)o1).compareTo(o2);
			};
		};
		this.findComparator = new FindComparator<T, K>() {
			public int compare(T o1, K o2) {
				return comparator.compare(o1, (T)o2);
			}
		};
	}

	public AvlIterator<T> end(){
		return itend;
	}
	public void clear(){
		avl = null;
		size = 0;
	}
	public long size() {
		return size;
	}
	
	private AvlNode<T> insertSimple(AvlNode<T> curNode , AvlNode<T> in) {
		while (true) {
			int cmp = comparator.compare(curNode.t,in.t);
			if ( cmp > 0 ) {
				if ( curNode.left == null){
					in.parent = curNode;
					curNode.left = in;
					curNode.nleft = (short) (1 + ((in.nleft < in.nright)?in.nright:in.nleft));
					break;
				}
				curNode = curNode.left;
			} else {
				if ( curNode.right == null){
					in.parent = curNode;
					curNode.right = in;
					curNode.nright = (short) (1 + ((in.nleft < in.nright)?in.nright:in.nleft));
					break;
				}
				curNode = curNode.right;
			}
		}
		return curNode;
	}
	private AvlNode<T> insertEquilibrium(AvlNode<T> curNode,AvlNode<T> termNode){
		AvlNode<T> parentNode;
		while (true) {
			parentNode = curNode.parent;
			if ( parentNode == termNode) {
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
	private AvlNode<T> insertNodeTree( AvlNode<T> targetNode , AvlNode<T> node ) {
		AvlNode<T> inserted = insertSimple(targetNode, node);
		return insertEquilibrium(inserted,targetNode.parent);
	}
	public void insert(T t) {
		if ( avl == null ) {
			avl = new AvlNode<T>(t); 
			size++;
			return ;
		}
		AvlNode<T> inserted = insertSimple(avl, new AvlNode<T>(t));
		size++;
		avl = insertEquilibrium(inserted,null);
	}

	private AvlNode<T> insertUnique(AvlNode<T> curNode , AvlNode<T> node) {
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
			} else if ( cmp < 0 ){
				if ( curNode.right == null){
					node.parent = curNode;
					curNode.right = node;
					curNode.nright = (short) (1 + ((node.nleft < node.nright)?node.nright:node.nleft));
					break;
				}
				curNode = curNode.right;
			} else {
				return null;
			}
		}
		return curNode;
	}

	public boolean insertUnique(T t) {
		if ( avl == null ) {
			avl = new AvlNode<T>(t); 
			size++;
			return true;
		}
		AvlNode<T> inserted = insertUnique(avl, new AvlNode<T>(t));
		if ( inserted == null ) {
			return false;
		}
		avl = insertEquilibrium(inserted,null);
		size++;
		return true;
	}

	private AvlNode<T> insertReplace(AvlNode<T> curNode , AvlNode<T> node) {
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
			} else if ( cmp < 0 ){
				if ( curNode.right == null){
					node.parent = curNode;
					curNode.right = node;
					curNode.nright = (short) (1 + ((node.nleft < node.nright)?node.nright:node.nleft));
					break;
				}
				curNode = curNode.right;
			} else {
				curNode.t = node.t;
				return null;
			}
		}
		return curNode;
	}
	public boolean insertReplace(T t) {
		if ( avl == null ) {
			avl = new AvlNode<T>(t); 
			size++;
			return false;
		}
		AvlNode<T> inserted = insertReplace(avl, new AvlNode<T>(t));
		if ( inserted == null ) {
			return true;
		}
		avl = insertEquilibrium(inserted,null);
		size++;
		return false;
	}
	
	
	public AvlIterator<T> begin() {
		if ( avl == null ) {
			return itend;
		}
		return  new AvlIterator<T>(this,avl.begin());
	}

	public AvlIterator<T> last() {
		if ( avl == null ) {
			return itend;
		}
		return  new AvlIterator<T>(this,avl.last());
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
					return insertNodeTree(targetNode.left,targetNode.right);
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
				AvlNode<T> merge = insertNodeTree(targetNode.left,targetNode.right);
				return insertNodeTree(avl,merge);
			}
			return insertNodeTree(avl,targetNode.left);
		} else {
			if ( targetNode.right != null ) {
				return insertNodeTree(avl,targetNode.right);
			}
			return avl;
		}
	}
	public boolean erase(AvlIterator<T> it) {
		if (it.tree != this ) {
			if ( it.isEnd() ) 
				return false;
			throw new RuntimeException("Failure : Othe tree iterator !");
		}
		avl = erase(it.node);
		size--;
		return true;
	}
	public boolean rawReplace(AvlIterator<T> it , T t) {
		if (it.tree != this ) {
			throw new RuntimeException("Failure : Othe tree iterator !");
		}
		if ( comparator.compare(it.node.t,t) == 0 ) {
			it.node.t = t;
			return true;
		}
		return false;
	}
	
//*
	public AvlIterator<T> find(K k) {
		if ( avl == null ) {
			return itend;
		}
		AvlNode<T> curNode = avl;
		while (true) {
			int cmp = findComparator.compare(curNode.t,k);
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
				return new AvlIterator<T>(this,curNode);
			}
		}
	}

	public AvlIterator<T> findFirst(K k) {
		if ( avl == null ) {
			return itend;
		}
		AvlNode<T> curNode = avl;
		AvlNode<T> retNode = null;
		while (true) {
			int cmp = findComparator.compare(curNode.t,k);
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
		return new AvlIterator<T>(this,retNode);
	}

	public AvlIterator<T> findLast(K k) {
		if ( avl == null ) {
			return itend;
		}
		AvlNode<T> curNode = avl;
		AvlNode<T> retNode = null;
		while (true) {
			int cmp = findComparator.compare(curNode.t,k);
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
		return new AvlIterator<T>(this,retNode);
	}

	public Pair<AvlIterator<T>,AvlIterator<T>> equalRange(K k) {
		if ( avl == null ) {
			return new Pair<AvlIterator<T>, AvlIterator<T>>(itend, itend);
		}
		AvlNode<T> curNode = avl;
		AvlNode<T> firstNode = null;
		AvlNode<T> secondNode = avl;
		while (true) {
			int cmp = findComparator.compare(curNode.t,k);
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
			return new Pair<AvlIterator<T>, AvlIterator<T>>(itend, itend);
		}
		curNode = secondNode;
		while (true) {
			int cmp = findComparator.compare(curNode.t,k);
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
		return new Pair<AvlIterator<T>, AvlIterator<T>>(
				new AvlIterator<T>(this,firstNode),
				new AvlIterator<T>(this,secondNode));
	}
	public AvlIterator<T> upperBound(K k) {
		if ( avl == null ) {
			return itend;
		}
		AvlNode<T> curNode = avl;
		AvlNode<T> retNode = null;
		while (true) {
			int cmp = findComparator.compare(curNode.t,k);
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
		return new AvlIterator<T>(this,retNode);
	}

	public AvlIterator<T> lowerBound(K k) {
		if ( avl == null ) {
			return itend;
		}
		AvlNode<T> curNode = avl;
		AvlNode<T> retNode = null;
		while (true) {
			int cmp = findComparator.compare(curNode.t,k);
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
		return new AvlIterator<T>(this,retNode);
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
	public AvlStdIterator<T> find(T t) {
		if ( avl == null ) {
			return itend;
		}
		FindOperation<T> op = new FindOperation<T>();
		findFrame(avl,t, op);
		if ( op.retNode == null ) {
			return itend;
		}
		return new AvlStdIterator<T>(op.retNode);
	}
	private static class FindFirstOperation<T> extends FindOperation<T>{
		@Override
		AvlNode<T> doEqual( AvlNode<T> curNode){
			retNode = curNode;
			return curNode.left;
		}
	}
	public AvlStdIterator<T> findFirst(T t) {
		if ( avl == null ) {
			return itend;
		}
		FindOperation<T> op = new FindFirstOperation<T>();
		findFrame(avl,t, op);
		if ( op.retNode == null ) {
			return itend;
		}
		return new AvlStdIterator<T>(op.retNode);
	}
	private static class FindLastOperation<T> extends FindOperation<T>{
		@Override
		AvlNode<T> doEqual( AvlNode<T> curNode){
			retNode = curNode;
			return curNode.right;
		}
	}
	public AvlStdIterator<T> findLast(T t) {
		if ( avl == null ) {
			return itend;
		}
		FindOperation<T> op = new FindLastOperation<T>();
		findFrame(avl,t, op);
		if ( op.retNode == null ) {
			return itend;
		}
		return new AvlStdIterator<T>(op.retNode);
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
	public Pair<AvlStdIterator<T>,AvlStdIterator<T>> equalRange(T t) {
		if ( avl == null ) {
			return new Pair<AvlStdIterator<T>, AvlStdIterator<T>>(itend, itend);
		}
		
		EqualRangeFirstOperation<T> firstOp = new EqualRangeFirstOperation<T>();
		findFrame(avl,t, firstOp);
		if ( firstOp.retNode == null ) {
			return new Pair<AvlStdIterator<T>, AvlStdIterator<T>>(itend, itend);
		}
		if ( firstOp.savePoint == null ) {
			firstOp.savePoint = avl;
		}
		UpperBoundOperation<T> secondOp = new UpperBoundOperation<T>();
		findFrame(firstOp.savePoint ,t, secondOp);
		if ( secondOp.retNode == null ) {
			return new Pair<AvlStdIterator<T>, AvlStdIterator<T>>(
					new AvlStdIterator<T>(firstOp.retNode), itend);
		}
		return new Pair<AvlStdIterator<T>, AvlStdIterator<T>>(
				new AvlStdIterator<T>(firstOp.retNode),
				new AvlStdIterator<T>(secondOp.retNode));
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
	public AvlStdIterator<T> upperBound(T t) {
		if ( avl == null ) {
			return itend;
		}
		FindOperation<T> op = new UpperBoundOperation<T>();
		findFrame(avl,t, op);
		if ( op.retNode == null ) {
			return itend;
		}
		return new AvlStdIterator<T>(op.retNode);
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
	public AvlStdIterator<T> lowerBound(T t) {
		if ( avl == null ) {
			return itend;
		}
		FindOperation<T> op = new LowerBoundOperation<T>();
		findFrame(avl,t, op);
		if ( op.retNode == null ) {
			return itend;
		}
		return new AvlStdIterator<T>(op.retNode);
	}
//*/
}
