package jp.co.rakuten.util.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

import junit.framework.TestCase;

public class AvlTreeParformanceTest extends TestCase {
	
	static final int N = 200000;
	Integer[] datas = new  Integer[N];

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
		for ( int i = 0 ; i < N ; i++)
			datas[i] = new Integer(i);
//		Collections.shuffle(Arrays.asList(datas));
		//
		for ( int i = 0 ; i < datas.length; i++) {
			set.add(datas[i]);
			tree.insert(datas[i]);
		}
		super.setUp();
	}
	public void testAdd() {
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		TreeSet<Integer> set = new TreeSet<Integer>();
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			set.add(datas[i]);
		}
		long se = System.currentTimeMillis();

		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		AvlTree<Integer> tree = new AvlTree<Integer>(); 
		long ts = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			tree.insert(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  add: " + (se - ss));
		System.out.println("Tree add: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	public void testFind() {
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			if ( set.contains(datas[i]))
				set.ceiling(datas[i]);
		}
		long se = System.currentTimeMillis();
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		long ts = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			tree.find(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find: " + (se - ss));
		System.out.println("Tree find: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	public void testFindFirst() {
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			if ( set.contains(datas[i]))
				set.ceiling(datas[i]);
		}
		long se = System.currentTimeMillis();
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		long ts = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			tree.findFirst(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find-frist: " + (se - ss));
		System.out.println("Tree find-frist: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	public void testFindLast() {
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			if ( set.contains(datas[i]))
				set.floor(datas[i]);
		}
		long se = System.currentTimeMillis();
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		
		long ts = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			tree.findFirst(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find-last: " + (se - ss));
		System.out.println("Tree find-last: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	public void testFindHigher() {
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			set.higher(datas[i]);
		}
		long se = System.currentTimeMillis();
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		
		long ts = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			tree.upperBound(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find-higher: " + (se - ss));
		System.out.println("Tree find-higher: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	public void testFindLower() {
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			set.lower(datas[i]);
		}
		long se = System.currentTimeMillis();
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		
		long ts = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			tree.lowerBound(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find-lower: " + (se - ss));
		System.out.println("Tree find-lower: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	public void testErase() {
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		long ss = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			set.remove(datas[i]);
		}
		long se = System.currentTimeMillis();
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		
		long ts = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			tree.erase(tree.find(datas[i]));
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find-erase: " + (se - ss));
		System.out.println("Tree find-erase: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
}
