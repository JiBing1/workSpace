package chapter2;

import java.util.EmptyStackException;

/**
 * 利用栈结构实现自定义简单计算器
 */
public class MyCalculator {
    /**
     * 后缀表达式运算，例如1223+*5++结果应为16
     * @param op
     * @return
     */
    public static double sufCalculate(String op){
        char[] chars = op.toCharArray();
        MyStack<Double> nums = new MyStack<>();
        for(int i = 0;i < chars.length;i++){
            char c = chars[i];
            if("+-*/".contains(String.valueOf(c))){
                 double a = nums.pop();
                 double b = nums.pop();
                 switch(c){
                     case '+':
                         nums.push(a + b);
                         break;
                     case '-':
                         nums.push(b - a);
                         break;
                     case '*':
                         nums.push(a * b);
                         break;
                     case '/':
                         nums.push(b / a);
                         break;
                 }
            }else {
                nums.push(Double.parseDouble(String.valueOf(c)));
            }
        }
        return nums.pop();
    }

    /**
     * 中缀表达式，即我们常用表达式，例如 1+2*3+2*(1+2)结果为13
     * @param op
     * @return
     */
    public static double midCalculate(String op){
        String str = mid2suf(op);
        return sufCalculate(str);
    }

    /**
     * 中缀转后缀
     * @param str
     * @return
     */
    public static String mid2suf(String str){
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        MyStack<Character> opStack = new MyStack<>();
        for(int i = 0;i < chars.length;i++){
            char c = chars[i];
            if("+-*/()".contains(String.valueOf(c))) {
                switch (c) {
                    case '+':
                    case '-':
                        while (!opStack.isEmpty()) {
                            char c1 = opStack.pop();
                            if (c1 != '(')
                                sb.append(c1);
                            else {
                                opStack.push(c1);
                                break;
                            }
                        }
                        opStack.push(c);
                        break;
                    case '*':
                    case '/':
                        opStack.push(c);
                        break;
                    case '(':
                        opStack.push(c);
                        break;
                    case ')':
                        while (!opStack.isEmpty()) {
                            char c1 = opStack.pop();
                            if (c1 != '(')
                                sb.append(c1);
                            else
                                break;
                        }
                }
            }else{
                sb.append(c);
            }
        }
        if(opStack != null){
            while (!opStack.isEmpty()){
                sb.append(opStack.pop());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(sufCalculate("1223-*5++"));
        System.out.println(midCalculate("2-3*(2+5)+1-2*(3*(1+1))"));
    }

    private static class MyStack<T>{
        class Node<T>{
            T data;
            Node next;

            Node(){

            }

            Node(T data,Node next){
                this.data = data;
                this.next = next;
            }
        }

        public Node<T> top;

        MyStack(){

        }

        public boolean isEmpty(){
            return top == null;
        }

        public boolean push(T data){
            Node node = new Node();
            node.data = data;
            node.next = top;
            top = node;
            return true;
        }

        public T pop(){
            if(top == null)
                throw new EmptyStackException();
            T data = top.data;
            top = top.next;
            return data;
        }
    }
}
