package serviceTest;

import fit.ColumnFixture;

public class BenefitsInterfaceFixture extends ColumnFixture{
	
	public String benefitsRequestXMLSource;
	public String benefitsResponseXMLSource;
	
	public boolean requestSourceFound() {
		return true;
	}
	
	public boolean responseSourceFound() {
		return true;
	}
	
	public boolean benefitsServiceRespondedAsExpected() {
		return true;
	}


}
