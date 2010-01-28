package jp.co.rakuten.util.multiindex;

import java.util.Comparator;

import jp.co.rakuten.util.collection.avltree.AvlTree;
import jp.co.rakuten.util.collection.avltree.FindComparator;


public class MultiIndex<T> {
	private final IndexBy<T> indexBy;
//	private final ArrayList<Container<T> > dataContainer;
	private final AvlTree<Container<T>,Container<T>> dataContainer = new AvlTree<Container<T>,Container<T>>(
			new Comparator<Container<T>>() {
				@Override
				public int compare(Container<T> o1, Container<T> o2) {
					return o1.id.compareTo(o2.id);
				}
			},
			new FindComparator<Container<T>,Container<T>> () {
				@Override
				public int compare(Container<T> o1, Container<T> o2) {
					return o1.id.compareTo(o2.id);
				}
			}
	);
	private final Integer size;
	private final static Integer DEFAULT_SIZE = 4096;
	
	public MultiIndex(final IndexBy<T> indexBy ) {
		this.size = DEFAULT_SIZE;
		this.indexBy = indexBy;
		for ( Index<T> index : indexBy )
			index.opInit(this.dataContainer,this.size);
	}
	public MultiIndex(final IndexBy<T> indexBy , final Integer size ) {
		this.size = size;
		this.indexBy = indexBy;
		for ( Index<T> index : indexBy )
			index.opInit(this.dataContainer,this.size);
	}
	
	public Index<T> index(final int n){
		return indexBy.getIndex(n);
	}
	public void add(final T t) {
		Container<T> c = new Container<T>(t);
		for ( Index<T> index : indexBy )
			index.opAdd(c);
		this.dataContainer.insert(c);
	}
	public boolean safeAdd(final T t) {
		for ( Index<T> index : indexBy )
			if ( !index.opCheckAdd(t) )
				return false;
		this.add(t);
		return true;
	}
	public void remove(final Container<T> c){
		for ( Index<T> index : indexBy ) 
			index.opRemove(c);
		this.dataContainer.erase(this.dataContainer.find(c));
	}
	public void modify(final Container<T> c , final T t) {
		for ( Index<T> index : indexBy ) 
			index.opModify(c,t);
		c.t = t;
	}
	public boolean safeModify(final Container<T> c , final T t) {
		for ( Index<T> index : indexBy )
			if ( ! index.opCheckModify(c, t) )
				return false;
		this.modify(c,t);
		return true;
	}
	public void clear() {
		for ( Index<T> index : indexBy )
			index.opClear();
	}
}

