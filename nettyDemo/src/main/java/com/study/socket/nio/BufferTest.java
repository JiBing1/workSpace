package com.study.socket.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BufferTest {
	public static void main(String[] args){
		ByteBuffer buf = ByteBuffer.allocate(11);
		printCPLM(buf);
		buf.put(new byte[]{1,2,3,4,5});
		buf.put(new byte[]{6,7,8,9,10,11});
		printCPLM(buf);
		buf.flip();
		printCPLM(buf);
		byte[] bytes = new byte[11];
		buf.get(bytes,buf.position(),5);
		printCPLM(buf);
		for(byte b : bytes){
			System.out.println(b);
		}
		buf.flip();
		printCPLM(buf);
		buf.compact();
		printCPLM(buf);

	}
	
	public static void printCPLM(Buffer buf){
		System.out.println("capacity:" + buf.capacity());
		System.out.println("position:" + buf.position());
		System.out.println("limit:" + buf.limit());
	}
}
