import imagereader.IImageProcessor;
import java.awt.*;
import java.awt.image.*;

// Implement IImageProcessor interface.
public class ImageProcessor implements IImageProcessor
{
	private static final int REDCHANNEL = 0xffff0000;
	private static final int GREENCHANNEL = 0xff00ff00;
	private static final int BLUECHANNEL = 0xff0000ff;
	private static final int GRAYCHENNAL = 0xff000000;
	
	// Show red channel
    public Image showChanelR(Image sourceImage) {
        RedFilter filter = new RedFilter();
        Toolkit kit = Toolkit.getDefaultToolkit();
        return kit.createImage(new FilteredImageSource(sourceImage.getSource(), filter));
    }

	// Show green channel
    public Image showChanelG(Image sourceImage) {
        GreenFilter filter = new GreenFilter();
        Toolkit kit = Toolkit.getDefaultToolkit();
        return kit.createImage(new FilteredImageSource(sourceImage.getSource(), filter));
    }

	// Show blue channel
    public Image showChanelB(Image sourceImage) {
        BlueFilter filter = new BlueFilter();
        Toolkit kit = Toolkit.getDefaultToolkit();
        return kit.createImage(new FilteredImageSource(sourceImage.getSource(), filter));
    }

	// Show gray image
    public Image showGray(Image sourceImage) {
        GrayFilter filter = new GrayFilter();
        Toolkit kit = Toolkit.getDefaultToolkit();
        return kit.createImage(new FilteredImageSource(sourceImage.getSource(), filter));
    }
    
    // Extend RBGImageFiler
    class RedFilter extends RGBImageFilter {
        public RedFilter() {
            canFilterIndexColorModel = true;
        }
        public int filterRGB(int x, int y, int rgb) {
            return (rgb & REDCHANNEL);
        }
    }
    
    // Extend RBGImageFiler
    class GreenFilter extends RGBImageFilter {
        public GreenFilter() {
            canFilterIndexColorModel = true;
        }
        public int filterRGB(int x, int y, int rgb) {
            return (rgb & GREENCHANNEL);
        }
    }
    
    // Extend RBGImageFiler
    class BlueFilter extends RGBImageFilter {
        public BlueFilter() {
            canFilterIndexColorModel = true;
        }
        public int filterRGB(int x, int y, int rgb) {
            return (rgb & BLUECHANNEL);
        }
    }
    
    // Extend RBGImageFiler
    class GrayFilter extends RGBImageFilter {
        public GrayFilter() {
            canFilterIndexColorModel = true;
        }
        public int filterRGB(int x, int y, int rgb) {
            int gray = (int)(((rgb & 0x00ff0000)>>16)*0.299 + ((rgb & 0x0000ff00)>>8)*0.587 + (rgb & 0x000000ff)*0.114);
            return (rgb & GRAYCHENNAL)+(gray<<16)+(gray<<8)+gray;
        } 
    }
}