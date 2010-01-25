package jp.co.rakuten.util.multiindex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

import jp.co.rakuten.util.tree.Pair;
import junit.framework.TestCase;

class MultiIndexData implements Comparable<MultiIndexData> {
	static Integer GenUnique = 0;
	public final Integer i;
	public final Long l;
	public final String s;
	public final Integer unique;
	public MultiIndexData(final Integer i ,final Long l, final String s) {
		this.i = i;
		this.l = l;
		this.s = s;
		this.unique = ++GenUnique;
	}
	public void dump(){
		System.out.println(String.format("I:%d , L:%d , S:%s Unique : %d", i,l,s,unique));
	}
	@Override
	public boolean equals (Object d) {
		return i.equals(((MultiIndexData)d).i);
	}
	@Override
	public int compareTo(MultiIndexData d) {
		return i.compareTo(d.i);
	}
}
public class MultiIndexTest extends TestCase {
	MultiIndex<MultiIndexData> mi;
	SequenceIndex<MultiIndexData> sequence;
	OrderedUniqueIndex<Integer, MultiIndexData> orderedUnique;	
	HashedUniqueIndex<Long, MultiIndexData> hashedUnique;	
	IdentityIndex<MultiIndexData> identity;
	OrderedNonUniqueIndex<String, MultiIndexData> orderedNonUnique;	

