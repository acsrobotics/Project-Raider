package org.usfirst.frc.team4716.robot.networking.clients;

import java.net.InetAddress;

import org.usfirst.frc.team4716.robot.networking.dataTypes.CameraData;



public class VisionClient extends SocketClient<CameraData> {

	public VisionClient(InetAddress hostName, int port) {
		super(hostName, port + 2, new CameraData());
	}

}
