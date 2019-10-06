package chapter1;

import java.util.Random;
import java.util.Scanner;

/**
 * 求指定大小数组的中位数
 */
public class Test1 {
    public static int f1(int[] arr){
        int length = arr.length;
        int target  = length / 2;
        return findMiddle(arr,0,arr.length - 1,target);
    }

    public static int findMiddle(int[] arr,int start,int end,int target){
        int i = start,j = end;
        while(i < j){
            while(i < j&&arr[i] <= arr[j]){
                i++;
            }
            if(i < j&&arr[i] < arr[j]){
                swap(arr[i],arr[j]);
            }

            while(i < j&&arr[i] <= arr[j]){
                j--;
            }
            if(i < j&&arr[i] < arr[j]){
                swap(arr[i],arr[j]);
            }
        }
        if(i == target) return arr[i];
        if(i < target) return findMiddle(arr,i + 1,end,target);
        else return findMiddle(arr,0,i -1,target);
    }

    public static void swap(int i,int j){
        int temp = i;
        i = j;
        j = temp;
    }

    public static void main(String[] args){
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入数组大小:");
            int size = scanner.nextInt();
            int[] arr = genFixSizeArr(size);
            long start = System.currentTimeMillis();
            int k = f1(arr);
            long end = System.currentTimeMillis();
            System.out.println("数组大小：" + arr.length + " " + "查找结果：" + k + " " + "耗时：" + (end - start));


            long start2 = System.currentTimeMillis();
            int k2 = f2(arr);
            long end2 = System.currentTimeMillis();
            System.out.println("数组大小：" + arr.length + " " + "查找结果：" + k2 + " " + "耗时：" + (end2 - start2));
        }
    }

    public static int[] genFixSizeArr(int size){
        int[] arr = new int[size];
        for(int i = 0; i < arr.length;i++){
            arr[i] = new Random(Integer.MAX_VALUE).nextInt();
        }
        return arr;
    }

    public static int f2(int[] arr){
        int length = arr.length;
        for(int i = 0; i < length;i++){
            for(int j = 0;j < length - 1- i;j++){
                if(arr[j] > arr[j + 1]){
                    swap(arr[j],arr[j + 1]);
                }
            }
        }

        return arr[length/2];
    }
}
