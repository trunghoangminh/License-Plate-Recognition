package org.it.tdt.edu.vn.openandclose;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author hmtrung
 *
 */
public class OpenAndClose {

	private Mat thresholdMat;

	public OpenAndClose(Mat thresholdMat) {
		this.thresholdMat = thresholdMat;
	}

	public Mat getThresholdMat() {
		return thresholdMat;
	}

	public void setThresholdMat(Mat thresholdMat) {
		this.thresholdMat = thresholdMat;
	}

	private Mat open() {
		int dilationSize = 5;
		Mat destinationMat = new Mat(thresholdMat.rows(), thresholdMat.cols(),
				thresholdMat.type());
		Mat element1 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
				new Size(2 * dilationSize + 1, 2 * dilationSize + 1));
		Imgproc.dilate(thresholdMat, destinationMat, element1);
		return destinationMat;
	}

	private Mat close() {
		Mat destinationMat = new Mat(thresholdMat.rows(), thresholdMat.cols(),
				thresholdMat.type());
		int erosionSize = 5;
		Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
				new Size(2 * erosionSize + 1, 2 * erosionSize + 1));
		Imgproc.erode(thresholdMat, destinationMat, element);
		return destinationMat;
	}

	public void openBeforeCloseAfter() {
		setThresholdMat(open());
		setThresholdMat(close());
	}

	public void closeBeforeOpeneAfter() {
		setThresholdMat(close());
		setThresholdMat(open());
	}
}
