package org.it.tdt.edu.vn.testcase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
}
