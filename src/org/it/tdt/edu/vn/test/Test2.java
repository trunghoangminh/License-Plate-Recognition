package org.it.tdt.edu.vn.test;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Test2 {

	public static void main(String[] args) {
		try {

			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			Mat source = Imgcodecs
					.imread("/home/hmtrung/Pictures/biensoxe_gray_threshold.jpg", CvType.CV_8UC1);
			Mat destination = new Mat(source.rows(), source.cols(),
					source.type());

			destination = source;
			/*Imgproc.threshold(source, destination, 127, 255,
					Imgproc.THRESH_TOZERO);*/
			Imgproc.adaptiveThreshold(source, destination, 255,
					Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15,
					40);
			Imgcodecs.imwrite(
					"/home/hmtrung/Pictures/biensoxe_gray_threshold_binary.jpg",
					destination);

		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
	}
}
