package test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * ArrayList在迭代过程中不要用ArrayList本身引用去修改list，因为迭代器迭代过程中都会去检测list有无改变，如果被改变则直接抛异常，可以迭代器本身去删除元素
 * CopyOnWriteArratlist可以在迭代过程中用list引用本身去修改对象，这主要是因为CopyOnWriteArratlist在修改list时并不会直接修改数组原有内容，而是将修改后的数组复制到另一个数组后返回
 * 
 * @author user
 *
 */
public class Test2 {
	public static ArrayList<Integer> list1 = new ArrayList<Integer>();
	public static CopyOnWriteArrayList<Integer> list2 = new CopyOnWriteArrayList<Integer>();
	static{
		list1.add(1);
		list1.add(2);
		list1.add(3);
		
		list2.add(1);
		list2.add(2);
		list2.add(3);
	}
	
	public static Field getSnapshotOfIterator(Iterator it){
		Class<Iterator> clazz = (Class<Iterator>) it.getClass();
		try {
			return clazz.getDeclaredField("snapshot");
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Field getArrayOfList(List list){
		Class<List> clazz = (Class<List>) list.getClass();
		try {
			return clazz.getDeclaredField("array");
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException{
		Iterator it = list2.iterator();
		Field f = getSnapshotOfIterator(it);
		Field f1 = getArrayOfList(list2);
		f.setAccessible(true);
		f1.setAccessible(true);
		CopyOnWriteArrayList<Integer> list3 = list2;
		//list2.remove(0);
		list2.set(0, 2);
		Object[] o = (Object[])f.get(it);
		Object[] o1 = (Object[])f1.get(list2);
		System.out.println(o.length);
		System.out.println(o1.length);
		while(it.hasNext()){
			System.out.println(it.next());
		}
		System.out.println(list3.size());
		/*Iterator<Integer> it = list1.iterator();
		while(it.hasNext()){
			int i = it.next();
			System.out.println(i);
			if(i == 1){
				list1.remove((Integer)i);
			}
		}*/
		/*for(Integer i : list1){
			System.out.println(i);
			if(i == 1){
				list1.remove((Integer)i);
			}
		}
		System.out.println(list2);*/
	}
}
