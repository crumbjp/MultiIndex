package jp.co.rakuten.util.multiindex;

import java.util.Comparator;

import jp.co.rakuten.util.collection.avltree.AvlTree;
import jp.co.rakuten.util.collection.avltree.FindComparator;

/**
 * <h3>Multi indexable container.</h3>
 * <pre>
 *   <h3>Can select indexes from following</h3>
 *      - {@link SequenceIndex}          : by insertion
 *      - {@link IdentityIndex}          : by data-type identity
 *      - {@link OrderedUniqueIndex}     : by unique field
 *      - {@link OrderedNonUniqueIndex}  : by none unique field
 *    
 *   Reference above, how to access to data.
 *   <h3>About updating</h3>  
 *   Be careful when update associating data,
 *   because it need to update all indexes.
 *   So need to use modify() or rawModify() function absolutely.
 *      (There is a possibility to be going to improve in the future.)
 * <h3>Definition</h3> {@code
 *  // Target data-type
 *  class MyData implements Comparable<MyData>{
 *      Integer i1;
 *      Integer i2;
 *      String  s1;
 *      public MyData(Integer i1,Integer i2,String s1){
 *         this.i1 = i1; this.i2 = i2; this.s1 = s1;
 *       }
 *      public int compareTo(MyData o) {
 *        return this.i1.compareTo(o.i1);
 *       }
 *   }
 *   
 *  // MultiIndex definition.
 *  MultiIndex<MyData> multiIndex = new MultiIndex<MyData>(
 *    new IndexBy<MyData>(
 *       new SequenceIndex<MyData>(),
 *       new IdentityIndex<MyData>(),
 *       new OrderedUniqueIndex<Integer,MyData>(MyData.class.getField("i1")),
 *       new OrderedNonUniqueIndex<Integer,MyData>(MyData.class.getField("i2")),
 *       new OrderedNonUniqueIndex<String ,MyData>(MyData.class.getField("s1"))
 *     )
 *   );
 *   
 *  // Get indexes.
 *  SequenceIndex<MyData>                 sequence   = (SequenceIndex<MyData>)multiIndex.index(0);
 *  IdentityIndex<MyData>                 identity   = (IdentityIndex<MyData>)multiIndex.index(1);
 *  OrderedUniqueIndex<Integer,MyData>    unique     = (OrderedUniqueIndex<Integer,MyData>)multiIndex.index(2); 
 *  OrderedNonUniqueIndex<Integer,MyData> nonUnique1 = (OrderedNonUniqueIndex<Integer,MyData>)multiIndex.index(3); 
 *  OrderedNonUniqueIndex<String ,MyData> nonUnique2 = (OrderedNonUniqueIndex<String,MyData>)multiIndex.index(4);
 *  }   
 * <h3>Insertion</h3> {@code
 *  // Data input.
 *  multiIndex.add(new MyData(1,10,"d1"));    
 *  multiIndex.add(new MyData(2,10,"d2"));    
 *  multiIndex.add(new MyData(3,20,"d1"));    
 *  multiIndex.add(new MyData(4,20,"d2"));    
 *  multiIndex.add(new MyData(5,20,"d3"));    
 *  multiIndex.add(new MyData(6,30,"d1"));    
 *  multiIndex.add(new MyData(7,30,"d2"));    
 *  multiIndex.add(new MyData(8,30,"d3"));    
 *  multiIndex.add(new MyData(9,30,"d4"));    
 * }
 * <h3>Others</h3> {@code
 *  // Data update.( find & update )
 *  StdIterator<Pair<Integer,Record<MyData>>> it = i1UniqueIndex.find(3);
 *  Record<MyData> r = it.get().second;
 *  multiIndex.modify(r,new MyData(10,40,"d1"));
 *  
 *  // Data remove.
 *  multiIndex.remove(r);
 *  }
 * }
 * </pre>
 * @author hiroaki.kubota@mail.rakuten.co.jp
 * @see SequenceIndex
 * @see IdentityIndex
 * @see OrderedUniqueIndex
 * @see OrderedNonUniqueIndex
 * 
 * @param <T> Target data-type.
 */
public class MultiIndex <T> {
	private final IndexBy<T> indexBy;
	final AvlTree<Record<T>,Record<T>> dataContainer = new AvlTree<Record<T>,Record<T>>(
			new Comparator<Record<T>>() {
				@Override
				public int compare(Record<T> o1, Record<T> o2) {
					return o1.id.compareTo(o2.id);
				}
			},
			new FindComparator<Record<T>,Record<T>> () {
				@Override
				public int compare(Record<T> o1, Record<T> o2) {
					return o1.id.compareTo(o2.id);
				}
			}
	);
	private final Integer size;
	private final static Integer DEFAULT_SIZE = 4096;
	/**
	 * Instantiate with indexes.
	 * 
	 * @param indexBy Indexes definition.
	 */
	public MultiIndex(final IndexBy<T> indexBy ) {
		this.size = DEFAULT_SIZE;
		this.indexBy = indexBy;
		for ( Index<T> index : indexBy )
			index.opInit(this,this.size);
	}
	/**
	 * Instantiate with indexes. (T.B.D)
	 * 
	 * @param indexBy Indexes definition.
	 * @param size T.B.D
	 */
	public MultiIndex(final IndexBy<T> indexBy , final Integer size ) {
		this.size = size;
		this.indexBy = indexBy;
		for ( Index<T> index : indexBy )
			index.opInit(this,this.size);
	}
	/**
	 * Get index.
	 * 
	 * @param n Number of indexes order.
	 * @return Index object.
	 */
	public Index<T> index(final int n){
		return indexBy.getIndex(n);
	}
	/**
	 * <pre>
	 * Raw I/F. Associates new data.
	 *    (Be careful to unique restriction of unique indexes.)
	 * </pre>
	 * @param t Specifying data.
	 */
	@Deprecated
	public void rawAdd(final T t) {
		Record<T> c = new Record<T>(t);
		for ( Index<T> index : indexBy )
			index.opAdd(c);
		this.dataContainer.insert(c);
	}
	/**
	 * <pre>
	 * Associates new data.
	 * </pre>
	 * @param t New data. 
	 * @return true if success.
	 */
	public boolean add(final T t) {
		for ( Index<T> index : indexBy )
			if ( !index.opCheckAdd(t) )
				return false;
		this.rawAdd(t);
		return true;
	}
	/**
	 * Remove data.
	 * 
	 * @param c Target data.
	 */
	public void remove(final Record<T> c){
		for ( Index<T> index : indexBy ) 
			index.opRemove(c);
		this.dataContainer.erase(this.dataContainer.find(c));
	}
	/**
	 * <pre>
	 * Raw I/F. Updates associating data.
	 *    (Be careful to unique restriction of unique indexes.)
	 * </pre>
	 * @param c Target data.
	 * @param t New data.
	 */
	@Deprecated
	public void rawModify(final Record<T> c , final T t) {
		for ( Index<T> index : indexBy ) 
			index.opModify(c,t);
		c.t = t;
	}
	/**
	 * <pre>
	 * Updates associating data.
	 * </pre>
	 * @param c Target data.
	 * @param t New data.
	 * @return true if success.
	 */
	public boolean modify(final Record<T> c , final T t) {
		for ( Index<T> index : indexBy )
			if ( ! index.opCheckModify(c, t) )
				return false;
		this.rawModify(c,t);
		return true;
	}
	/**
	 * Remove all data.
	 */
	public void clear() {
		for ( Index<T> index : indexBy )
			index.opClear();
	}
}

