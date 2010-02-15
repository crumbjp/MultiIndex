package jp.co.rakuten.util.multiindex;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * <h3>Index container.</h3>
 *     Use to define some kinds of index for MultiIndex.
 * {@code
 *   new IndexBy<T>(SequenceIndex<T>(),IndentityIndex<T>());
 * }
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @see MultiIndex
 * @see SequenceIndex
 * @see IdentityIndex
 * @see OrderedUniqueIndex
 * @see OrderedNonUniqueIndex
 * @param <T> Target data-type.
 */
public class IndexBy<T> implements Iterable<Index<T>>{
	private final List<Index<T>> container;
	/**
	 * Initialize with indexes.
	 *       
	 * @param i Index.
	 */
	public IndexBy(final Index<T> ...i) {
		container = Arrays.asList(i);
	}
	/**
	 * Get index object.
	 * 
	 * @param n Number of index.
	 * @return Returns Index object.
	 */
	public Index<T> getIndex(final int n) {
		return container.get(n);
	}
	/**
	 * Maybe for developer.
	 * 
	 * @return Returns iterator.
	 */
	@Override
	public Iterator<Index<T>> iterator() {
		return container.iterator();
	}
}

