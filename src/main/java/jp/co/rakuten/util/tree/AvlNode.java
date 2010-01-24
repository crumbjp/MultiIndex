package jp.co.rakuten.util.tree;

import java.util.Comparator;


// -agentlib:hprof=cpu=samples,heap=sites
public class AvlNode<T> {
	final T		t;
	AvlNode<T>		parent = null;
	AvlNode<T>		left   = null;
	int				nleft  = 0;
	AvlNode<T>		right  = null;
	int				nright = 0;
	final Comparator<T> comparator;
	AvlNode(Comparator<T> comparator){
		this.t = null;
		this.comparator = comparator;
	}
	AvlNode(Comparator<T> comparator,T t){
		this.t = t;
		this.comparator = comparator;
	}
	int deep() {
		return (nleft < nright)?nright:nleft;
	}
	AvlNode<T> shiftL(){
		AvlNode<T> ret = left;
		// switch parent 
		ret.parent = parent;
		parent     = ret;
		// child switch
		left = ret.right;
		if( left != null) {
			left.parent = this;
		}
		ret.right = this;
		// calc n
		nleft = ret.nright;
		ret.nright = 1 + deep();
		// @@@
		ret.right = this.equilibrium();
		return ret;
	}
	AvlNode<T> shiftR(){
		AvlNode<T> ret = right;
		// switch parent 
		ret.parent = parent;
		parent     = ret;
		// child switch
		right = ret.left;
		if( right != null) {
			right.parent = this;
		}
		ret.left = this;
		// calc n
		nright = ret.nleft;
		ret.nleft = 1 + deep();
		// @@@
		ret.left= this.equilibrium();
		return ret;
	}
/*
--befor---


                    1 - A(2) 
           3 - A(4) 

                              1 - A(6) 
                    2 - A(11) 
                              1 - I(14) 
 1 - K(13) 
--after---


          1 - A(2) 
 1 - A(4) 

                              1 - A(6) 
                    2 - A(11) 
                              1 - I(14) 
          3 - K(13) 


--- shold ---


http://upload.wikimedia.org/wikipedia/commons/c/c4/Tree_Rebalancing.gif
                    1 - A(2) 
           3 - A(4) 

        1 - A(6) 
2 - A(11) 
                1 - I(14) 
        1 - K(13) 




             - A(2)
      - A(4)
A(6)
             - A(11)
      - I(14)
           - K(13)




             - A(2)
      - A(4)
             - A(6)
A(11)
      - I(14)
           - K(13)






--befor---


                    1 - A(2) 
           3 - A(4) 

                              1 - A(6) 
                    2 - A(11) 
                              1 - I(14) 
 1 - K(13) 
--after---


          1 - A(2) 
 1 - A(4) 

                    1 - A(6) 
          3 - A(11) 

                              1 - I(14) 
                    2 - K(13) 



























                   1 - A(2) 
          3 - A(4) 

                             1 - A(6) 
                   2 - A(11) 
                             1 - I(14) 
 0 - K(3) 

                                      1 - K(13) 
                             2 - M(5) 
                                      1 - M(16) 
                   3 - N(10) 

                                      1 - O(12) 
                             2 - S(8) 
          4 - T(7) 

                            1 - U(9) 
                   2 - W(1) 
                            1 - Y(15) 
-------------------------------------------


          1 - A(2) 
 0 - A(4) 

                                                          1 - A(6) 
                                                2 - A(11) 
                                                          1 - I(14) 
                                      3 - K(13) 
                             4 - M(5) 
                                      1 - M(16) 
                   5 - N(10) 

                                      1 - O(12) 
                             2 - S(8) 
          6 - T(7) 

                            1 - U(9) 
                   2 - W(1) 
                            1 - Y(15) 
	
 */
	AvlNode<T> equilibrium(){
		if ( (nleft - nright ) > 1 ){
			return shiftL();
		} else if ( (nright - nleft ) > 1 ){
			return shiftR();
		}
		return this;
	}
	AvlNode<T> insertL(AvlNode<T> in) {
		if ( left == null ) {
			in.parent = this;
			left = in;
		}else{
			left = left.insert(in);
		}
		nleft = 1 + left.deep();
		// @@@
//		return equilibrium();
		System.out.println("--befor---");
		AvlTreeTest.dump(this,1);
		System.out.println("");
		AvlNode<T> t = equilibrium();
		System.out.println("--after---");
		AvlTreeTest.dump(t,1);
		System.out.println("");
		return t;
	}
	AvlNode<T> insertR(AvlNode<T> in) {
		if ( right == null ) {
			in.parent = this;
			right = in;
		}else{
			right = right.insert(in);
		}
		nright = 1 + right.deep();
		return equilibrium();
	}
	AvlNode<T> insert(AvlNode<T> in) {
		int cmp = comparator.compare(t, in.t);
//		int cmp = t.compareTo(in.t); 
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
		if ( left == null && right != null ) {
			c = right;
		} else if ( right == null && left != null ) {
			c = left;
		} else if ( (nright + left.nright) < (nleft + right.nleft) ) {
			c = left.insert(right);
		} else {
			c = right.insert(left);
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
	
	
	StdIterator<T> find(T in,Operation<T> op) {
		int cmp = comparator.compare(t, in);
//		int cmp = t.compareTo(in); 
		if ( cmp > 0 ) {
			// in < t
			if( left != null) {
				return left.find(in, op);
			}
			return op.doEnd(cmp,this,in);
		} else if ( cmp < 0 ){
			// in >= t
			if ( right != null ) {
				return right.find(in,op);
			}
			return op.doEnd(cmp,this,in);
		} else {
			return op.doEqual(this,in);
		}
	}
}

abstract class Operation<T> {
	public StdIterator<T> it = new StdIterator<T>();
	public abstract StdIterator<T> doEqual(AvlNode<T> h,T in);
	public StdIterator<T> doEnd(int cmp,AvlNode<T> h,T in) {
		return it;
	}
}
class FindOp<T> extends Operation<T>{
	@Override
	public StdIterator<T> doEqual(AvlNode<T> h,T in){
		it.h = h;
		return it;
	}
}
class FindLastOp<T> extends Operation<T>{
	@Override
	public StdIterator<T> doEqual(AvlNode<T> h,T in){
		it.h = h;
		if ( h.right == null ) {
			return doEnd(0,h,in);
		}
		return h.right.find(in, this);
	}
}
class FindFirstOp<T> extends Operation<T>{
	@Override
	public StdIterator<T> doEqual(AvlNode<T> h,T in){
		it.h = h;
		if ( h.left == null ) {
			return doEnd(0,h,in);
		}
		return h.left.find(in, this);
	}
}
class EqualRangeOp<T> extends Operation<T>{
	Pair<StdIterator<T>,StdIterator<T>> p = new Pair<StdIterator<T>, StdIterator<T>>(AvlTree.itend,AvlTree.itend);
	@Override
	public StdIterator<T> doEqual(AvlNode<T> h,T in){
		it.h = h;
		if ( p.second.isEnd() ) {
			p.second = new StdIterator<T>(it);
			if ( h.left != null ) {
				h.left.find(in, this);
			}
			p.first  = it;
			it = p.second;
			if ( h.right != null ) {
				h.right.find(in, this);
			}
			return it.next();
		} else {
			if ( p.first.isEnd() ){
				if ( h.left != null )
					return h.left.find(in, this);
			} else {
				if ( h.right != null )
					return h.right.find(in, this);
			}
			return it;
		}
	}
}

class UpperBoundOp<T> extends FindLastOp<T>{
	@Override
	public StdIterator<T> doEnd(int cmp,AvlNode<T> h,T in) {
		if ( it.isEnd() ) {
			it.h = h;
			if ( cmp < 0 ) {
				it.next();
			}
			return it;
		}
		return it.next();
	}
}

class LowerBoundOp<T> extends FindFirstOp<T>{
	@Override
	public StdIterator<T> doEnd(int cmp,AvlNode<T> h,T in) {
		if ( it.isEnd() ) {
			it.h = h;
			if ( cmp > 0 ) {
				it.prev();
			}
			return it;
		}
		return it.prev();
	}
}
