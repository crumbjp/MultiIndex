package jp.co.rakuten.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import jp.co.rakuten.util.tree.Pair;
import junit.framework.TestCase;

class MultiMapData implements Comparable<MultiMapData>{
	static Integer GenUnique = 0;
	public final Integer key;
	public final Integer unique;
	public MultiMapData(Integer key) {
		this.key = key;
		this.unique = ++GenUnique;
	}
	@Override
	public int compareTo(MultiMapData o) {
		return this.key.compareTo(o.key);
	}
	public void dump(){
		System.out.println(String.format("Key : %d , Unique : %d",key,unique));
	}
}
public class MultiMapTest extends TestCase {
	MultiMap<Integer,MultiMapData> mm = new MultiTreeMap<Integer,MultiMapData>();
	ArrayList<Integer> uniqueOrder = new ArrayList<Integer>();
	@Override
	protected void tearDown() throws Exception {
		mm.clear();
		MultiMapData.GenUnique = 0;
		super.tearDown();
	}
	@Override
	protected void setUp() throws Exception {
		mm.put(3,new MultiMapData(3)); // Unique = 1
		mm.put(1,new MultiMapData(1)); // Unique = 2
		mm.put(2,new MultiMapData(2)); // Unique = 3
		mm.put(5,new MultiMapData(5)); // Unique = 4
		mm.put(4,new MultiMapData(4)); // Unique = 5
		mm.put(3,new MultiMapData(3)); // Unique = 6
		uniqueOrder.add(2);
		uniqueOrder.add(3);
		uniqueOrder.add(1);
		uniqueOrder.add(6);
		uniqueOrder.add(5);
		uniqueOrder.add(4);
		
		super.setUp();
	}
	public void testIterator() {
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testDescendingIterator() {
		Collections.reverse(uniqueOrder);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : new Iterable<ComparablePair<Integer,MultiMapData>>(){
			@Override
			public Iterator<ComparablePair<Integer,MultiMapData>> iterator() {
				return mm.descendingIterator();
			}
		}) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}

	public void testClear() {
		mm.clear();
		for ( Pair<Integer,MultiMapData> p : mm ) {
			assertTrue("Should be empty",false);
		}
		assertTrue(mm.isEmpty());
		assertEquals(0, mm.size());
	}

	public void testContains() {
		assertTrue(mm.contains(1));
		assertTrue(mm.contains(2));
		assertTrue(mm.contains(3));
		assertTrue(mm.contains(4));
		assertTrue(mm.contains(5));
		assertFalse(mm.contains(0));
		assertFalse(mm.contains(6));
		ArrayList<ComparablePair<Integer,MultiMapData>> cs= new ArrayList<ComparablePair<Integer,MultiMapData>>();
		cs.add(new ComparablePair<Integer,MultiMapData>(1,new MultiMapData(1)));
		cs.add(new ComparablePair<Integer,MultiMapData>(2,new MultiMapData(2)));
		cs.add(new ComparablePair<Integer,MultiMapData>(3,new MultiMapData(3)));
		cs.add(new ComparablePair<Integer,MultiMapData>(4,new MultiMapData(4)));
		cs.add(new ComparablePair<Integer,MultiMapData>(5,new MultiMapData(5)));
		assertTrue(mm.containsAll(cs));
		cs.add(new ComparablePair<Integer,MultiMapData>(6,new MultiMapData(6)));
		assertFalse(mm.containsAll(cs));
	}

	public void testAddAll() {
		ArrayList<ComparablePair<Integer,MultiMapData>> cs= new ArrayList<ComparablePair<Integer,MultiMapData>>();
		cs.add(new ComparablePair<Integer,MultiMapData>(2,new MultiMapData(2))); // Unique = 7
		cs.add(new ComparablePair<Integer,MultiMapData>(3,new MultiMapData(3))); // Unique = 8
		cs.add(new ComparablePair<Integer,MultiMapData>(4,new MultiMapData(4))); // Unique = 9
		cs.add(new ComparablePair<Integer,MultiMapData>(5,new MultiMapData(5))); // Unique = 10
		cs.add(new ComparablePair<Integer,MultiMapData>(6,new MultiMapData(6))); // Unique = 11
		mm.putAll(cs);
		uniqueOrder.add(2,7);
		uniqueOrder.add(5,8);
		uniqueOrder.add(7,9);
		uniqueOrder.add(9,10);
		uniqueOrder.add(10,11);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}

