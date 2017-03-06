package org.it.tdt.edu.vn.test;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Test1 {
	
	public static void main(String[] args) {

		try {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			File file = new File("/home/hmtrung/Pictures/biensoxe.jpg");
			BufferedImage originalImage = ImageIO.read(file);
			byte[] originalData = ((DataBufferByte) originalImage.getRaster().getDataBuffer())
					.getData();
			 Mat oringinMat = new Mat(originalImage.getHeight(), originalImage.getWidth(), CvType.CV_8UC3);
			 oringinMat.put(0, 0, originalData);
			 
	         Mat destinationMat = new Mat(originalImage.getHeight(), originalImage.getWidth(), CvType.CV_8UC1);
	         Imgproc.cvtColor(oringinMat, destinationMat, Imgproc.COLOR_RGB2GRAY);
	         
	         byte[] dataDestination = new byte[destinationMat.rows() * destinationMat.cols() * (int)(destinationMat.elemSize())];
	         
	         destinationMat.get(0, 0, dataDestination);
	         BufferedImage destinationImage = new BufferedImage(destinationMat.cols(),destinationMat.rows(), BufferedImage.TYPE_BYTE_GRAY);
	         destinationImage.getRaster().setDataElements(0, 0, destinationMat.cols(), destinationMat.rows(), dataDestination);
	         
	         File ouptut = new File("/home/hmtrung/Pictures/biensoxegray.jpg");
	         ImageIO.write(destinationImage, "jpg", ouptut);
	         
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
