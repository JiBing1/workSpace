package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * 计算数组的最大和子区间
 * @author user
 *
 */
public class Test5 {
	/**
	 * 循环遍历每一个子区间分别求和，时间复杂度O(n^3)
	 * @param arr
	 */
	public static Map<String,String> f1(int[] arr){
		Map<String,String> map = new HashMap<String,String>();
		int maxSum = 0;
		List<Integer> result = new ArrayList<Integer>();
		//int begin = 0,end = 0;
		for(int i = 0; i < arr.length;i++){
			for(int j = i;j < arr.length;j++){
				//这个循环是没必要的，详见f2
				int sum = 0;
				for(int k = i;k <= j;k++){
					sum+=arr[k];
				}
				
				if(sum > maxSum){
					/*begin = i;
					end = j;*/
					maxSum = sum;
				}
			}
		}
		/*for(int m = begin;m <= end;m++){
			result.add(arr[m]);
		}*/
		map.put("result", String.valueOf(maxSum));
		map.put("range", result.toString());
		return map;
	}
	
	/**
	 * 改进f1，时间复杂度O(n^2)
	 * @param arr
	 */
	public static Map<String,String> f2(int[] arr){
		Map<String,String> map = new HashMap<String,String>();
		int maxSum = 0;
		List<Integer> result = new ArrayList<Integer>();
		//int begin = 0,end = 0;
		for(int i = 0; i < arr.length;i++){
			int sum = 0;
			for(int j = i;j < arr.length;j++){
				sum += arr[j];
				if(sum > maxSum){
					/*begin = i;
					end = j;*/
					maxSum = sum;
				}
			}
		}
		/*for(int m = begin;m <= end;m++){
			result.add(arr[m]);
		}*/
		map.put("result", String.valueOf(maxSum));
		map.put("range", result.toString());
		return map;
	}
	
	/**
	 * 递归版本，时间复杂度O(nlogn)
	 * 缺点，不利于统计具体哪个区间
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 */
	public static int f3(int[] arr,int left,int right){
		if(left == right){
			return arr[left] > 0 ? arr[left] : 0;
		}
		int middle = (left + right)/2;
		int leftMaxSum = f3(arr,left,middle);
		int rightMaxSum = f3(arr,middle + 1,right);
		int maxValueContainBound = getMaxValueByMid(arr,left,right);
		return Math.max(Math.max(leftMaxSum, rightMaxSum), maxValueContainBound);
	}
	
	public static int getMaxValueByMid(int[] arr,int left, int right){
		int middle = (left + right) / 2;
		int leftSum = 0,rightSum = 0;
		int sum = 0;
		for(int i = middle;i >= left;i--){
			sum += arr[i];
			if(sum > leftSum) leftSum = sum;
		}
		
		sum = 0;
		for(int j = middle + 1;j <= right;j++){
			sum += arr[j];
			if(sum > rightSum) rightSum = sum;
		}
		return leftSum + rightSum;
	}
	
	public static int[] genRnadomArray(int size){
		int[] arr =new int[size];
		for(int i = 0; i < size;i++){
			int ele = new Random().nextInt(1000) - 500;
			arr[i] = ele;
		}
		return arr;
	}
	
	public static int f4(int[] arr){
		int maxSum = 0,sum = 0;
		for(int i = 0;i < arr.length;i++){
			sum += arr[i];
			if(sum > maxSum){
				maxSum = sum;
			}else if(sum < 0){
				sum = 0;
			}
		}
		return maxSum;
	}
	
	public static <T> void print(String pre,T t){
		System.out.println(pre + t);
	}
	
	public static void main(String[] args){
		while(true){
			System.out.println("请输入数组大小：");
			Scanner sc = new Scanner(System.in);
			int size = sc.nextInt();
			print("数组大小：",size);
			long start = System.nanoTime();
			int[] arr = genRnadomArray(size);
			long end = System.nanoTime();
			print("生成数组耗时：",(end - start));
			//print("数组为：",Arrays.toString(arr));
			start = System.nanoTime();
			Map<String,String> result = f1(arr);
			end = System.nanoTime();
			print("f1结果:",result);
			print("f1耗时：",(end - start));
			
			start = System.nanoTime();
			result  = f2(arr);
			end = System.nanoTime();
			print("f2结果:",result);
			print("f2耗时：",(end - start));
			
			start = System.nanoTime();
			int sum  = f3(arr,0,arr.length - 1);
			end = System.nanoTime();
			print("f3结果:",sum);
			print("f3耗时：",(end - start));
			
			start = System.nanoTime();
			int maxSum  = f4(arr);
			end = System.nanoTime();
			print("f4结果:",maxSum);
			print("f4耗时：",(end - start));
			
		}
	}
}
