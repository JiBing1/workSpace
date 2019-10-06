package utils;

import java.util.Properties;

public class PropertiesUtil {
	private static Properties pros = new Properties();
	
	private PropertiesUtil(){};
	
	public static void loadFile(String fielName){
		try{
			pros.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fielName));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		return pros.getProperty(key);
	}
}
