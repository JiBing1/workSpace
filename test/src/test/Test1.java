package test;

import java.util.concurrent.TimeUnit;

public class Test1 {
	private static volatile boolean stopRequest;
	public static void main(String[] args) throws InterruptedException{
		Thread backgroundThread = new Thread(){
			@Override
			public void run(){
				int i = 0;
				while(!stopRequest){
					i++;
				}
			}
		};
		backgroundThread.start();
		TimeUnit.SECONDS.sleep(1);
		stopRequest = true;
	}
}
