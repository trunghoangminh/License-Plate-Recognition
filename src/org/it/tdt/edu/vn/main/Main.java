package org.it.tdt.edu.vn.main;

import org.it.tdt.edu.vn.process.LicensePlateDetection;
import org.opencv.core.Core;
import org.opencv.core.Mat;

public class Main {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) {
		LicensePlateDetection licensePlateDetection = new LicensePlateDetection(new Mat());
		licensePlateDetection.licensePlateDetection();
	}
}
