package jp.co.rakuten.util.collection.tree;

public class AvlNode<T> {
	short			nleft  = 0;
	short			nright = 0;
	AvlNode<T>		parent = null;
	AvlNode<T>		left   = null;
	AvlNode<T>		right  = null;
	T		t;

	AvlNode(T t){
		this.t = t;
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
		pivot.nright = (short) (1 + ((nleft < nright)?nright:nleft));
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
		pivot.nleft= (short) (1 + ((nleft < nright)?nright:nleft));
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
		pivot.nleft = (short) (1+((L.nleft < L.nright)?L.nright:L.nleft));
		// (*3)
		left = D;
		if ( D!=null) {
			D.parent = this;
		}
		nleft = pivot.nright;
		// (*4)
		pivot.right= this;
		parent = pivot;
		pivot.nright= (short) (1+((nleft < nright)?nright:nleft));
		
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
		pivot.nright= (short) (1+((R.nleft < R.nright)?R.nright:R.nleft));
		// (*3)
		right = D;
		if ( D!=null) {
			D.parent = this;
		}
		nright = pivot.nleft;
		// (*4)
		pivot.left= this;
		parent = pivot;
		pivot.nleft= (short) (1+((nleft < nright)?nright:nleft));
		
		return pivot;
	}
	
	
	AvlNode<T> equilibrium(){
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
	AvlNode<T> begin() {
		AvlNode<T> curNode = this;
		while (true) {
			if ( curNode.left == null ) {
				return curNode;
			}
			curNode = curNode.left;
		}
	}
	AvlNode<T> last() {
		AvlNode<T> curNode = this;
		while (true) {
			if ( curNode.right == null ) {
				return curNode;
			}
			curNode = curNode.right;
		}
	}
	
	AvlNode<T> next() {
		if ( right != null ) {
			return right.begin();
		}
		AvlNode<T> curNode = this;
		while ( true ) {
			if ( curNode.parent == null ){
				return null;
			}
			if ( curNode.parent.right != curNode ) {
				return curNode.parent; 
			}
			curNode = curNode.parent;
		}
	}
	AvlNode<T> prev() {
		if ( left != null ) {
			return left.last();
		}
		AvlNode<T> curNode = this;
		while ( true ) {
			if ( curNode.parent == null ){
				return null;
			}
			if ( curNode.parent.left != curNode ) {
				return curNode.parent; 
			}
			curNode = curNode.parent;
		}
	}
}