package jp.co.rakuten.util.multiindex;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IndexBy<T> implements Iterable<Index<T>>{
	final List<Index<T>> container;
	public IndexBy(final Index<T> ...i) {
		container = Arrays.asList(i);
	}
	public Index<T> getIndex(final int n) {
		return container.get(n);
	}
	@Override
	public Iterator<Index<T>> iterator() {
		return container.iterator();
	}
}

