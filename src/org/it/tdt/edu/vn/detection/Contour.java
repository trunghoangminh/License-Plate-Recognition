package org.it.tdt.edu.vn.detection;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author hmtrung
 *
 */
public class Contour {

	private Mat mat;
	private List<MatOfPoint> contours;
	private Mat matHierarchy;


	public List<MatOfPoint> getContours() {
		return contours;
	}

	public void setContours(List<MatOfPoint> contours) {
		this.contours = contours;
	}

	public Mat getMatHierarchy() {
		return matHierarchy;
	}

	public void setMatHierarchy(Mat matHierarchy) {
		this.matHierarchy = matHierarchy;
	}

	public Mat getMat() {
		return mat;
	}

	public void setMat(Mat mat) {
		this.mat = mat;
	}

	public Contour(Mat mat) {
		this.matHierarchy = new Mat();
		this.contours = new ArrayList<MatOfPoint>();
		this.mat = mat;
	}

	public Contour(Mat mat, Mat matHierarchy, List<MatOfPoint> contourList ) {
		this.matHierarchy = matHierarchy;
		this.contours = contourList;
		this.mat = mat;
	}

	public Contour createContour() {
		Imgproc.findContours(mat, contours, matHierarchy, Imgproc.RETR_LIST,
				Imgproc.CHAIN_APPROX_SIMPLE);
		return new Contour(mat, matHierarchy, contours);
	}

	public Mat drawContour() {
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Imgproc.findContours(mat, contours, new Mat(), Imgproc.RETR_LIST,
				Imgproc.CHAIN_APPROX_SIMPLE);
		Imgproc.drawContours(mat, contours, -1, new Scalar(255, 255, 0));
		return mat;
	}
	public static void main(String[] args) {
		
	}
}
