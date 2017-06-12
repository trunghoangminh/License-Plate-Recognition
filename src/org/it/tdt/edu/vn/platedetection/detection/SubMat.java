package org.it.tdt.edu.vn.platedetection.detection;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author Trung Drop image
 *
 */
public class SubMat {
	private Mat original;
	private List<MatOfPoint> contours;

	public Mat getOriginal() {
		return original;
	}

	public void setOriginal(Mat original) {
		this.original = original;
	}

	public List<MatOfPoint> getContours() {
		return contours;
	}

	public void setContours(List<MatOfPoint> contours) {
		this.contours = contours;
	}

	public SubMat(Mat original, List<MatOfPoint> contours) {
		this.original = original;
		this.contours = contours;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Mat> dropImage() {
		List<Mat> result = new ArrayList<Mat>();
		for (MatOfPoint contour : contours) {
			Rect rect = Imgproc.boundingRect(contour);
			Mat temp = original.submat(rect);
			result.add(temp);
		}
		return result;
	}
}
