package org.it.tdt.edu.vn.preprocessor;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author hmtrung
 *         <p>
 *         This class use to process mat image in gray image as:gray, binary,
 *         threshold.
 *         <p>
 */
public class GrayMat {
	/**
	 * @param mat
	 * @return destinationMat
	 *         <p>
	 *         create threshold image.
	 *         <p>
	 */
	public static Mat createThresholdImage(Mat mat) {
		if (mat != null) {
			Mat destinationMat = new Mat(mat.cols(), mat.rows(), mat.type());
			Imgproc.threshold(mat, destinationMat, 0, 10, Imgproc.THRESH_BINARY_INV);
			byte[] dataDestination = new byte[destinationMat.rows()
					* destinationMat.cols() * (int) (destinationMat.elemSize())];
			destinationMat.get(0, 0, dataDestination);
			return destinationMat;
		}
		System.err.println("Mat is empty");
		return null;
	}

	/**
	 * @param mat
	 * @return destinationMat
	 *         <p>
	 *         create binary image
	 *         </p>
	 *         .
	 */
	public static Mat createBinaryImage(Mat mat) {
		if (mat != null) {
			Mat destinationMat = new Mat(mat.rows(), mat.cols(), mat.type());
			destinationMat = mat;
			Imgproc.adaptiveThreshold(mat, destinationMat, 255,
					Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 15,
					40);
			byte[] dataDestination = new byte[destinationMat.rows()
					* destinationMat.cols() * (int) (destinationMat.elemSize())];
			destinationMat.get(0, 0, dataDestination);
			return destinationMat;
		}
		System.err.println("Mat is empty");
		return null;
	}

	/**
	 * @param mat
	 * @return destinationMat
	 *         <p>
	 *         create pyrDown image</b>.
	 */

	public Mat createPyrDownmage(Mat mat) {
		if (mat != null) {

			Mat destinationMat = new Mat(mat.rows(), mat.cols(), mat.type());
			destinationMat = mat;
			Imgproc.pyrDown(destinationMat, destinationMat, new Size(
					mat.cols() / 2, mat.rows() / 2));
			byte[] dataDestination = new byte[destinationMat.rows()
					* destinationMat.cols() * (int) (destinationMat.elemSize())];
			destinationMat.get(0, 0, dataDestination);

			return destinationMat;
		}
		System.err.println("Mat is empty");
		return null;

	}

	/**
	 * 
	 * @param mat
	 * @return destinationMat
	 *         <p>
	 *         create GaussianBlur image</b>
	 */
	public static Mat createGaussianBlur(Mat mat) {
		if (mat != null) {
			Mat destinationMat = new Mat(mat.rows(), mat.cols(), mat.type());
			destinationMat = mat;
			Imgproc.GaussianBlur(mat, destinationMat, new org.opencv.core.Size(
					5, 5), 5);
			byte[] dataDestination = new byte[destinationMat.rows()
					* destinationMat.cols() * (int) (destinationMat.elemSize())];
			destinationMat.get(0, 0, dataDestination);
			return destinationMat;
		}
		System.err.println("Mat is empty");
		return null;
	}
}
