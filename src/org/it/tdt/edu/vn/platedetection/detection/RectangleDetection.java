package org.it.tdt.edu.vn.platedetection.detection;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author hmtrung
 *
 */
public class RectangleDetection {

	/**
	 * 
	 */
	private Mat mat;

	public Mat getMat() {
		return mat;
	}

	public void setMat(Mat mat) {
		this.mat = mat;
	}

	public RectangleDetection(Mat mat) {
		this.mat = mat;
	}
	
	public List<MatOfPoint> executeRectangleDetection() {

		// get Mat contour
		Contour con = new Contour(mat);
		ContourResult contourAction = new ContourResult(con);
		mat = contourAction.createContour().getMat();

		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		List<MatOfPoint> contoursResult = new ArrayList<MatOfPoint>();
		contours = contourAction.createContour().getContours();
		for (int i = 0; i < contours.size(); i++) {

			MatOfPoint2f approxCurve = new MatOfPoint2f();
			MatOfPoint contour = contours.get(i);

			// convert MatOfPoint to MatOfPoint2f
			MatOfPoint2f contourCurrent = new MatOfPoint2f();
			contour.convertTo(contourCurrent, CvType.CV_32F);
			double peri = Imgproc.arcLength(contourCurrent, true);

			Imgproc.approxPolyDP(contourCurrent, approxCurve, peri * 0.02, true);
			if (approxCurve.total() == 4) {

				// convert MatOfPoint2f to MatOfPoint
				MatOfPoint result = new MatOfPoint();
				approxCurve.convertTo(result, CvType.CV_32S);

				contoursResult.add(result);
			}
		}
		List<MatOfPoint> finalContour = new ArrayList<MatOfPoint>();
		for (int i = 0; i < contoursResult.size(); i++) {
			double contourArea = Imgproc.contourArea(contoursResult.get(i));
			if (contourArea > 50) {
				finalContour.add(contoursResult.get(i));
			}
		}
		return finalContour;
	}
}
