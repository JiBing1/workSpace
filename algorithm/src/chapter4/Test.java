package chapter4;

import java.io.File;

public class Test {
    public static void list(File file,int depth){
        printTab(depth);
        System.out.println("|--" + file.getName());
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files){
                list(f,depth + 1);
            }
        }
    }

    public static void printTab(int depth){
        for(int i = 0; i < depth;i++){
            System.out.print("  ");
        }
    }

    public static void list(String path){
        list(new File(path),0);
    }

    public static long size(File file){
        long totalSize = file.length();
        if(file.isDirectory()){
            for(File f : file.listFiles()){
                totalSize += size(f);
            }
        }
        return totalSize;
    }

    public static void main(String[] args){
        list(new File("").getAbsolutePath());
        System.out.println(size(new File("").getAbsoluteFile()));
    }
}
