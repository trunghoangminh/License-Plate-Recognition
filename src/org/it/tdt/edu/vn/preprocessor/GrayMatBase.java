package org.it.tdt.edu.vn.preprocessor;

import org.opencv.core.Mat;

public abstract class GrayMatBase implements IMatProcess {
	private Mat mat;

	/**
	 * This method will be defined in a subclass
	 */

	public GrayMatBase(Mat mat) {
		this.mat = mat;
	}

	@Override
	public abstract Mat createMatResult();

	public Mat getMat() {
		return mat;
	}

	public void setMat(Mat mat) {
		this.mat = mat;
	}
}
