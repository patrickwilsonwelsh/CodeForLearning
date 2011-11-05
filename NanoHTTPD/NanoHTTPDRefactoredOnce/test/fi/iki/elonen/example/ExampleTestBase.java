package fi.iki.elonen.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExampleTestBase {

	public String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
	
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return sb.toString();
	}

}
