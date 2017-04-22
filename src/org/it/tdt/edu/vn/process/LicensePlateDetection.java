package org.it.tdt.edu.vn.process;

import java.awt.image.BufferedImage;

import org.it.tdt.edu.vn.detection.RectangleDetection;
import org.it.tdt.edu.vn.guiwindows.ImageResult;
import org.it.tdt.edu.vn.io.OriginalImage;
import org.it.tdt.edu.vn.preprocessor.BilateralFilteringMat;
import org.it.tdt.edu.vn.preprocessor.CannyMat;
import org.it.tdt.edu.vn.preprocessor.HistogramEqualizationMat;
import org.it.tdt.edu.vn.preprocessor.MorphologyMatBase;
import org.it.tdt.edu.vn.preprocessor.OpenMat;
import org.it.tdt.edu.vn.preprocessor.OriginalMat;
import org.it.tdt.edu.vn.preprocessor.SubtractMat;
import org.it.tdt.edu.vn.preprocessor.ThresholdMat;
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

		Mat mat = originalMat.createGrayImage();
		BilateralFilteringMat bilateralFilteringMat = new BilateralFilteringMat(
				mat, 75, 75, 1);

		HistogramEqualizationMat histogramEqualizationMat = new HistogramEqualizationMat(
				bilateralFilteringMat.createMatResult());

		OpenMat openMat = new OpenMat(
				histogramEqualizationMat.createMatResult(), Imgproc.MORPH_RECT,
				5, 5, 2);

		SubtractMat subtractMat = new SubtractMat(openMat.createMatResult(),
				histogramEqualizationMat.createMatResult());

		ThresholdMat thresholdMat = new ThresholdMat(
				subtractMat.createMatResult(), 0, 255, Imgproc.THRESH_OTSU);

		CannyMat cannyMat = new CannyMat(thresholdMat.createMatResult(), 250,
				255);

		MorphologyMatBase morphologyMatBase = new MorphologyMatBase(
				cannyMat.createMatResult(), Imgproc.MORPH_RECT, 3, 3, 1);

		RectangleDetection rect = new RectangleDetection(morphologyMatBase.dilate());
		
		
		ImageResult imageResult = new ImageResult(rect.executeRectangleDetection(),
				"GrayImage");
		imageResult.showResultImage();
	}
}
