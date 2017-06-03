package org.it.tdt.edu.vn.platedetection.process;

import java.awt.image.BufferedImage;

import org.it.tdt.edu.vn.guiwindows.ImageResult;
import org.it.tdt.edu.vn.io.OriginalImage;
import org.it.tdt.edu.vn.platedetection.detection.RectangleDetection;
import org.it.tdt.edu.vn.platedetection.preprocessor.BilateralFilteringMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.CannyMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.CloseMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.HistogramEqualizationMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.MorphologyMatBase;
import org.it.tdt.edu.vn.platedetection.preprocessor.OpenMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.OriginalMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.SubtractMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.ThresholdMat;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author TrungHoang
 *
 */
public class LicensePlateDetection {
	private String imgUrl;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public LicensePlateDetection(String imgUrl) {
		super();
		this.imgUrl = imgUrl;
	}

	public void executePreprocessor() {
		OriginalImage originalImage = new OriginalImage(imgUrl);
		BufferedImage bufferedImage = originalImage
				.getImageFromResourcesDirectory();
		OriginalMat originalMat = new OriginalMat(bufferedImage);

		Mat mat = originalMat.createGrayImage();
		showImageResult(mat, "Ảnh gốc");
		long blackCount = 0;
		long whiteCount = 0;

		for (int i = 0; i < mat.rows(); i++) {
			for (int j = 0; j < mat.cols(); j++) {
				double temp[] = mat.get(i, j);
				if (temp[0] > 230)
					whiteCount++;
				else if (temp[0] < 35)
					blackCount++;
			}
		}
		int index = 0;
		for (int i = 0; i < mat.rows(); i += 16) {
			for (int j = 0; j < mat.cols(); j += 8) {
				Rect rect = new Rect(new Point(i, j), new Size(8, 16));
				index++;
				System.out.println(rect.toString());
			}
		}
		System.out.println(index);
		ThresholdMat thresholdMat = new ThresholdMat(mat, 0, 255,
				Imgproc.THRESH_OTSU);
		Mat mat1 = thresholdMat.createMatResult();
		if (blackCount > whiteCount) {
			showImageResult(mat1, "Ảnh lấy đã lấy ngưỡng");

			CloseMat openMat = new CloseMat(mat1, Imgproc.MORPH_RECT, 5, 5, 1);
			Mat mat2 = openMat.createMatResult();
			showImageResult(mat2, "Thuật toán open");
		} else {

		}
	}

	public void showImageResult(Mat mat, String name) {
		ImageResult imageResult1 = new ImageResult(mat, name);
		imageResult1.showResultImage();
	}

	public void licensePlateDetection() {

		OriginalImage originalImage = new OriginalImage(imgUrl);
		BufferedImage bufferedImage = originalImage
				.getImageFromResourcesDirectory();
		OriginalMat originalMat = new OriginalMat(bufferedImage);

		// Step 1
		Mat mat = originalMat.createGrayImage();

		long blackCount = 0;
		long whiteCount = 0;
		for (int i = 0; i < mat.rows(); i++) {
			for (int j = 0; j < mat.cols(); j++) {
				double temp[] = mat.get(i, j);
				if (temp[0] > 230)
					whiteCount++;
				else if (temp[0] < 35)
					blackCount++;
			}
		}
		System.out.println("whiteCount: " + whiteCount);
		System.out.println("blackCount: " + blackCount);
		// ImageResult imageResult1 = new ImageResult(mat,
		// "GrayImage1");
		// imageResult1.showResultImage();

		// Step 2
		BilateralFilteringMat bilateralFilteringMat = new BilateralFilteringMat(
				mat, 75, 75, 1);
		Mat mat2 = bilateralFilteringMat.createMatResult();

		// ImageResult imageResult2 = new ImageResult(mat2,
		// "Gauss");
		// imageResult2.showResultImage();
		// Step 3
		HistogramEqualizationMat histogramEqualizationMat = new HistogramEqualizationMat(
				mat2);

		Mat mat3 = histogramEqualizationMat.createMatResult();
		// ImageResult imageResult3 = new ImageResult(mat3,
		// "HistogramEqualizationMat");
		// imageResult3.showResultImage();

		// Step 4
		OpenMat openMat = new OpenMat(mat3, Imgproc.MORPH_RECT, 5, 5, 2.2);

		Mat mat4 = openMat.createMatResult();
		// ImageResult imageResult4 = new ImageResult(mat4,
		// "OpenMat");
		// imageResult4.showResultImage();

		// Step 5
		SubtractMat subtractMat = new SubtractMat(mat4, mat3);

		Mat mat5 = subtractMat.createMatResult();
		// ImageResult imageResult5 = new ImageResult(mat5,
		// "SubtractMat");
		// imageResult5.showResultImage();
		//
		// Step 6
		ThresholdMat thresholdMat = new ThresholdMat(mat5, 0, 255,
				Imgproc.THRESH_OTSU);

		// Mat mat6 = thresholdMat.createMatResult();
		// ImageResult imageResult6 = new ImageResult(mat6,
		// "THRESH_OTSU");
		// imageResult6.showResultImage();

		// //Step 7
		// CannyMat cannyMat = new CannyMat(mat6, 250,
		// 255);
		//
		// Mat mat7 = cannyMat.createMatResult();
		// ImageResult imageResult7 = new ImageResult(mat7,
		// "GrayImage7");
		// imageResult7.showResultImage();
		//
		// //Step 8
		// MorphologyMatBase morphologyMatBase = new MorphologyMatBase(
		// mat7, Imgproc.MORPH_RECT, 3, 3, 1);
		// Mat mat8 = morphologyMatBase.dilate();
		// ImageResult imageResult8 = new ImageResult(mat8,
		// "GrayImage8");
		// imageResult8.showResultImage();
		//
		// //Step 9
		// RectangleDetection rect = new RectangleDetection(mat8);
		// ImageResult imageResult = new
		// ImageResult(rect.executeRectangleDetection(),
		// "GrayImage9");
		// imageResult.showResultImage();

	}

