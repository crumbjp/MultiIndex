package jp.co.rakuten.util.collection;

public class ConstStdIterator<T> implements StdIterator<T>{
	final StdIterator<T> it; 
	public ConstStdIterator(StdIterator<T> it) {
		this.it = it;
	}
	@Override
	public T get() {
		return it.get();
	}
	@Override
	public boolean replace(T t){
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public boolean isEnd(){
		return it.isEnd();
	}
	@Override
	public ConstStdIterator<T> next(){
		it.next();
		return this;
	}
	@Override
	public ConstStdIterator<T> prev(){
		it.prev();
		return this;
	}
	@Override
	public boolean erase(){
		throw new UnsupportedOperationException("Operation has not been permitted .");
	}
	@Override
	public boolean equals(Object obj) {
		ConstStdIterator<T> in = (ConstStdIterator<T>)obj; 
		return it.equals(in.it);
	}
}
