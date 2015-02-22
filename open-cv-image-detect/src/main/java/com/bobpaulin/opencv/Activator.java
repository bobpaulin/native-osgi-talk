package com.bobpaulin.opencv;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.bobpaulin.opencv.impl.FaceDetectorServiceImpl;
import com.bobpaulin.opencv.impl.ImageScannerServiceImpl;

public class Activator implements BundleActivator {
	
	private ServiceRegistration<FaceDetectorService> faceDetectorServiceReg;
	
	private ServiceRegistration<ImageScannerService> imageScannerServiceReg;
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		FaceDetectorService faceDetector = new FaceDetectorServiceImpl();
		faceDetectorServiceReg = context.registerService(FaceDetectorService.class, faceDetector, null);
		
		ImageScannerServiceImpl imageScanner = new ImageScannerServiceImpl();
		imageScanner.setFaceDetectorService(faceDetector);
		imageScannerServiceReg = context.registerService(ImageScannerService.class, imageScanner, null);
		
		imageScanner.scanForImage("image");
		
	}
	@Override
	public void stop(BundleContext context) throws Exception {
		ImageScannerService imageScannerService = context.getService(imageScannerServiceReg.getReference());
		imageScannerService.stopScan();
		imageScannerServiceReg.unregister();
		faceDetectorServiceReg.unregister();
		
	}

}
