import jp.co.rakuten.util.collection.AvlTreeMapTest;
import jp.co.rakuten.util.collection.AvlTreeMultiMapTest;
import jp.co.rakuten.util.collection.AvlTreeMultiSetTest;
import jp.co.rakuten.util.collection.AvlTreeSetTest;
import jp.co.rakuten.util.collection.tree.AvlTreeParformanceTest;
import jp.co.rakuten.util.collection.tree.AvlTreeTest;
import jp.co.rakuten.util.multiindex.MultiIndexTest;
import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for default package");
		//$JUnit-BEGIN$
//		suite.addTestSuite(MultiMapTest.class);
//		suite.addTestSuite(MultiSetTest.class);
		suite.addTestSuite(MultiIndexTest.class);
		suite.addTestSuite(AvlTreeTest.class);
//		suite.addTestSuite(AvlTreeParformanceTest.class);
		suite.addTestSuite(AvlTreeSetTest.class);
		suite.addTestSuite(AvlTreeMapTest.class);
		suite.addTestSuite(AvlTreeMultiSetTest.class);
		suite.addTestSuite(AvlTreeMultiMapTest.class);
		//$JUnit-END$
		return suite;
	}
}
