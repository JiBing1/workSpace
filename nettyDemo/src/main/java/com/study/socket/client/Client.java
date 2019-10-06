package com.study.socket.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class Client {
	private static Socket s = null;
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException{
		s = new Socket("127.0.0.1",6666);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String mes = "i am client!";
		String response = "";
		int i = 0;
		do{
			mes = "Message" + (i++) + "\n";
			bw.write(mes);
			bw.flush();
			while(!(response = br.readLine()).equals("Over")){  //要实现与服务端交互，必须协议好终止命令，而不能想当然的用！=null试图实现交互
				System.out.println("recevie response from server:" + response);
			}
			TimeUnit.SECONDS.sleep(3);
		}
		while(i < 100);
	}
}
