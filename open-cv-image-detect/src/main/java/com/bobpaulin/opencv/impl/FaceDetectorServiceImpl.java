package com.bobpaulin.opencv.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

import com.bobpaulin.opencv.FaceDetectorService;

public class FaceDetectorServiceImpl implements FaceDetectorService {
	public void detectFaces(String filePath, String outputDir) {
	    System.out.println("\nRunning DetectFaceDemo");

	    // Create a face detector from the cascade file in the resources
	    // directory.
	    CascadeClassifier faceDetector = new CascadeClassifier(new File("lbpcascade_frontalface.xml").getAbsolutePath());
	    Mat image = Highgui.imread(filePath);

	    // Detect faces in the image.
	    // MatOfRect is a special container class for Rect.
	    MatOfRect faceDetections = new MatOfRect();
	    faceDetector.detectMultiScale(image, faceDetections);

	    Rect[] faceDetectionArray = faceDetections.toArray();
	    System.out.println(String.format("Detected %s faces", faceDetectionArray.length));

	    // Draw a bounding box around each face.
	    List<Rect> filteredFaceList = new ArrayList<Rect>();
	    double sumOfArea = 0;
	    for (Rect rect1 : faceDetections.toArray()) {
	    	
	    	if(!detectRectWithinRect(faceDetections, rect1))
	    	{
	    		filteredFaceList.add(rect1);
	    		sumOfArea += rect1.area();
	    	}
	    }
	    double halfAverageArea = (sumOfArea/faceDetectionArray.length)/2;
	    int actualFaceCount = 0;
	    for(Rect rect: filteredFaceList)
	    {
	    	if(rect.area() > halfAverageArea)
	    	{
	    		Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
	    		actualFaceCount++;
	    	}
	    }
	    
	    System.out.println(String.format("Boxed %s faces", actualFaceCount));

	    // Save the visualized detection.
	    String filename = outputDir + File.separator + new File(filePath).getName();
	    System.out.println(String.format("Writing %s", filename));
	    Highgui.imwrite(filename, image);
	  }

	private boolean detectRectWithinRect(MatOfRect faceDetections, Rect sampleRect) {
		for(Rect otherRect: faceDetections.toArray())
		{
			if(sampleRect != otherRect)
			{
				if(otherRect.contains(new Point(sampleRect.x, sampleRect.y)) && 
						otherRect.contains(new Point(sampleRect.x + sampleRect.width, sampleRect.y + sampleRect.height)))
				{
					return true;
				}
			}
			
		}
		return false;
	}
}
