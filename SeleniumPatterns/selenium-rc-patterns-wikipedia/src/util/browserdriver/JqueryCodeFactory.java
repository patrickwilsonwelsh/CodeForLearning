package util.browserdriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JqueryCodeFactory {
	private static final String WINDOW_JQUERY = "window.jQuery";
	protected static final String JQUERY_FILE_PATH = "resources/BD_jquery.js";
	protected static final String SE_SPECIFIC_JQUERY_OBJECT = WINDOW_JQUERY;
	private static String injectableJqueryLibraryAsString;

	protected static String getVisibilityCode(String jsId) {
		return JqueryCodeFactory.SE_SPECIFIC_JQUERY_OBJECT + "(\"" + jsId + "\").is(':visible')";
	}	
	
	protected static String getJqueryLibraryString() {
		if (injectableJqueryLibraryAsString == null) injectableJqueryLibraryAsString = loadJqueryFromFile();
		
		return injectableJqueryLibraryAsString;
	}
	
	protected static String getJqueryUndefinedString() {
		return WINDOW_JQUERY + " == undefined";
	}


	private static String loadJqueryFromFile()  {
		String jqueryString = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(JqueryCodeFactory.JQUERY_FILE_PATH));
			jqueryString = readJqueryFile(br, jqueryString);
			new FileReader(JqueryCodeFactory.JQUERY_FILE_PATH).close();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return jqueryString;
	}

	private static String readJqueryFile(BufferedReader br, String jqueryString)
			throws IOException {
		String line;
		while ((line = br.readLine()) != null) {
			jqueryString += line += "\r\n";
		}
		return jqueryString;
	}

	public static String getClickCode(String locator) {
		String jQueryBlock = getJqueryExecuteWrapper(locator) + 
			".bind('click.jQueryReallyClick', " + 
			"function(event) {if(event.target.href){ window.location = event.target.href; }}).click().unbind('click.jQueryReallyClick');";
		
		return jQueryBlock;
	}

	public static String getTextCode(String locator) {
		String jQueryBlock = getJqueryExecuteWrapper(locator) + ".text()";
		
		return jQueryBlock;
	}	
	
	private static String getJqueryExecuteWrapper(String locator) {
		return WINDOW_JQUERY + "(\"" + locator + "\")";
	}

}
