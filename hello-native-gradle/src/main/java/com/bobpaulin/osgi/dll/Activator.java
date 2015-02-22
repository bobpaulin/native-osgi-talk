package com.bobpaulin.osgi.dll;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	
	@Override
	public void start(BundleContext context) throws Exception {
		System.loadLibrary("helloworld");
		HelloWorld app = new HelloWorld();
        System.out.println( app.sayHello() );
		
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		
	}

}
