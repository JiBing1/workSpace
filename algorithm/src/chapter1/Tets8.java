package chapter1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 生成随机序列的随机置换
 */
public class Tets8 {
    public static int genI2J(int i,int j){
        return i + new Random().nextInt(j - i + 1);
    }

    public static int[] genArray1(int n){
        int[] arr = new int[n];
        for(int i = 0; i < n;i++){
            boolean flag = true;
            int target = 0;
            while(flag) {
                target = genI2J(1,n);
                int j;
                for (j = 0; j < i; j++) {
                    if (arr[j] == target)
                        break;
                }
                if(j == i)
                    flag = false;
            }
            arr[i] = target;
        }
        return arr;
    }

    public static int[] genArray2(int n){
        int arr[] = new int[n];
        Map<Integer,Boolean> map = new HashMap<>();
        for(int i = 0; i < n;i++){
            boolean flag = true;
            int target = 0;
            while(flag) {
                target = genI2J(1,n);
                if(map.get(target) == null) {
                    flag = false;
                    map.put(target,true);
                }
            }
            arr[i] = target;
        }
        return arr;
    }

    public static int[] genArray3(int n){
        int arr[] = new int[n];
        for(int i = 0;i < n;i++){
            arr[i] = i + 1;
        }
        //保证每个数都有可能与其他数置换
        for(int i = 0;i < n;i++){
            swapReferences(arr,i,genI2J(0,i));
        }
        return arr;
    }

    public static void swapReferences(int[] arr,Integer i,Integer j){
        Integer tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args){
        long start = System.nanoTime();
        Arrays.toString(genArray1(8));
        long end = System.nanoTime();
        System.out.println("N = "+ " 方法1运行时长 ： " + (end - start));


        start = System.nanoTime();
        Arrays.toString(genArray2(8000000));
        end = System.nanoTime();
        System.out.println("N = "  + " 方法2运行时长 ： " + (end - start));

        start = System.nanoTime();
        Arrays.toString(genArray3(4));
        end = System.nanoTime();
        System.out.println("N = " + " 方法3运行时长 ： " + (end - start));
    }

}