	public void testPollFirst1() {
		mm.put(0,new MultiMapData(0)); // Unique = 7
		assertEquals(new Integer(7), mm.pollFirst().unique);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testPollFirst2() {
		mm.put(1,new MultiMapData(1)); // Unique = 7
		uniqueOrder.set(0, 7);
		assertEquals(new Integer(2), mm.pollFirst().unique);
		
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testPollLast1() {
		mm.put(6,new MultiMapData(6)); // Unique = 7
		assertEquals(new Integer(7), mm.pollLast().unique);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testPollLast2() {
		mm.put(5,new MultiMapData(5)); // Unique = 7
		assertEquals(new Integer(7), mm.pollLast().unique);
		
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	
	public void testFirst() {
		assertEquals(new Integer(2), mm.first().unique);
	}

	public void testLast() {
		assertEquals(new Integer(4), mm.last().unique);
	}

	public void testCeiling() {
		assertEquals(new Integer(1), mm.ceiling(3).unique);
		assertEquals(new Integer(5), mm.ceiling(4).unique);
	}

	public void testFloor() {
		assertEquals(new Integer(6), mm.floor(3).unique);
		assertEquals(new Integer(5), mm.floor(4).unique);
	}

	public void testHigher() {
		assertEquals(new Integer(5), mm.higher(3).unique);
		assertEquals(new Integer(1), mm.higher(2).unique);
		assertNull(mm.higher(5));
	}

	public void testLower() {
		assertEquals(new Integer(6), mm.lower(4).unique);
		assertEquals(new Integer(3), mm.lower(3).unique);
		assertNull(mm.lower(1));
	}
	public void testRemove() {
		Iterator<ComparablePair<Integer,MultiMapData>> it = mm.iterator();
		it.next();
		mm.remove(it);
		uniqueOrder.remove(0);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testRemove1() {
		Iterator<ComparablePair<Integer,MultiMapData>> it = mm.iterator();
		it.next();
		it.next();
		it.remove(); // 2 - 3
		it.next();
		it.remove(); // 3 - 1
		it.next();
		it.remove(); // 3 - 6
		it.next();
		it.next();
		it.remove(); // 5 - 4
		uniqueOrder.clear();
		uniqueOrder.add(new Integer(2));
		uniqueOrder.add(new Integer(5));
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testRemove2() {
		Iterator<ComparablePair<Integer,MultiMapData>> it = mm.descendingIterator();
		it.next();
		it.next();
		it.remove(); // 4 - 5
		it.next();
		it.remove(); // 3 - 6
		it.next();
		it.remove(); // 3 - 1
		it.next();
		it.next();
		it.remove(); // 1 - 2
		uniqueOrder.clear();
		uniqueOrder.add(new Integer(4));
		uniqueOrder.add(new Integer(3));
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : new Iterable<ComparablePair<Integer,MultiMapData>>() {
			public Iterator<ComparablePair<Integer,MultiMapData>> iterator() {
				return mm.descendingIterator();
			}
		}) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	
	public void testRemove3() {
		Iterator<ComparablePair<Integer,MultiMapData>> it = mm.iterator();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		assertTrue(mm.isEmpty());
	}
	public void testRemove4() {
		Iterator<ComparablePair<Integer,MultiMapData>> it = mm.descendingIterator();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		assertTrue(mm.isEmpty());
	}
	public void testRemove5() {
		Iterator<ComparablePair<Integer,MultiMapData>> it = mm.equalRange(3);
		it.next();
		mm.remove(new Integer(3), it.next().second, new Comparator<MultiMapData>() {
			public int compare(MultiMapData o1, MultiMapData o2) {
				return o1.unique - o2.unique;
			}
		});
		uniqueOrder.remove(3);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testRemoveAll() {
		assertEquals(6, mm.size());
		assertTrue(mm.removeAll(3));
		assertEquals(4, mm.size());
		assertTrue(mm.removeAll(2));
		assertEquals(3,mm.size());
		assertFalse(mm.removeAll(2));
		assertEquals(3,mm.size());

		uniqueOrder.remove(2);
		uniqueOrder.remove(2);
		uniqueOrder.remove(1);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testEqualRange1() {
		uniqueOrder.clear();
		uniqueOrder.add(1);
		uniqueOrder.add(6);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : new Iterable<ComparablePair<Integer,MultiMapData>>() {
			public Iterator<ComparablePair<Integer,MultiMapData>> iterator() {
				return mm.equalRange(3);
			}
		}) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testEqualRange2() {
		uniqueOrder.clear();
		uniqueOrder.add(3);
		uniqueOrder.add(1);
		uniqueOrder.add(6);
		uniqueOrder.add(5);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiMapData> p : new Iterable<ComparablePair<Integer,MultiMapData>>() {
			public Iterator<ComparablePair<Integer,MultiMapData>> iterator() {
				return mm.equalRange(2,true,4,true);
			}
		}) {
				assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
}
