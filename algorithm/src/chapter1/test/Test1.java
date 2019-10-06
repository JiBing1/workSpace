package chapter1.test;

/**
 * 二分法查找，时间复杂度O(logn)
 * 数组必须有序的
 */
public class Test1 {
    /**
     * 递归版本
     * @param arr
     * @param start
     * @param end
     * @param target
     * @return
     */
    public static int binarySearch(int[] arr,int start,int end,int target){
        int middle = (start + end) / 2;
        if(target == arr[middle]){
            return middle;
        }
        if(start == end) return -1;
        if(target < arr[middle]){
            return binarySearch(arr,start,middle,target);
        }else{
            return binarySearch(arr,middle + 1,end,target);
        }
    }

    public static int binarySearch2(int[] arr,int target){
        int low = 0,high = arr.length;
        while (low < high){
            int middle = (low + high) / 2;
            if(target < arr[middle]){
                high = middle;continue;
            }
            if(target > arr[middle]){
                low = middle + 1;continue;
            }
            return middle;
        }
        return -1;
    }

    public static int search(int[] arr,int target){
        return binarySearch(arr,0,arr.length - 1,target);
    }

    public static void main(String[] args){
        int[] arr = new int[]{1,2,4,5};
        System.out.println(search(arr,6));
        System.out.println(binarySearch2(arr,6));
    }
}
