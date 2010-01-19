package jp.co.rakuten.util.multiindex;

import java.util.List;

import jp.co.rakuten.util.UnmodifiableListWrapper;

public class SequenceIndex<T> extends UnmodifiableListWrapper<Container<T>> implements Index<T> , List<Container<T>>{
	public SequenceIndex() {
		// Nothing to do.
	}
	@Override
	public void opInit   (final List<Container<T>> origin,final Integer size){
		this.container = origin;
	}
	@Override
	public void opClear() {
		;
	}
	@Override
	public void opAdd(final Container<T> c) {
		;
	}
	@Override
	public void opRemove(final Container<T> c){
		;
	}
	@Override
	public void opModify(final Container<T> c, T t) {
		;
	}
	@Override
	public boolean opCheckAdd(final T t) {
		return true;
	}
	@Override
	public boolean opCheckModify(final Container<T> c, T t) {
		return true;
	}
}
