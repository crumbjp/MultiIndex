package jp.co.rakuten.util.tree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

class StdIterator<T extends Comparable<T>> {
	static final StdIterator end = new StdIterator();
	AvlTree<T> h = null;
	public StdIterator () { }
	public StdIterator (AvlTree<T> h) {
		this.h = h;
	}
	public StdIterator (StdIterator<T> t) {
		this.h = t.h;
	}
	public T get(){
		return h.t;
	}
	public boolean equals(StdIterator<T> it) {
		if ( h == null ){
			if ( it.h == null ) {
				return true;
			}
			return false;
		}
		return h.equals(it.h);
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

// -agentlib:hprof=cpu=samples,heap=sites
class AvlTree<T extends Comparable<T>> {
	final T   t;
	AvlTree<T> 		parent = null;
	AvlTree<T>       left   = null;
	private int   nleft  = 0;
	AvlTree<T>       right  = null;
	private int   nright = 0;
	
	public AvlTree(){
		this.t = null;
	}
	public AvlTree(T t){
		this.t = t;
	}

	int deep() {
		return (nleft < nright)?nright:nleft;
	}
	AvlTree<T> shiftL(){
		AvlTree<T> ret = left;
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
		return ret;
	}
	AvlTree<T> shiftR(){
		AvlTree<T> ret = right;
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
		return ret;
	}
	AvlTree<T> equilibrium(){
		if ( (nleft - nright ) > 1 ){
			return shiftL();
		} else if ( (nright - nleft ) > 1 ){
			return shiftR();
		}
		return this;
	}
	AvlTree<T> insertL(AvlTree<T> in) {
		if ( left == null ) {
			in.parent = this;
			left = in;
		}else{
			left = left.insert(in);
		}
		nleft = 1 + left.deep();
		return equilibrium();
	}
	AvlTree<T> insertR(AvlTree<T> in) {
		if ( right == null ) {
			in.parent = this;
			right = in;
		}else{
			right = right.insert(in);
		}
		nright = 1 + right.deep();
		return equilibrium();
	}
	AvlTree<T> insert(AvlTree<T> in) {
		int cmp = t.compareTo(in.t); 
		if ( cmp > 0 ) {
			return insertL(in);
		} else if ( cmp < 0 ){
			// in >= t
			return insertR(in);
		} else {
			return insertR(in);
		}
	}

	AvlTree<T> erase(AvlTree<T> root) {
		// @@@
		AvlTree<T> c = null;
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
		} else if ( left.nright < right.nleft ) {
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

	AvlTree<T> begin() {
		if ( left == null ) {
			return this;
		}
		return left.begin();
	}
	AvlTree<T> last() {
		if ( right == null ) {
			return this;
		}
		return right.last();
	}
	
	AvlTree<T> next(boolean start) {
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
	
	AvlTree<T> prev(boolean start) {
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
	
	
	StdIterator<T> find(T in,Op<T> op) {
		int cmp = t.compareTo(in); 
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
abstract class Op<T extends Comparable<T>> {
	public StdIterator<T> it = new StdIterator<T>();
	public abstract StdIterator<T> doEqual(AvlTree<T> h,T in);
	public StdIterator<T> doEnd(int cmp,AvlTree<T> h,T in) {
		return it;
	}
}
class FindOp<T extends Comparable<T>> extends Op<T>{
	@Override
	public StdIterator<T> doEqual(AvlTree<T> h,T in){
		it.h = h;
		return it;
	}
}
class FindLastOp<T extends Comparable<T>> extends Op<T>{
	@Override
	public StdIterator<T> doEqual(AvlTree<T> h,T in){
		it.h = h;
		if ( h.right == null ) {
			return it;
		}
		return h.right.find(in, this);
	}
}
class FindFirstOp<T extends Comparable<T>> extends Op<T>{
	@Override
	public StdIterator<T> doEqual(AvlTree<T> h,T in){
		it.h = h;
		if ( h.left == null ) {
			return it;
		}
		return h.left.find(in, this);
	}
}
class EqualRangeOp<T extends Comparable<T>> extends Op<T>{
	Pair<StdIterator<T>,StdIterator<T>> p = new Pair<StdIterator<T>, StdIterator<T>>(null,null);
	@Override
	public StdIterator<T> doEqual(AvlTree<T> h,T in){
		it.h = h;
		if ( p.second == null ) {
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
			if ( p.first == null ){
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

class UpperBoundOp<T extends Comparable<T>> extends FindLastOp<T>{
	@Override
	public StdIterator<T> doEnd(int cmp,AvlTree<T> h,T in) {
		if ( it.equals(StdIterator.end)) {
			it.h = h;
			if ( cmp < 0 ) {
				it.next();
			}
			return it;
		}
		return it.next();
	}
}

class LowerBoundOp<T extends Comparable<T>> extends FindFirstOp<T>{
	@Override
	public StdIterator<T> doEnd(int cmp,AvlTree<T> h,T in) {
		if ( it.equals(StdIterator.end)) {
			it.h = h;
			if ( cmp > 0 ) {
				it.prev();
			}
			return it;
		}
		return it.prev();
	}
}

class AvlRoot<T extends Comparable<T>> {
	AvlTree<T> avl = null;
	Comparator<T> comparator;
	public AvlRoot(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	public AvlRoot() {

	}
	public StdIterator<T> end(){
		return StdIterator.end;
	}
	public void clear(){
		avl = null;
	}
	public void insert(T t) {
		if ( avl == null ) {
			avl = new AvlTree<T>(t); 
			return;
		}
		avl = avl.insert(new AvlTree<T>(t));
	}
	public StdIterator<T> begin() {
		if ( avl == null ) {
			return StdIterator.end;
		}
		return  new StdIterator<T>(avl.begin());
	}
	public StdIterator<T> last() {
		if ( avl == null ) {
			return StdIterator.end;
		}
		return  new StdIterator<T>(avl.last());
	}
	public StdIterator<T> find(T t) {
		if ( avl == null ) {
			return StdIterator.end;
		}
		return avl.find(t,new FindOp<T>());
	}
	public StdIterator<T> findFirst(T t) {
		if ( avl == null ) {
			return StdIterator.end;
		}
		return avl.find(t,new FindFirstOp<T>());
	}
	public StdIterator<T> findLast(T t) {
		if ( avl == null ) {
			return StdIterator.end;
		}
		return avl.find(t,new FindLastOp<T>());
	}
	public Pair<StdIterator<T>,StdIterator<T>> equalRange(T t) {
		if ( avl == null ) {
			return new Pair<StdIterator<T>, StdIterator<T>>( StdIterator.end,StdIterator.end);
		}
		EqualRangeOp<T> op = new EqualRangeOp<T>();
		avl.find(t,op);
		return op.p;
	}
	public StdIterator<T> upperBound(T t) {
		if ( avl == null ) {
			return StdIterator.end;
		}
		return avl.find(t,new UpperBoundOp<T>());
	}
	public StdIterator<T> lowerBound(T t) {
		if ( avl == null ) {
			return StdIterator.end;
		}
		return avl.find(t,new LowerBoundOp<T>());
	}

	public boolean erase(StdIterator<T> it) {
		if ( avl == null ) {
			return false;
		}
		if ( it.h == null ) {
			return false;
		}
		avl = it.h.erase(avl);
		return true;
	}

}


class D implements Comparable<D> {
	static Integer U = 1;
	String i;
	Integer u;
	public D(String i){
		this.i = i;
		this.u = U++;
	}
	public int compareTo(D o) {
		return i.compareTo(o.i);
	}
	public String toString () {
		return String.format("%s(%2d),", i,u);
	}
}
public class Tree {
	public static void main(String args[]) {
//*
		AvlRoot<D> h1 = new AvlRoot<D>();
		h1.insert(new D("W"));
		dump(h1);
		h1.insert(new D("A"));
		dump(h1);
		h1.insert(new D("K"));
		dump(h1);
		h1.insert(new D("A"));
		dump(h1);
		h1.insert(new D("M"));
		dump(h1);
		h1.insert(new D("A"));
		dump(h1);
		h1.insert(new D("T"));
		dump(h1);
		h1.insert(new D("S"));
		dump(h1);
		h1.insert(new D("U"));
		dump(h1);
		h1.insert(new D("N"));
		dump(h1);
		h1.insert(new D("A"));
		dump(h1);
		h1.insert(new D("O"));
		dump(h1);
		h1.insert(new D("K"));
		dump(h1);
		h1.insert(new D("I"));
		dump(h1);
		h1.insert(new D("Y"));
		dump(h1);
		h1.insert(new D("M"));
		dump(h1);
		
		for ( StdIterator<D> it = h1.begin(); ! it.equals(h1.end());it.next()){
			System.out.println("IT :" + it.get().toString());
		}
		for ( StdIterator<D> it = h1.last(); ! it.equals(h1.end());it.prev()){
			System.out.println("RIT :" + it.get().toString());
		}
		
		System.out.println("FIND-L:" + ( h1.find(new D("L")) == h1.end()));
		System.out.println("FIND-K:" + h1.find(new D("K")).get().toString());
		System.out.println("FIND-A:" + h1.find(new D("A")).get().toString());
		System.out.println("FIND-Y:" + h1.find(new D("Y")).get().toString());
		System.out.println("FIND-M:" + h1.find(new D("M")).get().toString());
		System.out.println("FINDF-L:" + ( h1.findFirst(new D("L")) == h1.end()));
		System.out.println("FINDF-K:" + h1.findFirst(new D("K")).get().toString());
		System.out.println("FINDF-A:" + h1.findFirst(new D("A")).get().toString());
		System.out.println("FINDF-Y:" + h1.findFirst(new D("Y")).get().toString());
		System.out.println("FINDF-M:" + h1.findFirst(new D("M")).get().toString());
		System.out.println("FINDL-L:" + ( h1.findLast(new D("L")) == h1.end()));
		System.out.println("FINDL-K:" + h1.findLast(new D("K")).get().toString());
		System.out.println("FINDL-A:" + h1.findLast(new D("A")).get().toString());
		System.out.println("FINDL-Y:" + h1.findLast(new D("Y")).get().toString());
		System.out.println("FINDL-M:" + h1.findLast(new D("M")).get().toString());
		{
			Pair<StdIterator<D>,StdIterator<D>> p = h1.equalRange(new D("A"));
			for ( StdIterator<D> it = p.first; ! it.equals(p.second);it.next()){
				System.out.println("RANGE-A:" + it.get().toString());
			}
		}
		{
			Pair<StdIterator<D>,StdIterator<D>> p = h1.equalRange(new D("M"));
			for ( StdIterator<D> it = p.first; ! it.equals(p.second);it.next()){
				System.out.println("RANGE-M:" + it.get().toString());
			}
		}
		{
			Pair<StdIterator<D>,StdIterator<D>> p = h1.equalRange(new D("K"));
			for ( StdIterator<D> it = p.first; ! it.equals(p.second);it.next()){
				System.out.println("RANGE-K:" + it.get().toString());
			}
		}
		{
			Pair<StdIterator<D>,StdIterator<D>> p = h1.equalRange(new D("I"));
			for ( StdIterator<D> it = p.first; ! it.equals(p.second);it.next()){
				System.out.println("RANGE-I:" + it.get().toString());
			}
		}
		System.out.println("FIND-UP-1:" + h1.upperBound(new D("1")).get().toString());
		System.out.println("FIND-UP-A:" + h1.upperBound(new D("A")).get().toString());
		System.out.println("FIND-UP-B:" + h1.upperBound(new D("B")).get().toString());
		System.out.println("FIND-UP-K:" + h1.upperBound(new D("K")).get().toString());
		System.out.println("FIND-UP-M:" + h1.upperBound(new D("M")).get().toString());
		System.out.println("FIND-UP-P:" + h1.upperBound(new D("P")).get().toString());
		System.out.println("FIND-UP-S:" + h1.upperBound(new D("S")).get().toString());
		System.out.println("FIND-UP-Y:" + (h1.upperBound(new D("Y")) == h1.end()));
		System.out.println("FIND-UP-Z:" + (h1.upperBound(new D("Z")) == h1.end()));


		System.out.println("FIND-LO-1:" + (h1.lowerBound(new D("1")) == h1.end()));
		System.out.println("FIND-LO-A:" + (h1.lowerBound(new D("A")) == h1.end()));
		System.out.println("FIND-LO-B:" + h1.lowerBound(new D("B")).get().toString());
		System.out.println("FIND-LO-K:" + h1.lowerBound(new D("K")).get().toString());
		System.out.println("FIND-LO-M:" + h1.lowerBound(new D("M")).get().toString());
		System.out.println("FIND-UP-P:" + h1.lowerBound(new D("P")).get().toString());
		System.out.println("FIND-LO-S:" + h1.lowerBound(new D("S")).get().toString());
		System.out.println("FIND-LO-Y:" + h1.lowerBound(new D("Y")).get().toString());
		System.out.println("FIND-LO-Z:" + h1.lowerBound(new D("Z")).get().toString());

		dump(h1);
		h1.erase(h1.find(new D("T")));
		dump(h1);
//		System.exit(0);
//*/
/*
		Heap<Integer> h2= new Heap<Integer>();
		for ( int i = 0 ; i < 20; i++){
			h2.add(i);
			dump(h2);
		}
//*/
//*
		Integer[] datas = new Integer[100000];
		for ( int i = 0 ; i < 100000 ; i++)
			datas[i] = new Integer(i);
		{
			TreeSet<Integer> set = new TreeSet<Integer>();
			long as = System.currentTimeMillis();
			for ( int i = 0 ; i < 100000; i++)
				set.add(i);
			long ae = System.currentTimeMillis();
			System.out.println("Tree add : " + (ae - as));
			long gs = System.currentTimeMillis();
			for ( int i = 0 ; i < 100000; i++)
				set.ceiling(i);
			long ge = System.currentTimeMillis();
			System.out.println("Tree find : " + (ge - gs));
		}
		{
			AvlRoot<Integer> heap= new AvlRoot<Integer>();
			long ss = System.currentTimeMillis();
			for ( int i = 0 ; i < 100000; i++)
				heap.insert(i);
			long se = System.currentTimeMillis();
			System.out.println("Heap add : " + (se - ss));
			long gs = System.currentTimeMillis();
			for ( int i = 0 ; i < 100000; i++)
				heap.find(i);
			long ge = System.currentTimeMillis();
			System.out.println("Heap find: " + (ge - gs));
			long fs = System.currentTimeMillis();
			for ( int i = 0 ; i < 100000; i++)
				heap.findFirst(i);
			long fe = System.currentTimeMillis();
			System.out.println("Heap findf: " + (fe - fs));
			long ls = System.currentTimeMillis();
			for ( int i = 0 ; i < 100000; i++)
				heap.findLast(i);
			long le = System.currentTimeMillis();
			System.out.println("Heap findl: " + (le - ls));
		}
//*/
/*
		{
			TreeSet<Integer> set = new TreeSet<Integer>();
			long ss = System.currentTimeMillis();
			for ( int i = 0 ; i < 100000; i++)
				set.add(datas[i]);
			long se = System.currentTimeMillis();
			System.out.println("Tree : " + (se - ss));
		}
/*/
/*
		{
			Heap<Integer> heap= new Heap<Integer>();
			long ss = System.currentTimeMillis();
			for ( int i = 0 ; i < 100000; i++)
				heap.add(datas[i]);
			long se = System.currentTimeMillis();
			System.out.println("Heap : " + (se - ss));
//			dump(heap);
		}
//*/
/*
		{
			TreeSet<Integer> set = new TreeSet<Integer>();
			long ss = System.currentTimeMillis();
			for ( int i = 100000 ; i > 0 ; i--)
				set.add(i);
			long se = System.currentTimeMillis();
			System.out.println("Tree : " + (se - ss));
		}
		{
			Heap<Integer> heap= new Heap<Integer>();
			long ss = System.currentTimeMillis();
			for ( int i = 100000 ; i > 0; i--)
				heap.add(i);
			long se = System.currentTimeMillis();
			System.out.println("Heap : " + (se - ss));
		}
//*/
	}
	public static void dump (AvlRoot h) {
		System.out.println("-------------------------------------------");
		dump(h.avl,1);
		System.out.println("");
	}
	public static void dump (AvlTree h , int n ) {
		String line = h.t!=null?h.t.toString():"[ROOT]";
		char[] pad = new char[n];
		Arrays.fill(pad,' ');
		String log = String.format(" - %s ",line);
		n = n + log.length();
		if ( h.left!= null ) {
			dump(h.left,n);
		} else {
			if ( h.parent != null && h.parent.left == h ) {
				System.out.println("");
			}
		}
		System.out.println("");
		System.out.print(pad);
		System.out.print(log);
		if ( h.right!= null ) {
			dump(h.right,n);
		}else {
		}
	}
}
