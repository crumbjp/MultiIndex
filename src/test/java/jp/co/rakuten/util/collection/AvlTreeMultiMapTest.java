package jp.co.rakuten.util.collection;

import jp.co.rakuten.util.collection.tree.AvlTreeMultiMap;
import jp.co.rakuten.util.collection.tree.Pair;
import jp.co.rakuten.util.collection.tree.AvlIterator;
import junit.framework.TestCase;



public class AvlTreeMultiMapTest extends TestCase{
	AvlTreeMultiMap<Integer,Integer> multiMap = new AvlTreeMultiMap<Integer,Integer>();

	@Override
	protected void setUp() throws Exception {
		multiMap.insert(3,3);
		multiMap.insert(0,0);
		multiMap.insert(8,8);
		multiMap.insert(9,9);
		multiMap.insert(5,5);
		multiMap.insert(2,2);
		multiMap.insert(4,4);
		multiMap.insert(7,7);
		multiMap.insert(1,1);
		multiMap.insert(6,6);
		multiMap.insert(3,13);
		multiMap.insert(0,10);
		multiMap.insert(8,18);
		multiMap.insert(9,19);
		multiMap.insert(5,15);
		multiMap.insert(2,12);
		multiMap.insert(4,14);
		multiMap.insert(7,17);
		multiMap.insert(1,11);
		multiMap.insert(6,16);
		super.setUp();
	}
	@Override
	protected void tearDown() throws Exception {
		multiMap.clear();
		super.tearDown();
	}
	
	public void testSize() {
		assertEquals(20L,multiMap.size());
	}
	public void testInsert() {
		multiMap.insert(new Pair<Integer,Integer>(10,10));
		assertEquals(new Integer(10),multiMap.findFirst(10).get().second);
		assertEquals(21L,multiMap.size());
		multiMap.insert(new Pair<Integer,Integer>(10,100));
		assertEquals(new Integer(100),multiMap.findLast(10).get().second);
		assertEquals(22L,multiMap.size());
	}	

	public void testEqualRange() {
		Pair<StdIterator<Pair<Integer,Integer>>,StdIterator<Pair<Integer,Integer>>> p = multiMap.equlRange(6);
		StdIterator<Pair<Integer,Integer>> it = p.first;
		assertEquals(new Integer(6),it.get().second);
		it.next();
		assertEquals(new Integer(16),it.get().second);
		it.next();
		assertTrue(it.equals(p.second));
	}
	public void testErase() {
		for ( int i = 0 ; i < 10 ; i++)
			multiMap.erase(multiMap.findFirst(i));
		assertEquals(10L,multiMap.size());
	}
	
	public void testReplace(){
		assertFalse(multiMap.replace(multiMap.findFirst(10),100));
	}
	public void testReplace1(){
		multiMap.replace(multiMap.findFirst(3), 33);
		assertEquals(new Integer(33),multiMap.findFirst(3).get().second);
	}
	public void testReplace2(){
		multiMap.replace(multiMap.findFirst(3),new Pair<Integer,Integer>(3,333));
		assertEquals(new Integer(333),multiMap.findFirst(3).get().second);
	}
	public void testFindFirst() {
		for ( int i = 0 ; i < 10 ; i++)
			assertEquals(new Integer(i),multiMap.findFirst(i).get().second);
		assertTrue(multiMap.findFirst(-1).isEnd());
		assertTrue(multiMap.findFirst(10).isEnd());
		
		
	}
	public void testForwardIterator() {
		int i = 0;
		for ( StdIterator<Pair<Integer,Integer>> it = multiMap.begin(),itend = multiMap.end();
			! it.equals(itend);
			it.next() ){
			assertEquals(new Integer(i/2),it.get().first);
			if ( (i % 2) == 0)
				assertEquals(new Integer(i/2),it.get().second);
			else 
				assertEquals(new Integer((i/2)+10),it.get().second);
			i++;
		}
	}	
	public void testBackIterator(){
		int i = 19;
		for ( StdIterator<Pair<Integer,Integer>> it = multiMap.last(),itend = multiMap.end();
			! it.equals(itend);
			it.prev() ){
			assertEquals(new Integer((i)/2),it.get().first);
			if ( (i % 2) == 0)
				assertEquals(new Integer(i/2),it.get().second);
			else 
				assertEquals(new Integer((i/2)+10),it.get().second);
			i--;
		}
	}	
}
