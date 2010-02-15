
package jp.co.rakuten.util.multiindex;
/**
 * <h3>MultiIndex's record.</h3>
 * <pre>
 *    This class is wrapper of user specifying data-type.
 *    {@link MultiIndex} container distinguish the record by this object user operating.
 *    So user must operate the data through this.
 * </pre>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @see MultiIndex 
 * @param <T> Target data-type.
 */
/*
public class Record <T> {
	private static Integer currentUniqueId = 0;
	final Integer id;
	T        t;
	public Record(final T t) {
		this.id = ++currentUniqueId;
		this.t  = t;
	}
	public T get(){
		return t;
	}
}
*/