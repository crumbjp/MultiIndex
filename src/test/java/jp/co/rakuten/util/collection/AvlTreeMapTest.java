package jp.co.rakuten.util.collection;

import jp.co.rakuten.util.collection.avltree.AvlTreeMap;
import junit.framework.TestCase;



public class AvlTreeMapTest extends TestCase{
	AvlTreeMap<Integer,Integer> map = new AvlTreeMap<Integer,Integer>();

	@Override
	protected void setUp() throws Exception {
		map.insert(3,3);
		map.insert(0,0);
		map.insert(8,8);
		map.insert(9,9);
		map.insert(5,5);
		map.insert(2,2);
		map.insert(4,4);
		map.insert(7,7);
		map.insert(1,1);
		map.insert(6,6);
		super.setUp();
	}
	@Override
	protected void tearDown() throws Exception {
		map.clear();
		super.tearDown();
	}
	public void testSize() {
		assertEquals(10L,map.size());
	}
	public void testErase() {
		for ( int i = 0 ; i < 10 ; i++)
			map.erase(map.find(i));
		assertEquals(0L,map.size());
	}
	public void testSet(){
		assertFalse(map.set(10,10));
		assertEquals(new Integer(10),map.find(10).get().second);
		assertEquals(11L,map.size());
	}
	public void testSet1(){
		assertTrue(map.set(3,33));
		assertEquals(new Integer(33),map.find(3).get().second);
		assertEquals(10L,map.size());
	}
	public void testSet2(){
		assertTrue(map.set(new Pair<Integer,Integer>(3,333)));
		assertEquals(new Integer(333),map.find(3).get().second);
		assertEquals(10L,map.size());
	}
	public void testInsert(){
		assertTrue(map.insert(new Pair<Integer,Integer>(10,10)));
		assertEquals(new Integer(10),map.find(10).get().second);
		assertEquals(11L,map.size());
	}
	public void testInsert1(){
		assertFalse(map.insert(new Pair<Integer,Integer>(3,33)));
		assertEquals(10L,map.size());
	}
	public void testGet(){
		assertEquals(new Integer(5),map.get(5));
	}
	public void testGet1(){
		assertTrue(map.get(10)==null);
	}
	public void testFind() {
		for ( int i = 0 ; i < 10 ; i++)
			assertEquals(new Integer(i),map.find(i).get().second);
		assertTrue(map.find(-1).isEnd());
		assertTrue(map.find(10).isEnd());
	}
	public void testForwardIterator() {
		int i = 0;
		for ( StdIterator<Pair<Integer,Integer>> it = map.begin(),itend = map.end();
			! it.equals(itend);
			it.next() ){
			assertEquals(new Integer(i++),it.get().second);
		}
	}	
	public void testBackIterator(){
		int i = 9;
		for ( StdIterator<Pair<Integer,Integer>> it = map.last(),itend = map.end();
			! it.equals(itend);
			it.prev() ){
			assertEquals(new Integer(i--),it.get().second);
		}
	}	
	public void testCompatibleIterable(){
		int i = 0;
		for ( Pair<Integer,Integer> p : new CompatibleIterable<Pair<Integer,Integer>>(map)){
			assertEquals(new Integer(i++),p.second);
		}
	}
	public void testCompatibleReverseIterable(){
		int i = 9;
		for ( Pair<Integer,Integer> p : new CompatibleReverseIterable<Pair<Integer,Integer>>(map)){
			assertEquals(new Integer(i--),p.second);
		}
	}
}
