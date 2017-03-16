package org.it.tdt.edu.vn.processfile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.it.tdt.edu.vn.utils.Utils;

/**
 * 
 * @author hmtrung This class use to get image file from URL image and have one
 *         method process to return BufferedImage
 */
public class LiscencePlateBufferedImage {

	public static BufferedImage getBufferedImageFromImageUrl() {
		File imageFile = new File(Utils.PATH_IMAGE);
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(imageFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return bufferedImage;
	}
}
