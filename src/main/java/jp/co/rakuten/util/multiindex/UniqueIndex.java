package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdMap;
import jp.co.rakuten.util.collection.avltree.AvlTree;
import jp.co.rakuten.util.collection.avltree.AvlTreeMap;
import jp.co.rakuten.util.collection.avltree.FindComparator;

import java.util.Comparator;

/**
 * <h3>Base class of unique index.</h3>
 *     for developer.
 * 
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @param <K> Ordering key-type
 * @param <T> Target data-type
 */
public abstract class UniqueIndex <K extends Comparable<K>,T> implements Index<T> , StdMap<K,T> {
	private AvlTreeMap<K,Record<T>> container = new AvlTreeMap<K, Record<T>>(
			new AvlTree<Pair<K,Record<T>>, K>(
					new Comparator<Pair<K,Record<T>>>() {
						public int compare(Pair<K,Record<T>> o1, Pair<K,Record<T>> o2) {
							return o1.first.compareTo(o2.first);
						}
					},
					new FindComparator<Pair<K,Record<T>>, K>() {
						public int compare(Pair<K,Record<T>> o1, K o2) {
							return o1.first.compareTo(o2);
						}
					}
			)
	); 
	protected abstract K getKey(final T t);
	public UniqueIndex() {
	}
	protected MultiIndex<T> origin;
	@Override
	public void opInit(final MultiIndex<T> origin, final Integer size) {
		this.origin = origin;
	}
	@Override
	public void opClear(){
		this.container.clear();
	}
	@Override
	public void opAdd(final Record<T> c) {
		K k = getKey(c.t);
		if ( ! container.insert(k,c) ) 
			throw new RuntimeException("ADD : Identity is specified conflicting key !");
	}
	@Override
	public void opRemove(final Record<T> c) {
		K k = getKey(c.t);
		container.erase(container.find(k));
	}
	@Override
	public void opModify(final Record<T> c, final T t) {
		K newKey = getKey(t);
		K oldKey = getKey(c.t);
		if (! oldKey.equals(newKey) ) {
			container.erase(container.find(oldKey));
			if ( ! container.insert(newKey,c) ) 
				throw new RuntimeException("MODIFY : Identity is specified conflicting key !");
		}
	}
	@Override
	public boolean opCheckAdd(final T t) {
		K k = getKey(t);
		return container.find(k).isEnd();
	}
	@Override
	public boolean opCheckModify(final Record<T> c, final T t) {
		K newKey = getKey(t);
		K oldKey = getKey(c.t);
		if (! oldKey.equals(newKey) ) {
			return container.find(newKey).isEnd();
		}
		return true;
	}
	@Override
	public T get(K k) {
		return container.get(k).t;
	}
	@Override
	public StdIterator<Pair<K, T>> begin() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StdIterator<Pair<K, T>> last() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StdIterator<Pair<K, T>> end() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long size() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public StdIterator<Pair<K, T>> lowerBound(K k) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StdIterator<Pair<K, T>> upperBound(K k) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StdIterator<Pair<K, T>> find(K k) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Pair<StdIterator<Pair<K, T>>, StdIterator<Pair<K, T>>> equlRangeStartWith(
			K k, FindComparator<Pair<K, T>, K> startWithComparator) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StdIterator<Pair<K, T>> findFirstStartWith(K k,
			FindComparator<Pair<K, T>, K> startWithComparator) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StdIterator<Pair<K, T>> findLastStartWith(K k,
			FindComparator<Pair<K, T>, K> startWithComparator) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StdIterator<Pair<K, T>> lowerBoundStartWith(K k,
			FindComparator<Pair<K, T>, K> startWithComparator) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StdIterator<Pair<K, T>> upperBoundStartWith(K k,
			FindComparator<Pair<K, T>, K> startWithComparator) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean insert(K k, T t) {
		// TODO Auto-generated method stub
		return origin.add(t);
	}
	@Override
	public boolean insert(Pair<K, T> t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean replace(K k, T t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean replace(StdIterator<Pair<K, T>> it, Pair<K, T> t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void erase(StdIterator<Pair<K, T>> it) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean insertReplace(Pair<K, T> t) {
		// TODO Auto-generated method stub
		return false;
	}
}
