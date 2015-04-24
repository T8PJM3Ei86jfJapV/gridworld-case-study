import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

import org.junit.Before;
import org.junit.Test;

public class testImageProcessor {

	// @throws java.lang.Exception
	@Before
	public void setUp() throws Exception {
	}

	// Test ShowChanelB method
	@Test
	public void testShowChanelB() {
		// New an ImageIOImplement class and an ImageProcessor class
		ImageIOImplement imageIO = new ImageIOImplement();
		ImageProcessor imageProcessor = new ImageProcessor();

		// Try to read images and test.
		try {
			// Store RGB values in Arrays.
			int[][] destRGB;
			int[][] goalRGB;

			// Using myRead method to load source image,
			// and process it.
			Image srcImage = imageIO.myRead("source/bmptest/1.bmp");
			Image destImage = imageProcessor.showChanelB(srcImage);
			BufferedImage destBuf = new BufferedImage(destImage.getWidth(null),
					destImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
			destRGB = new int[destBuf.getWidth()][ destBuf.getWidth()];
			for (int h = 0; h < destBuf.getHeight(); h++) {
				for (int w = 0; w < destBuf.getWidth(); w++) {
					destRGB[w][h] = destBuf.getRGB(w, h);
				}
			}

			// Read goal image by using API.
			Image goalImage = ImageIO.read(new File("source/bmptest/goal/1_blue_goal.bmp"));
			BufferedImage goalBuf = new BufferedImage(goalImage.getWidth(null),
					goalImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
			goalRGB = new int[goalBuf.getWidth()][ goalBuf.getWidth()];
			for (int h = 0; h < goalBuf.getHeight(); h++) {
				for (int w = 0; w < goalBuf.getWidth(); w++) {
					goalRGB[w][h] = goalBuf.getRGB(w, h);
				}
			}
			
			// Test if Arrays are equal.
			assertArrayEquals(destRGB, goalRGB);

		} catch (IOException e) {
			// e.printStackTrace();
			// fail("IO exception");
		}
	}
	
	// Test ShowChanelR method
	@Test
	public void testShowChanelR() {
		// New an ImageIOImplement class and an ImageProcessor class
		ImageIOImplement imageIO = new ImageIOImplement();
		ImageProcessor imageProcessor = new ImageProcessor();

		// Try to read images and test.
		try {
			// Store RGB values in Arrays.
			int[][] destRGB;
			int[][] goalRGB;

			// Using myRead method to load source image,
			// and process it.
			Image srcImage = imageIO.myRead("source/bmptest/1.bmp");
			Image destImage = imageProcessor.showChanelR(srcImage);
			BufferedImage destBuf = new BufferedImage(destImage.getWidth(null),
					destImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
			destRGB = new int[destBuf.getWidth()][ destBuf.getWidth()];
			for (int h = 0; h < destBuf.getHeight(); h++) {
				for (int w = 0; w < destBuf.getWidth(); w++) {
					destRGB[w][h] = destBuf.getRGB(w, h);
				}
			}

			// Read goal image by using API.
			Image goalImage = ImageIO.read(new File("source/bmptest/goal/1_red_goal.bmp"));
			BufferedImage goalBuf = new BufferedImage(goalImage.getWidth(null),
					goalImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
			goalRGB = new int[goalBuf.getWidth()][ goalBuf.getWidth()];
			for (int h = 0; h < goalBuf.getHeight(); h++) {
				for (int w = 0; w < goalBuf.getWidth(); w++) {
					goalRGB[w][h] = goalBuf.getRGB(w, h);
				}
			}
			
			// Test if Arrays are equal.
			assertArrayEquals(destRGB, goalRGB);

		} catch (IOException e) {
			// e.printStackTrace();
			// fail("IO exception");
		}
	}

	// Test ShowChanelG method
	@Test
	public void testShowChanelG() {
		// New an ImageIOImplement class and an ImageProcessor class
		ImageIOImplement imageIO = new ImageIOImplement();
		ImageProcessor imageProcessor = new ImageProcessor();

		// Try to read images and test.
		try {
			// Store RGB values in Arrays.
			int[][] destRGB;
			int[][] goalRGB;

			// Using myRead method to load source image,
			// and process it.
			Image srcImage = imageIO.myRead("source/bmptest/1.bmp");
			Image destImage = imageProcessor.showChanelG(srcImage);
			BufferedImage destBuf = new BufferedImage(destImage.getWidth(null),
					destImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
			destRGB = new int[destBuf.getWidth()][ destBuf.getWidth()];
			for (int h = 0; h < destBuf.getHeight(); h++) {
				for (int w = 0; w < destBuf.getWidth(); w++) {
					destRGB[w][h] = destBuf.getRGB(w, h);
				}
			}

			// Read goal image by using API.
			Image goalImage = ImageIO.read(new File("source/bmptest/goal/1_green_goal.bmp"));
			BufferedImage goalBuf = new BufferedImage(goalImage.getWidth(null),
					goalImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
			goalRGB = new int[goalBuf.getWidth()][ goalBuf.getWidth()];
			for (int h = 0; h < goalBuf.getHeight(); h++) {
				for (int w = 0; w < goalBuf.getWidth(); w++) {
					goalRGB[w][h] = goalBuf.getRGB(w, h);
				}
			}
			
			// Test if Arrays are equal.
			assertArrayEquals(destRGB, goalRGB);

		} catch (IOException e) {
			// e.printStackTrace();
			// fail("IO exception");
		}
	}

	// Test ShowGray method
	@Test
	public void testShowGray() {
		// New an ImageIOImplement class and an ImageProcessor class
		ImageIOImplement imageIO = new ImageIOImplement();
		ImageProcessor imageProcessor = new ImageProcessor();

		// Try to read images and test.
		try {
			// Store RGB values in Arrays.
			int[][] destRGB;
			int[][] goalRGB;

			// Using myRead method to load source image,
			// and process it.
			Image srcImage = imageIO.myRead("source/bmptest/1.bmp");
			Image destImage = imageProcessor.showGray(srcImage);
			BufferedImage destBuf = new BufferedImage(destImage.getWidth(null),
					destImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
			destRGB = new int[destBuf.getWidth()][ destBuf.getWidth()];
			for (int h = 0; h < destBuf.getHeight(); h++) {
				for (int w = 0; w < destBuf.getWidth(); w++) {
					destRGB[w][h] = destBuf.getRGB(w, h);
				}
			}

			// Read goal image by using API.
			Image goalImage = ImageIO.read(new File("source/bmptest/goal/1_gray_goal.bmp"));
			BufferedImage goalBuf = new BufferedImage(goalImage.getWidth(null),
					goalImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
			goalRGB = new int[goalBuf.getWidth()][ goalBuf.getWidth()];
			for (int h = 0; h < goalBuf.getHeight(); h++) {
				for (int w = 0; w < goalBuf.getWidth(); w++) {
					goalRGB[w][h] = goalBuf.getRGB(w, h);
				}
			}
			
			// Test if Arrays are equal.
			assertArrayEquals(destRGB, goalRGB);

		} catch (IOException e) {
			// e.printStackTrace();
			// fail("IO exception");
		}
	}
}
