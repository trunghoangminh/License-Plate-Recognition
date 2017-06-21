package org.it.tdt.edu.vn.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;

public class WriteImageNumber {

	public static final String EXTENSION_FILE = "png";
	private String name;
	private String directoryUrl;
	private Mat mat;

	public WriteImageNumber(String name, Mat mat, String directoryUrl) {
		this.name = name;
		this.mat = mat;
		this.directoryUrl = directoryUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Mat getMat() {
		return mat;
	}

	public void setMat(Mat mat) {
		this.mat = mat;
	}

	public String getDirectoryUrl() {
		return directoryUrl;
	}

	public void setDirectoryUrl(String directoryUrl) {
		this.directoryUrl = directoryUrl;
	}

	public void executeWriteFile() throws IOException {
		ParseData parseData = new ParseData(mat);
		try {
			// retrieve image
			BufferedImage bi = parseData.convertMatToBufferedImage();
			File outputfile = new File(directoryUrl + name + "."
					+ EXTENSION_FILE);
			ImageIO.write(bi, EXTENSION_FILE, outputfile);
		} catch (IOException e) {
			throw e;
		}
	}
}
