package com.study.socket.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 服务于多个客户端
 * @author user
 *
 */
public class ServerForManyClient {
	private static ServerSocket ss = null;
	private static ExecutorService threadPool = null;
	
	static{
		try {
			ss = new ServerSocket(6666);
			threadPool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException{
		while(true){
			final Socket s = ss.accept();
			threadPool.submit(new HandleRequestTask(s));
		}
	}
	
	private static class HandleRequestTask implements Callable<Void>{
		private final Socket s;
		public HandleRequestTask(Socket s){
			this.s = s;
		}
		@Override
		public Void call() throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			String request,response;
			while((request = br.readLine()) != null){
				System.out.println(Thread.currentThread().getName() + " handel request from " + request);
				response = handleRequest(request);
				if("Over".equals(response)){
					System.out.println(Thread.currentThread().getName() + " end service,socket close!");
					break;
				}
				bw.write(response + "\r\nOver\r\n");
				bw.flush();
			}
			close();
			return null;
		}
		
		private String handleRequest(String request) throws InterruptedException{
			TimeUnit.SECONDS.sleep(2);
			if("Done".equalsIgnoreCase(request)){
				return "Over";
			}
			return "OK";
		}
		
		private void close() throws IOException{
			s.close();
		}
	}
}
