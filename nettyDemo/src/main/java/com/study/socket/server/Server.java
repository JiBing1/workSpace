package com.study.socket.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
/**
 * 很显然，该服务只能服务于一个客户端
 * @author user
 *
 */
public class Server {
	private static ServerSocket ss = null;
	private static final long TIMEOUT = 5000L;
	private static long start;
	public static void main(String[] args) throws IOException, InterruptedException{
		ss = new ServerSocket(6666);
		final Socket clientSocket = ss.accept();
		start = System.currentTimeMillis();
		new Thread(){
			@Override
			public void run(){
				while(true){
					long currTime = System.currentTimeMillis();
					System.out.println((currTime - start));
					if((currTime - start) > TIMEOUT){
						try {
							clientSocket.close();
							break;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		String request,response;
		while((request = in.readLine()) != null){
			start = System.currentTimeMillis();
			System.out.println("recevice request from client:" + request);
			if("Done".equals(request)){
				break;
			}
			TimeUnit.SECONDS.sleep(2);
			response = "200 OK \r\nOver\r\n";
			out.write(response);
			out.flush();
		}
	}
}
