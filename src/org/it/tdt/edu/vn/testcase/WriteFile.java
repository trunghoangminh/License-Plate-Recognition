package org.it.tdt.edu.vn.testcase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.it.tdt.edu.vn.platedetection.process.LicensePlateDetection;
import org.it.tdt.edu.vn.utils.Utils;
import org.opencv.core.Core;

public class WriteFile {
	private String fileName;
	private String content;

	public WriteFile(String fileName, String content) {
		this.fileName = fileName;
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void writeFileText() {
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(ReadFile.URL_DIRECTORY + fileName);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null)
					bufferedWriter.close();
				if (fileWriter != null)
					fileWriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		LicensePlateDetection ls = new LicensePlateDetection(
				Utils.PATH_IMAGE_BLACK + "img (1).jpg");
		WriteFile writeFile = new WriteFile("demo.txt", ls.test().dump());
		writeFile.writeFileText();
		System.out.println("Done!");
	}
}
