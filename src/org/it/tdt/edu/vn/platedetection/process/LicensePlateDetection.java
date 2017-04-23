package org.it.tdt.edu.vn.platedetection.process;

import java.awt.image.BufferedImage;

import org.it.tdt.edu.vn.guiwindows.ImageResult;
import org.it.tdt.edu.vn.io.OriginalImage;
import org.it.tdt.edu.vn.platedetection.detection.RectangleDetection;
import org.it.tdt.edu.vn.platedetection.preprocessor.BilateralFilteringMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.CannyMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.HistogramEqualizationMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.MorphologyMatBase;
import org.it.tdt.edu.vn.platedetection.preprocessor.OpenMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.OriginalMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.SubtractMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.ThresholdMat;
import org.it.tdt.edu.vn.utils.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author TrungHoang
 *
 */
public class LicensePlateDetection {

	private Mat mat;

	public Mat getMat() {
		return mat;
	}

	public void setMat(Mat mat) {
		this.mat = mat;
	}

	public LicensePlateDetection(Mat mat) {
		super();
		this.mat = mat;
	}

	public void licensePlateDetection() {

		OriginalImage originalImage = new OriginalImage(Utils.PATH_IMAGE);
		BufferedImage bufferedImage = originalImage
				.getImageFromResourcesDirectory();
		OriginalMat originalMat = new OriginalMat(bufferedImage);

		//Step 1
		Mat mat = originalMat.createGrayImage();
		ImageResult imageResult1 = new ImageResult(mat,
				"GrayImage1");
		imageResult1.showResultImage();
		
		//Step 2
		BilateralFilteringMat bilateralFilteringMat = new BilateralFilteringMat(
				mat, 75, 75, 1);
		Mat mat2 = bilateralFilteringMat.createMatResult();
		
		ImageResult imageResult2 = new ImageResult(mat2,
				"Gauss");
		imageResult2.showResultImage();
		//Step 3
		HistogramEqualizationMat histogramEqualizationMat = new HistogramEqualizationMat(
				mat2);

		Mat mat3 = histogramEqualizationMat.createMatResult();
		ImageResult imageResult3 = new ImageResult(mat3,
				"HistogramEqualizationMat");
		imageResult3.showResultImage();
		
		//Step 4
		OpenMat openMat = new OpenMat(
				mat3, Imgproc.MORPH_RECT,
				5, 5, 2.2);
		
		Mat mat4 = openMat.createMatResult();
		ImageResult imageResult4 = new ImageResult(mat4,
				"OpenMat");
		imageResult4.showResultImage();
		
		
		//Step 5
		SubtractMat subtractMat = new SubtractMat(mat4,
				mat3);
		
		Mat mat5 = subtractMat.createMatResult();
		ImageResult imageResult5 = new ImageResult(mat5,
				"SubtractMat");
		imageResult5.showResultImage();
		
		//Step 6
		ThresholdMat thresholdMat = new ThresholdMat(
				mat5, 0, 255, Imgproc.THRESH_OTSU);
		
		Mat mat6 = thresholdMat.createMatResult();
		ImageResult imageResult6 = new ImageResult(mat6,
				"THRESH_OTSU");
		imageResult6.showResultImage();
		
		//Step 7
		CannyMat cannyMat = new CannyMat(mat6, 250,
				255);
		
		Mat mat7 = cannyMat.createMatResult();
		ImageResult imageResult7 = new ImageResult(mat7,
				"GrayImage7");
		imageResult7.showResultImage();
		
		//Step 8
		MorphologyMatBase morphologyMatBase = new MorphologyMatBase(
				mat7, Imgproc.MORPH_RECT, 3, 3, 1);
		Mat mat8 = morphologyMatBase.dilate();
		ImageResult imageResult8 = new ImageResult(mat8,
				"GrayImage8");
		imageResult8.showResultImage();
		
		//Step 9
		RectangleDetection rect = new RectangleDetection(mat8);
		ImageResult imageResult = new ImageResult(rect.executeRectangleDetection(),
				"GrayImage9");
		imageResult.showResultImage();
	}
}
