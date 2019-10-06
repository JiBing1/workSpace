package Test;

import java.util.Arrays;

public class SortTest {
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    /**
     * 冒泡排序
     * 时间复杂度O(n`2)
     * 空间复杂度O(1)
     * 稳定性：稳定
     * @param arr
     */
    public static void bubbleSort(int [] arr){
        int len = arr.length;
        for(int i = len - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j + 1]){
                    swap(arr,j,j + 1);
                }
            }
        }
    }

    /**
     * 快速排序
     * 时间复杂度(最好：O(nlogn;平均:O(nlogn);最差：O(n`2))
     * 空间复杂度(O(logn))
     * 稳定性：不稳定
     * @param arr
     */
    public static void quickSort(int[] arr){
        quickSort(arr,0,arr.length - 1);
    }

    public static void quickSort(int[] arr,int low,int high){
        int i = low;
        int  j = high;
        while(i < j){
            while(i < j && arr[i] <= arr[j]){
                i++;
            }
            if(i < j && arr[i] > arr[j]){
                swap(arr,i,j);
            }
            while(i < j && arr[i] <= arr[j]){
                j--;
            }
            if(i < j && arr[i] > arr[j]){
                swap(arr,i,j);
            }
        }

        if(i > low) quickSort(arr,low,i - 1);
        if(j < high) quickSort(arr,j + 1,high);
    }


    /**
     * 插入排序
     * 时间复杂度(最好:O(n);平均:O(n`2);最差:O(n`2))
     * 空间复杂度(O(1))
     * 稳定性：稳定
     * @param arr
     */
    public static void insertSort(int[] arr){
        int i = 1;
        while(i < arr.length){
            int k = arr[i];
            int j = i - 1;
            while(j > -1 && arr[j] > k){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = k;
            i++;
        }
    }

    /**
     * 归并排序
     * 时间复杂度(O(nlogn))
     * 空间复杂度(O(nlogn))
     * 稳定性：稳定
     * @param arr
     */
    public static void mergeSort(int[] arr){
        mergeSort(arr,0, arr.length - 1);
    }

    public static void mergeSort(int[] arr,int i,int j){
        if(i < j){
            mergeSort(arr,i,(i + j) / 2);
            mergeSort(arr,(i + j) / 2 + 1,j);
            merge(arr,i,j);
        }
    }

    public static void merge(int[] arr,int i,int j){
        int middle = i + ((j - i) >> 1);
        int[] temp = new int[j - i + 1];
        int k = 0;
        int l = i;
        int r = middle + 1;
        while(l <= middle && r <= j){
            //这里可以看出是稳定的
            temp[k++] = arr[l] > arr[r] ? arr[r++] : arr[l++];
        }

        while(l <= middle){
            temp[k++] = arr[l++];
        }

        while(r <= j){
            temp[k++] = arr[r++];
        }

        for(int m = 0; m < temp.length; m++){
            arr[m + i] = temp[m];
        }
    }

    /**
     * 堆排序
     * 时间复杂度O(nlogn)
     * 空间复杂度(O(1))
     * 稳定性：不稳定
     * @param arr
     */
    public static void heapSort(int[] arr){
        int size = arr.length;
        buildMaxHeap(arr);
        for(int i = size - 1; i > 0; i--){
            swap(arr,0,i);
            size--;
            heapIfy(arr,0,size);
        }
    }

    /**
     * 初始化堆
     * 时间复杂度(O(n)),在计算排序总时间复杂度时可以抹去
     * @param arr
     */
    public static void buildMaxHeap(int[] arr){
        int len = arr.length;
        int start = len >> 2 - 1;
        for(int i = start; i >= 0; i--){
            heapIfy(arr,i,len);
        }
    }

    public static void heapIfy(int[] arr,int pos,int size){
        int left = 2 * pos + 1;
        int right  = 2 * pos + 2;
        int max = pos;

        if(left < size && arr[left] > arr[pos]){
            max = left;
        }

        if(right < size && arr[right] > arr[max]){
            max = right;
        }

        if(max != pos){
            swap(arr,pos,max);
            heapIfy(arr,max,size);
        }
    }

    public static void main(String[] args){
        int[] arr1 = generateArr();
        int[] arr2 = generateArr();
        int[] arr3 = generateArr();
        int[] arr4 = generateArr();
        int[] arr5 = generateArr();

        System.out.println("冒泡排序结果：");
        bubbleSort(arr1);
        System.out.println(Arrays.toString(arr1));
        System.out.println("插入排序结果：");
        insertSort(arr2);
        System.out.println(Arrays.toString(arr2));
        System.out.println("快速排序结果：");
        quickSort(arr3);
        System.out.println(Arrays.toString(arr3));
        System.out.println("归并排序结果：");
        mergeSort(arr4);
        System.out.println(Arrays.toString(arr4));
        System.out.println("堆排序结果：");
        heapSort(arr5);
        System.out.println(Arrays.toString(arr5));
    }

    public static int[] generateArr(){
        int[] arr = new int[]{2,1,9,6,4,2,8,5,6,3};
        return arr;
    }
}
