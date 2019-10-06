package chapter4;

import java.util.Comparator;

public class BinarySearchTree<T extends Comparable<T>> {
    private BinaryNode root;
    private Comparator<T> comparator;
    private static class BinaryNode<T>{
        T data;
        BinaryNode left;
        BinaryNode right;

        public BinaryNode(T data){
            this(data,null,null);
        }

        public BinaryNode(T data,BinaryNode left,BinaryNode right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public BinarySearchTree(){
        root = null;
    }

    public BinarySearchTree(T data){
        root = new BinaryNode(data,null,null);
    }

    public BinarySearchTree(Comparator comparator){
        this();
        this.comparator = comparator;
    }

    /**
     * 置空
     */
    public void makeEmpty(){
        root = null;
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        return root == null;
    }

    /**
     * 是否包含指定元素
     * @param data
     * @return
     */
    public boolean contains(T data){
        return contains(data,root);
    }

    public void insert(T data){
        root = insert(data,root);
    }

    /**
     * 寻找最小值
     * @return
     */
    public T finidMin(){
        BinaryNode<T> findResult = findMin(root);
        if(findResult == null){
            return null;
        }else{
            return findResult.data;
        }
    }

    public T findMax(){
        BinaryNode<T> findResult = findMax(root);
        if(findResult == null){
            return null;
        }else{
            return findResult.data;
        }
    }

    /**
     * 删除指定元素
     * @param data
     */
    public void remove(T data){
        root = remove(data,root);
    }

    private BinaryNode<T> insert(T data,BinaryNode<T> t){
        if(t == null){
            t = new BinaryNode(data);
            return t;
        }

        int compareResult = data.compareTo(t.data);
        if(compareResult > 0){
            t.right = insert(data,t.right);
        }else if(compareResult < 0){
            t.left = insert(data,t.left);
        }else{

        }
        return t;
    }

    private boolean contains(T data,BinaryNode<T> t){
        if(t == null){
            return false;
        }
         int compareResult = data.compareTo(t.data);

        if(compareResult < 0){
            return contains(data,t.left);
        }else if(compareResult > 0){
            return contains(data,t.right);
        }else{
            return true;
        }
    }

    private BinaryNode<T> findMin(BinaryNode<T> t){
        if(t == null){
            return t;
        }

        if(t.left != null){
            return findMin(t.left);
        }else{
            return t;
        }
    }

    private BinaryNode<T> findMax(BinaryNode<T> t){
        if(t == null || t.right == null)
            return t;
        return findMax(t.right);
    }

    /**
     * 应该要理解这个递归思想：remove方法首先会比较目标数据与当前节点数据，
     * 如果大于当前节点，则说明当前节点的右子树的结构要变，则对当前节点的右子树
     * 递归调用remove，这里有一个问题，remove应该返回什么？
     * 可以看出，其实返回的是需要改变的子树的根节点
     * @param data
     * @param t
     * @return
     */
    private BinaryNode<T> remove(T data,BinaryNode<T> t){
        if(t == null){
            return t;
        }

        int compareResult = data.compareTo(t.data);
        if(compareResult < 0){
            t.left = remove(data,t.left);
        }else if(compareResult > 0){
            t.right = remove(data,t.right);
        }else{
            //如果要删除的节点既有左子节点又有右子节点，则把当前的节点的值改为右子树中的最小值，然后再把右子树的最小值删除
            if(t.left != null && t.right != null){
                t.data = (T) findMin(t.right).data; //由于运行时泛型擦除机制，所以这里要告诉编译器返回值类型，否则编译报错
                t.right = remove(t.data,t.right);
            }else{
                t = t.left != null ? t.left : t.right;
            }
        }
        return t;
    }

    public void add(T data){
        BinaryNode node = new BinaryNode(data,null,null);
        if(root.data == null)
            root = node;
        else
            compare(node,root);
    }

    private void compare(BinaryNode<T> node1,BinaryNode<T> node2){
        if(comparator != null){
            if(comparator.compare(node1.data,node2.data) > 0){
                if(node2.right != null)
                    compare(node1,node2.right);
                else
                    node2.right = node1;
            }else if(comparator.compare(node1.data,node2.data) < 0){
                if(node2.left != null)
                    compare(node1,node2.left);
                else
                    node2.left = node1;

            }
        }else{
            if(node1.data.compareTo(node2.data) > 0){
                if(node2.right != null)
                    compare(node1,node2.right);
                else
                    node2.right = node1;
            }else if(node1.data.compareTo(node2.data) < 0){
                if(node2.left != null)
                    compare(node1,node2.left);
                else
                    node2.left = node1;
            }
        }
    }

    public void middlePrint(){
         middlePrint(root);
    }

    private void middlePrint(BinaryNode node){
        if(node.left != null)
            middlePrint(node.left);
        System.out.println(node.data);
        if(node.right != null){
            middlePrint(node.right);
        }
    }

    public static void main(String[] args){
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        System.out.println("是否为空树：" + binarySearchTree.isEmpty());
        binarySearchTree.insert(5);
        binarySearchTree.insert(4);
        binarySearchTree.insert(1);
        binarySearchTree.insert(7);
        binarySearchTree.insert(4);
        binarySearchTree.insert(9);
        binarySearchTree.insert(3);
        binarySearchTree.insert(6);
        binarySearchTree.insert(2);
        binarySearchTree.insert(8);
        System.out.println("树的最小元素：" + binarySearchTree.finidMin());
        System.out.println("树的最大元素：" + binarySearchTree.findMax());
        System.out.println("是否包含元素2:" + binarySearchTree.contains(2));
        System.out.println("是否包含元素10：" + binarySearchTree.contains(10));
        System.out.println("打印树：");
        binarySearchTree.middlePrint();
        System.out.println("删除元素5");
        binarySearchTree.remove(5);
        System.out.println("打印树：");
        binarySearchTree.middlePrint();
    }
}
