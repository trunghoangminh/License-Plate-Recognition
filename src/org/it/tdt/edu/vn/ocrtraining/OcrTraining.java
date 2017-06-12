package org.it.tdt.edu.vn.ocrtraining;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencv.core.Mat;
import org.opencv.ml.KNearest;

/**
 * Make sure all data training and data input the same size.
 * 
 * @author Trung
 *
 */
public class OcrTraining {

	// 0 1 2 3 4 5 6 7 8 9 A B C D E F G H I J K M N O P Q S T U V X Y Z
	// contains 37 map , each map contains 10 element
	private Map<String, List<Mat>> dataTraining;

	// list image get from license
	private List<Mat> dataInput;
	// Traingning
	private KNearest kNearest;

	public KNearest getkNearest() {
		return kNearest;
	}

	public void setkNearest(KNearest kNearest) {
		this.kNearest = kNearest;
	}

	public Map<String, List<Mat>> getDataTraining() {
		return dataTraining;
	}

	public void setDataTraining(Map<String, List<Mat>> dataTraining) {
		this.dataTraining = dataTraining;
	}

	public List<Mat> getDataInput() {
		return dataInput;
	}

	public void setDataInput(List<Mat> dataInput) {
		this.dataInput = dataInput;
	}

	public OcrTraining(Map<String, List<Mat>> dataTraining,
			List<Mat> dataInput, KNearest kNearest) {
		this.dataTraining = dataTraining;
		this.dataInput = dataInput;
		this.kNearest = kNearest;
	}

	/**
	 * Get data training
	 * 
	 * @return
	 */
	public Map<String, List<Mat>> getResultTraining() {
		Map<String, List<Mat>> mapResult = new HashMap<String, List<Mat>>();
		for (Map.Entry<String, List<Mat>> currentList : dataTraining.entrySet()) {
			List<Mat> listResult = new ArrayList<Mat>();
			for (Mat mat : currentList.getValue()) {
				Mat sample = new Mat(mat.rows(), mat.cols(), mat.type());
				boolean sucessed = kNearest.train(mat, 0, sample);
				if(sucessed){
					listResult.add(sample);
				}
			}
			mapResult.put(currentList.getKey(), listResult);
		}
		return mapResult;
	}

	/**
	 * 
	 * @return 
	 */
	public List<String> getNumberOfPlate() {

		Map<String, List<Mat>> mapResult = getResultTraining();
		List<String> listResult = new ArrayList<String>();

		for (Mat currnentInput : dataInput) {
			for (Map.Entry<String, List<Mat>> currentList : mapResult
					.entrySet()) {
				for (Mat mat : currentList.getValue()) {
					float percent = kNearest.findNearest(mat, 0, currnentInput);
					if (percent > 0.8) {
						listResult.add(currentList.getKey());
					}
				}
			}
		}
		return listResult;
	}
}
