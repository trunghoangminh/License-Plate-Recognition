package org.it.tdt.edu.vn.main;

import java.util.List;

import org.it.tdt.edu.vn.guiwindows.ImageResult;
import org.it.tdt.edu.vn.platedetection.process.LicensePlateDetection;
import org.it.tdt.edu.vn.utils.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;

public class Main {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {
		String imgUrlList[] = Utils.arrBlack;
		LicensePlateDetection licensePlateDetection = new LicensePlateDetection(
				Utils.PATH_IMAGE_BLACK + imgUrlList[0]);

		
		List<Mat> result = licensePlateDetection.processImagePointBlackBiggerThanPointWhiteTest();
		for (int i = 0; i < result.size(); i++) {
			ImageResult imageResult = new ImageResult(result.get(i),
					"Result " + i);
			imageResult.showResultImage();
		}
	}
}
