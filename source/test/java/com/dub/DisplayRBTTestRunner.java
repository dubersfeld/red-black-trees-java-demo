package com.dub;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class DisplayRBTTestRunner {
	
	public static void main(String[] args) {

		System.out.println("main begin");
		  
		Result result = JUnitCore.runClasses(DisplayRBTTestJunit.class);
			  
		for (Failure failure : result.getFailures()) { 
			System.out.println(failure.toString());
		}
			  
		System.out.println("result was successful: " + result.wasSuccessful());
	   
	}
	
}