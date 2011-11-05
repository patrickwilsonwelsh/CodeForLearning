package fi.iki.elonen.example;

import java.util.Properties;

import fi.iki.elonen.server.BasicServer;
import fi.iki.elonen.server.httpsession.Response;

public class NullServer extends BasicServer {
	public Response serve(String uri, String httpMethod, Properties header,
			Properties parms) {
		
		return new Response();
	}

}
