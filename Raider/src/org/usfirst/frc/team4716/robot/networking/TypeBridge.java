package org.usfirst.frc.team4716.robot.networking;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class TypeBridge {
	
	// copy the value from a subset non-static object to its parent static object
	public static <T1, T2> void copy(T1 original, T2 newData){
		// get declared fields from two objects 
		Field[] originalFields = original.getClass().getDeclaredFields();
		Field[] newDataFields  = newData.getClass().getDeclaredFields();
		
		
		// extracted field name into an array list 
		ArrayList<String> originalFieldNames = new ArrayList<String>();
		
		for(Field f : originalFields){
			originalFieldNames.add(f.getName());
		}
		
		// using array list to look up name that exists in the parent object 
		for(Field f : newDataFields){
			if(originalFieldNames.contains(f.getName())){
				try {
					// update the parent field 
					// The documentation for Field#get is HORRIBLE! 
					Double d;
					d = (Double)f.get(newData);
					original.getClass().getDeclaredField(f.getName()).setDouble(null, d);
//					System.out.println("Update field: " + f.getName() + " with value of " + original.getClass().getDeclaredField(f.getName()).getDouble(null));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
