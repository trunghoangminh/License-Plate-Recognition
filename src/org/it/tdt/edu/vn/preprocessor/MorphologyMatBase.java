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
public class MorphologyMatBase extends GrayMatBase {

	private int shape;
	private int width;
	private int height;
	private int size;

	public MorphologyMatBase(Mat mat, int shape, int width, int height, int size) {
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

	@Override
	public Mat createMatResult() {
		// TODO Auto-generated method stub
		return new Mat();
	}
}
