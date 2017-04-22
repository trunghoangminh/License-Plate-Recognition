package org.it.tdt.edu.vn.preprocessor;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class CannyMat extends GrayMatBase {

	private double thresholdOne;
	private double thresholdTwo;
	
	public CannyMat(Mat mat, double thresholdOne, double thresholdTwo) {
		super(mat);
		this.thresholdOne = thresholdOne;
		this.thresholdTwo = thresholdTwo;
	}

	@Override
	public Mat createMatResult() {
		Mat destinationMat = new Mat(getMat().rows(), getMat().cols(), getMat()
				.type());
		Imgproc.Canny(getMat(), destinationMat, thresholdOne, thresholdTwo);
		Core.convertScaleAbs(getMat(),destinationMat);
		return destinationMat;
	}

	public double getThresholdOne() {
		return thresholdOne;
	}

	public void setThresholdOne(double thresholdOne) {
		this.thresholdOne = thresholdOne;
	}

	public double getThresholdTwo() {
		return thresholdTwo;
	}

	public void setThresholdTwo(double thresholdTwo) {
		this.thresholdTwo = thresholdTwo;
	}
	
}
