package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdMap;
import jp.co.rakuten.util.collection.tree.AvlTree;
import jp.co.rakuten.util.collection.tree.AvlTreeMap;
import jp.co.rakuten.util.collection.tree.FindComparator;
import jp.co.rakuten.util.collection.tree.UnmodifiableAvlTreeMap;

import java.util.Comparator;

public abstract class UniqueIndex<K extends Comparable<K>,T> extends UnmodifiableAvlTreeMap<K, Container<T>> implements Index<T> , StdMap<K,Container<T>> {
	AvlTreeMap<K,Container<T>> container = new AvlTreeMap<K, Container<T>>(
			new AvlTree<Pair<K,Container<T>>, K>(
					new Comparator<Pair<K,Container<T>>>() {
						public int compare(Pair<K,Container<T>> o1, Pair<K,Container<T>> o2) {
							return o1.first.compareTo(o2.first);
						}
					},
					new FindComparator<Pair<K,Container<T>>, K>() {
						public int compare(Pair<K,Container<T>> o1, K o2) {
							return o1.first.compareTo(o2);
						}
					}
			)
	); 
	protected abstract K getKey(final T t);
	public UniqueIndex() {
	}
	@Override
	public void opInit(final AvlTree<Container<T>,Container<T>> origin, final Integer size) {
		avlTree = container.getTree(); 
	}
	@Override
	public void opClear(){
		this.avlTree.clear();
	}
	@Override
	public void opAdd(final Container<T> c) {
		K k = getKey(c.t);
		if ( ! container.insert(k,c) ) 
			throw new RuntimeException("ADD : Identity is specified conflicting key !");
	}
	@Override
	public void opRemove(final Container<T> c) {
		K k = getKey(c.t);
		container.find(k).erase();
	}
	@Override
	public void opModify(final Container<T> c, final T t) {
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
	public boolean opCheckModify(final Container<T> c, final T t) {
		K newKey = getKey(t);
		K oldKey = getKey(c.t);
		if (! oldKey.equals(newKey) ) {
			return container.find(newKey).isEnd();
		}
		return true;
	}
}
