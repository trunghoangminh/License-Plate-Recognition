package org.it.tdt.edu.vn.preprocessor;

import org.opencv.core.Mat;

public class OpenMat extends MorphologyMatBase {

	public OpenMat(Mat mat, int shape, int width, int height, int size) {
		super(mat, shape, width, height, size);
	}

	@Override
	public Mat createMatResult() {
		setMat(erode());
		setMat(dilate());
		return getMat();
	}

}
