package org.it.tdt.edu.vn.preprocessor;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
/**
 * 
 * @author TrungHoang
 *
 */
public class HistogramEqualization extends GrayMatBase {

	public HistogramEqualization(Mat mat) {
		super(mat);
	}

	@Override
	public Mat createMatResult() {
		Mat destinationMat = new Mat(getMat().cols(), getMat().rows(),
				CvType.CV_8UC1);
		Imgproc.equalizeHist(getMat(), destinationMat);
		return destinationMat;
	}

}
