package test;

public class Test13 {
	public static class Collection<T>{
		private Object[] collections;
		private int size;
		private static final int DEFAULT_SIZE = 10;
		
		public Collection(){
			this(DEFAULT_SIZE);
		}
		
		public Collection(int size){
			collections = new Object[size];
		}
		public boolean isEmpty(){
			return size == 0;
		}
		
		public void makeEmpty(){
			size = 0;
		}
		
		public void insert(T t){
			if(size == collections.length){
				//TODO throws Exception
				throw new RuntimeException();
			}
			collections[size++] = t;
		}
		
		
		public void fastrermove(int index){
			int copySize = size - 1 - index;
			if(copySize > 0){
				System.arraycopy(collections, index + 1, collections, index, copySize);
			}
			collections[--size] = null;
		}
	}
	
}
