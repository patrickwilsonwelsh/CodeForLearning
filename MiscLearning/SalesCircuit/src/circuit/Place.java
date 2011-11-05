package circuit;

public class Place {

	public String placeName;
	public Place previousPlace;
	public Place nextPlace;

	public Place(String placeName) {
		this.placeName = placeName;
	}
	
	public void setPreviousPlace(Place place) {
		this.previousPlace = place;
		
	}

	public void setNextPlafce(Place place) {
		this.nextPlace = place;
		
	}
	
	public String toString() {
		return placeName;
	}

}
