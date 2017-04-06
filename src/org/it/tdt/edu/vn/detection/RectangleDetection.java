package org.it.tdt.edu.vn.detection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.it.tdt.edu.vn.preprocessor.GrayImage;
import org.it.tdt.edu.vn.preprocessor.Morphology;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

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

	public RectangleDetection() {

	}

	public void executeRectangleDetection() {

		// get Mat contour
		Contour contourAction = new Contour(mat);
		mat = contourAction.createContour().getMat();

		double maxArea = -1;
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		contours = contourAction.getContours();
		MatOfPoint2f approxCurve = new MatOfPoint2f();
		MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
		for (int i = 0; i < contours.size(); i++) {

			MatOfPoint contour = contours.get(i);
			Rect rect = Imgproc.boundingRect(contour);
			double contourArea = Imgproc.contourArea(contour);
			if (contourArea > maxArea) {
				matOfPoint2f.fromList(contour.toList());
				Imgproc.approxPolyDP(matOfPoint2f, approxCurve,
						Imgproc.arcLength(matOfPoint2f, true) * 0.02, true);
				long total = approxCurve.total();
				if (total == 4) {
					List<Double> cos = new ArrayList<>();
					Point[] points = approxCurve.toArray();
					for (int j = 2; j < total + 1; j++) {
						cos.add(angle(points[(int) (j % total)], points[j - 2],
								points[j - 1]));
					}
					Collections.sort(cos);
					Double minCos = cos.get(0);
					Double maxCos = cos.get(cos.size() - 1);
					boolean isRect = total == 4 && minCos >= -0.1
							&& maxCos <= 0.3;

					if (isRect) {
						
						Imgproc.drawContours(mat, contours, -1, new Scalar(255, 255, 0));
//					    double[] temp_double;
//					    temp_double = approxCurve.get(0, 0);
//					    Point p1 = new Point(temp_double[0], temp_double[1]);
//
//					    temp_double = approxCurve.get(1, 0);
//					    Point p2 = new Point(temp_double[0], temp_double[1]);
//
//					    temp_double = approxCurve.get(2, 0);
//					    Point p3 = new Point(temp_double[0], temp_double[1]);
//
//					    temp_double = approxCurve.get(3, 0);
//					    Point p4 = new Point(temp_double[0], temp_double[1]);
//					    List<Point> source = new ArrayList<Point>();
//					    source.add(p1);
//					    source.add(p2);
//					    source.add(p3);
//					    source.add(p4);
//					    Mat startM = Converters.vector_Point2f_to_Mat(source);
//					    Mat result = warp(GrayImage.createThresholdImage(), startM);
						Imgcodecs.imwrite(i+".jpg", mat);
					   //return mat;
					}
				}
			}
		}
		//return null;
	}
	
	
	public static Mat warp(Mat inputMat, Mat startM)
	{

	    int resultWidth = 1200;
	    int resultHeight = 680;

	    Point ocvPOut4 = new Point(0, 0);
	    Point ocvPOut1 = new Point(0, resultHeight);
	    Point ocvPOut2 = new Point(resultWidth, resultHeight);
	    Point ocvPOut3 = new Point(resultWidth, 0);

	    if (inputMat.height() > inputMat.width())
	    {
	        ocvPOut3 = new Point(0, 0);
	        ocvPOut4 = new Point(0, resultHeight);
	        ocvPOut1 = new Point(resultWidth, resultHeight);
	        ocvPOut2 = new Point(resultWidth, 0);
	    }

	    Mat outputMat = new Mat(resultWidth, resultHeight, CvType.CV_8UC4);

	    List<Point> dest = new ArrayList<Point>();
	    dest.add(ocvPOut1);
	    dest.add(ocvPOut2);
	    dest.add(ocvPOut3);
	    dest.add(ocvPOut4);

	    Mat endM = Converters.vector_Point2f_to_Mat(dest);

	    Mat perspectiveTransform = Imgproc.getPerspectiveTransform(startM, endM);

	    Imgproc.warpPerspective(inputMat, outputMat, perspectiveTransform, new Size(resultWidth, resultHeight), Imgproc.INTER_CUBIC);

	    return outputMat;
	}

	private double angle(Point pt1, Point pt2, Point pt0) {
		double dx1 = pt1.x - pt0.x;
		double dy1 = pt1.y - pt0.y;
		double dx2 = pt2.x - pt0.x;
		double dy2 = pt2.y - pt0.y;
		return (dx1 * dx2 + dy1 * dy2)
				/ Math.sqrt((dx1 * dx1 + dy1 * dy1) * (dx2 * dx2 + dy2 * dy2)
						+ 1e-10);
	}

	private void drawText(Point ofs, String text) {
		//Mat mat = GrayImage.createGaussianBlur();
		Imgproc.putText(mat, text, ofs, Core.FONT_HERSHEY_SIMPLEX, 0.5,
				new Scalar(255, 255, 25));
		Imgcodecs.imwrite("/result/demo" + ".jpg", mat);
	}

	public static void main(String[] args) {

	}
}
