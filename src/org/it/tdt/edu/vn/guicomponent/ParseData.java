package org.it.tdt.edu.vn.guicomponent;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.it.tdt.edu.vn.processfile.LiscencePlateBufferedImage;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class ParseData {
	private String imgUrl;
	private Mat matResult;
	private int typeByte;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

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

	/**
	 * 
	 * @param imgUrl
	 *            This is constructor used to convert BufferedImage to Mat
	 */
	public ParseData(String imgUrl) {
		this.imgUrl = imgUrl;
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
		LiscencePlateBufferedImage image = new LiscencePlateBufferedImage(
				imgUrl);
		BufferedImage originalImage = image.getBufferedImageFromImageUrl();

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
