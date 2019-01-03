package com.ysan.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo1 {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8888);
		
		while (true) {
			Socket socket = server.accept();
			
			FileInputStream fis = new FileInputStream(new File("./src/test.html"));
			
			OutputStream out = socket.getOutputStream();
			
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			
			out.close();
			fis.close();
		}
	}
 
}
