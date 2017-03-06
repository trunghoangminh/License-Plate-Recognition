package org.it.tdt.edu.vn.preprocessor;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImageProcessors {

	private Mat mat;

	public ImageProcessors(Mat mat) {
		this.mat = mat;
	}

	public Mat getMat() {
		return mat;
	}

	public void setMat(Mat mat) {
		this.mat = mat;
	}

	public Mat createGrayImage() {
		Mat destinationMat = new Mat(mat.cols(), mat.rows(), CvType.CV_8UC1);
		Imgproc.cvtColor(mat, destinationMat, Imgproc.COLOR_RGB2GRAY);
		byte[] dataDestination = new byte[destinationMat.rows()
				* destinationMat.cols() * (int) (destinationMat.elemSize())];
		destinationMat.get(0, 0, dataDestination);
		return destinationMat;
	}

	public Mat createThresholdImage() {
		mat = createGrayImage();
		Mat destinationMat = new Mat(mat.cols(), mat.rows(), mat.type());

		Imgproc.threshold(mat, destinationMat, 127, 255, Imgproc.THRESH_TOZERO);
		byte[] dataDestination = new byte[destinationMat.rows()
				* destinationMat.cols() * (int) (destinationMat.elemSize())];
		destinationMat.get(0, 0, dataDestination);
		return destinationMat;
	}

	public Mat createBinaryImage() {
		mat = createThresholdImage();
		Mat destinationMat = new Mat(mat.rows(), mat.cols(), mat.type());

		destinationMat = mat;
		Imgproc.adaptiveThreshold(mat, destinationMat, 255,
				Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, 40);
		byte[] dataDestination = new byte[destinationMat.rows()
				* destinationMat.cols() * (int) (destinationMat.elemSize())];
		destinationMat.get(0, 0, dataDestination);
		return destinationMat;
	}

	public Mat createPyrDownmage() {
		mat = createGrayImage();
		Mat destinationMat = new Mat(mat.rows(), mat.cols(), mat.type());
		destinationMat = mat;
		Imgproc.pyrDown(destinationMat, destinationMat, new Size(
				mat.cols() / 2, mat.rows() / 2));
		byte[] dataDestination = new byte[destinationMat.rows()
				* destinationMat.cols() * (int) (destinationMat.elemSize())];
		destinationMat.get(0, 0, dataDestination);
		return destinationMat;
	}

}
