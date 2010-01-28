package jp.co.rakuten.util.collection;

import jp.co.rakuten.util.collection.avltree.AvlIterator;
import jp.co.rakuten.util.collection.avltree.AvlTreeSet;
import junit.framework.TestCase;



public class AvlTreeSetTest extends TestCase{
	AvlTreeSet<Integer> set = new AvlTreeSet<Integer>();

	@Override
	protected void setUp() throws Exception {
		set.insert(3);
		set.insert(0);
		set.insert(8);
		set.insert(9);
		set.insert(5);
		set.insert(2);
		set.insert(4);
		set.insert(7);
		set.insert(1);
		set.insert(6);
		super.setUp();
	}
	@Override
	protected void tearDown() throws Exception {
		set.clear();
		super.tearDown();
	}
	public void testSize() {
		assertEquals(10L,set.size());
	}
	public void testErase() {
		for ( int i = 0 ; i < 10 ; i++)
			set.erase(set.find(i));
		assertEquals(0L,set.size());
	}
	public void testFind() {
		for ( int i = 0 ; i < 10 ; i++)
			assertEquals(new Integer(i),set.find(i).get());
		assertTrue(set.find(-1).isEnd());
		assertTrue(set.find(10).isEnd());
	}
	public void testSet(){
		set.set(5);
		set.set(10);
		for ( int i = 0 ; i < 11 ; i++)
			assertEquals(new Integer(i),set.find(i).get());
		assertEquals(11L,set.size());
	}

	public void testForwardIterator() {
		int i = 0;
		for ( StdIterator<Integer> it = set.begin(),itend = set.end();
			! it.equals(itend);
			it.next() ){
			assertEquals(new Integer(i++),it.get());
		}
	}	
	public void testBackIterator(){
		int i = 9;
		for ( StdIterator<Integer> it = set.last(),itend = set.end();
			! it.equals(itend);
			it.prev() ){
			assertEquals(new Integer(i--),it.get());
		}
	}	
	public void testCompatibleIterable(){
		int i = 0;
		for ( Integer v : new CompatibleIterable<Integer>(set.getTree())){
			assertEquals(new Integer(i++),v);
		}
	}
	public void testCompatibleReverseIterable(){
		int i = 9;
		for ( Integer v : new CompatibleReverseIterable<Integer>(set.getTree())){
			assertEquals(new Integer(i--),v);
		}
	}
}
