package org.it.tdt.edu.vn.detection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.it.tdt.edu.vn.openandclose.Contour;
import org.it.tdt.edu.vn.preprocessor.GrayImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
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

	public RectangleDetection() {

	}

	public void executeRectangleDetection() {

		Contour contourAction = new Contour(mat);
		mat = contourAction.createContour().getMat();
		MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
		MatOfPoint2f approxCurve = new MatOfPoint2f();
		System.out.println(contourAction.createContour().getMatHierarchy()
				.dump());

		for (int idx = 0; idx >= 0; idx = (int) contourAction.createContour()
				.getMatHierarchy().get(0, idx)[0]) {
			MatOfPoint contour = contourAction.createContour().getContours()
					.get(idx);
			Rect rect = Imgproc.boundingRect(contour);
			// double contourArea = Imgproc.contourArea(contour);
			matOfPoint2f.fromList(contour.toList());
			Imgproc.approxPolyDP(matOfPoint2f, approxCurve,
					Imgproc.arcLength(matOfPoint2f, true) * 0.02, true);
			long total = approxCurve.total();
			if (total == 3) { // is triangle
				// do things for triangle
			}
			if (total >= 4 && total <= 6) {
				List<Double> cos = new ArrayList<>();
				Point[] points = approxCurve.toArray();
				for (int j = 2; j < total + 1; j++) {
					cos.add(angle(points[(int) (j % total)], points[j - 2],
							points[j - 1]));
				}
				Collections.sort(cos);
				Double minCos = cos.get(0);
				Double maxCos = cos.get(cos.size() - 1);
				boolean isRect = total == 4 && minCos >= -0.1 && maxCos <= 0.3;
				boolean isPolygon = (total == 5 && minCos >= -0.34 && maxCos <= -0.27)
						|| (total == 6 && minCos >= -0.55 && maxCos <= -0.45);
				if (isRect) {
					double ratio = Math.abs(1 - (double) rect.width
							/ rect.height);
					drawText(rect.tl(), ratio <= 0.02 ? "SQU" : "RECT");
				}
				if (isPolygon) {
					drawText(rect.tl(), "Polygon");
				}
			}
		}
	}

	private double angle(Point pt1, Point pt2, Point pt0) {
		double dx1 = pt1.x - pt0.x;
		double dy1 = pt1.y - pt0.y;
		double dx2 = pt2.x - pt0.x;
		double dy2 = pt2.y - pt0.y;
		return (dx1 * dx2 + dy1 * dy2)
				/ Math.sqrt((dx1 * dx1 + dy1 * dy1) * (dx2 * dx2 + dy2 * dy2)
						+ 1e-10);
	}

	private void drawText(Point ofs, String text) {
		// Imgproc.putText(colorImage, text, ofs, Core.FONT_HERSHEY_SIMPLEX,
		// 0.5, new Scalar(255,255,25));
	}

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		RectangleDetection rectangleDetection = new RectangleDetection();
		rectangleDetection.setMat(GrayImage.createThresholdImage());
		rectangleDetection.executeRectangleDetection();

	}
}
