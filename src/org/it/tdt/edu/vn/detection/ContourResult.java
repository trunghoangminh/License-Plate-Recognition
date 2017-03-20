package org.it.tdt.edu.vn.detection;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;

/**
 * 
 * @author hmtrung
 *
 */
public class ContourResult {
	private List<MatOfPoint> matResult;
	private Mat matHierarchy;

	public Mat getMatHierarchy() {
		return matHierarchy;
	}

	public void setMatHierarchy(Mat matHierarchy) {
		this.matHierarchy = matHierarchy;
	}

	public List<MatOfPoint> getMatResult() {
		return matResult;
	}

	public void setMatResult(List<MatOfPoint> matResult) {
		this.matResult = matResult;
	}

	public ContourResult(List<MatOfPoint> matResult, Mat matHierarchy) {
		this.matResult = matResult;
		this.matHierarchy = matHierarchy;
	}

	public ContourResult() {
		super();
	}

}
