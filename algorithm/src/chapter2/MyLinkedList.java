package chapter2;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<AnyType> implements Iterable<AnyType> {
    private int size;
    private int modCount;
    private Node<AnyType> head;
    private Node<AnyType> tail;

    public MyLinkedList(){
        doclear();
    }

    public void clear(){
        doclear();
    }

    public void doclear(){
        head = new Node<AnyType>(null,null,null);
        tail  = new Node<AnyType>(null,null,null);
        head.next = tail;
        tail.prev = head;

        size = 0;
        modCount++;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean add(AnyType t){
        add(size(),t);
        return true;
    }

    private void add(int idx,AnyType t){
        addBefore(getNode(idx),t);
    }

    private void addBefore(Node<AnyType> t,AnyType e){
        Node<AnyType> newNode = new Node<>(e,t.prev,t);
        newNode.prev.next = newNode;
        t.prev = newNode;

        size++;
        modCount++;
    }

    public AnyType set(int idx,AnyType e){
        Node<AnyType> target = getNode(idx);
        AnyType oldData = target.data;
        target.data = e;
        return oldData;
    }

    public AnyType remove(int idx){
        return remove(getNode(idx));
    }

    private AnyType remove(Node<AnyType> e){
        e.next.prev = e.prev;
        e.prev.next = e.next;
        size--;
        modCount++;
        return e.data;
    }

    public AnyType get(int idx){
        return getNode(idx).data;
    }

    public Node<AnyType> getNode(int idx){
        return getNode(idx,0,size());
    }

    private Node<AnyType> getNode(int idx,int lower,int upper){
        if(idx < lower || idx > upper)
            throw new IndexOutOfBoundsException();
        Node<AnyType> p;

        if(idx < size() / 2){
            p = head.next;
            for(int i = 0; i < idx;i++){
                p = p.next;
            }
        }else{
            p = tail;
            for(int i = size(); i > idx;i--){
                p = p.prev;
            }
        }
        return p;
    }

    public Iterator<AnyType> iterator(){
        return new MyIterator();
    }

    public static class Node<AnyType>{
        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;

        public Node(AnyType data,Node prev,Node next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private class MyIterator implements Iterator<AnyType>{
        private Node<AnyType> current = head.next;
        private int exceptedModCount = modCount;
        private boolean okToRemove = false;
        public boolean hasNext(){
            return current != tail;
        }

        public AnyType next(){
            if(exceptedModCount != modCount)
                throw new ConcurrentModificationException();
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            AnyType t = current.data;
            current = current.next;
            okToRemove = true;
            return t;
        }

        public void remove(){
            if(exceptedModCount != modCount)
                throw new ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.remove(current.prev);
            exceptedModCount++;
            okToRemove = false;
        }
    }

}
