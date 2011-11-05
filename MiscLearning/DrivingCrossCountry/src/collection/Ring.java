package collection;

public class Ring {
	Place currentPlace = null;

	public boolean isEmpty() {
		return (getNumberOfPlaces() == 0);
	}

	private int getNumberOfPlaces() {
		if (null == currentPlace) return 0;
		
		//String startingPlaceName = currentPlace.getName();
		
		return 0;
	}

	public void appendPlace(Place newPlace) {
//		currentPlace.add(newPlace);
	}

	public int numberOfPlaces() {
		return 1;
//		return getNumberOfPlaces();
	}

}
