package jp.co.rakuten.util.tree;

import java.util.TreeSet;

import junit.framework.TestCase;

public class AvlTreeParformanceTest extends TestCase {
	Integer[] datas = new  Integer[100000];
	AvlTree<Integer> tree = new AvlTree<Integer>(); 
	TreeSet<Integer> set = new TreeSet<Integer>();
	@Override
	protected void tearDown() throws Exception {
		tree.clear();
		set.clear();
		super.tearDown();
	}
	@Override
	protected void setUp() throws Exception {
		// Generate data.
		for ( int i = 0 ; i < 100000 ; i++)
			datas[i] = new Integer(i);
		//
		for ( int i = 0 ; i < datas.length; i++) {
			set.add(datas[i]);
			tree.insert(datas[i]);
		}
		super.setUp();
	}
	public void testAdd() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			set.add(datas[i]);
		}
		long se = System.currentTimeMillis();

		
		long ts = System.currentTimeMillis();
		AvlTree<Integer> tree = new AvlTree<Integer>(); 
		for ( int i = 0 ; i < datas.length; i++) {
			tree.insert(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  add: " + (se - ss));
		System.out.println("Tree add: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	public void testFind() {
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			if ( set.contains(datas[i]))
				set.ceiling(datas[i]);
		}
		long se = System.currentTimeMillis();
		
		long ts = System.currentTimeMillis();
		AvlTree<Integer> tree = new AvlTree<Integer>(); 
		for ( int i = 0 ; i < datas.length; i++) {
			tree.find(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find: " + (se - ss));
		System.out.println("Tree find: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	public void testFindFirst() {
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			if ( set.contains(datas[i]))
				set.ceiling(datas[i]);
		}
		long se = System.currentTimeMillis();
		
		long ts = System.currentTimeMillis();
		AvlTree<Integer> tree = new AvlTree<Integer>(); 
		for ( int i = 0 ; i < datas.length; i++) {
			tree.findFirst(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find-frist: " + (se - ss));
		System.out.println("Tree find-frist: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	public void testFindLast() {
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			if ( set.contains(datas[i]))
				set.floor(datas[i]);
		}
		long se = System.currentTimeMillis();
		
		long ts = System.currentTimeMillis();
		AvlTree<Integer> tree = new AvlTree<Integer>(); 
		for ( int i = 0 ; i < datas.length; i++) {
			tree.findFirst(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find-last: " + (se - ss));
		System.out.println("Tree find-last: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	public void testFindHigher() {
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			set.higher(datas[i]);
		}
		long se = System.currentTimeMillis();
		
		long ts = System.currentTimeMillis();
		AvlTree<Integer> tree = new AvlTree<Integer>(); 
		for ( int i = 0 ; i < datas.length; i++) {
			tree.upperBound(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find-higher: " + (se - ss));
		System.out.println("Tree find-higher: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	public void testFindLower() {
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			set.lower(datas[i]);
		}
		long se = System.currentTimeMillis();
		
		long ts = System.currentTimeMillis();
		AvlTree<Integer> tree = new AvlTree<Integer>(); 
		for ( int i = 0 ; i < datas.length; i++) {
			tree.lowerBound(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find-lower: " + (se - ss));
		System.out.println("Tree find-lower: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	public void testErase() {
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			set.remove(datas[i]);
		}
		long se = System.currentTimeMillis();
		
		long ts = System.currentTimeMillis();
		AvlTree<Integer> tree = new AvlTree<Integer>(); 
		for ( int i = 0 ; i < datas.length; i++) {
			tree.erase(tree.find(datas[i]));
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find-lower: " + (se - ss));
		System.out.println("Tree find-lower: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
}
