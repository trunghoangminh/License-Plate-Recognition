package org.it.tdt.edu.vn.preprocessor;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author hmtrung
 *         <p>
 *         This class use to dilate and erode image<>
 *
 */
public class Morphology {

	public static Mat dilate(Mat mat, int dilationSize) { 
		Mat destinationMat = new Mat(mat.rows(), mat.cols(), mat.type());
		Mat temp = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
				new Size(2 * dilationSize , 2 * dilationSize ));
		Imgproc.dilate(mat, destinationMat, temp);
		return destinationMat;
	}

	public static Mat erode(Mat mat, int erosionSize) {
		Mat destinationMat = new Mat(mat.rows(), mat.cols(), mat.type());
		Mat temp = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
				new Size(2 * erosionSize , 2 * erosionSize));
		Imgproc.erode(mat, destinationMat, temp);
		return destinationMat;
	}

	/**
	 * 
	 * @param mat
	 * @return mat
	 *         <p>
	 *         erode before and dilate after
	 *         <p>
	 */
	public static Mat open(Mat mat,int dilationSize,int erosionSize) {
		return erode(dilate(mat, erosionSize), erosionSize);
	}

	/**
	 * 
	 * @param mat
	 * @return mat
	 *         <p>
	 *         dilate before and erode after
	 *         <p>
	 */
	public static Mat close(Mat mat,int dilationSize,int erosionSize) {
		return dilate(erode(mat,erosionSize),dilationSize);
	}
}
