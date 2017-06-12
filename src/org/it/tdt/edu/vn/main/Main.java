package org.it.tdt.edu.vn.main;

import java.util.List;

import org.it.tdt.edu.vn.guiwindows.ImageResult;
import org.it.tdt.edu.vn.platedetection.process.LicensePlateDetection;
import org.it.tdt.edu.vn.utils.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Main {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {
		String imgUrlList[] = Utils.arrBlack;
		LicensePlateDetection licensePlateDetection = new LicensePlateDetection(
				Utils.PATH_IMAGE_BLACK + imgUrlList[0]);

		List<Mat> results = licensePlateDetection
				.processImagePointBlackBiggerThanPointWhiteTest();
		for (int i = 0; i < results.size(); i++) {
			Mat originalImage = results.get(i);
			Mat resizeImage = new Mat();
			Size newSize = new Size(30, 30);
			Imgproc.resize(originalImage, resizeImage, newSize);

			ImageResult imageResult = new ImageResult(resizeImage, "Result "
					+ i + " x=" + resizeImage.cols() + ", y="
					+ resizeImage.rows());
			imageResult.showResultImage();
		}
	}
}
