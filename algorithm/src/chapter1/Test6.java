package chapter1;

/**
 * 输出一个字符串的所有排列组合
 */
public class Test6 {
    public static void permute(String str){
        char[] chars = str.toCharArray();
        permute(chars,0,chars.length);
    }

    public static void permute(char[] str,int low,int high){
        if(low == high){
            System.out.println(str);
        }
        for(int i = low; i < high;i++){
            if(isSwap(str,low,i)) {
                swap(str, low, i);
                permute(str, low + 1, high);
                swap(str, low, i);
            }
        }
    }

    public static void swap(char[] arr,int i,int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean isSwap(char[] str,int m,int n){
        boolean flag = true;
        for(int i=m;i<n;i++){
            flag = true;
            if(str[i] == str[n]){
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args){
        String str = "abb";
        permute(str);
    }
}
