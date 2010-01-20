package jp.co.rakuten.util.multiindex;
import java.util.List;

public interface Index <T> {
	public void opInit   (final List<Container<T>> origin,final Integer size);
	public void opAdd    (final Container<T> c);
	public void opRemove (final Container<T> c);
	public void opModify (final Container<T> c,final T t);
	public void opClear  ();
	public boolean opCheckAdd(final T t);
	public boolean opCheckModify(final Container<T> c,final T t);
}
