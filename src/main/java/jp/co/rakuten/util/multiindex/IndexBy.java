package jp.co.rakuten.util.multiindex;

import java.util.Arrays;
import java.util.List;

import jp.co.rakuten.util.ListWrapper;

public class IndexBy<T> extends ListWrapper<Index<T>> implements List<Index<T>>{
	public IndexBy(final Index<T> ...i) {
		this.container = Arrays.asList(i);
	}
	public Index<T> getIndex(final int n) {
		return container.get(n);
	}
}

