package com.bobpaulin.felix.picture.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

public class PictureServlet extends HttpServlet {
	  @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.getWriter().write("Hello World");      
	  }
	  
	  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		  InputStream in = req.getInputStream();
		  
		  File targetFile = new File("targetFile.jpg");
		  
		  FileUtils.copyInputStreamToFile(in, targetFile);
		super.doPost(req, resp);
	}
}
