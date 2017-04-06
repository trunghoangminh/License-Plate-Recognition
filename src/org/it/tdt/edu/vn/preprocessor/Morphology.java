package org.it.tdt.edu.vn.preprocessor;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author hmtrung
 *
 */
public class Morphology {

	public static Mat dilate(Mat mat) {
		int dilationSize = 1;
		Mat destinationMat = new Mat(mat.rows(), mat.cols(), mat.type());
		Mat element1 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
				new Size(2 * dilationSize + 1, 2 * dilationSize + 1));
		Imgproc.dilate(mat, destinationMat, element1);
		return destinationMat;
	}

	public static Mat erode(Mat mat) {
		Mat destinationMat = new Mat(mat.rows(), mat.cols(), mat.type());
		int erosionSize = 1;
		Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
				new Size(2 * erosionSize + 1, 2 * erosionSize + 1));
		Imgproc.erode(mat, destinationMat, element);
		return destinationMat;
	}

	/**
	 * erode before and dilate after
	 */
	public static Mat open(Mat mat) {
		return erode(dilate(mat));
	}

	/**
	 * dilate before and erode after
	 */
	public static Mat close(Mat mat) {
		return dilate(erode(mat));
	}
}
