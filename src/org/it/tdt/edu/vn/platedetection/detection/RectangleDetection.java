package org.it.tdt.edu.vn.platedetection.detection;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.it.tdt.edu.vn.io.OriginalImage;
import org.it.tdt.edu.vn.platedetection.preprocessor.OriginalMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.ThresholdMat;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author hmtrung
 *
 */
public class RectangleDetection {

	/**
	 * 
	 */
	private Mat mat;

	public Mat getMat() {
		return mat;
	}

	public void setMat(Mat mat) {
		this.mat = mat;
	}

	public RectangleDetection(Mat mat) {
		this.mat = mat;
	}

	public List<Mat> executeRectangleDetection() {

		// get Mat contour
		Contour con = new Contour(mat);
		ContourResult contourAction = new ContourResult(con);
		mat = contourAction.createContour().getMat();

		Rect rect = null;
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		List<MatOfPoint> contoursResult = new ArrayList<MatOfPoint>();
		contours = contourAction.createContour().getContours();
		for (int i = 0; i < contours.size(); i++) {

			MatOfPoint2f approxCurve = new MatOfPoint2f();
			MatOfPoint contour = contours.get(i);

			// convert MatOfPoint to MatOfPoint2f
			MatOfPoint2f contourCurrent = new MatOfPoint2f();
			contour.convertTo(contourCurrent, CvType.CV_32F);
			double peri = Imgproc.arcLength(contourCurrent, true);

			Imgproc.approxPolyDP(contourCurrent, approxCurve, peri * 0.02, true);
			if (approxCurve.total() == 4) {

				// convert MatOfPoint2f to MatOfPoint
				MatOfPoint result = new MatOfPoint();
				approxCurve.convertTo(result, CvType.CV_32S);

				contoursResult.add(result);
			}
		}
		int k = 0;
		List<MatOfPoint> finalContour = new ArrayList<MatOfPoint>();
		for (int i = 0; i < contoursResult.size(); i++) {
			double contourArea = Imgproc.contourArea(contoursResult.get(i));
			if (contourArea > 50) {
				finalContour.add(contoursResult.get(i));
				rect = Imgproc.boundingRect(contoursResult.get(i));
				k++;
			}
		}
		//System.out.println(k);
		OriginalImage originalImage = new OriginalImage(
				"D:\\plate\\black\\img (1).jpg");
		BufferedImage bufferedImage = originalImage
				.getImageFromResourcesDirectory();
		OriginalMat originalMat = new OriginalMat(bufferedImage);
		Mat mat = originalMat.createGrayImage();

		
		// drop license plate
		
		SubMat subMat = new SubMat(mat, rect);
		Mat result = subMat.dropImage();
		
		Mat matResult = new Mat(result.cols() * 2, result.rows() * 2,
				result.type());
		Imgproc.resize(result, matResult,
				new Size(result.cols() * 2.5, result.rows() * 2.5));

		ThresholdMat thresholdMat = new ThresholdMat(matResult, 0, 255,
				Imgproc.THRESH_OTSU);
		CharacterSegment characterSegment = new CharacterSegment(
				thresholdMat.createMatResult());

		List<Mat> listImageNumber = characterSegment.findBoundingBoxes()
				.getListImageNumber();
		return listImageNumber;
	}

	public Mat executeRectangleDetectionTest() {

		// // get Mat contour
		// Contour con = new Contour(mat);
		// ContourResult contourAction = new ContourResult(con);
		// mat = contourAction.createContour().getMat();
		// Rect rect = null;
		// List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		// List<MatOfPoint> contoursResult = new ArrayList<MatOfPoint>();
		// contours = contourAction.createContour().getContours();
		// for (int i = 0; i < contours.size(); i++) {
		//
		// MatOfPoint2f approxCurve = new MatOfPoint2f();
		// MatOfPoint contour = contours.get(i);
		//
		// //convert MatOfPoint to MatOfPoint2f
		// MatOfPoint2f contourCurrent = new MatOfPoint2f();
		// contour.convertTo(contourCurrent, CvType.CV_32F);
		// double peri = Imgproc.arcLength(contourCurrent, true);
		//
		//
		// Imgproc.approxPolyDP(contourCurrent, approxCurve, peri * 0.02, true);
		// if (approxCurve.total() == 4) {
		//
		// //convert MatOfPoint2f to MatOfPoint
		// MatOfPoint result = new MatOfPoint();
		// approxCurve.convertTo(result, CvType.CV_32S);
		// rect = Imgproc.boundingRect(result);
		// contoursResult.add(result);
		// }
		// }
		//
		// List<MatOfPoint> finalContour = new ArrayList<MatOfPoint>();
		// for (int i = 0; i < contoursResult.size(); i++) {
		// double contourArea = Imgproc.contourArea(contoursResult.get(i));
		// if (contourArea > 50) {
		// finalContour.add(contoursResult.get(i));
		// }
		// }
		//
		// //Imgproc.drawContours(mat, finalContour, -1, new Scalar(81, 190, 0),
		// 1);
		// mat.submat(rect);
		// return mat;
		return new Mat();
	}
}
