package jp.co.rakuten.util.tree;

import java.util.Comparator;


// -agentlib:hprof=cpu=times,heap=sites
public class AvlNode<T> {
	short			nleft  = 0;
	short			nright = 0;
	AvlNode<T>		parent = null;
	AvlNode<T>		left   = null;
	AvlNode<T>		right  = null;
	private final Comparator<T> comparator;
	final T		t;

	AvlNode(Comparator<T> comparator,T t){
		this.t = t;
		this.comparator = comparator;
	}
	private int deep() {
		return (nleft < nright)?nright:nleft;
	}

/*
             -D
         -LL
             -C 
   -pivot
         -B
-this
    -A
       

       -D
   -LL
       -C 
-pivot
       -B(*1)
  -this(*2)
       -A
*/        

	private AvlNode<T> shiftLL(){
		AvlNode<T> pivot = left;
		AvlNode<T> B = pivot.right;
		
		// switch parent 
		pivot.parent = parent;
		// (*1)
		left = B;
		if( B!= null) {
			B.parent = this;
		}
		nleft = pivot.nright;
		// (*2)
		pivot.right = this;
		parent       = pivot;
		pivot.nright = (short) (1 + deep());
		return pivot;
	}
	
/*
    -A
-this
        -B
    -pivot
            -C 
        -RR
            -D


        -A
    -this(*2)
        -B(*1)
-pivot
        -C 
    -RR
        -D
*/
	
	private AvlNode<T> shiftRR(){
		AvlNode<T> pivot = right;
		AvlNode<T> B = pivot.left;
		
		// switch parent 
		pivot.parent = parent;
		// (*1)
		right = B;
		if( B!= null) {
			B.parent = this;
		}
		nright= pivot.nleft;
		// (*2)
		pivot.left = this;
		parent       = pivot;
		pivot.nleft= (short) (1 + deep());
		return pivot;
	}


/*

        -B
    -L
            -C 
        -pivot
            -D
-this
    -A



        -B
    -L(*2) 
        -C(*1)
-pivot
        -D(*3)
    -this(*4)
        -A
*/        
	private AvlNode<T> shiftLR(){
		AvlNode<T> pivot = left.right;
		AvlNode<T> L = left;
		AvlNode<T> C = pivot.left;
		AvlNode<T> D = pivot.right;
		
		// switch parent 
		pivot.parent = parent;
		parent       = pivot;
		// (*1)
		L.right = C;
		if( C!= null) {
			C.parent = L;
		}
		L.nright = pivot.nleft;
		// (*2)
		pivot.left = L;
		L.parent = pivot;
		pivot.nleft = (short) (L.nright +1); // (1+L.deep())
		// (*3)
		left = D;
		if ( D!=null) {
			D.parent = this;
		}
		nleft = pivot.nright;
		// (*4)
		pivot.right= this;
		parent = pivot;
		pivot.nright= (short) (1+deep());
		
		return pivot;
	}
/*

    -A
-this
            -D
        -pivot
            -C 
    -R
        -B


        -A
    -this(*4)
        -D(*3)
-pivot
        -C(*1)
    -R(*2) 
        -B
*/        
	private AvlNode<T> shiftRL(){
		AvlNode<T> pivot = right.left;
		AvlNode<T> R = right;
		AvlNode<T> C = pivot.right;
		AvlNode<T> D = pivot.left;
		
		// switch parent 
		pivot.parent = parent;
		parent       = pivot;
		// (*1)
		R.left = C;
		if( C!= null) {
			C.parent = R;
		}
		R.nleft  = pivot.nright;
		// (*2)
		pivot.right = R;
		R.parent = pivot;
		pivot.nright= (short) (R.nleft + 1); // (1+L.deep())
		// (*3)
		right = D;
		if ( D!=null) {
			D.parent = this;
		}
		nright = pivot.nleft;
		// (*4)
		pivot.left= this;
		parent = pivot;
		pivot.nleft= (short) (1+deep());
		
		return pivot;
	}
	
	
	private AvlNode<T> equilibrium(){
		if ( (nleft - nright ) > 1 ){
			if ( left.nleft > left.nright ) {
				return shiftLL();
			}else {
				return shiftLR();
			}
		} else if ( (nright - nleft ) > 1 ){
			if ( right.nright > right.nleft ) {
				return shiftRR();
			}else {
				return shiftRL();
			}
		}
		return this;
	}
	private AvlNode<T> insertL(AvlNode<T> in) {
		if ( left == null ) {
			in.parent = this;
			left = in;
		}else{
			left = left.insert(in);
		}
		nleft = (short) (1 + left.deep());
		return equilibrium();
	}
	private AvlNode<T> insertR(AvlNode<T> in) {
		if ( right == null ) {
			in.parent = this;
			right = in;
		}else{
			right = right.insert(in);
		}
		nright = (short) (1 + right.deep());
		return equilibrium();
	}

