package org.it.tdt.edu.vn.platedetection.preprocessor;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author TrungHoang
 *
 */
public class BinaryMat extends GrayMatBase {

	private double maxValue;
	private int adaptiveMethod;
	private int thresholdType;
	private int blockSize;
	private int c;

	public BinaryMat(Mat mat) {
		super(mat);

	}

	@Override
	public Mat createMatResult() {
		Mat destinationMat = new Mat(getMat().rows(), getMat().cols(), getMat()
				.type());
		destinationMat = getMat();
		Imgproc.adaptiveThreshold(getMat(), destinationMat, maxValue,
				adaptiveMethod, thresholdType, blockSize, c);
		byte[] dataDestination = new byte[destinationMat.rows()
				* destinationMat.cols() * (int) (destinationMat.elemSize())];
		destinationMat.get(0, 0, dataDestination);
		return destinationMat;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public int getAdaptiveMethod() {
		return adaptiveMethod;
	}

	public void setAdaptiveMethod(int adaptiveMethod) {
		this.adaptiveMethod = adaptiveMethod;
	}

	public int getThresholdType() {
		return thresholdType;
	}

	public void setThresholdType(int thresholdType) {
		this.thresholdType = thresholdType;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

}
