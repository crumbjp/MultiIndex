package jp.co.rakuten.util.collection.avltree;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

import jp.co.rakuten.util.collection.avltree.AvlTree;

public class AvlTune {

	/**
	 * @param args
	 */
	static final int N = 100000;
	public static void main(String[] args) throws Exception {
		Integer[] datas = new  Integer[N];

		AvlTree<Integer,Integer> tree = new AvlTree<Integer,Integer>(); 
		TreeSet<Integer> set = new TreeSet<Integer>();
		// Generate data.
		for ( int i = 0 ; i < N ; i++) {
			datas[i] = new Integer(i);
		}
		Collections.shuffle(Arrays.asList(datas));
		for ( int i = 0 ; i < datas.length; i++) {
			set.add(datas[i]);
			tree.insert(datas[i]);
		}
		
	}
}
