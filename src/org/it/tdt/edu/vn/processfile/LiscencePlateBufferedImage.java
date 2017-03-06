package org.it.tdt.edu.vn.processfile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author hmtrung
 * This class use to get image file from URL image and have one method process to return BufferedImage 
 */
public class LiscencePlateBufferedImage {
	
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LiscencePlateBufferedImage(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public BufferedImage getBufferedImageFromImageUrl(){
		File imageFile = new File(imageUrl);
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(imageFile);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return bufferedImage;
	}
}
