package org.it.tdt.edu.vn.openandclose;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author hmtrung
 *
 */
public class Contour {

	private Mat mat;

	public Mat getMat() {
		return mat;
	}

	public void setMat(Mat mat) {
		this.mat = mat;
	}

	public Contour(Mat mat) {
		this.mat = mat;
	}

	public Mat createContour() {

		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Imgproc.findContours(mat, contours, new Mat(), Imgproc.RETR_LIST,
				Imgproc.CHAIN_APPROX_SIMPLE);
		Imgproc.drawContours(mat, contours, -1, new Scalar(255, 255, 0));

		for (int i = 0; i < contours.size(); i++) {
			if (Imgproc.contourArea(contours.get(i)) > 8) {
				Rect rect = Imgproc.boundingRect(contours.get(i));
				if ((rect.height > 7 && rect.height < 16)
						&& (rect.width > 100 && rect.width < 100)) {
					Core.rectangle(mat, new Point(rect.x, rect.y), new Point(
							rect.x + rect.width, rect.y + rect.height),
							new Scalar(0, 0, 255));
				}
			}
		}
		return mat;
	}

	public Mat drawContour() {
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Imgproc.findContours(mat, contours, new Mat(), Imgproc.RETR_LIST,
				Imgproc.CHAIN_APPROX_SIMPLE);
		Imgproc.drawContours(mat, contours, -1, new Scalar(255, 255, 0));
		return mat;
	}
}
