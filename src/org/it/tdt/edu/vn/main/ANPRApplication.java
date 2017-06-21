package org.it.tdt.edu.vn.main;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.it.tdt.edu.vn.io.WriteImageNumber;
import org.it.tdt.edu.vn.platedetection.process.LicensePlateDetection;
import org.it.tdt.edu.vn.utils.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ANPRApplication {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) throws IOException {
		String imgUrlList[] = Utils.listImg;
		for (int k = 0; k < imgUrlList.length; k++) {

			LicensePlateDetection licensePlateDetection = new LicensePlateDetection(
					Utils.PATH_IMAGE_INPUT + imgUrlList[k]);

			Map<String, List<Mat>> results = licensePlateDetection
					.processImagePointBlackBiggerThanPointWhiteTest();
			for (Map.Entry<String, List<Mat>> current : results.entrySet()) {

				for (int i = 0; i < current.getValue().size(); i++) {

					Mat originalImage = current.getValue().get(i);
					Mat resizeImage = new Mat();
					Size newSize = new Size(40, 40);
					Imgproc.resize(originalImage, resizeImage, newSize);

					// ImageResult imageResult = new ImageResult(resizeImage,
					// "Result = " + current.getKey() + " " + i + " x="
					// + resizeImage.cols() + ", y="
					// + resizeImage.rows());
					WriteImageNumber imageNumber = new WriteImageNumber("ImgNumber = "
							+ k + ",Key = " + current.getKey() + ", Number = " + i,
							resizeImage, Utils.PATH_IMAGE_RESULT);
					imageNumber.executeWriteFile();
					// imageResult.showResultImage();
				}
			}
		}
	}
}
