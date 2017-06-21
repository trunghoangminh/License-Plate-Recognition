package org.it.tdt.edu.vn.ocrtraining;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.it.tdt.edu.vn.io.FileDirectory;
import org.it.tdt.edu.vn.utils.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.TermCriteria;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.Ml;
import org.opencv.ml.SVM;

/**
 * Make sure all data training and data input the same size.
 * 
 * @author Trung
 *
 */
public class OcrTraining {

	private final static String characters[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "B", "C", "D", "F", "G", "H", "J", "K", "L",
			"M", "N", "P", "R", "S", "T", "V", "W", "X", "Y", "Z" };
	private static final String XML_PATH = "D:\\Data Test\\Image\\G6\\one.xml";
	// 0 1 2 3 4 5 6 7 8 9 A B C D E F G H I J K M N O P Q S T U V X Y Z
	// contains 37 map , each map contains 10 element
	private Map<String, List<Mat>> dataTraining;

	private List<Mat> dataInput;
	// Traingning
	private SVM svm;

	private Mat trainDatas = new Mat();
	private Mat trainLabels = new Mat();

	public OcrTraining() {

		this.svm = SVM.create();
		this.svm.setType(SVM.C_SVC);
		this.svm.setKernel(SVM.LINEAR);
		this.svm.setGamma(3);

		TermCriteria crit = new TermCriteria();
		crit.maxCount = 100;
		crit.epsilon = 0.000001;
		crit.type = TermCriteria.MAX_ITER;

		this.svm.setTermCriteria(crit);
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

	public SVM getSvm() {
		return svm;
	}

	public void setSvm(SVM svm) {
		this.svm = svm;
	}

	public OcrTraining(Map<String, List<Mat>> dataTraining, List<Mat> dataInput) {
		this.dataTraining = dataTraining;
		this.dataInput = dataInput;

	}

	private static Mat getMat(String path) {

		Mat con = Imgcodecs.imread(path, Imgproc.COLOR_BGR2GRAY);
		Mat mat = new Mat(con.height(), con.width(), con.type());
		con.convertTo(mat, CvType.CV_32FC1);
		return mat;
	}

	/**
	 * Get data training
	 * 
	 * @return
	 */
	public void writeResultTraining() {
		List<File> files = new ArrayList<File>(
				FileDirectory.getFiles(Utils.PATH_IMAGE_ZERO));
		files.forEach(file -> {
			Mat mat = getMat(file.getAbsolutePath());

			this.trainDatas.push_back(mat);
			this.trainLabels.push_back(new Mat(mat.height(), mat.width(),
					CvType.CV_32FC1, new Scalar(-1)));
		});
		this.svm.train(trainDatas, Ml.ROW_SAMPLE, trainLabels);
		this.svm.save(XML_PATH);
	}

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		OcrTraining ocrTraining = new OcrTraining();
		ocrTraining.writeResultTraining();

	}

}
