package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdMap;
import jp.co.rakuten.util.collection.avltree.AvlTree;
import jp.co.rakuten.util.collection.avltree.AvlTreeMap;
import jp.co.rakuten.util.collection.avltree.FindComparator;
import jp.co.rakuten.util.collection.avltree.UnmodifiableAvlTreeMap;

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
public abstract class UniqueIndex <K extends Comparable<K>,T> extends UnmodifiableAvlTreeMap<K, T> implements Index<T> , StdMap<K,T> {
	private AvlTreeMap<K,T> container = new AvlTreeMap<K, T>(
			new AvlTree<Pair<K,T>, K>(
					new Comparator<Pair<K,T>>() {
						public int compare(Pair<K,T> o1, Pair<K,T> o2) {
							return o1.first.compareTo(o2.first);
						}
					},
					new FindComparator<Pair<K,T>, K>() {
						public int compare(Pair<K,T> o1, K o2) {
							return o1.first.compareTo(o2);
						}
					}
			)
	); 
	protected abstract K getKey(final T t);
	public UniqueIndex() {
	}
	@Override
	public void opInit(final Integer size) {
		avlTree = container.getTree(); 
	}
	@Override
	public void opClear(){
		this.avlTree.clear();
	}
	@Override
	public void opAdd(final T c,final Integer u) {
		K k = getKey(c);
		if ( ! container.insert(k,c) ) 
			throw new RuntimeException("ADD : Identity is specified conflicting key !");
	}
	@Override
	public void opRemove(final T c,final Integer u) {
		K k = getKey(c);
		container.find(k).erase();
	}
	@Override
	public void opModify(final T c,final Integer u,final T t) {
		K newKey = getKey(t);
		K oldKey = getKey(c);
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
	public boolean opCheckModify(final T c,final Integer u,final T t) {
		K newKey = getKey(t);
		K oldKey = getKey(c);
		if (! oldKey.equals(newKey) ) {
			return container.find(newKey).isEnd();
		}
		return true;
	}
}
