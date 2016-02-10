package org.usfirst.frc.team4716.robot.utils;

import org.json.*;
import org.usfirst.frc.team4716.robot.RobotMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;;

public class ConfigClient {
	
	private static String serverAddr;
	private static int    port;
	private static String configJsonText;
	
	public static void init(String serverAddr, int port){
		ConfigClient.serverAddr = serverAddr;
		ConfigClient.port       = port;
	}
	
	public static void initConfigUpdate(){
		if(queryServer()){
			parseBootTimeResult();
		}else{
			bootTimeFallback();
		}
	}
	
	public static void runtimeConfigUpdate(){
		if(queryServer()){
			parseRuntimeResult();
		}else{
			runtimeFallback();
		}
	}
	
	private static void bootTimeFallback(){
		// TODO 
	}
	
	private static void runtimeFallback(){
		// TODO finish error handling routine in case the server query fails
	}
	
	private static void parseBootTimeResult(){
		// TODO parse everything 
	}
	
	private static void parseRuntimeResult(){
		// TODO only parse variable such as speed as rate 
		
		JSONObject root = new JSONObject(ConfigClient.configJsonText);
		//RobotMap.TEST_SPEED = root.getDouble("speed");
		System.out.println("OEHHEHEHEHEH : " + root.getDouble("speed"));
		// just trying to mess with gyro port for experiment 
		//RobotMap.gyroPort = root.getJSONObject("sensor_configs")
		//						.getJSONObject("gyro")
		//						.getInt("port");
	}
	
	private static boolean queryServer() {
		try {
			Socket server;
			
			server = new Socket(ConfigClient.serverAddr, ConfigClient.port);
			PrintWriter out = new PrintWriter(server.getOutputStream());
			Scanner in = new Scanner(server.getInputStream());
			out.println("");
			ConfigClient.configJsonText = "";
			while(in.hasNext()){
				ConfigClient.configJsonText += in.nextLine();
			}
			ConfigClient.configJsonText = configJsonText.replaceAll("null", "");
			
			// cleaning up
			server.close();
			in.close();
			// successfully established connection
			return true; 
		} catch (IOException e) {
			// failed to connect to the server
			//e.printStackTrace();
			return false;
		}
		
	}
	
}
