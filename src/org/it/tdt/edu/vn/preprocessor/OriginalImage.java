package org.it.tdt.edu.vn.preprocessor;

import java.awt.image.BufferedImage;
import org.it.tdt.edu.vn.processfile.LiscencePlateBufferedImage;
import org.it.tdt.edu.vn.processfile.ParseData;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author hmtrung This class use get mat original and create gray image.
 *
 */
public class OriginalImage {

	private static Mat getImage() {

		BufferedImage bufferedImage = LiscencePlateBufferedImage
				.getBufferedImageFromImageUrl();
		ParseData parseData = new ParseData(bufferedImage);
		Mat matOriginal = parseData.convertBufferedImageToMat();
		return matOriginal;
	}

	public static Mat getOriginalImage() {
		Mat originalMat = getImage();
		return originalMat;
	}

	/**
	 * 
	 * @return destinationMat
	 *         <p>
	 *         Create Mat image.
	 *         </p>
	 */
	public static Mat createGrayImage() {
		Mat originalMat = getImage();

		Mat destinationMat = new Mat(originalMat.cols(), originalMat.rows(),
				CvType.CV_8UC1);
		Imgproc.cvtColor(originalMat, destinationMat, Imgproc.COLOR_RGB2GRAY);
		byte[] dataDestination = new byte[destinationMat.rows()
				* destinationMat.cols() * (int) (destinationMat.elemSize())];
		destinationMat.get(0, 0, dataDestination);
		return destinationMat;
	}
}
