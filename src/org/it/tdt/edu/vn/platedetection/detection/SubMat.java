package org.it.tdt.edu.vn.platedetection.detection;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

/**
 * 
 * @author Trung Drop image
 *
 */
public class SubMat {
	private Mat original;
	private Rect rect;

	public Mat getOriginal() {
		return original;
	}

	public void setOriginal(Mat original) {
		this.original = original;
	}

	public Rect getRect() {
		return rect;
	}

	public void setRect(Rect rect) {
		this.rect = rect;
	}

	public SubMat(Mat original, Rect rect) {
		this.original = original;
		this.rect = rect;
	}

	public Mat dropImage() {
		return original.submat(rect);
	}
}
