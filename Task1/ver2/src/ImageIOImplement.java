import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

import javax.imageio.ImageIO;

import imagereader.IImageIO;

// Implement IImageIO interface
public class ImageIOImplement implements IImageIO {

	// --- Private constants
	private final static int BITMAP_FILE_HEADER_SIZE = 14;
	private final static int BITMAP_INFO_HEADER_SIZE = 40;

	// Throws an IOExcepion to load an image.
	public Image myRead(java.lang.String arg) throws java.io.IOException {

		Image image;

		try {
			FileInputStream fs = new FileInputStream(arg);

			// 14 byte BITMAP_FILE_HEADER_SIZE
			int bflen = BITMAP_FILE_HEADER_SIZE;
			byte bf[] = new byte[bflen];
			fs.read(bf, 0, bflen);

			// 40-byte BITMAP_INFO_HEADER_SIZE
			int bilen = BITMAP_INFO_HEADER_SIZE;
			byte info[] = new byte[bilen];
			fs.read(info, 0, bilen);

			int biWidth = (((int) info[7] & 0xff) << 24)
					| (((int) info[6] & 0xff) << 16)
					| (((int) info[5] & 0xff) << 8) | (int) info[4] & 0xff;

			int biHeight = (((int) info[11] & 0xff) << 24)
					| (((int) info[10] & 0xff) << 16)
					| (((int) info[9] & 0xff) << 8) | (int) info[8] & 0xff;

			int biBitCount = (((int) info[15] & 0xff) << 8) | (int) info[14] & 0xff;

			int biSizeImage = (((int) info[23] & 0xff) << 24)
					| (((int) info[22] & 0xff) << 16)
					| (((int) info[21] & 0xff) << 8) | (int) info[20] & 0xff;

			// Read a 24-bit Bitmap image.
			if (biBitCount == 24) {
				int nPad = (biSizeImage / biHeight) - biWidth * 3;
				if (nPad == 4) {
					nPad = 0;
				}
				int ndata[] = new int[biHeight * biWidth];
				byte brgb[] = new byte[(biWidth + nPad) * 3 * biHeight];
				fs.read(brgb, 0, (biWidth + nPad) * 3 * biHeight);
				int nIndex = 0;
				for (int j = 0; j < biHeight; j++) {
					for (int i = 0; i < biWidth; i++) {
						ndata[biWidth * (biHeight - j - 1) + i] = (255 & 0xff) << 24
								| (((int) brgb[nIndex + 2] & 0xff) << 16)
								| (((int) brgb[nIndex + 1] & 0xff) << 8)
								| (int) brgb[nIndex] & 0xff;

						nIndex += 3;
					}
					nIndex += nPad;
				}

				image = Toolkit.getDefaultToolkit()
						.createImage(
								new MemoryImageSource(biWidth, biHeight, ndata,
										0, biWidth));

			} else {
				// If not a 24-bit Bitmap, return null.
				image = (Image) null;
			}

			fs.close();
			return image;

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		return (Image) null;
	}

	public Image myWrite(Image image, String fileName)
			throws java.io.IOException {
		try {
			// transform an image to BufferedImage
			BufferedImage buf = new BufferedImage(image.getWidth(null),
					image.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics graph = buf.createGraphics();
			graph.drawImage(image, 0, 0, null);
			graph.dispose();

			// Write the image to a bitmap file.
			ImageIO.write(buf, "bmp", new File(fileName));
			return image;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return image;
	}
}