package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.UnmodifiableMapWrapper;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class IdentityIndex<T>  extends UnmodifiableMapWrapper<T,Container<T>> implements Index<T> , Map<T,Container<T>>{
	public IdentityIndex() {
	}
	@Override
	public void opInit(final List<Container<T>> origin, final Integer size) {
		this.container = new TreeMap<T,Container<T>>();
	}
	@Override
	public void opClear(){
		this.container.clear();
	}
	@Override
	public void opAdd(final Container<T> c) {
		if ( this.container.put(c.pair.second,c) != null )
			throw new RuntimeException("ADD : Identity is specified conflicting key !");
	}
	@Override
	public void opRemove(final Container<T> c) {
		this.container.remove(c.pair.second);
	}
	@Override
	public void opModify(final Container<T> c, final T t) {
		T oldKey = c.pair.second;
		T newKey = t;
		if (! oldKey.equals(newKey) ) {
			this.container.remove(oldKey); 
			if ( this.container.put(newKey,c) != null ) 
				throw new RuntimeException("MODIFY : Identity is specified conflicting key !");
		}
	}
	@Override
	public boolean opCheckAdd(final T t) {
		return !this.container.containsKey(t);
	}
	@Override
	public boolean opCheckModify(final Container<T> c, final T t) {
		T oldKey = c.pair.second;
		T newKey = t;
		if (! oldKey.equals(newKey) ) {
			return ! this.containsKey(newKey);
		}
		return true;
	}
}
