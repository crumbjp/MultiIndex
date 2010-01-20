import jp.co.rakuten.util.MultiMapTest;
import jp.co.rakuten.util.MultiSetTest;
import jp.co.rakuten.util.multiindex.MultiIndexTest;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;


public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for default package");
		//$JUnit-BEGIN$
		suite.addTestSuite(MultiMapTest.class);
		suite.addTestSuite(MultiSetTest.class);
		suite.addTestSuite(MultiIndexTest.class);
		//$JUnit-END$
		return suite;
	}
}
