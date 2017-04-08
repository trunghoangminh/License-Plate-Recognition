package org.it.tdt.edu.vn.io;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * 
 * @author hmtrung
 *         <p>
 *         This class use to parse convert Mat to BufferedImage and otherwise
 *         <p>
 *
 */
public class ParseData {
	private Mat matResult;
	private BufferedImage originalImage;

	public Mat getMatResult() {
		return matResult;
	}

	public void setMatResult(Mat matResult) {
		this.matResult = matResult;
	}

	public BufferedImage getOriginalImage() {
		return originalImage;
	}

	public void setOriginalImage(BufferedImage originalImage) {
		this.originalImage = originalImage;
	}

	/**
	 * 
	 * @param imgUrl
	 *            <p>
	 *            This is constructor used to convert BufferedImage to Mat
	 *            <p>
	 */
	public ParseData(BufferedImage originalImage) {
		this.originalImage = originalImage;
	}

	/**
	 * 
	 * @param image
	 * @param typeByte
	 *            <p>
	 *            This is constructor used to convert Mat to BufferedImage
	 *            <p>
	 */
	public ParseData(Mat matResult) {
		this.matResult = matResult;
	}

	/**
	 * 
	 * @return oringinMat
	 *         <p>
	 *         Return data Mat result
	 *         <p>
	 */
	public Mat convertBufferedImageToMat() {

		byte[] originalData = ((DataBufferByte) originalImage.getRaster()
				.getDataBuffer()).getData();
		Mat oringinMat = new Mat(originalImage.getHeight(),
				originalImage.getWidth(), CvType.CV_8UC3);
		oringinMat.put(0, 0, originalData);
		return oringinMat;
	}

	/**
	 * 
	 * @return destinationImage
	 *         <p>
	 *         Return data BufferedImage result
	 *         <p>
	 */
	public BufferedImage convertMatToBufferedImage() {
		byte[] dataDestination = new byte[matResult.rows() * matResult.cols()
				* (int) (matResult.elemSize())];
		matResult.get(0, 0, dataDestination);
		BufferedImage destinationImage = new BufferedImage(matResult.cols(),
				matResult.rows(), BufferedImage.TYPE_BYTE_GRAY);
				destinationImage.getRaster().setDataElements(0, 0, matResult.cols(),
				matResult.rows(), dataDestination);
		return destinationImage;
	}
}
