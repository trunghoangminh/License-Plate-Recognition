package org.it.tdt.edu.vn.preprocessor;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author hmtrung This class use to process mat image in gray image as:gray,
 *         binary, threshold.
 */
public class GrayImage {
	/**
	 * 
	 * @return destinationMat create threshold image.
	 */
	public static Mat createThresholdImage() {
		Mat mat = OgrinalImage.createGrayImage();
		Mat destinationMat = new Mat(mat.cols(), mat.rows(), mat.type());
		Imgproc.threshold(mat, destinationMat, 127, 255, Imgproc.THRESH_TOZERO);
		byte[] dataDestination = new byte[destinationMat.rows()
				* destinationMat.cols() * (int) (destinationMat.elemSize())];
		destinationMat.get(0, 0, dataDestination);
		return destinationMat;
	}

	/**
	 * 
	 * @return destinationMat create binary image.
	 */
	public static Mat createBinaryImage() {
		Mat mat = createThresholdImage();
		Mat destinationMat = new Mat(mat.rows(), mat.cols(), mat.type());
		destinationMat = mat;
		Imgproc.adaptiveThreshold(mat, destinationMat, 255,
				Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, 40);
		byte[] dataDestination = new byte[destinationMat.rows()
				* destinationMat.cols() * (int) (destinationMat.elemSize())];
		destinationMat.get(0, 0, dataDestination);
		return destinationMat;
	}

	/**
	 * 
	 * @return destinationMat create pyrDown image.
	 */

	public Mat createPyrDownmage() {
		Mat mat = OgrinalImage.createGrayImage();
		Mat destinationMat = new Mat(mat.rows(), mat.cols(), mat.type());
		destinationMat = mat;
		Imgproc.pyrDown(destinationMat, destinationMat, new Size(
				mat.cols() / 2, mat.rows() / 2));
		byte[] dataDestination = new byte[destinationMat.rows()
				* destinationMat.cols() * (int) (destinationMat.elemSize())];
		destinationMat.get(0, 0, dataDestination);
		return destinationMat;
	}
}
