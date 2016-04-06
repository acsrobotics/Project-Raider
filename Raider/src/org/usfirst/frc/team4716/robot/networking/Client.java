package org.usfirst.frc.team4716.robot.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.NoRouteToHostException;

import org.usfirst.frc.team4716.robot.networking.clients.ConfigClient;
import org.usfirst.frc.team4716.robot.networking.clients.UdpClient;
import org.usfirst.frc.team4716.robot.networking.clients.VisionClient;
import org.usfirst.frc.team4716.robot.networking.dataTypes.CameraData;
import org.usfirst.frc.team4716.robot.networking.dataTypes.RobotMapMirror;



public class Client {
	
	String hostName;
	int    port;
	
	UdpClient          udpClient;
	ConfigClient       configSocketClient;
	VisionClient       visionSocketClient;
	
	// For hostName, use "10.47.16.255"
	public Client(String hostName, int port) throws NoRouteToHostException{
		
		int retryCounter = 0;
		
		this.udpClient = new UdpClient(hostName, port);
		
		DatagramPacket broadcastPacket;
		
		// keep broadcasting if unable to find the server 
		while((broadcastPacket = this.udpClient.broadCastPackets()) == null){
			
			if(retryCounter >= 20){
				// stop trying after 20 times 
				// I'm not sure if it is really needed 
				throw new NoRouteToHostException();
			}
			
			// send packet every two seconds in case the broadcasting request jams traffic
			try {
				Thread.sleep(1000);
				retryCounter++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.configSocketClient = new ConfigClient(broadcastPacket.getAddress(), broadcastPacket.getPort());
		this.visionSocketClient = new VisionClient(broadcastPacket.getAddress(), broadcastPacket.getPort());
		try {
			this.update();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void update(){
		this.configSocketClient.updateData();
		this.visionSocketClient.updateData();
	}
	
	@SuppressWarnings("unchecked")
	public synchronized <T> T getDataObject(Class<T> dataObjType) throws IOException{
		if(dataObjType.getName().equals(RobotMapMirror.class.getName())){
			return (T) this.configSocketClient.getDataObject();
		}else if(dataObjType.getName().equals(CameraData.class.getName())){
			return (T) this.visionSocketClient.getDataObject();
		}else{
			throw new IOException("Unable to read DataObject");
		}
	}
	
}
