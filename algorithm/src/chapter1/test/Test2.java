package chapter1.test;

/**
 * 求两个整数的最大公约数
 */
public class Test2 {
    public static int gcd2(int m,int n){
        while(n != 0){
            int rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }

    public static int gcd(int m,int n){
        if(m == n) return m;
        if(m > n) {
            return gcd2(m, n);
        }else{
            return gcd2(n,m);
        }
    }

    public static void main(String[] args){
        System.out.println(gcd(49,21));
    }
}
