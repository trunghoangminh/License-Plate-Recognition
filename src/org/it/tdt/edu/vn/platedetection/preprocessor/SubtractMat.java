package org.it.tdt.edu.vn.platedetection.preprocessor;

import org.opencv.core.Core;
import org.opencv.core.Mat;

public class SubtractMat extends GrayMatBase {

	private Mat matInput;

	public SubtractMat(Mat mat, Mat matInput) {
		super(mat);
		this.matInput = matInput;
	}

	@Override
	public Mat createMatResult() {
		Mat destinationMat = new Mat(getMat().rows(), getMat().cols(), getMat()
				.type());
		Core.absdiff(getMat(), matInput, destinationMat);
		return destinationMat;
	}

	public Mat getMatInput() {
		return matInput;
	}

	public void setMatInput(Mat matInput) {
		this.matInput = matInput;
	}

}
