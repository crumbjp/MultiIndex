package jp.co.rakuten.util.collection;
/**
 * How come jdk isn't equipped with basic classes like This?
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @param <FIRST>  The first data-type.
 * @param <SECOND> The second data-type.
 */
public class Pair<FIRST,SECOND> {
	/**
	 * The first data.
	 */
	public FIRST first;
	/**
	 * The second data.
	 */
	public SECOND second;
	/**
	 * Instantiate with initial values.
	 * 
	 * @param first  Initializing value.
	 * @param second Initializing value.
	 */
	public Pair( final FIRST first , final SECOND second) {
		this.first = first;
		this.second = second;
	}
}
