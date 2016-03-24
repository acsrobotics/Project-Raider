package org.usfirst.frc.team4716.robot.networking.clients;

import java.net.InetAddress;

import org.usfirst.frc.team4716.robot.networking.dataTypes.RobotMapMirror;

public class ConfigClient extends SocketClient<RobotMapMirror> {

	public ConfigClient(InetAddress hostName, int port) {
		super(hostName, port + 1, new RobotMapMirror());
	}
	
}
