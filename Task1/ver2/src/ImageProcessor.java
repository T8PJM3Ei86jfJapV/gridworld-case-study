import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

import imagereader.IImageProcessor;

// Implement IImageProcessor interface.
public class ImageProcessor implements IImageProcessor {

	private static final int REDCHANNEL = 0xffff0000;
	private static final int GREENCHANNEL = 0xff00ff00;
	private static final int BLUECHANNEL = 0xff0000ff;
	private static final int GRAYCHENNAL = 0xff000000;

	private int[] colors;

	public Image showChanelR(Image image) {
		return showChanel(image, REDCHANNEL);
	}

	public Image showChanelG(Image image) {
		return showChanel(image, GREENCHANNEL);
	}

	public Image showChanelB(Image image) {
		return showChanel(image, BLUECHANNEL);
	}


	private Image showChanel(Image image, int colorChannel) {
		// transform a image to BufferedImage
		BufferedImage buf = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_RGB);

		Graphics graphic = buf.createGraphics();
		graphic.drawImage(image, 0, 0, null);
		graphic.dispose();

		colors = new int[buf.getHeight() * buf.getWidth()];

		for (int i = buf.getHeight() - 1; i >= 0; i--) {
			for (int j = 0; j < buf.getWidth(); j++) {
				colors[i * buf.getWidth() + j] = buf.getRGB(j, i) & colorChannel;
			}
		}

		return Toolkit.getDefaultToolkit().createImage(
				new MemoryImageSource(buf.getWidth(), buf
						.getHeight(), colors, 0, buf.getWidth()));
	}

	public Image showGray(Image image) {
		BufferedImage buf = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_RGB);

		Graphics graphic = buf.createGraphics();
		graphic.drawImage(image, 0, 0, null);
		graphic.dispose();

		colors = new int[buf.getHeight() * buf.getWidth()];

		for (int i = buf.getHeight() - 1; i >= 0; i--) {
			for (int j = 0; j < buf.getWidth(); j++) {
				int rgb = buf.getRGB(j, i);

				// get the value of gray
				int gray = (int)(((rgb & 0x00ff0000)>>16)*0.299 + ((rgb & 0x0000ff00)>>8)*0.587 + (rgb & 0x000000ff)*0.114);

				colors[i * buf.getWidth() + j] = (0x00FF << 24)
						| (gray << 16) | (gray << 8) | gray;
			}
		}
		return Toolkit.getDefaultToolkit().createImage(
				new MemoryImageSource(buf.getWidth(), buf
						.getHeight(), colors, 0, buf.getWidth()));
	}

}