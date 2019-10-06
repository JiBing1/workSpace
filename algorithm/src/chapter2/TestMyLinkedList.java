package chapter2;

import java.util.Iterator;

public class TestMyLinkedList {
    public static void main(String[] args){
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("aaa");
        myLinkedList.add("bbb");
        myLinkedList.add("ccc");

        System.out.println(myLinkedList.get(0));

        Iterator<String> iterator = myLinkedList.iterator();
        while(iterator.hasNext()){
            String s = iterator.next();
            System.out.println(s);
            if("aaa".equals(s)){
                iterator.remove();
            }
        }

        System.out.println(myLinkedList.get(0));
    }
}
