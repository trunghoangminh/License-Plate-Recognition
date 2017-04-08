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

	public static Mat dilate(Mat mat) {
		int dilationSize = 1;
		Mat destinationMat = new Mat(mat.rows(), mat.cols(), mat.type());
		Mat temp = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
				new Size(2 * dilationSize + 1, 2 * dilationSize + 1));
		Imgproc.dilate(mat, destinationMat, temp);
		return destinationMat;
	}

	public static Mat erode(Mat mat) {
		Mat destinationMat = new Mat(mat.rows(), mat.cols(), mat.type());
		int erosionSize = 3;
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
	public static Mat open(Mat mat) {
		return erode(dilate(mat));
	}

	/**
	 * 
	 * @param mat
	 * @return mat
	 *         <p>
	 *         dilate before and erode after
	 *         <p>
	 */
	public static Mat close(Mat mat) {
		return dilate(erode(mat));
	}
}
