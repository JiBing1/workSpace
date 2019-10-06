package chapter1.test;

import java.util.Scanner;

/**
 * 幂运算
 */
public class Test3 {
    public static int pow(int m,int n){
        if(n == 1) return m;
        if(n % 2 == 0) return pow(m * m,n / 2) ;
        else return pow(m * m,n / 2)  * m;
    }

    public static int pow2(int m,int n){
        int result = 1;
        while(n > 0){
            result *= m;
            n--;
        }
        return result;
    }

    public static void main(String[] args){
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入底数：");
            int m = sc.nextInt();
            System.out.println("请输入幂数：");
            int n = sc.nextInt();
            long start = System.nanoTime();
            int result = pow(m,n);
            long end = System.nanoTime();
            System.out.println("递归结果：" + result + " 递归耗时：" + (end - start));
            start = System.nanoTime();
            result = pow2(m,n);
            end = System.nanoTime();
            System.out.println("循环结果：" + result + " 循环耗时：" + (end - start));
        }
    }
}
