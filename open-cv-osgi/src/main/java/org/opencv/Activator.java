package org.opencv;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	
	@Override
	public void start(BundleContext context) throws Exception {
		System.loadLibrary("opencv_java2410");
		
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		
	}

}
