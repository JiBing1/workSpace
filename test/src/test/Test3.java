package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Test3 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	//版本号，不加版本号如果反序列化生成的class文件与内存中class文件不一致，会导致反序列化失败 
	private final int i;
	private final String s;
	private int g;
	
	public Test3(int i,String s){
		this.i = i;
		this.s = s;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
		//serializing();
		deserializing();
		
	}

	private static void serializing() throws IOException, FileNotFoundException {
		File f = new File("aaa");
		System.out.println(f.getCanonicalPath());
		ObjectOutputStream oos = null;
		try{
			oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject((Object)new Test3(1,"abc"));
		}finally{
			if (oos != null)
				oos.close();
		}
	}
	
	private static void deserializing() throws FileNotFoundException, IOException, ClassNotFoundException{
		File f = new File("aaa");
		ObjectInputStream ois = null;
		try{
			ois = new ObjectInputStream(new FileInputStream(f));
			Test3 t = (Test3)ois.readObject();
			System.out.println(t);
		}finally{
			if(ois != null){
				ois.close();
			}
		}
	}
	
	@Override
	public String toString(){
		return String.format("i = %d,s = %s", i,s);
	}
}
