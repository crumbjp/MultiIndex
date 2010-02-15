package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.avltree.AvlTree;

/**
 * <h3>Index base I/F of MultiIndex.</h3>
 *     for developer. 
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
	public void opInit   (final Integer size);
	/**
	 * Event of add into container.
	 * <pre> 
	 *     for developer.
	 * </pre>
	 * 
	 * @param c Adding data.
	 * @param u Unique id
	 */
	public void opAdd    (final T c,final Integer u);
	/**
	 * Event of remove from container.
	 * <pre> 
	 *     for developer.
	 * </pre>
	 * 
	 * @param c Removing data.
	 * @param u Unique id
	 */
	public void opRemove (final T c,final Integer u);
	/**
	 * Event of update in container.
	 * <pre> 
	 *     for developer.
	 * </pre>
	 * 
	 * @param c Targeting data.
	 * @param u Unique id
	 * @param t Updating new data.
	 */
	public void opModify (final T c,final Integer u,final T t);
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
	 * @param u Unique id
	 * @param t Charenging new data. 
	 * @return True if possible.
	 */
	public boolean opCheckModify(final T c,final Integer u,final T t);
}
