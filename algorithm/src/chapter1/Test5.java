package chapter1;

/**
 * 计算一个数子的二进制表示中1的个数
 */
public class Test5 {
    public static int count1(int i){
        if(i == 1){
            return 1;
        }
        if(i%2 == 1){
            return count1(i/2) + 1;
        }else{
            return count1(i / 2);
        }
    }

    public static void main(String[] args){
        System.out.println(count1(1));
        System.out.println(count1(2));
        System.out.println(count1(3));
        System.out.println(count1(4));
        System.out.println(count1(5));
        System.out.println(count1(11));
        System.out.println(count1(64));
        System.out.println(count1(100));
        System.out.println(count1(101));
    }
}
