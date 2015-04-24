import imagereader.Runner;

// A final class
public final class ImageRunner {
	private ImageRunner() {
		// An Empty private constructor
	}

	public static void main(String[] args){
		ImageIOImplement imageioer = new ImageIOImplement();
		ImageProcessor processor = new ImageProcessor();
		Runner.run(imageioer, processor);
	}
}