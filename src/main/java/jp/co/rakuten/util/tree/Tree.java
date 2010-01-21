package jp.co.rakuten.util.tree;

import java.util.Arrays;

class It<T extends Comparable<T>> {
	static final It end = new It();
	AvlTree<T> h = null; 
	public T get(){
		return h.t;
	}
	public It<T> end(){
		return end;
	}
	public boolean equals(It<T> it) {
		if ( h == null ){
			if ( it.h == null ) {
				return true;
			}
			return false;
		}
		return h.equals(it.h);
	}
	
	It<T> next(It<T> it , AvlTree<T> p) { 
		if ( p == null ) {
			it.h = null;
			return it;
		}
		if ( p.right != it.h ){
			it.h = it.h.parent;
			return it;
		} else {
			it.h = p;
			return next(it,it.h.parent);
		}
	}
	It<T> next(It<T> it) { 
		if ( it.h.right != null ) {
			return it.h.right.begin(this);
		} else {
			return next(it,it.h.parent);
		}
	}
	public It<T> next() {
		return next(this);
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
		if ( (nleft - nright ) > 2 ){
			return shiftL();
		}
		if ( (nright - nleft ) > 2 ){
			return shiftR();
		}
		return this;
	}
	AvlTree<T> addL(AvlTree<T> in) {
		if ( left == null ) {
			in.parent = this;
			left = in;
		}else{
			left = left.add(in);
		}
		nleft = 1 + left.deep();
		return equilibrium();
	}
	AvlTree<T> addR(AvlTree<T> in) {
		if ( right == null ) {
			in.parent = this;
			right = in;
		}else{
			right = right.add(in);
		}
		nright = 1 + right.deep();
		return equilibrium();
	}
	AvlTree<T> add(AvlTree<T> in) {
		int cmp = t.compareTo(in.t); 
		if ( cmp > 0 ) {
			return addL(in);
		} else if ( cmp < 0 ){
			// in >= t
			return addR(in);
		} else {
			return addR(in);
		}
	}
	It<T> begin(It<T> it) {
		if ( left == null ) {
			it.h = this;
			return it;
		}
		return left.begin(it);
	}
}

class AvlRoot<T extends Comparable<T>> {
	AvlTree<T> avl = null;
	public void add(T t) {
		if ( avl == null ) {
			avl = new AvlTree<T>(t); 
		}
		avl = avl.add(new AvlTree<T>(t));
	}
	public It<T> begin() {
		It<T> it = new It<T>();
		return avl.begin(it);
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
		h1.add(new D("W"));
		dump(h1);
		h1.add(new D("A"));
		dump(h1);
		h1.add(new D("K"));
		dump(h1);
		h1.add(new D("A"));
		dump(h1);
		h1.add(new D("M"));
		dump(h1);
		h1.add(new D("A"));
		dump(h1);
		h1.add(new D("T"));
		dump(h1);
		h1.add(new D("S"));
		dump(h1);
		h1.add(new D("U"));
		dump(h1);
		h1.add(new D("N"));
		dump(h1);
		h1.add(new D("A"));
		dump(h1);
		h1.add(new D("O"));
		dump(h1);
		h1.add(new D("K"));
		dump(h1);
		h1.add(new D("I"));
		dump(h1);
		h1.add(new D("Y"));
		dump(h1);
		
		for ( It<D> it = h1.begin(); ! it.equals(it.end());it.next()){
			System.out.println("IT :" + it.get().toString());
		}
//*/
/*
		Heap<Integer> h2= new Heap<Integer>();
		for ( int i = 0 ; i < 20; i++){
			h2.add(i);
			dump(h2);
		}
//*/
/*
		Integer[] datas = new Integer[100000];
		for ( int i = 0 ; i < 100000 ; i++)
			datas[i] = new Integer(i);
		{
			TreeSet<Integer> set = new TreeSet<Integer>();
			long ss = System.currentTimeMillis();
			for ( int i = 0 ; i < 100000; i++)
				set.add(i);
			long se = System.currentTimeMillis();
		}
		{
			Heap<Integer> heap= new Heap<Integer>();
			long ss = System.currentTimeMillis();
			for ( int i = 0 ; i < 100000; i++)
				heap.add(i);
			long se = System.currentTimeMillis();
		}
*/
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
		}
		System.out.println("");
		System.out.print(pad);
		System.out.print(log);
		if ( h.right!= null ) {
			dump(h.right,n);
		}
	}
}
