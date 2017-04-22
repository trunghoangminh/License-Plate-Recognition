package org.it.tdt.edu.vn.preprocessor;

import org.opencv.core.Mat;

/**
 * 
 * @author TrungHoang
 *
 */
public class CloseMat extends MorphologyMatBase {

	public CloseMat(Mat mat, int shape, int width, int height, int size) {
		super(mat, shape, width, height, size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Mat createMatResult() {
		setMat(dilate());
		setMat(erode());
		return super.getMat();
	}

}
