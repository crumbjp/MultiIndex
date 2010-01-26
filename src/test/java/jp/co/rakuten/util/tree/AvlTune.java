package jp.co.rakuten.util.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class AvlTune extends AvlTreeParformanceTest{

	/**
	 * @param args
	 */
	static final int N = 100000;
	public static void main(String[] args) throws Exception {
		Integer[] datas = new  Integer[N];

		AvlTree<Integer> tree = new AvlTree<Integer>(); 
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
