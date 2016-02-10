package server;

import java.beans.IndexedPropertyChangeEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
	
	static String content;
	
	public static void main(String[] args) {
		try {
			start_server(3002);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void start_server(int port) throws Exception{
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("server started.. ");
		while(true){
			Socket client = serverSocket.accept();
			System.err.println("Client request handling");
			FileServer.content = read("test.json");
			
			PrintWriter out = new PrintWriter(client.getOutputStream());
			out.write(FileServer.content);
			System.err.println("Message out");
			System.out.println(FileServer.content);
			out.close();
			client.close();
		}
	}
	
	private static String read(String fileName){
		String text = "";
		try{
			StringBuilder sb = new StringBuilder();
			BufferedInputStream input = 
					new BufferedInputStream(new FileInputStream(fileName));
			while(input.available() > 0){
				sb.append((char)input.read());
			}
			text = sb.toString();
			input.close();
		} catch (IOException ex){
			System.out.println("Unable to perform IO operations\n"
					+ "Please make sure the input file name is correct");
		}
		return text;
	}
}