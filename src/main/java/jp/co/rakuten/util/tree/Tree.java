package jp.co.rakuten.util.tree;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

class Heap<T extends Comparable<T>> {
	HeapNode<T> root = null;
	public void add(T in) {
		if ( root == null ) {
			root = new HeapNode<T>(in);
		}else {
			root = root.add(new HeapNode<T>(in));
			root.red = false;
		}
	}
}
class HeapNode<T extends Comparable<T>> {
	final T   t;
	static final int LEFT  = 0;
	static final int RIGHT = 1;
	final HeapNode<T>[] dir   = new HeapNode[2];
	HeapNode<T> left  = null;
	HeapNode<T> right = null;
	boolean red   = false;
	public HeapNode(T t){
		this.t = t;
	}

	public HeapNode<T> add(HeapNode<T> in) {
		if ( t.compareTo(in.t) > 0 ) {
			// in < t (left)
			if ( red ) {
				if ( left == null ) { 
					// Generate left
					left = in;
				} else { 
					// is left
					left = left.add(in);
					if ( left.red ) {
						red = false;
					}
				}
			} else if( left == null ) {
				// Generate left-red
				in.red = true;
				left = in;
			} else if ( left.red && (right == null || !right.red) && left.left == null && left.right == null ) {
				// 3rd key
				if ( left.t.compareTo(in.t) > 0 ) {
					HeapNode<T> h = left;
					h.red = false;
					h.right = this;
					in.red = true;
					h.left  = in;
					left  = null;
					red   = true;
					return h;
				} else {
					HeapNode<T> h = in;
					h.left = left;
					h.right = this;
					left = null;
					red   = true;
					return h;
				}
			} else if ( left.red && right != null && right.red ) {
				// split
				left.red = false;
				right.red = false;
				left = left.add(in);
				red = true;
			} else {
				left = left.add(in);
			}
		} else {
			// in >= t
			if ( red ) {
				if ( right == null ) { 
					// Generate left
					right= in;
				} else { 
					// is left
					right = right.add(in);
					if ( right.red ){
//						red = false; @@@
					}
				}
			} else if( right == null ) {
				// Generate left-red
				in.red = true;
				right = in;
			} else if ( right.red && (left == null || !left.red) && right.left == null && right.right == null ) {
				// 3rd key
				if ( right.t.compareTo(in.t) > 0 ) {
					HeapNode<T> h = in;
					h.right = right;
					h.left  = this;
					this.right = null;
					this.red   = true;
					return h;
				} else {
					HeapNode<T> h = right;
					h.red = false;
					in.red=true;
					h.right = in;
					h.left  = this;
					right= null;
					red   = true;
					return h;
				}
			} else if (right.red && left != null && left.red ) {
				// split
				right.red = false;
				left.red = false;
				right = right.add(in);
				red = true;
			} else {
				right = right.add(in);
				if ( right.red && right.left != null && right.left.red ) {
					HeapNode<T> r = right;
					r.red = false;
					r.left = this;
					right = r.left;
					red = true;
					return r;
				} else if ( right.red && right.right != null && right.right.red ){
					if ( right.left != null ) {
						HeapNode<T> r = right;
						r.red = false;
						r.left = this;
						right = r.left;
						red = true;
						return r;
					}
				}
			}
		}
		return this;
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
		/*
		Heap<D> h = new Heap<D>();
		h.add(new D("W"));
		dump(h);
		h.add(new D("A"));
		dump(h);
		h.add(new D("K"));
		dump(h);
		h.add(new D("A"));
		dump(h);
		h.add(new D("M"));
		dump(h);
		h.add(new D("A"));
		dump(h);
		h.add(new D("T"));
		dump(h);
		h.add(new D("S"));
		dump(h);
		h.add(new D("U"));
		dump(h);
		h.add(new D("N"));
		dump(h);
		h.add(new D("A"));
		dump(h);
		h.add(new D("O"));
		dump(h);
		h.add(new D("K"));
		dump(h);
		h.add(new D("I"));
		dump(h);
*/
		Heap<Integer> h= new Heap<Integer>();
		for ( int i = 0 ; i < 20; i++){
			h.add(i);
			dump(h);
		}

/*		
		TreeSet<Integer> set = new TreeSet<Integer>();
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < 1000; i++)
			set.add(i);
		long se = System.currentTimeMillis();
		Heap<Integer> heap= new Heap<Integer>();
		long hs = System.currentTimeMillis();
		for ( int i = 0 ; i < 1000; i++)
			heap.add(i);
		long he = System.currentTimeMillis();
		dump(heap);
		System.err.println(se - ss);
		System.err.println(he - hs);
*/
	}
	static Map<Integer,String> result = new TreeMap<Integer, String>();
	public static void dump (Heap h) {
		result.clear();
		dump(h.root,0);
		System.out.println("-------------------------------------------");
		for ( Map.Entry<Integer,String> p : result.entrySet() ){
			System.out.println(p.getValue());
		}
	}
	public static int dump (HeapNode h , int nest) {
		String line = "";
		if ( ! h.red ) {
			nest++;
			if ( h.left != null && h.left.red) {
				line += h.left.t.toString();
			}
			line += h.t.toString();
			if ( h.right != null && h.right.red) {
				line += h.right.t.toString();
			}
		}
		int ret = 0;
		if ( h.left != null ) {
			ret = dump(h.left,nest);
		}
		if ( h.right != null ) {
			ret += dump(h.right,nest);
		}
		if ( h.red ) {
			return ret;
		}
		if ( !result.containsKey(nest) ) {
			result.put(nest, new String());
		}
		String str = result.get(nest);
		char[] pad = new char[ret];
		Arrays.fill(pad,' ');
		str += String.format("%s * %s  ",new String(pad),line);
		result.remove(nest);
		result.put(nest, str);
		return ret + line.length()-1;

	}
}
