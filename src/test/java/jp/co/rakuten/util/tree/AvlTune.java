package jp.co.rakuten.util.tree;

import java.util.TreeSet;

public class AvlTune extends AvlTreeParformanceTest{

	/**
	 * @param args
	 */
	static final int N = 200000;
	public static void main(String[] args) throws Exception {
		Integer[] datas = new  Integer[N];

		AvlTree<Integer> tree = new AvlTree<Integer>(); 
		TreeSet<Integer> set = new TreeSet<Integer>();
		// Generate data.
		for ( int i = 0 ; i < N ; i++) {
			datas[i] = new Integer(i);
		}
//		Collections.shuffle(Arrays.asList(datas));
		for ( int i = 0 ; i < datas.length; i++) {
//			set.add(datas[i]);
			tree.insert(datas[i]);
		}
		
	}
}
/*
rank   self  accum   count trace method
1 23.54% 23.54% 3137858 301709 jp.co.rakuten.util.tree.AvlNode.insertR
2 16.46% 40.00% 2937860 301713 jp.co.rakuten.util.tree.AvlNode.insert
3 11.31% 51.31% 3137858 301706 jp.co.rakuten.util.tree.AvlTree$1.compare
4 11.27% 62.58% 3137858 301705 java.lang.Integer.compareTo
5  5.60% 68.17%       4 301638 java.lang.Object.wait
6  5.60% 73.77%      14 301649 java.lang.ref.ReferenceQueue.remove
7  5.44% 79.21% 3137858 301708 jp.co.rakuten.util.tree.AvlNode.equilibrium
8  5.23% 84.44% 3337857 301698 java.lang.Integer.compareTo
9  5.08% 89.52% 3137858 301707 jp.co.rakuten.util.tree.AvlNode.deep
10  1.51% 91.03%  199999 301703 jp.co.rakuten.util.tree.AvlNode.insertR
11  1.23% 92.26%  200000 301690 jp.co.rakuten.util.tree.AvlTree.insert
12  1.13% 93.39%  199999 301704 jp.co.rakuten.util.tree.AvlNode.insert
13  1.11% 94.50%  199998 301710 jp.co.rakuten.util.tree.AvlNode.insert
14  0.85% 95.35%       1 301714 jp.co.rakuten.util.tree.AvlTune.main
15  0.74% 96.09%  199982 301712 jp.co.rakuten.util.tree.AvlNode.shiftRR
16  0.73% 96.82%  199999 301700 jp.co.rakuten.util.tree.AvlTree$1.compare
17  0.72% 97.54%  199999 301699 java.lang.Integer.compareTo
18  0.71% 98.25%  200000 301685 java.lang.Integer.<init>
19  0.33% 98.58%  200000 301689 jp.co.rakuten.util.tree.AvlNode.<init>
20  0.32% 98.91%  199982 301711 jp.co.rakuten.util.tree.AvlNode.deep
21  0.32% 99.23%  199999 301702 jp.co.rakuten.util.tree.AvlNode.equilibrium
22  0.32% 99.55%  199999 301701 jp.co.rakuten.util.tree.AvlNode.deep
23  0.29% 99.84%  200000 301684 java.lang.Number.<init>
*/