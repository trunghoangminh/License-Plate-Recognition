package org.it.tdt.edu.vn.platedetection.preprocessor;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author TrungHoang
 *
 */
public class ThresholdMat extends GrayMatBase {

	private double thresh;
	private double maxVal;
	private int type;

	public ThresholdMat(Mat mat, double thresh, double maxVal, int type) {
		super(mat);
		this.thresh = thresh;
		this.maxVal = maxVal;
		this.type = type;
	}

	@Override
	public Mat createMatResult() {
		Mat destinationMat = new Mat(getMat().cols(), getMat().rows(), getMat()
				.type());
		Imgproc.threshold(getMat(), destinationMat, thresh, maxVal, type);
		byte[] dataDestination = new byte[destinationMat.rows()
				* destinationMat.cols() * (int) (destinationMat.elemSize())];
		destinationMat.get(0, 0, dataDestination);
		return destinationMat;
	}

	public double getThresh() {
		return thresh;
	}

	public void setThresh(double thresh) {
		this.thresh = thresh;
	}

	public double getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(double maxVal) {
		this.maxVal = maxVal;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
