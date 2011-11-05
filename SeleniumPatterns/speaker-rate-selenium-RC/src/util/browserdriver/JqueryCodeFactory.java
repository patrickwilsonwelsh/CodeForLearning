package util.browserdriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JqueryCodeFactory {
  private static final String JQUERY_FILE_PATH = "resources/BD_jquery.js";
	protected static final String WINDOW_JQUERY = "window.jQuery";
	private static String injectableJqueryLibraryAsString;
  
  protected String getVisibilityCode(String cssSelector) {
    return WINDOW_JQUERY + "(\"" + cssSelector + "\").is(':visible')";
  } 
  
	protected String getJqueryLibraryString() {
    if (injectableJqueryLibraryAsString == null) injectableJqueryLibraryAsString = loadJqueryFromFile();
    
    return injectableJqueryLibraryAsString;
  }
	
  protected String getJqueryUndefinedString() {
    return WINDOW_JQUERY + " == undefined";
  }

	private String loadJqueryFromFile() {
		String jqueryString = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					JqueryCodeFactory.JQUERY_FILE_PATH));
			jqueryString = readJqueryFile(br, jqueryString);
			new FileReader(JqueryCodeFactory.JQUERY_FILE_PATH).close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return jqueryString;
	}

	private String readJqueryFile(BufferedReader br, String jqueryString)
			throws IOException {
		String line;
		while ((line = br.readLine()) != null) {
			jqueryString += line += "\r\n";
		}
		return jqueryString;
	}

}
