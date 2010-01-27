package jp.co.rakuten.util.multiindex;

import jp.co.rakuten.util.collection.Pair;
import jp.co.rakuten.util.collection.StdIterator;
import jp.co.rakuten.util.collection.StdMultiMap;
import jp.co.rakuten.util.collection.tree.AvlIterator;
import jp.co.rakuten.util.collection.tree.AvlTree;
import jp.co.rakuten.util.collection.tree.AvlTreeMultiMap;
import jp.co.rakuten.util.collection.tree.FindComparator;
import jp.co.rakuten.util.collection.tree.UnmodifiableAvlTreeMultiMap;

import java.util.Comparator;

public abstract class NonUniqueIndex<K extends Comparable<K>,T> extends UnmodifiableAvlTreeMultiMap<K, Container<T>> implements Index<T> , StdMultiMap<K,Container<T>> {
	AvlTreeMultiMap<K,Container<T>> container = new AvlTreeMultiMap<K, Container<T>>(
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
	public NonUniqueIndex() {
	}
	@Override
	public void opInit(final AvlTree<Container<T>,Container<T>> origin, final Integer size) {
		avlTree = container.getTree(); 
	}
	@Override
	public void opClear(){
		avlTree.clear();
	}
	@Override
	public void opAdd(final Container<T> c) {
		K k = getKey(c.t);
		container.insert(k,c); 
	}
	@Override
	public void opRemove(final Container<T> c) {
		K k = getKey(c.t);
		Pair<StdIterator<Pair<K,Container<T>>>,StdIterator<Pair<K,Container<T>>>> pair = container.equlRange(k);
		for (	AvlIterator<Pair<K,Container<T>>> it = (AvlIterator<Pair<K,Container<T>>>)pair.first,
				itend = (AvlIterator<Pair<K,Container<T>>>)pair.second;
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
	public void opModify(final Container<T> c, final T t) {
		K newKey = getKey(t);
		K oldKey = getKey(c.t);
		if (! oldKey.equals(newKey) ) {
			Pair<StdIterator<Pair<K,Container<T>>>,StdIterator<Pair<K,Container<T>>>> pair = container.equlRange(oldKey);
			for (	AvlIterator<Pair<K,Container<T>>> it = (AvlIterator<Pair<K,Container<T>>>)pair.first,
					itend = (AvlIterator<Pair<K,Container<T>>>)pair.second;
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
	public boolean opCheckModify(final Container<T> c, final T t) {
		return true;
	}
}