	ArrayList<Integer> uniqueSequenceOrder = new ArrayList<Integer>();
	ArrayList<Integer> uniqueOrderedUniqueOrder = new ArrayList<Integer>();
	ArrayList<Integer> uniqueHashedUniqueOrder = new ArrayList<Integer>();
	ArrayList<Integer> uniqueIdentityOrder = new ArrayList<Integer>();
	ArrayList<Integer> uniqueOrderedNonUniqueOrder = new ArrayList<Integer>();
	@Override
	protected void tearDown() throws Exception {
		mi.clear();
		MultiIndexData.GenUnique = 0;
		super.tearDown();
	}
	@Override
	protected void setUp() throws Exception {
		mi = new MultiIndex<MultiIndexData>(
				new IndexBy<MultiIndexData>(
						new SequenceIndex<MultiIndexData>(),
						new OrderedUniqueIndex<Integer, MultiIndexData>(MultiIndexData.class.getField("i")),
						new HashedUniqueIndex<Long, MultiIndexData>(MultiIndexData.class.getField("l")),
						new IdentityIndex<MultiIndexData>() ,
						new OrderedNonUniqueIndex<String,MultiIndexData>(MultiIndexData.class.getField("s"))
						)
				);
		sequence = (SequenceIndex<MultiIndexData>)mi.index(0);
		orderedUnique = (OrderedUniqueIndex<Integer, MultiIndexData>)mi.index(1);
		hashedUnique  = (HashedUniqueIndex<Long, MultiIndexData>)mi.index(2);
		identity  = (IdentityIndex<MultiIndexData>)mi.index(3);
		orderedNonUnique = (OrderedNonUniqueIndex<String, MultiIndexData>)mi.index(4);
		
		mi.add(new MultiIndexData(6,2l,"abc")); // Unique = 1
		mi.add(new MultiIndexData(5,1l,"efg")); // Unique = 2
		mi.add(new MultiIndexData(4,3l,"hij")); // Unique = 3
		mi.add(new MultiIndexData(3,5l,"klm")); // Unique = 4
		mi.add(new MultiIndexData(2,6l,"nop")); // Unique = 5
		mi.add(new MultiIndexData(1,4l,"abc")); // Unique = 6

		uniqueSequenceOrder.add(1);
		uniqueSequenceOrder.add(2);
		uniqueSequenceOrder.add(3);
		uniqueSequenceOrder.add(4);
		uniqueSequenceOrder.add(5);
		uniqueSequenceOrder.add(6);

		uniqueIdentityOrder.add(6);
		uniqueIdentityOrder.add(5);
		uniqueIdentityOrder.add(4);
		uniqueIdentityOrder.add(3);
		uniqueIdentityOrder.add(2);
		uniqueIdentityOrder.add(1);
		
		uniqueOrderedUniqueOrder.add(6);
		uniqueOrderedUniqueOrder.add(5);
		uniqueOrderedUniqueOrder.add(4);
		uniqueOrderedUniqueOrder.add(3);
		uniqueOrderedUniqueOrder.add(2);
		uniqueOrderedUniqueOrder.add(1);
		
		uniqueHashedUniqueOrder.add(2);
		uniqueHashedUniqueOrder.add(1);
		uniqueHashedUniqueOrder.add(3);
		uniqueHashedUniqueOrder.add(6);
		uniqueHashedUniqueOrder.add(4);
		uniqueHashedUniqueOrder.add(5);
		
		uniqueOrderedNonUniqueOrder.add(1);
		uniqueOrderedNonUniqueOrder.add(6);
		uniqueOrderedNonUniqueOrder.add(2);
		uniqueOrderedNonUniqueOrder.add(3);
		uniqueOrderedNonUniqueOrder.add(4);
		uniqueOrderedNonUniqueOrder.add(5);
		
		super.setUp();
	}
	public void testSafeAdd() {
		assertFalse(mi.safeAdd(new MultiIndexData(0,4l,"abc"))); // Unique = 7
		assertFalse(mi.safeAdd(new MultiIndexData(1,7l,"abc"))); // Unique = 8
		assertTrue (mi.safeAdd(new MultiIndexData(0,7l,"abc"))); // Unique = 9
		
		uniqueSequenceOrder.add(9);
		uniqueIdentityOrder.add(0,9);
		uniqueOrderedUniqueOrder.add(0,9);
		uniqueHashedUniqueOrder.add(9);
		uniqueOrderedNonUniqueOrder.add(2,9);
		
		Iterator<Integer> sexp = uniqueSequenceOrder.iterator();
		for ( Container<MultiIndexData> c : sequence ) {
			assertEquals(sexp.next(), c.pair.second.unique);
		}
		assertFalse(sexp.hasNext());
		
		Iterator<Integer> iexp = uniqueIdentityOrder.iterator();
		for ( Container<MultiIndexData> c : identity ) {
			assertEquals(iexp.next(), c.pair.second.unique);
		}
		assertFalse(iexp.hasNext());
	
		Iterator<Integer> oexp = uniqueOrderedUniqueOrder.iterator();
		for ( Container<MultiIndexData> c : orderedUnique ) {
			assertEquals(oexp.next(), c.pair.second.unique);
		}
		assertFalse(oexp.hasNext());
		
		Iterator<Integer> hexp = uniqueHashedUniqueOrder.iterator();
		for ( Container<MultiIndexData> c : hashedUnique) {
			assertEquals(hexp.next(), c.pair.second.unique);
		}
		assertFalse(hexp.hasNext());

		Iterator<Integer> nexp = uniqueOrderedNonUniqueOrder.iterator();
		for ( Pair<String,Container<MultiIndexData>> p : orderedNonUnique ) {
			assertEquals(nexp.next(), p.second.pair.second.unique);
		}
		assertFalse(nexp.hasNext());
		
	}
	public void testSafeModify() {
		Container<MultiIndexData> d = sequence.get(1); // 5 - 1 - efg
		assertFalse(mi.safeModify(d, new MultiIndexData(0,4l,"abc"))); // Unique = 7
		assertFalse(mi.safeModify(d, new MultiIndexData(1,7l,"abc"))); // Unique = 8
		assertTrue(mi.safeModify(d, new MultiIndexData(0,7l,"abc")));  // Unique = 9
		assertTrue(mi.safeModify(d, new MultiIndexData(5,7l,"abc")));  // Unique = 10
		assertTrue(mi.safeModify(d, new MultiIndexData(5,1l,"efg")));  // Unique = 11

		uniqueSequenceOrder.set(1,11);
		uniqueIdentityOrder.set(4,11);
		uniqueOrderedUniqueOrder.set(4,11);
		uniqueHashedUniqueOrder.set(0,11);
		uniqueOrderedNonUniqueOrder.set(2,11);
	
		Iterator<Integer> sexp = uniqueSequenceOrder.iterator();
		for ( Container<MultiIndexData> c : sequence ) {
			assertEquals(sexp.next(), c.pair.second.unique);
		}
		assertFalse(sexp.hasNext());
		
		Iterator<Integer> iexp = uniqueIdentityOrder.iterator();
		for ( Container<MultiIndexData> c : identity ) {
			assertEquals(iexp.next(), c.pair.second.unique);
		}
		assertFalse(iexp.hasNext());
	
		Iterator<Integer> oexp = uniqueOrderedUniqueOrder.iterator();
		for ( Container<MultiIndexData> c : orderedUnique ) {
			assertEquals(oexp.next(), c.pair.second.unique);
		}
		assertFalse(oexp.hasNext());
		
		Iterator<Integer> hexp = uniqueHashedUniqueOrder.iterator();
		for ( Container<MultiIndexData> c : hashedUnique) {
			assertEquals(hexp.next(), c.pair.second.unique);
		}
		assertFalse(hexp.hasNext());

		Iterator<Integer> nexp = uniqueOrderedNonUniqueOrder.iterator();
		for ( Pair<String,Container<MultiIndexData>> p : orderedNonUnique ) {
			assertEquals(nexp.next(), p.second.pair.second.unique);
		}
		assertFalse(nexp.hasNext());
	}
	public void testRemove() {
//		mi.add(new MultiIndexData(6,2l,"abc")); // Unique = 1 x
//		mi.add(new MultiIndexData(5,1l,"efg")); // Unique = 2
//		mi.add(new MultiIndexData(4,3l,"hij")); // Unique = 3 
//		mi.add(new MultiIndexData(3,5l,"klm")); // Unique = 4 x
//		mi.add(new MultiIndexData(2,6l,"nop")); // Unique = 5
//		mi.add(new MultiIndexData(1,4l,"abc")); // Unique = 6 x
		Container<MultiIndexData> d1 = orderedUnique.get(6); // 6 - 2 - abc
		mi.remove(d1);
		Container<MultiIndexData> d2 = orderedUnique.get(1); // 1 - 4 - abc
		mi.remove(d2);
		Container<MultiIndexData> d3 = orderedUnique.get(3); // 3 - 5 - klm
		mi.remove(d3);

		uniqueSequenceOrder.remove(5);
		uniqueSequenceOrder.remove(3);
		uniqueSequenceOrder.remove(0);
		uniqueIdentityOrder.remove(5);
		uniqueIdentityOrder.remove(2);
		uniqueIdentityOrder.remove(0);
		uniqueOrderedUniqueOrder.remove(5);
		uniqueOrderedUniqueOrder.remove(2);
		uniqueOrderedUniqueOrder.remove(0);
		uniqueHashedUniqueOrder.remove(4);
		uniqueHashedUniqueOrder.remove(3);
		uniqueHashedUniqueOrder.remove(1);
		uniqueOrderedNonUniqueOrder.remove(4);
		uniqueOrderedNonUniqueOrder.remove(1);
		uniqueOrderedNonUniqueOrder.remove(0);
	
		Iterator<Integer> sexp = uniqueSequenceOrder.iterator();
		for ( Container<MultiIndexData> c : sequence ) {
			assertEquals(sexp.next(), c.pair.second.unique);
		}
		assertFalse(sexp.hasNext());
		
		Iterator<Integer> iexp = uniqueIdentityOrder.iterator();
		for ( Container<MultiIndexData> c : identity ) {
			assertEquals(iexp.next(), c.pair.second.unique);
		}
		assertFalse(iexp.hasNext());
	
		Iterator<Integer> oexp = uniqueOrderedUniqueOrder.iterator();
		for ( Container<MultiIndexData> c : orderedUnique ) {
			assertEquals(oexp.next(), c.pair.second.unique);
		}
		assertFalse(oexp.hasNext());
		
		Iterator<Integer> hexp = uniqueHashedUniqueOrder.iterator();
		for ( Container<MultiIndexData> c : hashedUnique) {
			assertEquals(hexp.next(), c.pair.second.unique);
		}
		assertFalse(hexp.hasNext());

		Iterator<Integer> nexp = uniqueOrderedNonUniqueOrder.iterator();
		for ( Pair<String,Container<MultiIndexData>> p : orderedNonUnique ) {
			assertEquals(nexp.next(), p.second.pair.second.unique);
		}
		assertFalse(nexp.hasNext());
	}
/*	
	public void testIterator() {
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiIndexData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testDescendingIterator() {
		Collections.reverse(uniqueOrder);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiIndexData> p : new Iterable<ComparablePair<Integer,MultiIndexData>>(){
			@Override
			public Iterator<ComparablePair<Integer,MultiIndexData>> iterator() {
				return mi.descendingIterator();
			}
		}) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}

	public void testClear() {
		mi.clear();
		for ( Pair<Integer,MultiIndexData> p : mm ) {
			assertTrue("Should be empty",false);
		}
		assertTrue(mi.isEmpty());
		assertEquals(0, mi.size());
	}

	public void testContains() {
		assertTrue(mi.contains(1));
		assertTrue(mi.contains(2));
		assertTrue(mi.contains(3));
		assertTrue(mi.contains(4));
		assertTrue(mi.contains(5));
		assertFalse(mi.contains(0));
		assertFalse(mi.contains(6));
		ArrayList<ComparablePair<Integer,MultiIndexData>> cs= new ArrayList<ComparablePair<Integer,MultiIndexData>>();
		cs.add(new ComparablePair<Integer,MultiIndexData>(1,new MultiIndexData(1)));
		cs.add(new ComparablePair<Integer,MultiIndexData>(2,new MultiIndexData(2)));
		cs.add(new ComparablePair<Integer,MultiIndexData>(3,new MultiIndexData(3)));
		cs.add(new ComparablePair<Integer,MultiIndexData>(4,new MultiIndexData(4)));
		cs.add(new ComparablePair<Integer,MultiIndexData>(5,new MultiIndexData(5)));
		assertTrue(mi.containsAll(cs));
		cs.add(new ComparablePair<Integer,MultiIndexData>(6,new MultiIndexData(6)));
		assertFalse(mi.containsAll(cs));
	}

	public void testAddAll() {
		ArrayList<ComparablePair<Integer,MultiIndexData>> cs= new ArrayList<ComparablePair<Integer,MultiIndexData>>();
		cs.add(new ComparablePair<Integer,MultiIndexData>(2,new MultiIndexData(2))); // Unique = 7
		cs.add(new ComparablePair<Integer,MultiIndexData>(3,new MultiIndexData(3))); // Unique = 8
		cs.add(new ComparablePair<Integer,MultiIndexData>(4,new MultiIndexData(4))); // Unique = 9
		cs.add(new ComparablePair<Integer,MultiIndexData>(5,new MultiIndexData(5))); // Unique = 10
		cs.add(new ComparablePair<Integer,MultiIndexData>(6,new MultiIndexData(6))); // Unique = 11
		mi.putAll(cs);
		uniqueOrder.add(2,7);
		uniqueOrder.add(5,8);
		uniqueOrder.add(7,9);
		uniqueOrder.add(9,10);
		uniqueOrder.add(10,11);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiIndexData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}

	public void testPollFirst1() {
		mi.put(0,new MultiIndexData(0)); // Unique = 7
		assertEquals(new Integer(7), mi.pollFirst().unique);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiIndexData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testPollFirst2() {
		mi.put(1,new MultiIndexData(1)); // Unique = 7
		uniqueOrder.set(0, 7);
		assertEquals(new Integer(2), mi.pollFirst().unique);
		
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiIndexData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testPollLast1() {
		mi.put(6,new MultiIndexData(6)); // Unique = 7
		assertEquals(new Integer(7), mi.pollLast().unique);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiIndexData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testPollLast2() {
		mi.put(5,new MultiIndexData(5)); // Unique = 7
		assertEquals(new Integer(7), mi.pollLast().unique);
		
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiIndexData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	
	public void testFirst() {
		assertEquals(new Integer(2), mi.first().unique);
	}

	public void testLast() {
		assertEquals(new Integer(4), mi.last().unique);
	}

	public void testCeiling() {
		assertEquals(new Integer(1), mi.ceiling(3).unique);
		assertEquals(new Integer(5), mi.ceiling(4).unique);
	}

	public void testFloor() {
		assertEquals(new Integer(6), mi.floor(3).unique);
		assertEquals(new Integer(5), mi.floor(4).unique);
	}

	public void testHigher() {
		assertEquals(new Integer(5), mi.higher(3).unique);
		assertEquals(new Integer(1), mi.higher(2).unique);
		assertNull(mi.higher(5));
	}

	public void testLower() {
		assertEquals(new Integer(6), mi.lower(4).unique);
		assertEquals(new Integer(3), mi.lower(3).unique);
		assertNull(mi.lower(1));
	}
	public void testRemove() {
		Iterator<ComparablePair<Integer,MultiIndexData>> it = mi.iterator();
		it.next();
		mi.remove(it);
		uniqueOrder.remove(0);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiIndexData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testRemove1() {
		Iterator<ComparablePair<Integer,MultiIndexData>> it = mi.iterator();
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
		for ( Pair<Integer,MultiIndexData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testRemove2() {
		Iterator<ComparablePair<Integer,MultiIndexData>> it = mi.descendingIterator();
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
		for ( Pair<Integer,MultiIndexData> p : new Iterable<ComparablePair<Integer,MultiIndexData>>() {
			public Iterator<ComparablePair<Integer,MultiIndexData>> iterator() {
				return mi.descendingIterator();
			}
		}) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	
	public void testRemove3() {
		Iterator<ComparablePair<Integer,MultiIndexData>> it = mi.iterator();
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
		assertTrue(mi.isEmpty());
	}
	public void testRemove4() {
		Iterator<ComparablePair<Integer,MultiIndexData>> it = mi.descendingIterator();
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
		assertTrue(mi.isEmpty());
	}
	public void testRemove5() {
		Iterator<ComparablePair<Integer,MultiIndexData>> it = mi.equalRange(3);
		it.next();
		mi.remove(new Integer(3), it.next().second, new Comparator<MultiIndexData>() {
			public int compare(MultiIndexData o1, MultiIndexData o2) {
				return o1.unique - o2.unique;
			}
		});
		uniqueOrder.remove(3);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiIndexData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testRemoveAll() {
		assertEquals(6, mi.size());
		assertTrue(mi.removeAll(3));
		assertEquals(4, mi.size());
		assertTrue(mi.removeAll(2));
		assertEquals(3,mi.size());
		assertFalse(mi.removeAll(2));
		assertEquals(3,mi.size());

		uniqueOrder.remove(2);
		uniqueOrder.remove(2);
		uniqueOrder.remove(1);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiIndexData> p : mm ) {
			assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
	public void testEqualRange1() {
		uniqueOrder.clear();
		uniqueOrder.add(1);
		uniqueOrder.add(6);
		Iterator<Integer> exp = uniqueOrder.iterator();
		for ( Pair<Integer,MultiIndexData> p : new Iterable<ComparablePair<Integer,MultiIndexData>>() {
			public Iterator<ComparablePair<Integer,MultiIndexData>> iterator() {
				return mi.equalRange(3);
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
		for ( Pair<Integer,MultiIndexData> p : new Iterable<ComparablePair<Integer,MultiIndexData>>() {
			public Iterator<ComparablePair<Integer,MultiIndexData>> iterator() {
				return mi.equalRange(2,true,4,true);
			}
		}) {
				assertEquals(exp.next(), p.second.unique);
		}
		assertFalse(exp.hasNext());
	}
*/
}