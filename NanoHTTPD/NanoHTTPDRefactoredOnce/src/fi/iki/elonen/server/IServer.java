package fi.iki.elonen.server;

import java.util.Properties;

import fi.iki.elonen.server.httpsession.ParsedRequest;
import fi.iki.elonen.server.httpsession.Response;

public interface IServer {
	/**
	 * Implement these to customize the server. See the FileServerExample and HelloServerExample
	 * reference examples for details. 
	 * <p>
	 * 
	 * @parm uri Percent-decoded URI without parameters, for example
	 *       "/index.cgi"
	 * @parm method "GET", "POST" etc.
	 * @parm parms Parsed, percent decoded parameters from URI and, in case of
	 *       POST, data.
	 * @parm header Header entries, percent decoded
	 * @return HTTP response, see class Response for details
	 */

	public Response serve(String uri, String httpMethod, Properties header, Properties parms) ;
	public Response serve(ParsedRequest parsedRequest);
}
