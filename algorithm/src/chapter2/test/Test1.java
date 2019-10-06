package chapter2.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Test1 {
    public static void printLots(Collection<Integer> L, Collection<Integer> P){
        int j = 0;
        Iterator<Integer> it = L.iterator();
        Iterator<Integer> it2 = P.iterator();
        while(it2.hasNext())
        {
            int l = it2.next();
            if(l < 1||l > L.size()){
                throw new ArrayIndexOutOfBoundsException();
            }
            Collection<Integer> newCollection = new ArrayList<>();

            while(it.hasNext()){
                if(j == l) {
                    break;
                }
                Collections.addAll(newCollection,it.next());
                j++;
            }
            System.out.println(Collections.max(newCollection));
        }
    }

    public static void main(String[] args){
        ArrayList<Integer> list1 = new ArrayList<>();
        for(int i = 1; i < 11;i++){
            list1.add(i);
        }

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);
        list2.add(10);
        printLots(list1,list2);
    }
}
