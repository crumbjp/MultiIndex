package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.avltree.AvlTree;

/**
 * Index base I/F of MultiIndex
 * 
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @param <T> Target data-type.
 */
public interface Index <T> {
	/**
	 * Event of initial.
	 * 
	 * @param origin MultiIndex's inner container.
	 * @param size T.B.D
	 */
	public void opInit   (final AvlTree<Record<T>,Record<T>> origin,final Integer size);
	/**
	 * Event of add into container.
	 * <pre> 
	 *     for developer.
	 * </pre>
	 * 
	 * @param c Adding data.
	 */
	public void opAdd    (final Record<T> c);
	/**
	 * Event of remove from container.
	 * <pre> 
	 *     for developer.
	 * </pre>
	 * 
	 * @param c Removing data.
	 */
	public void opRemove (final Record<T> c);
	/**
	 * Event of update in container.
	 * <pre> 
	 *     for developer.
	 * </pre>
	 * 
	 * @param c Targeting data.
	 * @param t Updating new data.
	 */
	public void opModify (final Record<T> c,final T t);
	/**
	 * Event of all clear.
	 * <pre> 
	 *     for developer.
	 * </pre>
	 * 
	 */
	public void opClear  ();
	/**
	 * Event of check adding.
	 * <pre> 
	 *     for developer.
	 * </pre>
	 * 
	 * @param t Charenging data. 
	 * @return True if possible.
	 */
	public boolean opCheckAdd(final T t);
	/**
	 * Event of check updating.
	 * <pre> 
	 *     for developer.
	 * </pre>
	 * 
	 * @param c Targeting data. 
	 * @param t Charenging new data. 
	 * @return True if possible.
	 */
	public boolean opCheckModify(final Record<T> c,final T t);
}
