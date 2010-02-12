package jp.co.rakuten.util.collection.avltree;

public class StringStartWithComparator implements FindComparator<String, String> {
	@Override
	public int compare(String o1, String o2) {
		return o1.startsWith(o2)?0:o1.compareTo(o2);
	}
	private StringStartWithComparator() {
	}
	private static StringStartWithComparator This = new StringStartWithComparator();
	public static StringStartWithComparator getInstance(){
		return This;
	}
}
