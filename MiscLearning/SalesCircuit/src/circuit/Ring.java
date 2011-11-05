package circuit;

public class Ring {
	private Place pointers;
	public int numberOfPlaces;
	
	public Ring() {
		pointers = new Place("pointers");
		pointers.previousPlace = new Place("previous");
		pointers.nextPlace = new Place("next");
		
	}

	public void insertAtEnd(Place newPlace) {
		newPlace.nextPlace = pointers.nextPlace;
		pointers.nextPlace = newPlace;
		
		newPlace.previousPlace = pointers;
		
	}

}
