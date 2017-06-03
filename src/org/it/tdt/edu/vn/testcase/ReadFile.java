package org.it.tdt.edu.vn.testcase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public static final String URL_DIRECTORY = "D:\\Text\\";
	private String fileName;

	public ReadFile(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String readFileText() {
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		StringBuffer textResult = new StringBuffer();
		try {
			fileReader = new FileReader(URL_DIRECTORY + fileName);
			bufferedReader = new BufferedReader(fileReader);
			while (bufferedReader.readLine() != null) {
				textResult.append(bufferedReader.readLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null)
					bufferedReader.close();
				if (fileReader != null)
					fileReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return textResult.toString();
	}
}
