package org.it.tdt.edu.vn.platedetection.preprocessor;

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
public class MorphologyMatBase extends GrayMatBase {

	private int shape;
	private double width;
	private double height;
	private double size;

	public MorphologyMatBase(Mat mat, int shape, double width, double height, double size) {
		super(mat);
		this.shape = shape;
		this.width = width;
		this.height = height;
		this.size = size;
	}

	public Mat dilate() {
		Mat destinationMat = new Mat(getMat().rows(), getMat().cols(), getMat()
				.type());
		Mat temp = Imgproc.getStructuringElement(shape, new Size(size * width,
				size * height + 1));
		Imgproc.dilate(getMat(), destinationMat, temp);
		return destinationMat;
	}

	public Mat erode() {
		Mat destinationMat = new Mat(getMat().rows(), getMat().cols(), getMat()
				.type());
		Mat temp = Imgproc.getStructuringElement(shape, new Size(size * width,
				size * height + 1));
		Imgproc.erode(getMat(), destinationMat, temp);
		return destinationMat;
	}

	public int getShape() {
		return shape;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	@Override
	public Mat createMatResult() {
		// TODO Auto-generated method stub
		return new Mat();
	}
}
