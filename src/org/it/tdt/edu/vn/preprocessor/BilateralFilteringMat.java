package org.it.tdt.edu.vn.preprocessor;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
/**
 * 
 * @author TrungHoang
 *
 */
public class BilateralFilteringMat extends GrayMatBase {

	private int width;
	private int height;
	private double sigmaX;

	public BilateralFilteringMat(Mat mat, int width, int height, double sigmaX) {
		super(mat);
		this.width = width;
		this.height = height;
		this.sigmaX = sigmaX;
	}

	@Override
	public Mat createMatResult() {
		Mat destinationMat = new Mat(getMat().rows(), getMat().cols(), getMat()
				.type());
		destinationMat = getMat();
		Imgproc.GaussianBlur(getMat(), destinationMat, new Size(width, height),
				sigmaX);
		byte[] dataDestination = new byte[destinationMat.rows()
				* destinationMat.cols() * (int) (destinationMat.elemSize())];
		destinationMat.get(0, 0, dataDestination);
		return destinationMat;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getSigmaX() {
		return sigmaX;
	}

	public void setSigmaX(double sigmaX) {
		this.sigmaX = sigmaX;
	}

}
