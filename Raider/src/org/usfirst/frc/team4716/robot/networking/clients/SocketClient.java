package org.usfirst.frc.team4716.robot.networking.clients;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import com.google.gson.Gson;


public class SocketClient<DataObjectType> {

	InetAddress hostName;
//	String hostName = "192.168.1.255";
	
	int port;
//	int port = 3003;
	
	Socket socket;
	
	String rawData;
	
	DataObjectType dataObject;
	
	public SocketClient(InetAddress hostName, int port, DataObjectType dOType){
		this.hostName   = hostName;
		this.port       = port;
		this.dataObject = dOType;
	}
	
	
	public String getRawData() throws Exception{
		
		// upgrade protocol 
		System.out.println("\nConnecting to server...");
		try {
			this.rawData = "";
			socket = new Socket(this.hostName, this.port);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			Scanner in = new Scanner(socket.getInputStream());
			out.write(" \n");
			while(in.hasNext()){
//				System.out.println(in.nextLine());
				this.rawData += in.nextLine();
			}
//			System.out.println(this.data);
			in.close();
			out.close();
			return this.rawData;
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void updateConfigData(){
		int retry = 0;
		while(retry < 10){
			try {
				this.getRawData();
				this.setDataObject((DataObjectType) new Gson().fromJson(this.rawData, dataObject.getClass()));
				break;
			} catch (Exception e) {
				// sleep for 0.5 seconds 
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				retry++;
				e.printStackTrace();
			}
		}
	}


	public synchronized DataObjectType getDataObject() {
		return dataObject;
	}
	
	public synchronized void setDataObject(DataObjectType data){
		this.dataObject = data;
	}
	
	
	// TODO synchronized method for IO 
	
}
