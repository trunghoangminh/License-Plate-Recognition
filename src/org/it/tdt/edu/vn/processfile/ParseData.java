package org.it.tdt.edu.vn.processfile;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * 
 * @author hmtrung This class use to parse convert Mat to BufferedImage and
 *         reverse.
 *
 */
public class ParseData {
	private Mat matResult;
	private int typeByte;
	private BufferedImage originalImage;

	public Mat getMatResult() {
		return matResult;
	}

	public void setMatResult(Mat matResult) {
		this.matResult = matResult;
	}

	public int getTypeByte() {
		return typeByte;
	}

	public void setTypeByte(int typeByte) {
		this.typeByte = typeByte;
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
	 *            This is constructor used to convert BufferedImage to Mat
	 */
	public ParseData(BufferedImage originalImage) {
		this.originalImage = originalImage;
	}

	/**
	 * 
	 * @param image
	 * @param typeByte
	 *            This is constructor used to convert Mat to BufferedImage
	 */
	public ParseData(Mat matResult, int typeByte) {
		this.matResult = matResult;
		this.typeByte = typeByte;
	}

	public Mat convertBufferedImageToMat() {

		byte[] originalData = ((DataBufferByte) originalImage.getRaster()
				.getDataBuffer()).getData();
		Mat oringinMat = new Mat(originalImage.getHeight(),
				originalImage.getWidth(), CvType.CV_8UC3);
		oringinMat.put(0, 0, originalData);
		return oringinMat;
	}

	public BufferedImage convertMatToBufferedImage() {
		byte[] dataDestination = new byte[matResult.rows() * matResult.cols()
				* (int) (matResult.elemSize())];
		matResult.get(0, 0, dataDestination);
		BufferedImage destinationImage = new BufferedImage(matResult.cols(),
				matResult.rows(), typeByte);
		destinationImage.getRaster().setDataElements(0, 0, matResult.cols(),
				matResult.rows(), dataDestination);
		return destinationImage;
	}
}
