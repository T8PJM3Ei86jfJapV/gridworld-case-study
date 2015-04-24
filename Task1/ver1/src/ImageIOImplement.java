import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import imagereader.*;

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

			// 14 byte BITMAP_FILE_HEADER
			int bflen = BITMAP_FILE_HEADER_SIZE;
			byte bf[] = new byte[bflen];
			fs.read(bf, 0, bflen);

			// 40-byte BITMAP_INFO_HEADER
			int bilen = BITMAP_INFO_HEADER_SIZE;
			byte bi[] = new byte[bilen];
			fs.read(bi, 0, bilen);

			int nwidth = (((int) bi[7] & 0xff) << 24)
					| (((int) bi[6] & 0xff) << 16)
					| (((int) bi[5] & 0xff) << 8) | (int) bi[4] & 0xff;

			int nheight = (((int) bi[11] & 0xff) << 24)
					| (((int) bi[10] & 0xff) << 16)
					| (((int) bi[9] & 0xff) << 8) | (int) bi[8] & 0xff;

			int nbitcount = (((int) bi[15] & 0xff) << 8) | (int) bi[14] & 0xff;

			int nsizeimage = (((int) bi[23] & 0xff) << 24)
					| (((int) bi[22] & 0xff) << 16)
					| (((int) bi[21] & 0xff) << 8) | (int) bi[20] & 0xff;

			// Read a 24-bit Bitmap image.
			if (nbitcount == 24) {
				int npad = (nsizeimage / nheight) - nwidth * 3;
				if (npad == 4) {
					npad = 0;
				}
				int ndata[] = new int[nheight * nwidth];
				byte brgb[] = new byte[(nwidth + npad) * 3 * nheight];
				fs.read(brgb, 0, (nwidth + npad) * 3 * nheight);
				int nindex = 0;
				for (int j = 0; j < nheight; j++) {
					for (int i = 0; i < nwidth; i++) {
						ndata[nwidth * (nheight - j - 1) + i] = (255 & 0xff) << 24
								| (((int) brgb[nindex + 2] & 0xff) << 16)
								| (((int) brgb[nindex + 1] & 0xff) << 8)
								| (int) brgb[nindex] & 0xff;

						nindex += 3;
					}
					nindex += npad;
				}

				image = Toolkit.getDefaultToolkit()
						.createImage(
								new MemoryImageSource(nwidth, nheight, ndata,
										0, nwidth));

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
			BufferedImage bi = new BufferedImage(image.getWidth(null),
					image.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics graph = bi.createGraphics();
			graph.drawImage(image, 0, 0, null);
			graph.dispose();

			// Write the image to a bitmap file.
			ImageIO.write(bi, "bmp", new File(fileName));
			return image;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return image;
	}
}