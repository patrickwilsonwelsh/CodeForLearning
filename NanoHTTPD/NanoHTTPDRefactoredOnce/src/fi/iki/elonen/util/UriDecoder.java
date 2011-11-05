package fi.iki.elonen.util;
import java.util.Properties;
import java.util.StringTokenizer;

public class UriDecoder {
	private static final char SPACE = ' ';
	public static final String BAD_URI_INDICATOR = "bad-uri-indicator";

	public String decodePercentAndPlus(String str) throws InterruptedException {
		StringBuffer sb = new StringBuffer();
		try {
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c == '+') {c = SPACE;}
				if (c == '%') {
					char encodedNumber = (char)Integer.parseInt(str.substring(i + 1, i + 3), 16);
					c =  encodedNumber; 
					i += 2;
				}
				sb.append(c);
			}
		} catch (Exception e) {
			 return BAD_URI_INDICATOR;
		}

		return new String(sb.toString());
	}

	public Properties decodeRequestParameters(String allParameters, Properties requestParameters)
			throws InterruptedException {
		if (allParameters == null) return requestParameters;

		StringTokenizer st = separateOnAmpersand(allParameters);
		while (st.hasMoreTokens()) {
			requestParameters = decodeNextToken(requestParameters, st);
		}
		
		return requestParameters;
	}

	private Properties decodeNextToken(Properties requestParameters,
			StringTokenizer st) throws InterruptedException {
		String expression = st.nextToken();
		int equalSignIndex = expression.indexOf('=');
		if (equalSignIndex >= 0) requestParameters = 
			decodeKeyValuePair(requestParameters, expression, equalSignIndex);
		return requestParameters;
	}

	private StringTokenizer separateOnAmpersand(String parms) {
		return new StringTokenizer(parms, "&");
	}

	private Properties decodeKeyValuePair(Properties requestParameters,
			String expression, int equalsSignindex) throws InterruptedException {
		
		String firstHalfOfExpression = expression.substring(0, equalsSignindex).trim();
		String secondHalfOfExpression = expression.substring(equalsSignindex + 1);
		requestParameters.put(decodePercentAndPlus(firstHalfOfExpression), 
				decodePercentAndPlus(secondHalfOfExpression));
		
		return requestParameters;
	}

}