	AvlNode<T> insert(AvlNode<T> in) {
		int cmp = comparator.compare(t, in.t);
		if ( cmp > 0 ) {
			return insertL(in);
		} else if ( cmp < 0 ){
			// in >= t
			return insertR(in);
		} else {
			return insertR(in);
		}
	}

	AvlNode<T> erase(AvlNode<T> root) {
		AvlNode<T> c = null;
		if ( left != null ) {
			left.parent = null;
		}
		if ( right != null ) {
			right.parent = null;
		}
		if ( left == null && right == null ) {
			return (root==this)?null:root;
		} else if ( left == null && right != null ) {
			c = right;
		} else if ( right == null && left != null ) {
			c = left;
		} else if ( nright < nleft ) {
			c = right.insert(left);
		} else {
			c = left.insert(right);
		}
		if ( parent == null ) {
			return c;
		}
		if ( parent.right == this ) {
			parent.right = null;
			parent.nright = 0;
		} else if ( parent.left == this) {
			parent.left = null;
			parent.nleft= 0;
		}
		return root.insert(c);
	}

	AvlNode<T> begin() {
		if ( left == null ) {
			return this;
		}
		return left.begin();
	}
	AvlNode<T> last() {
		if ( right == null ) {
			return this;
		}
		return right.last();
	}
	
	AvlNode<T> next(boolean start) {
		if ( start && right != null ) {
			return right.begin();
		} else if ( parent == null ){ 
			return null;
		} else if ( parent.right != this) {
				return parent;
		} else {
			return parent.next(false);
		}
	}
	
	AvlNode<T> prev(boolean start) {
		if ( start && left != null ) {
			return left.last();
		} else if ( parent == null ){ 
			return null;
		} else if ( parent.left != this) {
				return parent;
		} else {
			return parent.prev(false);
		}
	}
	
	AvlNode<T> find(T in,Operation<T> op) {
		int cmp = comparator.compare(t, in);
		if ( cmp > 0 ) {
			// in < t
			return op.doLeft(this,in);
		} else if ( cmp < 0 ){
			// in >= t
			return op.doRight(this,in);
		} else {
			return op.doEqual(this,in);
		}
	}
	static abstract class Operation<T> {
		public AvlNode<T> curNode = null;
		abstract AvlNode<T> doEqual(AvlNode<T> node,T in);
		AvlNode<T> callLeft(AvlNode<T> node,T in){
			if ( node.left != null ) {
				return node.left.find(in, this);
			}
			return curNode;
		}
		AvlNode<T> callRight(AvlNode<T> node,T in) {
			if ( node.right!= null ) {
				return node.right.find(in,this);
			}
			return curNode;
		}
		AvlNode<T> doLeft(AvlNode<T> node,T in) {
			return callLeft(node,in);
		}
		AvlNode<T> doRight(AvlNode<T> node,T in) {
			return callRight(node,in);
		}
	}
	static class FindOp<T> extends Operation<T>{
		@Override
		AvlNode<T> doEqual(AvlNode<T> node,T in){
			curNode = node;
			return curNode;
		}
	}
	static class FindLastOp<T> extends Operation<T>{
		@Override
		AvlNode<T> doEqual(AvlNode<T> node,T in){
			curNode = node;
			return callRight(node, in);
		}
	}
	static class FindFirstOp<T> extends Operation<T>{
		@Override
		AvlNode<T> doEqual(AvlNode<T> node,T in){
			curNode = node;
			return callLeft(node,in);
		}
	}
	static class EqualRangeOp<T> extends Operation<T>{
		AvlNode<T> firstEq = null;
		AvlNode<T> upper   = null;
		boolean first = true;
		Pair<StdIterator<T>,StdIterator<T>> p = new Pair<StdIterator<T>, StdIterator<T>>(AvlTree.itend,AvlTree.itend);
		@Override
		AvlNode<T> doEqual(AvlNode<T> node,T in){
			curNode = node;
			if ( firstEq == null) {
				firstEq = node;
				callLeft(node, in);
				p.first  = new StdIterator<T>(curNode);
				callRight(firstEq, in);
				p.second = new StdIterator<T>(upper);
				return curNode;
			} else {
				if ( p.first.isEnd() ) {
					return callLeft(node, in);
				} else {
					return callRight(node, in);
				}
			}
		}
		AvlNode<T> doLeft(AvlNode<T> node, T in) {
			upper = node;
			return callLeft(node, in);
		}
	}
	
	static class UpperBoundOp<T> extends Operation<T>{
		@Override
		AvlNode<T> doEqual(AvlNode<T> node,T in){
			return callRight(node, in);
		}
		AvlNode<T> doLeft(AvlNode<T> node, T in) {
			curNode = node;
			return callLeft(node, in);
		}
	}
	
	static class LowerBoundOp<T> extends Operation<T>{
		@Override
		AvlNode<T> doEqual(AvlNode<T> node,T in){
			return callLeft(node, in);
		}
		AvlNode<T> doRight(AvlNode<T> node, T in) {
			curNode = node;
			return callRight(node, in);
		};
	}
}