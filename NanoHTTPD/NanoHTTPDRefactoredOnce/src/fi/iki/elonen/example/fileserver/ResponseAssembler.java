package fi.iki.elonen.example.fileserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import fi.iki.elonen.server.BasicServer;
import fi.iki.elonen.server.httpsession.Response;

public class ResponseAssembler {
	private static final String FORBIDDEN_LABEL = "FORBIDDEN: ";
	static final String ESCAPED_QUOTE = "\"";
	static final String CURRENT_DIRECTORY_SYMBOL = ".";
	static final String OPEN_HTML_BODY = "<html><body>";
	static final String FORWARD_SLASH = "/";
	static final String PARENT_DIRECTORY_SYMBOL = "..";
	
	private long skipStart;
	private File file;
	private Properties header;	
	
	public ResponseAssembler(Properties header) {
		this.header = header;
	}

	public Response assembleFileResponse(File file) throws FileNotFoundException, IOException {
		this.file = file;
		FileInputStream fis = new FileInputStream(file);
		Response r = new Response(BasicServer.HTTP_OK, getMimeType(), fis);
		skipByteServeRange(fis);
		addContentRangeHeader(r);
		addContentLengthHeader(r);
		return r;
	}

	private void skipByteServeRange(FileInputStream fis) throws IOException {
		String range = header.getProperty("Range");
		skipStart = computeByteServeRange(range);
		fis.skip(skipStart);
	}

	private void addContentRangeHeader(Response r) {
		r.addHeader("Content-range", "" + skipStart + "-"
				+ (file.length() - 1) + FORWARD_SLASH + file.length());
	}
	
	private void addContentLengthHeader(Response r) {
		r.addHeader("Content-length", "" + (file.length() - skipStart));
	}

	private long computeByteServeRange(String range) {
		long startFrom = 0;
		if (range != null) {
			if (range.startsWith("bytes=")) {
				range = range.substring("bytes=".length());
				int minus = range.indexOf('-'); 
				if (minus > 0)
					range = range.substring(0, minus);
				try {
					startFrom = Long.parseLong(range);
				} catch (NumberFormatException nfe) {
				}
			}
		}
		return startFrom;
	}

	private String getMimeType() throws IOException {
		String mime = null;
		int dotIndex = file.getCanonicalPath().lastIndexOf('.');
		if (dotIndex >= 0) {
			String fileSuffix = file.getCanonicalPath()
					.substring(dotIndex + 1).toLowerCase();
			mime = (String) BasicServer.theMimeTypes.get(fileSuffix);
		}
		if (mime == null)
			mime = BasicServer.MIME_DEFAULT_BINARY;
		return mime;
	}
	
	protected Response generateInternalErrorResponse(String message) {
		return new Response(BasicServer.HTTP_INTERNALERROR, BasicServer.MIME_PLAINTEXT,
				message);
	}
	
	protected Response generateNotFoundErrorResponse(String message) {
		return new Response(BasicServer.HTTP_NOTFOUND, BasicServer.MIME_PLAINTEXT,
				message);
	}
	
	protected Response generateForbiddenErrorResponse(String message) {
		return new Response(BasicServer.HTTP_FORBIDDEN, BasicServer.MIME_PLAINTEXT,
				FORBIDDEN_LABEL +
				message);
	}

}
