package com.bobpaulin.opencv.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Timer;
import java.util.TimerTask;

import com.bobpaulin.opencv.FaceDetectorService;
import com.bobpaulin.opencv.ImageScannerService;

public class ImageScannerServiceImpl implements ImageScannerService {
	
	private Timer timer;
	
	private FaceDetectorService faceDetectorService;

	public void scanForImage(String scanDirPath) {
		timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				File[] files = new File(scanDirPath).listFiles(new FilenameFilter() {

					@Override
					public boolean accept(File file, String name) {
						return name.endsWith(".png");
					}
				});
				if (files.length > 0) {
					
					faceDetectorService.detectFaces(files[0].getAbsolutePath(), "detection");
					files[0].renameTo(new File(files[0].getAbsolutePath() + ".done"));
				}
				
			}
		}, 1000, 2000);
		

	}
	
	public void stopScan()
	{
		timer.cancel();
	}
	
	public void setFaceDetectorService(FaceDetectorService faceDetectorService) {
		this.faceDetectorService = faceDetectorService;
	}
}
