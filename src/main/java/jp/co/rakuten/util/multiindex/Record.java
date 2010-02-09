package jp.co.rakuten.util.multiindex;
/**
 * MultiIndex's record.
 * <pre>
 *    This class is wrapper of user specifying data-type.
 *    MultiIndex container distinguish the record by this object user operating.
 *    So user must operate the data through this.
 * </pre>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @param <T> Target data-type.
 */
public class Record <T> {
	private static Integer currentUniqueId = 0;
	final Integer id;
	T        t;
	/**
	 * For developer
	 * @param t data.
	 */
	public Record(final T t) {
		this.id = ++currentUniqueId;
		this.t  = t;
	}
	/**
	 * Get data.
	 * @return Returns user data.
	 */
	public T get(){
		return t;
	}
}
