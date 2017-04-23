package org.it.tdt.edu.vn.platedetection.preprocessor;

import org.opencv.core.Mat;

/**
 * 
 * @author TrungHoang
 *         <p>
 *         This is interface common for process image base on Mat
 *         <p>
 *         <p>
 *         Every class process image must implement this interface
 *         <p>
 */
public interface IMatProcess {
	public Mat createMatResult();
}
