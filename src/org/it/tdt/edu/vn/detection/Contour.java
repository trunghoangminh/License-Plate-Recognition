package org.it.tdt.edu.vn.detection;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

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

	public Contour(Mat mat, Mat matHierarchy, List<MatOfPoint> contourList) {
		this.matHierarchy = matHierarchy;
		this.contours = contourList;
		this.mat = mat;
	}
}
