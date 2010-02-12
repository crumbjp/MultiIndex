package jp.co.rakuten.util.collection;

import jp.co.rakuten.util.collection.avltree.AvlTreeSet;
import jp.co.rakuten.util.collection.avltree.StringStartWithComparator;
import junit.framework.TestCase;

public class AvlTreeMultiStringTest extends TestCase{
	StdSet<String> multiSet= new AvlTreeSet<String>();

	@Override
	protected void setUp() throws Exception {
		String src = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int N = 5;
		int[] n = new int[N];
		for ( int i = 0 ; i < n.length ; i++ ) {
			n[i] = 0;
		}
		char[] key = new char[N];
		for ( int i = 0 ; i >= 0 ; ) {
			for( ; n[i] < src.length() ; ) {
				key[i]=src.charAt(n[i]);
				n[i]++;
				if ( i == (n.length-1) ){
					multiSet.insert(new String(key));
				}else {
					i++;
					n[i] = 0;
					i++;
					break;
				}
			}
			i--;
		}
		System.out.println("SIZE="+multiSet.size());
		super.setUp();
	}
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testInsert() {
		StringStartWithComparator sswc = StringStartWithComparator.getInstance();
		assertEquals("ABAAA", multiSet.findFirstStartWith("AB",sswc).get());
		assertEquals("ABZZZ", multiSet.findLastStartWith("AB",sswc).get());
		Pair<StdIterator<String>,StdIterator<String>> p = multiSet.equlRangeStartWith("AB",sswc);
		for (  StdIterator<String> it = p.first,
				itend = p.second;
				! it.equals(itend);
				it.next() ){
			System.out.println(it.get());
		}		
	}	
}
