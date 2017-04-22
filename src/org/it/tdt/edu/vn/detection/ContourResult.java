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
public class ContourResult {
	private Contour contour;

	public ContourResult(Contour contour) {
		this.contour = contour;
	}
	
	public Contour createContour() {
		Mat mat = contour.getMat();
		List<MatOfPoint> contours = contour.getContours();
		Mat matHierarchy = contour.getMatHierarchy();
		Imgproc.findContours(mat, contours, matHierarchy, Imgproc.RETR_TREE,
				Imgproc.CHAIN_APPROX_SIMPLE);
		return new Contour(mat, matHierarchy, contours);
	}

	public Mat drawContour() {
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Imgproc.findContours(contour.getMat(), contours, new Mat(), Imgproc.RETR_LIST,
				Imgproc.CHAIN_APPROX_SIMPLE);
		Imgproc.drawContours(contour.getMat(), contours, -1, new Scalar(255, 255, 0));
		return contour.getMat();
	}
}
