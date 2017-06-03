package org.it.tdt.edu.vn.main;

import org.it.tdt.edu.vn.guiwindows.ImageResult;
import org.it.tdt.edu.vn.platedetection.process.LicensePlateDetection;
import org.it.tdt.edu.vn.utils.Utils;
import org.opencv.core.Core;

public class Main {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {
		String imgUrlList[] = Utils.arrBlack;
		// for (int i = 0; i < 1; i++) {
		// LicensePlateDetection licensePlateDetection = new
		// LicensePlateDetection(Utils.PATH_IMAGE_TEST+imgUrlList[i]);
		// licensePlateDetection.licensePlateDetection();
		// }
		// LicensePlateDetection licensePlateDetection = new
		// LicensePlateDetection(
		// Utils.PATH_IMAGE);
		// licensePlateDetection.executePreprocessor();

//		for (int i = 0; i < imgUrlList.length; i++) {
//			LicensePlateDetection licensePlateDetection = new LicensePlateDetection(
//					Utils.PATH_IMAGE_BLACK + imgUrlList[i]);
//
//			ImageResult imageResult = new ImageResult(
//					licensePlateDetection
//							.processImagePointBlackBiggerThanPointWhite(),
//					"Result " + (i + 1));
//			imageResult.showResultImage();
//		}
		 LicensePlateDetection licensePlateDetection = new
		 LicensePlateDetection(
		 Utils.PATH_IMAGE_BLACK + imgUrlList[0]);
		
		 ImageResult imageResult = new ImageResult(
		 licensePlateDetection
		 .processImagePointBlackBiggerThanPointWhite(),
		 "Result");
		 imageResult.showResultImage();

	}// 8 16 10 3
}
