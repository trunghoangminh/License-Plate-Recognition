package org.it.tdt.edu.vn.io;

import org.apache.commons.io.FileUtils;
import org.it.tdt.edu.vn.platedetection.preprocessor.BilateralFilteringMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.BinaryMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.CannyMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.CloseMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.MorphologyMatBase;
import org.it.tdt.edu.vn.platedetection.preprocessor.OpenMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.OriginalMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.SubtractMat;
import org.it.tdt.edu.vn.platedetection.preprocessor.ThresholdMat;

/**
 * 
 * @author Trung Application context
 */
public class AppContext {

	private OriginalMat originalMat;
	private BilateralFilteringMat bilateralFilteringMat;
	private BinaryMat binaryMat;
	private CannyMat cannyMat;
	private MorphologyMatBase morphologyMatBase;
	private CloseMat closeMat;
	private OpenMat openMat;
	private SubtractMat subtractMat;
	private ThresholdMat thresholdMat;

	public AppContext(OriginalMat originalMat) {
		this.originalMat = originalMat;
	}

	public OriginalMat getOriginalMat() {
		return originalMat;
	}

	public void setOriginalMat(OriginalMat originalMat) {
		this.originalMat = originalMat;
	}

	public BilateralFilteringMat getBilateralFilteringMat() {
		return bilateralFilteringMat;
	}

	public void setBilateralFilteringMat(
			BilateralFilteringMat bilateralFilteringMat) {
		this.bilateralFilteringMat = bilateralFilteringMat;
	}

	public BinaryMat getBinaryMat() {
		return binaryMat;
	}

	public void setBinaryMat(BinaryMat binaryMat) {
		this.binaryMat = binaryMat;
	}

	public CannyMat getCannyMat() {
		return cannyMat;
	}

	public void setCannyMat(CannyMat cannyMat) {
		this.cannyMat = cannyMat;
	}

	public MorphologyMatBase getMorphologyMatBase() {
		return morphologyMatBase;
	}

	public void setMorphologyMatBase(MorphologyMatBase morphologyMatBase) {
		this.morphologyMatBase = morphologyMatBase;
	}

	public CloseMat getCloseMat() {
		return closeMat;
	}

	public void setCloseMat(CloseMat closeMat) {
		this.closeMat = closeMat;
	}

	public OpenMat getOpenMat() {
		return openMat;
	}

	public void setOpenMat(OpenMat openMat) {
		this.openMat = openMat;
	}

	public SubtractMat getSubtractMat() {
		return subtractMat;
	}

	public void setSubtractMat(SubtractMat subtractMat) {
		this.subtractMat = subtractMat;
	}

	public ThresholdMat getThresholdMat() {
		return thresholdMat;
	}

	public void setThresholdMat(ThresholdMat thresholdMat) {
		this.thresholdMat = thresholdMat;
	}
	public static void main(String[] args) {
		//FileUtils.
	}
}
