package org.it.tdt.edu.vn.platedetection.detection;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class CharacterSegment {

	private Mat result;
	private List<Mat> listImageNumber = new ArrayList<Mat>();

	public CharacterSegment(Mat result) {
		this.result = result;
	}
	public CharacterSegment(Mat result, List<Mat> listImageNumber) {
		this.result = result;
		this.listImageNumber = listImageNumber;
	}

	public Mat getResult() {
		return result;
	}

	public void setResult(Mat result) {
		this.result = result;
	}

	public List<Mat> getListImageNumber() {
		return listImageNumber;
	}

	public void setListImageNumber(List<Mat> listImageNumber) {
		this.listImageNumber = listImageNumber;
	}

	public CharacterSegment findBoundingBoxes() {
		Contour con = new Contour(result);
		ContourResult contourAction = new ContourResult(con);
		Contour temp = contourAction.createContourEx();
		result = temp.getMat();

		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		List<MatOfPoint> contoursResult = new ArrayList<MatOfPoint>();
		contours = temp.getContours();
		for (int i = 0; i < contours.size(); i++) {

			MatOfPoint2f approxCurve = new MatOfPoint2f();
			MatOfPoint contour = contours.get(i);

			// convert MatOfPoint to MatOfPoint2f
			MatOfPoint2f contourCurrent = new MatOfPoint2f();
			contour.convertTo(contourCurrent, CvType.CV_32F);
			double peri = Imgproc.arcLength(contourCurrent, true);

			Imgproc.approxPolyDP(contourCurrent, approxCurve, peri * 0.03, true);
			if (approxCurve.total() > 6) {

				// convert MatOfPoint2f to MatOfPoint
				MatOfPoint result = new MatOfPoint();
				approxCurve.convertTo(result, CvType.CV_32S);
				contoursResult.add(result);
			}
		}
		for (int i = 0; i < contoursResult.size(); i++) {

			MatOfPoint2f contourCurrent = new MatOfPoint2f();
			contoursResult.get(i).convertTo(contourCurrent, CvType.CV_32F);
			Rect rect = Imgproc.boundingRect(contoursResult.get(i));
			Imgproc.rectangle(result, rect.tl(), rect.br(),
					new Scalar(0, 90, 0));
			SubMat subMat = new SubMat(result, rect);
			Mat imageNumberResult = subMat.dropImage();
			listImageNumber.add(imageNumberResult);
		}
		System.out.println(contoursResult.size());
		return new CharacterSegment(result, listImageNumber);
	}
}
