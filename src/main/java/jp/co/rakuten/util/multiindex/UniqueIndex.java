package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdMap;
import jp.co.rakuten.util.collection.avltree.AvlTree;
import jp.co.rakuten.util.collection.avltree.AvlTreeMap;
import jp.co.rakuten.util.collection.avltree.FindComparator;
import jp.co.rakuten.util.collection.avltree.UnmodifiableAvlTreeMap;

import java.util.Comparator;

/**
 * Base class of unique index.
 * 
 * @author hiroaki.kubota@mail.rakuten.co.jp
 *
 * @param <K> Ordering key-type
 * @param <T> Target data-type
 */
public abstract class UniqueIndex <K extends Comparable<K>,T> extends UnmodifiableAvlTreeMap<K, Record<T>> implements Index<T> , StdMap<K,Record<T>> {
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
	@Override
	public void opInit(final AvlTree<Record<T>,Record<T>> origin, final Integer size) {
		avlTree = container.getTree(); 
	}
	@Override
	public void opClear(){
		this.avlTree.clear();
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
		container.find(k).erase();
	}
	@Override
	public void opModify(final Record<T> c, final T t) {
		K newKey = getKey(t);
		K oldKey = getKey(c.t);
		if (! oldKey.equals(newKey) ) {
			container.find(oldKey).erase();
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
}
