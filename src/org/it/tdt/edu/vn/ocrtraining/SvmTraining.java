package org.it.tdt.edu.vn.ocrtraining;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.it.tdt.edu.vn.io.FileDirectory;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Size;
import org.opencv.core.TermCriteria;
import org.opencv.features2d.KAZE;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.ANN_MLP;
import org.opencv.ml.Ml;
import org.opencv.ml.SVM;
import org.opencv.ml.TrainData;
import org.opencv.objdetect.HOGDescriptor;

/**
 * Make sure all data training and data input the same size.
 * 
 * @author Trung
 *
 */
public class SvmTraining {

	public static final String NEGATIVE_FOL = "D:\\Data Test\\Image\\G11\\negative";
	public static final String POSITIVE_FOL = "D:\\Data Test\\Image\\G11\\positive";;
	public static final String TRAINING_DATA_FOL = "D:\\Data Test\\Image\\G11\\train-data\\";

	private final static String characters[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "B", "C", "D", "F", "G", "H", "J", "K", "L",
			"M", "N", "P", "R", "S", "T", "V", "W", "X", "Y", "Z" };
	public static final String XML_PATH = "D:\\Data Test\\Image\\G6\\one.xml";
	// 0 1 2 3 4 5 6 7 8 9 A B C D E F G H I J K M N O P Q S T U V X Y Z
	// contains 37 map , each map contains 10 element
	private Map<String, List<Mat>> dataTraining;

	private List<Mat> dataInput;
	// Traingning
	private SVM svm;

	private Mat trainDatas = new Mat();
	private Mat trainLabels = new Mat();

	public SvmTraining() {

		this.svm = SVM.create();
		this.svm.setType(SVM.C_SVC);
		this.svm.setKernel(SVM.LINEAR);
		this.svm.setGamma(3);
		this.svm.setDegree(SVM.DEGREE);

		TermCriteria crit = new TermCriteria(TermCriteria.MAX_ITER, 100,
				0.000001);
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

	public SvmTraining(Map<String, List<Mat>> dataTraining, List<Mat> dataInput) {
		this.dataTraining = dataTraining;
		this.dataInput = dataInput;

	}

	private static Mat getMat(String path) {

		Mat con = Imgcodecs.imread(path, Imgproc.COLOR_RGB2GRAY);
		Mat mat = new Mat(con.height(), con.width(), con.type());
		con.convertTo(mat, CvType.CV_8UC3, 1.0 / 250.0);
		return mat;
	}

	public static Mat convertType(Mat mat, int type) {
		Mat result = new Mat(mat.height(), mat.width(), type);
		mat.convertTo(result, type);
		return result;
	}

	/**
	 * Get data training
	 * 
	 * @return
	 */
	public void writeResultTraining() {
		// List<File> files = new ArrayList<File>(
		// FileDirectory.getFiles(Utils.PATH_IMAGE_ZERO));
		// files.forEach(file -> {
		// Mat mat = getMat(file.getAbsolutePath());
		// mat.reshape(1, 1);
		// this.trainDatas.push_back(mat);
		// this.trainLabels.push_back(new Mat(1, 1, CvType.CV_32FC1,
		// new Scalar(-1)));
		// });
		// trainDatas.type();
		// TrainData data = TrainData.create(trainDatas, Ml.ROW_SAMPLE,
		// trainLabels);
		// this.svm.train(data);
		// this.svm.save(XML_PATH);

		Mat labels = new Mat(new Size(1, 4), CvType.CV_32SC1);
		labels.put(0, 0, 1);
		labels.put(1, 0, 1);
		labels.put(2, 0, 1);
		labels.put(3, 0, 0);

		Mat data = new Mat(new Size(1, 4), CvType.CV_32FC1);
		data.put(0, 0, 5);
		data.put(1, 0, 2);
		data.put(2, 0, 3);
		data.put(3, 0, 8);
		this.svm.train(data, Ml.ROW_SAMPLE, labels);
		this.svm.save(XML_PATH);
	}
	
	

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		Size winSize = new Size(40, 40);
		Size blockSize = new Size(16, 16);
		Size blockStride = new Size(8, 8);
		Size cellSize = new Size(8, 8);
		int nbins = 9;
		int derivAper = 1;
		int winSigma = -1;
		int histogramNormType = 0;
		double l2HysThresh = 0.2;
		boolean gammalCorrection1 = true;
		int nlevels = 64;

		HOGDescriptor hogDescriptor = new HOGDescriptor(winSize, blockSize,
				blockStride, cellSize, nbins, derivAper, winSigma,
				histogramNormType, l2HysThresh, gammalCorrection1, nlevels, gammalCorrection1);
		SVM svm = SVM.create();
		svm.load("D:\\Data Test\\Image\\G11\\train-data\\0.xml");
		Mat mat = getMat("D:\\Data Test\\Image\\G7\\img (14).png");
		MatOfFloat result = new MatOfFloat();
		hogDescriptor.compute(mat, result);
		System.out.println(result.cols());
		//System.out.println(svm.predict(convertType(result, CvType.CV_32FC1)));
	}

}
