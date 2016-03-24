package org.usfirst.frc.team4716.robot.networking.clients;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class UdpClient {
	
	final String dummy_data = "        ";
	
//	String hostName = "192.168.1.255";// host name must be changed during the event 
										// 10.47.16.255
	String hostName;
	
//	int port = 3003;
	int port;
	
	InetAddress host;
	DatagramSocket socket;
	DatagramPacket packet;
	
	Socket configSocket;
	
	public UdpClient(String hostName, int port){
		this.hostName = hostName;
		this.port     = port;
	}
	
	public DatagramPacket broadCastPackets(){
		try{
			host = InetAddress.getByName(hostName);
			socket = new DatagramSocket(null);
			packet = new DatagramPacket(dummy_data.getBytes(), dummy_data.length(), host, port);
			socket.send(packet);
			System.out.println("UDP broadcast packets out...");
			packet.setLength(dummy_data.length());
			socket.receive(packet);
			System.out.println("Server located...");
			socket.close();
			System.out.println("Server: " + packet.getAddress() + " on port: " + packet.getPort());
			return packet;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		

		
	}
}
