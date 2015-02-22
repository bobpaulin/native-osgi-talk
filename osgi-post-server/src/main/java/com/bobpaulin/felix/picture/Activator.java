package com.bobpaulin.felix.picture;

import java.util.Hashtable;

import javax.servlet.Servlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.bobpaulin.felix.picture.impl.PictureServlet;

public class Activator implements BundleActivator {

	private ServiceRegistration registration;

	  public void start(BundleContext context) throws Exception {
	    Hashtable props = new Hashtable();
	    props.put("alias", "/test");
	    props.put("init.message", "Hello World!");

	    this.registration = context.registerService(Servlet.class.getName(), new PictureServlet(), props);
	  }

	  public void stop(BundleContext context) throws Exception {
	    this.registration.unregister();
	  }

}
