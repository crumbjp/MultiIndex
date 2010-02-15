package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdMultiMap;
import jp.co.rakuten.util.collection.avltree.AvlTree;
import jp.co.rakuten.util.collection.avltree.AvlTreeMultiMap;
import jp.co.rakuten.util.collection.avltree.FindComparator;
import jp.co.rakuten.util.collection.avltree.UnmodifiableAvlTreeMultiMap;

import java.util.Comparator;

/**
 * <h3>Base class of non-unique index.</h3>
 *     for developer.
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @param <K> Ordering key-type
 * @param <T> Target data-type
 */
public abstract class NonUniqueIndex <K extends Comparable<K>,T> extends UnmodifiableAvlTreeMultiMap<K, Record<T>> implements Index<T> , StdMultiMap<K,Record<T>> {
	private AvlTreeMultiMap<K,Record<T>> container = new AvlTreeMultiMap<K, Record<T>>(
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
	public NonUniqueIndex() {
	}
	@Override
	public void opInit(final AvlTree<Record<T>,Record<T>> origin, final Integer size) {
		avlTree = container.getTree(); 
	}
	@Override
	public void opClear(){
		avlTree.clear();
	}
	@Override
	public void opAdd(final Record<T> c) {
		K k = getKey(c.t);
		container.insert(k,c); 
	}
	@Override
	public void opRemove(final Record<T> c) {
		K k = getKey(c.t);
		Pair<StdIterator<Pair<K,Record<T>>>,StdIterator<Pair<K,Record<T>>>> pair = container.equlRange(k);
		for (	StdIterator<Pair<K,Record<T>>> it = pair.first,
				itend = pair.second;
			!it.equals(itend);
			it.next())
		{
			if ( it.get().second.id == c.id ) {
				it.erase();
				break;
			}
		}
	}
	@Override
	public void opModify(final Record<T> c, final T t) {
		K newKey = getKey(t);
		K oldKey = getKey(c.t);
		if (! oldKey.equals(newKey) ) {
			Pair<StdIterator<Pair<K,Record<T>>>,StdIterator<Pair<K,Record<T>>>> pair = container.equlRange(oldKey);
			for (	StdIterator<Pair<K,Record<T>>> it = pair.first,
					itend = pair.second;
				!it.equals(itend);
				it.next())
			{
				if ( it.get().second.id == c.id ) {
					it.erase();
					break;
				}
			}
			container.insert(newKey,c); 
		}
	}
	@Override
	public boolean opCheckAdd(final T t) {
		return true;
	}
	@Override
	public boolean opCheckModify(final Record<T> c, final T t) {
		return true;
	}
}
