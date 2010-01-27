package jp.co.rakuten.util.collection;

import jp.co.rakuten.util.collection.tree.AvlIterator;
import jp.co.rakuten.util.collection.tree.AvlTreeMultiSet;
import jp.co.rakuten.util.collection.tree.Pair;
import junit.framework.TestCase;



public class AvlTreeMultiSetTest extends TestCase{
	AvlTreeMultiSet<Integer> multiSet = new AvlTreeMultiSet<Integer>();

	@Override
	protected void setUp() throws Exception {
		multiSet.insert(new Integer(3));
		multiSet.insert(new Integer(0));
		multiSet.insert(new Integer(8));
		multiSet.insert(new Integer(9));
		multiSet.insert(new Integer(5));
		multiSet.insert(new Integer(2));
		multiSet.insert(new Integer(4));
		multiSet.insert(new Integer(7));
		multiSet.insert(new Integer(1));
		multiSet.insert(new Integer(6));
		multiSet.insert(new Integer(3));
		multiSet.insert(new Integer(0));
		multiSet.insert(new Integer(8));
		multiSet.insert(new Integer(9));
		multiSet.insert(new Integer(5));
		multiSet.insert(new Integer(2));
		multiSet.insert(new Integer(4));
		multiSet.insert(new Integer(7));
		multiSet.insert(new Integer(1));
		multiSet.insert(new Integer(6));
		super.setUp();
	}
	@Override
	protected void tearDown() throws Exception {
		multiSet.clear();
		super.tearDown();
	}
	public void testEqualRange() {
		Pair<StdIterator<Integer>,StdIterator<Integer>> p = multiSet.equlRange(6);
		StdIterator<Integer> it = p.first;
		assertEquals(new Integer(6),it.get());
		it.next();
		assertEquals(new Integer(6),it.get());
		it.next();
		assertTrue(it.equals(p.second));
	}
	
	public void testSize() {
		assertEquals(20L,multiSet.size());
	}
	public void testErase() {
		for ( int i = 0 ; i < 10 ; i++)
			multiSet.erase(multiSet.findFirst(i));
		assertEquals(10L,multiSet.size());
	}

	
	public void testFindFirst() {
		for ( int i = 0 ; i < 10 ; i++)
			assertEquals(new Integer(i),multiSet.findFirst(i).get());
		assertTrue(multiSet.findFirst(-1).isEnd());
		assertTrue(multiSet.findFirst(10).isEnd());
	}
	public void testFindLast() {
		Integer first = multiSet.findFirst(3).get();
		Integer last= multiSet.findLast(3).get();
		assertFalse((first==last));
	}
	public void testForwardIterator() {
		int i = 0;
		for ( AvlIterator<Integer> it = multiSet.begin(),itend = multiSet.end();
			! it.equals(itend);
			it.next() ){
			assertEquals(new Integer(i++/2),it.get());
		}
	}	
	public void testBackIterator(){
		int i = 19;
		for ( AvlIterator<Integer> it = multiSet.last(),itend = multiSet.end();
			! it.equals(itend);
			it.prev() ){
			assertEquals(new Integer((i--)/2),it.get());
		}
	}	
	public void testCompatibleIterable(){
		int i = 0;
		for ( Integer v : new CompatibleIterable<Integer>(multiSet.getTree())){
			assertEquals(new Integer((i++)/2),v);
		}
	}
	public void testCompatibleReverseIterable(){
		int i = 19;
		for ( Integer v : new CompatibleReverseIterable<Integer>(multiSet.getTree())){
			assertEquals(new Integer((i--)/2),v);
		}
	}
}
