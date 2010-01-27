package jp.co.rakuten.util.collection.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

import jp.co.rakuten.util.collection.tree.AvlTree;
import jp.co.rakuten.util.collection.tree.AvlIterator;
import junit.framework.TestCase;

//-agentlib:hprof=cpu=times,heap=sites

//-XX:+PrintCompilation -XX:+CITime -XX:+UseOnStackReplacement
//-hotspot -XX:CompileThreshold=1 -Xms256M -Xmx256M -Xbatch

public class AvlTreeParformanceTest extends TestCase {
	
	static final int N = 100000;
	Integer[] datas = new  Integer[N];

	AvlTree<Integer,Integer> tree = new AvlTree<Integer,Integer>(
			new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					return o1.compareTo(o2);
				};
			},
			new FindComparator<Integer,Integer>() {
				public int compare(Integer o1, Integer o2) {
					return o1.compareTo(o2);
				};
			});

	TreeSet<Integer> set = new TreeSet<Integer>();
	@Override
	protected void tearDown() throws Exception {
//*
		tree.begin();
		tree.last();
		tree.end();
		tree.find(1);
		tree.findFirst(1);
		tree.findLast(1);
		tree.upperBound(1);
		tree.lowerBound(1);
		tree.equalRange(1);
		tree.erase(tree.find(1));
//*/
		tree.clear();
		set.clear();
		super.tearDown();
	}
	@Override
	protected void setUp() throws Exception {
		// Generate data.
		for ( int i = 0 ; i < N ; i++)
			datas[i] = new Integer(i);
		Collections.shuffle(Arrays.asList(datas));
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
		AvlTree<Integer,Integer> tree = new AvlTree<Integer,Integer>(); 
		long ts = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			tree.insert(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  add: " + (se - ss));
		System.out.println("Tree add: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	public void testIterate() {
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		long ss = System.currentTimeMillis();
		for ( Integer i : set ){
			int I = i.intValue();
		}
		long se = System.currentTimeMillis();
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		long ts = System.currentTimeMillis();
		for ( 	AvlIterator<Integer> it = tree.begin(),itend = tree.end();
			! it.equals(itend);
			it.next() ){
			int I = it.get().intValue();
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  it: " + (se - ss));
		System.out.println("Tree it: " + (te - ts));
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

//		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
//		long t2s = System.currentTimeMillis();
//		for ( int i = 0 ; i < datas.length; i++) {
//			tree.find2(datas[i]);
//		}
//		long t2e = System.currentTimeMillis();

		System.out.println("Set  find: " + (se - ss));
		System.out.println("Tree find: " + (te - ts));
//		System.out.println("Tree find2: " + (t2e - t2s));
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
			tree.findLast(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find-last: " + (se - ss));
		System.out.println("Tree find-last: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
	
	public void testEqualRange() {
		java.lang.management.ManagementFactory.getMemoryMXBean().gc();
		long ts = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			tree.equalRange(datas[i]);
		}
		long te = System.currentTimeMillis();

		System.out.println("Tree equalRange: " + (te - ts));
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
		
		AvlTreeTest.dump = true;
		long ts = System.currentTimeMillis();
		for ( int i = 0 ; i < datas.length; i++) {
			AvlIterator<Integer> it = tree.find(datas[i]);
//			AvlTreeTest.dump(tree);
//			System.out.println("****" + i + " : "+ datas[i]);
			tree.erase(it);
		}
		long te = System.currentTimeMillis();

		System.out.println("Set  find-erase: " + (se - ss));
		System.out.println("Tree find-erase: " + (te - ts));
		assertTrue("Set win !" , (se-ss) > (te-ts));
	}
}
