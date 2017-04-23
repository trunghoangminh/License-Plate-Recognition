package org.it.tdt.edu.vn.platedetection.preprocessor;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
/**
 * 
 * @author TrungHoang
 *
 */
public class HistogramEqualizationMat extends GrayMatBase {

	public HistogramEqualizationMat(Mat mat) {
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
