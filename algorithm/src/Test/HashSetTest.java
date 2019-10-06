package Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HashSetTest {
    public static void main(String[] args){
        HashSet<MyClass> set  = new HashSet<>();
        List<MyClass> list = new ArrayList<>();
        MyClass c1 = new MyClass("aaa","bbb","ccc");
        MyClass c2 = new MyClass("aaa","aaa","bbb");
        list.add(c1);
        list.add(c2);
        set.addAll(list);
        list = new ArrayList<>();
        MyClass c3 = new MyClass("aaa","bbb","ddd");
        MyClass c4 = new MyClass("aaa","ccc","ddd");
        list.add(c3);
        list.add(c4);
        set.addAll(list);
        System.out.println(set);
    }

    public static class MyClass{
        private String str1;
        private String str2;
        private String str3;

        public MyClass(String str1,String str2,String str3){
            this.str1 = str1;
            this.str2 = str2;
            this.str3 = str3;
        }

        public String getStr1() {
            return str1;
        }

        public void setStr1(String str1) {
            this.str1 = str1;
        }

        public String getStr2() {
            return str2;
        }

        public void setStr2(String str2) {
            this.str2 = str2;
        }

        public String getStr3() {
            return str3;
        }

        public void setStr3(String str3) {
            this.str3 = str3;
        }

        @Override
        public boolean equals(Object o){
            MyClass c = (MyClass) o;
            return this.getStr1().equals(c.getStr1()) && this.getStr2().equals(c.getStr2());
        }

        @Override
        public int hashCode(){
            return this.getStr1().hashCode() + this.getStr2().hashCode();
        }

        @Override
        public String toString() {
            return "MyClass{" +
                    "str1='" + str1 + '\'' +
                    ", str2='" + str2 + '\'' +
                    ", str3='" + str3 + '\'' +
                    '}';
        }
    }
}
