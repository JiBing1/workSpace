package chapter2;

import org.omg.CORBA.Any;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<AnyType> implements Iterable<AnyType>{
    private static final int DEFAULT_ZISE = 10;

    private int theSize;
    private AnyType[] theItems;

    public MyArrayList(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_ZISE);
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void trimToSize(){
        ensureCapacity(size());
    }

    public AnyType set(int idx,AnyType newValue){
        if(idx < 0||idx >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType old = theItems[idx];
        theItems[idx] = newValue;
        return old;
    }

    public void ensureCapacity(int newCapacity){
        if(newCapacity < size()) return;
        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[DEFAULT_ZISE];
        for(int i = 0; i < size();i++){
            theItems[i] = old[i];
        }
    }

    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }

    public void add(int idx,AnyType x){
        if(theItems.length == size()) ensureCapacity(size() * 2 + 1);
        for(int i = size();i > idx;i--){
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = x;
        theSize++;
    }

    public AnyType remove(int idx){
        AnyType removesItem = theItems[idx];
        for(int i = idx;i < size();i++){
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removesItem;
    }

    public Iterator<AnyType> iterator(){
        return new MyIterator();
    }

    private class MyIterator implements Iterator<AnyType>{
        private int current = 0;
        public boolean hasNext(){
            return current < size();
        }

        public AnyType next(){
            if(!hasNext())
                throw new NoSuchElementException();
            return theItems[current++];
        }

        public void remove(){
            MyArrayList.this.remove(--current);
        }
    }
}
