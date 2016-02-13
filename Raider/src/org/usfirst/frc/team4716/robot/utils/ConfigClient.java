package org.usfirst.frc.team4716.robot.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.Scanner;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.usfirst.frc.team4716.robot.RobotMap;

import com.sun.corba.se.impl.io.TypeMismatchException;

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
	
	@SuppressWarnings("static-access")
	private static void parseRuntimeResult(){
		// TODO only parse variable such as speed as rate 

		/**Idk who's code this was, but it was way off...
		JSONObject root = new JSONObject(ConfigClient.configJsonText);
		//RobotMap.TEST_SPEED = root.getDouble("speed");
		System.out.println("OEHHEHEHEHEH : " + root.getDouble("speed"));
		
		// just trying to mess with gyro port for experiment 
		//RobotMap.gyroPort = root.getJSONObject("sensor_configs")
		//						.getJSONObject("gyro")
		//						.getInt("port");
		 */
		
		/**Fixed your JSON Parsing, whoever wrote that. Just use the value of testSpeed
		 * for whatever it was that you were doing.
		 * @author Matthewacon
		 */
		JSONParser jsnpars = new JSONParser();
		double testSpeed = 0;
		try {
			Object o = jsnpars.parse(new StringReader(configJsonText), (ContainerFactory)null);
			if (!(o instanceof Double)) {
				throw new TypeMismatchException();
			} else {
				testSpeed = (Double)o;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TypeMismatchException e) {
			e.printStackTrace();
		}
		//Some deprecated value from the last broken code block?
//		RobotMap.TEST_SPEED = testSpeed;
		
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
