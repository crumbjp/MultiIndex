package jp.co.rakuten.util.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

import junit.framework.TestCase;

class AvlTreeData <T extends Comparable<T>> implements Comparable<AvlTreeData<T>>{
	static Integer GenUnique = 0;
	public final T key;
	public final Integer unique;
	public AvlTreeData (T key) {
		this.key = key;
		this.unique = ++GenUnique;
	}
	@Override
	public int compareTo(AvlTreeData<T> o) {
		return this.key.compareTo(o.key);
	}
	public String toString(){
		return String.format("%s(%d)",key.toString(),unique);
	}
}
public class AvlTreeTest extends TestCase {
	AvlTree<AvlTreeData<String>> tree = new AvlTree<AvlTreeData<String>>(); 
	ArrayList<Integer> uniqueOrder = new ArrayList<Integer>();
	@Override
	protected void tearDown() throws Exception {
		tree.clear();
		AvlTreeData.GenUnique = 0;
		super.tearDown();
	}
	@Override
	protected void setUp() throws Exception {
		dump = false;
		tree.insert(new AvlTreeData<String>("W"));
		tree.insert(new AvlTreeData<String>("A"));
		tree.insert(new AvlTreeData<String>("K"));
		tree.insert(new AvlTreeData<String>("A"));
		tree.insert(new AvlTreeData<String>("M"));
		tree.insert(new AvlTreeData<String>("A"));
		tree.insert(new AvlTreeData<String>("T"));
		tree.insert(new AvlTreeData<String>("S"));
		tree.insert(new AvlTreeData<String>("U"));
		tree.insert(new AvlTreeData<String>("N"));
		tree.insert(new AvlTreeData<String>("A"));
		tree.insert(new AvlTreeData<String>("O"));
		tree.insert(new AvlTreeData<String>("K"));
		tree.insert(new AvlTreeData<String>("I"));
		tree.insert(new AvlTreeData<String>("Y"));
		tree.insert(new AvlTreeData<String>("M"));
		
		uniqueOrder.add(2);	// A
		uniqueOrder.add(4);	// A
		uniqueOrder.add(6);	// A
		uniqueOrder.add(11);	// A
		uniqueOrder.add(14);	// I
		uniqueOrder.add(3);	// K
		uniqueOrder.add(13);	// K
		uniqueOrder.add(5);	// M
		uniqueOrder.add(16);	// M
		uniqueOrder.add(10);	// N
		uniqueOrder.add(12);	// O
		uniqueOrder.add(8);	// S
		uniqueOrder.add(7);	// T
		uniqueOrder.add(9);	// U
		uniqueOrder.add(1);	// W
		uniqueOrder.add(15);	// Y
		
		super.setUp();
	}
	public void testIteratorForward() {
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( StdIterator<AvlTreeData<String>> it = tree.begin(); ! it.equals(tree.end());it.next()){
			System.out.println("IT :" + it.get().toString());
			assertEquals(exp.next(),it.get().unique );
		}
		assertFalse(exp.hasNext());
	}
	public void testIteratorBack() {
		Collections.reverse(uniqueOrder);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( StdIterator<AvlTreeData<String>> it = tree.last(); ! it.equals(tree.end());it.prev()){
			System.out.println("RIT :" + it.get().toString());
			assertEquals(exp.next(),it.get().unique );
		}
		assertFalse(exp.hasNext());
	}
	public void testFind() {
		System.out.println("FIND-L:" + tree.find(new AvlTreeData<String>("L")).isEnd());
		System.out.println("FIND-K:" + tree.find(new AvlTreeData<String>("K")).get().toString());
		System.out.println("FIND-A:" + tree.find(new AvlTreeData<String>("A")).get().toString());
		System.out.println("FIND-Y:" + tree.find(new AvlTreeData<String>("Y")).get().toString());
		System.out.println("FIND-M:" + tree.find(new AvlTreeData<String>("M")).get().toString());
		assertTrue(tree.find(new AvlTreeData<String>("L")).isEnd());
		assertEquals(new Integer(3),tree.find(new AvlTreeData<String>("K")).get().unique);
		assertEquals(new Integer(4),tree.find(new AvlTreeData<String>("A")).get().unique);
		assertEquals(new Integer(15),tree.find(new AvlTreeData<String>("Y")).get().unique);
		assertEquals(new Integer(5),tree.find(new AvlTreeData<String>("M")).get().unique);
	}
	public void testFindFirst() {
		System.out.println("FIND-FIRST-L:" + tree.findFirst(new AvlTreeData<String>("L")).isEnd());
		System.out.println("FIND-FIRST-K:" + tree.findFirst(new AvlTreeData<String>("K")).get().toString());
		System.out.println("FIND-FIRST-A:" + tree.findFirst(new AvlTreeData<String>("A")).get().toString());
		System.out.println("FIND-FIRST-Y:" + tree.findFirst(new AvlTreeData<String>("Y")).get().toString());
		System.out.println("FIND-FIRST-M:" + tree.findFirst(new AvlTreeData<String>("M")).get().toString());
		assertTrue( tree.find(new AvlTreeData<String>("L")).isEnd());
		assertEquals(new Integer(3),tree.findFirst(new AvlTreeData<String>("K")).get().unique);
		assertEquals(new Integer(2),tree.findFirst(new AvlTreeData<String>("A")).get().unique);
		assertEquals(new Integer(15),tree.findFirst(new AvlTreeData<String>("Y")).get().unique);
		assertEquals(new Integer(5),tree.findFirst(new AvlTreeData<String>("M")).get().unique);
	}
	public void testFindLast() {
		System.out.println("FIND-LAST-L:" + tree.findLast(new AvlTreeData<String>("L")).isEnd());
		System.out.println("FIND-LAST-K:" + tree.findLast(new AvlTreeData<String>("K")).get().toString());
		System.out.println("FIND-LAST-A:" + tree.findLast(new AvlTreeData<String>("A")).get().toString());
		System.out.println("FIND-LAST-Y:" + tree.findLast(new AvlTreeData<String>("Y")).get().toString());
		System.out.println("FIND-LAST-M:" + tree.findLast(new AvlTreeData<String>("M")).get().toString());
		assertTrue(tree.find(new AvlTreeData<String>("L")).isEnd());
		assertEquals(new Integer(13),tree.findLast(new AvlTreeData<String>("K")).get().unique);
		assertEquals(new Integer(11),tree.findLast(new AvlTreeData<String>("A")).get().unique);
		assertEquals(new Integer(15),tree.findLast(new AvlTreeData<String>("Y")).get().unique);
		assertEquals(new Integer(16),tree.findLast(new AvlTreeData<String>("M")).get().unique);
	}
	public void testEqualRange() {
		{
			Pair<StdIterator<AvlTreeData<String>>,StdIterator<AvlTreeData<String>>> p = tree.equalRange(new AvlTreeData<String>("B"));
			assertTrue(p.first.isEnd());
			assertTrue(p.second.isEnd());
		}
		{
			Pair<StdIterator<AvlTreeData<String>>,StdIterator<AvlTreeData<String>>> p = tree.equalRange(new AvlTreeData<String>("A"));
			assertEquals(new Integer(2),p.first.get().unique);
			assertEquals(new Integer(14),p.second.get().unique); // I(14)
			for ( StdIterator<AvlTreeData<String>> it = p.first; ! it.equals(p.second);it.next()){
				System.out.println("RANGE-A:" + it.get().toString());
			}
		}
		{
			Pair<StdIterator<AvlTreeData<String>>,StdIterator<AvlTreeData<String>>> p = tree.equalRange(new AvlTreeData<String>("M"));
			assertEquals(new Integer(5),p.first.get().unique);
			assertEquals(new Integer(10),p.second.get().unique); // N(10)
			for ( StdIterator<AvlTreeData<String>> it = p.first; ! it.equals(p.second);it.next()){
				System.out.println("RANGE-M:" + it.get().toString());
			}
		}
		{
			Pair<StdIterator<AvlTreeData<String>>,StdIterator<AvlTreeData<String>>> p = tree.equalRange(new AvlTreeData<String>("K"));
			assertEquals(new Integer(3),p.first.get().unique);
			assertEquals(new Integer(5),p.second.get().unique); // M(5)
			for ( StdIterator<AvlTreeData<String>> it = p.first; ! it.equals(p.second);it.next()){
				System.out.println("RANGE-K:" + it.get().toString());
			}
		}
		{
			Pair<StdIterator<AvlTreeData<String>>,StdIterator<AvlTreeData<String>>> p = tree.equalRange(new AvlTreeData<String>("I"));
			assertEquals(new Integer(14),p.first.get().unique);
			assertEquals(new Integer(3),p.second.get().unique); // K(3)
			for ( StdIterator<AvlTreeData<String>> it = p.first; ! it.equals(p.second);it.next()){
				System.out.println("RANGE-I:" + it.get().toString());
			}
		}
	}
	public void testHigher() {
		System.out.println("FIND-UPPER-1:" + tree.upperBound(new AvlTreeData<String>("1")).get().toString());
		System.out.println("FIND-UPPER-A:" + tree.upperBound(new AvlTreeData<String>("A")).get().toString());
		System.out.println("FIND-UPPER-B:" + tree.upperBound(new AvlTreeData<String>("B")).get().toString());
		System.out.println("FIND-UPPER-K:" + tree.upperBound(new AvlTreeData<String>("K")).get().toString());
		System.out.println("FIND-UPPER-M:" + tree.upperBound(new AvlTreeData<String>("M")).get().toString());
		System.out.println("FIND-UPPER-P:" + tree.upperBound(new AvlTreeData<String>("P")).get().toString());
		System.out.println("FIND-UPPER-S:" + tree.upperBound(new AvlTreeData<String>("S")).get().toString());
		System.out.println("FIND-UPPER-Y:" + tree.upperBound(new AvlTreeData<String>("Y")).isEnd());
		System.out.println("FIND-UPPER-Z:" + tree.upperBound(new AvlTreeData<String>("Z")).isEnd());

		assertEquals(new Integer(2),tree.upperBound(new AvlTreeData<String>("1")).get().unique);
		assertEquals(new Integer(14),tree.upperBound(new AvlTreeData<String>("A")).get().unique);
		assertEquals(new Integer(14),tree.upperBound(new AvlTreeData<String>("B")).get().unique);
		assertEquals(new Integer(5),tree.upperBound(new AvlTreeData<String>("K")).get().unique);
		assertEquals(new Integer(10),tree.upperBound(new AvlTreeData<String>("M")).get().unique);
		assertEquals(new Integer(8),tree.upperBound(new AvlTreeData<String>("P")).get().unique);
		assertEquals(new Integer(7),tree.upperBound(new AvlTreeData<String>("S")).get().unique);
		assertTrue(tree.upperBound(new AvlTreeData<String>("Y")).isEnd());
		assertTrue(tree.upperBound(new AvlTreeData<String>("Z")).isEnd());

	}
	public void testLower() {
		System.out.println("FIND-LOWER-1:" + tree.lowerBound(new AvlTreeData<String>("1")).isEnd());
		System.out.println("FIND-LOWER-A:" + tree.lowerBound(new AvlTreeData<String>("A")).isEnd());
		System.out.println("FIND-LOWER-B:" + tree.lowerBound(new AvlTreeData<String>("B")).get().toString());
		System.out.println("FIND-LOWER-K:" + tree.lowerBound(new AvlTreeData<String>("K")).get().toString());
		System.out.println("FIND-LOWER-M:" + tree.lowerBound(new AvlTreeData<String>("M")).get().toString());
		System.out.println("FIND-LOWER-P:" + tree.lowerBound(new AvlTreeData<String>("P")).get().toString());
		System.out.println("FIND-LOWER-S:" + tree.lowerBound(new AvlTreeData<String>("S")).get().toString());
		System.out.println("FIND-LOWER-Y:" + tree.lowerBound(new AvlTreeData<String>("Y")).get().toString());
		System.out.println("FIND-LOWER-Z:" + tree.lowerBound(new AvlTreeData<String>("Z")).get().toString());
		
		assertTrue(tree.lowerBound(new AvlTreeData<String>("1")).isEnd());
		assertTrue(tree.lowerBound(new AvlTreeData<String>("A")).isEnd());
		assertEquals(new Integer(11),tree.lowerBound(new AvlTreeData<String>("B")).get().unique);
		assertEquals(new Integer(14),tree.lowerBound(new AvlTreeData<String>("K")).get().unique);
		assertEquals(new Integer(13),tree.lowerBound(new AvlTreeData<String>("M")).get().unique);
		assertEquals(new Integer(12),tree.lowerBound(new AvlTreeData<String>("P")).get().unique);
		assertEquals(new Integer(12),tree.lowerBound(new AvlTreeData<String>("S")).get().unique);
		assertEquals(new Integer(1),tree.lowerBound(new AvlTreeData<String>("Y")).get().unique);
		assertEquals(new Integer(15),tree.lowerBound(new AvlTreeData<String>("Z")).get().unique);
	}
	
	public void testErase1() {
		dump = true;
		dump(tree);
		tree.erase(tree.find(new AvlTreeData<String>("T")));
		dump(tree);
	}
	public void testErase2() {
		dump = true;
		dump(tree);
		tree.erase(tree.find(new AvlTreeData<String>("K")));
		dump(tree);
	}
	public void testErase3() {
		dump = true;
		dump(tree);
		tree.erase(tree.find(new AvlTreeData<String>("O")));
		dump(tree);
	}

	public static boolean dump = false;
	public static void dump (AvlTree h) {
		System.out.println("-------------------------------------------");
		dump(h.avl,1);
		System.out.println("");
	}
	public static void dump (AvlNode h , int n ) {
		if ( dump ) {
			String line = h.t!=null?h.t.toString():"[ROOT]";
			char[] pad = new char[n];
			Arrays.fill(pad,' ');
			int d = 0;
			if ( h.parent != null ) {
				if ( h.parent.right == h ) 
					d = h.parent.nright;
				else
					d = h.parent.nleft;
			}
			String log = String.format("%d - %s ",d,line);
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

}
