package test;

import java.util.Arrays;

public class MergeSortTest {
	private static int[] arr = new int[]{3,4,2,1,5,3,7,6,8};
	
	public static void main(String[] args){
		mergeSort(0,arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void mergeSort(int left,int right){
		if(left < right){
			mergeSort(left,(left + right) / 2);
			mergeSort((left + right) / 2 + 1,right);
		}
		merge(left,(left + right) / 2,right);
	}
	
	public static void merge(int left,int middle,int right){
		int[] leftArr = new int[middle - left + 1];
		int[] rightArr = new int[right - middle];
		
		for(int i = left; i < right + 1; i++){
			if(i > middle) rightArr[i - middle - 1] = arr[i];
			else leftArr[i - left] = arr[i];
		}
		
		int m = 0;
		int n = 0;
		int k = left;
		while(k < right + 1){
			if(m >= leftArr.length){
				arr[k] = rightArr[n];
				k++;
				n++;
				continue;
			}
			
			if(n >= rightArr.length){
				arr[k] = leftArr[m];
				k++;
				m++;
				continue;
			}
			
			if(leftArr[m] <= rightArr[n]){
				arr[k] = leftArr[m];
				m++;
				k++;
				continue;
			}
			
			if(rightArr[n] < leftArr[m]){
				arr[k] = rightArr[n];
				n++;
				k++;
			}
		}
	}
}
