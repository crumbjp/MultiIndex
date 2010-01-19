package jp.co.rakuten.util.multiindex;

import java.util.ArrayList;
import java.util.Map;

public class MultiIndex<T> {
	private final IndexBy<T> indexBy;
	private final ArrayList<Container<T> > dataContainer;
	private final Integer size;
	private final static Integer DEFAULT_SIZE = 4096;
	
	public MultiIndex(final IndexBy<T> indexBy ) {
		this.size = DEFAULT_SIZE;
		this.indexBy = indexBy;
		this.dataContainer = new ArrayList<Container<T> >();
		for ( Index<T> index : indexBy )
			index.opInit(this.dataContainer,this.size);
	}
	public MultiIndex(final IndexBy<T> indexBy , final Integer size ) {
		this.size = size;
		this.indexBy = indexBy;
		this.dataContainer = new ArrayList<Container<T> >();
		for ( Index<T> index : indexBy )
			index.opInit(this.dataContainer,this.size);
	}
	
	public Index<T> index(final int n){
		return indexBy.get(n);
	}
	void add(final T t) {
		Container<T> c = new Container<T>(t);
		for ( Index<T> index : indexBy )
			index.opAdd(c);
		this.dataContainer.add(c);
	}
	boolean safeAdd(final T t) {
		for ( Index<T> index : indexBy )
			if ( !index.opCheckAdd(t) )
				return false;
		this.add(t);
		return true;
	}
	public void remove(final Container<T> c){
		for ( Index<T> index : indexBy ) 
			index.opRemove(c);
		this.dataContainer.remove(c);
	}
	public void modify(final Container<T> c , final T t) {
		for ( Index<T> index : indexBy ) 
			index.opModify(c,t);
		c.pair.second = t;
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
	/*
	public static void main(String[] args) throws Throwable{
		Container<Data> c3 = ((UniqueIndex<Integer,Data>)mi.index(1)).get(1);
		System.out.println("Safe modify (false) : "+mi.safeModify(c3,new Data(2,4l,"Z")));
		System.out.println("Safe modify (true) : "+mi.safeModify(c3,new Data(11,4l,"Z")));
		Container<Data> c4 = ((UniqueIndex<Integer,Data>)mi.index(1)).get(11);
		System.out.println("Safe modify (true) : "+mi.safeModify(c4,new Data(111,4l,"Z")));
		
		Container<Data> c1 = ((UniqueIndex<Integer,Data>)mi.index(1)).get(6);
		mi.remove(c1);
		Container<Data> c2 = ((UniqueIndex<Integer,Data>)mi.index(1)).get(5);
		mi.modify(c2, new Data(55,88l,"NOP"));
		
		System.out.println("Sequence Test");
		SequenceIndex<Data> sequence = (SequenceIndex<Data>)mi.index(0);
		for (Container<Data> c : sequence){
			c.get().dump();
		}
		System.out.println("Unique (first) Test");
		UniqueIndex<Integer,Data> unique1 = (UniqueIndex<Integer,Data>)mi.index(1);
		for (Map.Entry<Integer,Container<Data>> p : unique1.entrySet() ){
			p.getValue().get().dump();
		}
		System.out.println("Unique (second) Test");
		UniqueIndex<Long,Data> unique2 = (UniqueIndex<Long,Data>)mi.index(2);
		for (Map.Entry<Long,Container<Data>> p : unique2.entrySet() ){
			p.getValue().get().dump();
		}
		System.out.println("Identity (first) Test");
		IdentityIndex<Data> identity1 = (IdentityIndex<Data>)mi.index(3);
		for (Data c : identity1.keySet() ){
			identity1.get(c).get().dump();
		}
	}
*/
}