	public Mat processImagePointBlackBiggerThanPointWhite() {

		OriginalImage originalImage = new OriginalImage(imgUrl);
		BufferedImage bufferedImage = originalImage
				.getImageFromResourcesDirectory();
		OriginalMat originalMat = new OriginalMat(bufferedImage);

		// Step 1
		Mat mat = originalMat.createGrayImage();

		ThresholdMat thresholdMat = new ThresholdMat(mat, 0, 255,
				Imgproc.THRESH_OTSU);
		Mat threshold = thresholdMat.createMatResult();

		MorphologyMatBase closeMat = new MorphologyMatBase(threshold,
				Imgproc.MORPH_RECT, 1, 1, 1);
		Mat close = closeMat.dilate();

		RectangleDetection rectangleDetection = new RectangleDetection(close);
		Mat rectangle = rectangleDetection.executeRectangleDetection();

		return rectangle;
	}

	public Mat test() {
		OriginalImage originalImage = new OriginalImage(imgUrl);
		BufferedImage bufferedImage = originalImage
				.getImageFromResourcesDirectory();
		OriginalMat originalMat = new OriginalMat(bufferedImage);

		// Step 1
		return originalMat.createGrayImage();
	}

	public Mat processImagePointWhiteBiggerThanPointBlack() {

		OriginalImage originalImage = new OriginalImage(imgUrl);
		BufferedImage bufferedImage = originalImage
				.getImageFromResourcesDirectory();
		OriginalMat originalMat = new OriginalMat(bufferedImage);

		// Step 1
		Mat mat = originalMat.createGrayImage();

		BilateralFilteringMat bilateralFilteringMat = new BilateralFilteringMat(
				mat, 75, 75, 1);
		Mat bilateralFiltering = bilateralFilteringMat.createMatResult();

		// ImageResult imageResult2 = new ImageResult(mat2,
		// "Gauss");
		// imageResult2.showResultImage();
		// Step 3
		// HistogramEqualizationMat histogramEqualizationMat = new
		// HistogramEqualizationMat(
		// bilateralFiltering);
		//
		// Mat histogramEqualization =
		// histogramEqualizationMat.createMatResult();
		// ImageResult imageResult3 = new ImageResult(mat3,
		// "HistogramEqualizationMat");
		// imageResult3.showResultImage();

		// // Step 4
		OpenMat openMat = new OpenMat(bilateralFiltering, Imgproc.MORPH_RECT,
				5, 5, 1);

		Mat open = openMat.createMatResult();
		// // ImageResult imageResult4 = new ImageResult(mat4,
		// // "OpenMat");
		// // imageResult4.showResultImage();
		//
		// Step 5
		// SubtractMat subtractMat = new SubtractMat(open,
		// histogramEqualization);
		// //
		// Mat subtract = subtractMat.createMatResult();
		// // ImageResult imageResult5 = new ImageResult(mat5,
		// // "SubtractMat");
		// // imageResult5.showResultImage();
		// //
		// // Step 6
		ThresholdMat thresholdMat = new ThresholdMat(open, 0, 255,
				Imgproc.THRESH_OTSU);

		Mat threshold = thresholdMat.createMatResult();
		// ImageResult imageResult6 = new ImageResult(mat6,
		// "THRESH_OTSU");
		// imageResult6.showResultImage();

		// //Step 7
		CannyMat cannyMat = new CannyMat(threshold, 250, 255);

		Mat canny = cannyMat.createMatResult();
		// ImageResult imageResult7 = new ImageResult(mat7,
		// "GrayImage7");
		// imageResult7.showResultImage();
		//
		// //Step 8
		MorphologyMatBase morphologyMatBase = new MorphologyMatBase(canny,
				Imgproc.MORPH_RECT, 3, 3, 1);
		Mat morphology = morphologyMatBase.dilate();
		// ImageResult imageResult8 = new ImageResult(mat8,
		// "GrayImage8");
		// imageResult8.showResultImage();
		//
		// //Step 9
		RectangleDetection rect = new RectangleDetection(morphology);
		Mat rectangle = rect.executeRectangleDetectionTest();

		return rectangle;
	}
}
