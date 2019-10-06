package test;

/**
 * 打印给定数组的所有排列组合
 * @author user
 *
 */
public class Test4 {

	public static void permute(String str){
		char[] chars = str.toCharArray();
		permute(chars,0,chars.length);
	}
	
	public static void permute(char[] str,int low,int high){
		if(low == high){
			System.out.println(str);
		}
		for(int i = low; i < high; i++){
			if(isSwap(str,low,i)){
				swap(str,low,i);
				permute(str,low + 1,high);
				swap(str,low,i);
			}
		}
	}
	
	public static boolean isSwap(char[] str,int low,int high){
		for(int i = low; i < high; i++){
			if(str[i] == str[high]){
				return false;
			}
		}
		return true;
	}
	
	public static void swap(char[] str,int i,int j){
		char temp = str[i];
		str[i] = str[j];
		str[j] = temp;
	}
	
	public static void main(String[] args){
		String str = "abcb";
		permute(str);
	}
}
