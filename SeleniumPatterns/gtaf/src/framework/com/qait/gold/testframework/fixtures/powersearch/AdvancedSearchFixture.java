
package com.qait.gold.testframework.fixtures.powersearch;

import com.qait.gold.testframework.powersearch.search.AdvancedSearchTestCase;
import fitlibrary.DoFixture;

/**
 * This class is a fixture that hooks up FitNesse test cases with the test framework
 * @author QAIT
 */
public class AdvancedSearchFixture extends DoFixture{

	//an instance of the system under test
    AdvancedSearchTestCase sut;

    public AdvancedSearchFixture() throws Exception {
    	
        sut = new AdvancedSearchTestCase();

        //this part hooks our application to the FitNesse framework
        //all methods in the sut can now be called through FitNesse
        setSystemUnderTest(sut);

    }

}//end class AdvancedSearchFixture
