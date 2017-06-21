package org.it.tdt.edu.vn.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Get all file in folder path
 * 
 * @author Trung
 *
 */
public class FileDirectory {
	/**
	 * 
	 * @param path
	 * @return list file in folder
	 */
	public static List<File> getFiles(String path) {
		List<File> files = new ArrayList<File>();
		try {
			files = Files.walk(Paths.get(path)).filter(Files::isRegularFile)
					.map(Path::toFile).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return files;
	}
}
