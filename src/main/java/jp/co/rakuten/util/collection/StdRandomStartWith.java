package jp.co.rakuten.util.collection;

import jp.co.rakuten.util.collection.avltree.FindComparator;
import jp.co.rakuten.util.collection.avltree.StringStartWithComparator;

/**
 * <h3>Random accessable interface of range search.</h3>
 * <pre>
 * This class provides the way of searching keys more fuzzy by giving different comparator.
 * The specified comparator keep rule following.
 *  - The order must be same or corresponding between origin and depending on the comparator.
 * This means comparing by start-with.
 * </pre>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @see FindComparator
 * @see StringStartWithComparator
 * @param <K> Key-type.
 * @param <T> Target-type. 
 */
public interface StdRandomStartWith<T,K> {
	/**
	 * <pre>
	 * Find first data of specifying.
	 * </pre>
	 * 
	 * @param k Specifying search key.
	 * @param startWithComparator comparator.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> findFirstStartWith(K k,FindComparator<T,K> startWithComparator);
	/**
	 * <pre>
	 * Find last data of specifying.
	 * </pre>
	 * 
	 * @param k Specifying search key.
	 * @param startWithComparator comparator.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> findLastStartWith(K k,FindComparator<T,K> startWithComparator);
	/**
	 * <pre>
	 * Find lowest data of upper than specifying in this container.
	 * </pre>
	 * @param k Specifying search key.
	 * @param startWithComparator comparator.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> upperBoundStartWith(K k,FindComparator<T,K> startWithComparator);
	/**
	 * <pre>
	 * Find highest data of lower than specifying in this container.
	 * </pre>
	 * @param k Specifying search key.
	 * @param startWithComparator comparator.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public StdIterator<T> lowerBoundStartWith(K k,FindComparator<T,K> startWithComparator);
	/**
	 * <pre>
	 * Find data by the specifying in this container.
	 *   Returns range-iterator.
	 *   
	 *   Range-iterator's first is a iterator of the first data of specifying.
	 *    This points end of container if not found case.
	 *    
	 *   Range-iterator's second is a iterator of the first data of upper.
	 *    Be careful! This is not pointing the last data of your specifying.
	 * </pre>
	 * @param k Specifying search key.
	 * @param startWithComparator comparator.
	 * @return Returns iterator of pointing the data or end of container.
	 */
	public Pair<StdIterator<T>,StdIterator<T>> equlRangeStartWith(K k,FindComparator<T,K> startWithComparator);
}
