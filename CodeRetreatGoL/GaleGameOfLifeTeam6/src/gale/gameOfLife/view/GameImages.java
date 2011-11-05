package gale.gameOfLife.view;

import java.applet.Applet;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.URL;


//TODO Import all graphic file references from properties file	

public class GameImages {
	private MediaTracker tracker;

	private Image filledOMark;
	private Image emptySquare;
	private Image startImage;
	private Image stopImage;
	private Image clearImage;

	public GameImages(Applet applet) {
		tracker = new MediaTracker(applet);
		URL codeBaseUrl = applet.getCodeBase();

		String graphicsDirectory = "../graphics/";
		
		filledOMark = applet.getImage(codeBaseUrl, graphicsDirectory + "oMarkFilled.jpg");
		emptySquare = applet.getImage(codeBaseUrl, graphicsDirectory + "emptySquare.jpg");
		startImage = applet.getImage(codeBaseUrl, graphicsDirectory + "media-playback-start.png");
		stopImage = applet.getImage(codeBaseUrl, graphicsDirectory + "media-playback-stop.png");
		clearImage = applet.getImage(codeBaseUrl, graphicsDirectory + "media-playback-clear.png");

		tracker.addImage(filledOMark, 0);
		tracker.addImage(emptySquare, 0);
		tracker.addImage(startImage, 0);
		tracker.addImage(stopImage, 0);
		tracker.addImage(clearImage, 0);
	}

	public void awaitImageLoad() {
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
			// TODO: might want to inform the user in a friendlier way than an
			// exception.
			throw new RuntimeException(
					"interrupted exception while loading images");
		}
	}

	public Image getImageForPlayerMark(boolean filled) {
		if (filled) return filledOMark;
		return emptySquare;
	}

	public MediaTracker getTracker() {
		return tracker;
	}

	public Image getFilledOMark() {
		return filledOMark;
	}

	public Image getEmptySquare() {
		return emptySquare;
	}

	public Image getStartImage() {
		return startImage;
	}

	public Image getStopImage() {
		return stopImage;
	}

	public Image getClearImage() {
		return clearImage;
	}
}
