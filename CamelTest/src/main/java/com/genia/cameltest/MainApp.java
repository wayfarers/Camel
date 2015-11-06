package com.genia.cameltest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) throws InterruptedException {
		@SuppressWarnings("resource")
		AbstractApplicationContext ctx = 
				new ClassPathXmlApplicationContext("META-INF\\spring\\camel-context.xml");
    	ctx.start();
    	System.out.println("Entered>>>>>");
    	Thread.sleep(480000);
    	ctx.destroy();
//    	ctx.stop();
	}
}
