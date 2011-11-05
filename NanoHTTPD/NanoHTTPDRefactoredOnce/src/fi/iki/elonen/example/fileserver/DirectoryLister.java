package fi.iki.elonen.example.fileserver;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import fi.iki.elonen.server.BasicServer;
import fi.iki.elonen.server.httpsession.Response;

public class DirectoryLister {
	private File directory;
	private String uri;
	private File homeDirectory;
	private boolean allowDirectoryListing;
	private String indexFileName;
	private ResponseAssembler assembler;
	private DirectoryContentsFormatter directoryContentsFormatter;

	public DirectoryLister(File homeDirectory, String uri, Properties header) {
		this.allowDirectoryListing = true; //SMELL Configurable?
		this.assembler = new ResponseAssembler(header);
		
		this.homeDirectory = homeDirectory;
		this.uri = discardUriArguments(uri);
		this.directory = new File(homeDirectory, uri);
		this.directoryContentsFormatter = new DirectoryContentsFormatter(uri);
	}
	
	private String discardUriArguments(String fullUri) {
		uri = fullUri.trim().replace(File.separatorChar, HtmlParts.SLASH_IN_SINGLE_QUOTES);
		if (fullUri.indexOf('?') >= 0)
			uri = fullUri.substring(0, fullUri.indexOf('?'));
		
		return uri;
	}
	
	public Response showDirectoryOrFile() {
		if (!homeDirectory.isDirectory()) 
			return assembler.generateInternalErrorResponse("INTERNAL ERROR: serveFile(): given homeDirectory is not a directory.");
		if (attemptingDirectoryChangeSecurityViolation()) 
			return assembler.generateForbiddenErrorResponse("Won't serve ../ for security reasons.");
		if (!directory.exists()) 
			return assembler.generateNotFoundErrorResponse("Error 404, file not found.");
		if (directory.isDirectory()) 
			return handleDirectory();
		
		return showFileInformation(directory);
	}

	private boolean attemptingDirectoryChangeSecurityViolation() {
		return uri.startsWith(HtmlParts.PARENT_DIRECTORY_SYMBOL) || uri.endsWith(HtmlParts.PARENT_DIRECTORY_SYMBOL)
				|| uri.indexOf(HtmlParts.PARENT_DIRECTORY_SYMBOL + HtmlParts.FORWARD_SLASH) >= 0;
	}
	
	private Response handleDirectory() {
		if (directoryContainsAnIndexFile(directory)) return displayIndexFileContents();
		return prepareAndShowDirectoryContents();
	}

	private Response displayIndexFileContents() {
		directory = addIndexDotHtmlToUri(homeDirectory, indexFileName);
		return showFileInformation(directory);
	}

	private boolean directoryContainsAnIndexFile(File file) {
		if  (directoryContainsFileNamed(file, HtmlParts.INDEX_DOT_HTM_L)) {
			indexFileName = HtmlParts.INDEX_DOT_HTM_L;
			return true;
		}
		
		if (directoryContainsFileNamed(file, HtmlParts.INDEX_DOT_HTM)) {
			indexFileName = HtmlParts.INDEX_DOT_HTM;
			return true;
		}
		
		return false;
	}
	
	private Response prepareAndShowDirectoryContents() {
		if (!allowDirectoryListing) return assembler.generateForbiddenErrorResponse("No directory listing.");
		if (!uri.endsWith(HtmlParts.FORWARD_SLASH)) return appendMissingSlashAndRedirect();

		return showDirectoryContents(directory);
	}
	
	private Response appendMissingSlashAndRedirect() {
		uri += HtmlParts.FORWARD_SLASH;
		Response r;
		r = new Response(BasicServer.HTTP_REDIRECT, BasicServer.MIME_HTML,
				HtmlParts.OPEN_HTML_BODY +
				"Redirected: " +
				HtmlParts.OPEN_LINK +
				HtmlParts.ESCAPED_QUOTE + uri + HtmlParts.ESCAPED_QUOTE + HtmlParts.CLOSING_ANGLE_BRACKET
				+ uri + HtmlParts.CLOSE_LINK +
				HtmlParts.CLOSE_BODY_HTML);
		r.addHeader("Location", uri);
		return r;
	}

	Response showFileInformation(File file) {
		try {
			return assembler.assembleFileResponse(file);
		} catch (IOException ioe) {
			return assembler.generateInternalErrorResponse("Reading file failed.");
		}
	}

	boolean directoryContainsFileNamed(File directory, String fileName) {
		return new File(directory, fileName).exists();
	}
	
	File addIndexDotHtmlToUri(File homeDirectory, String indexFileName) {
		return new File(homeDirectory, uri + HtmlParts.FORWARD_SLASH + indexFileName);
	}

	private Response showDirectoryContents(File directory) {
		String[] files = directory.list();
		String msg = directoryContentsFormatter.format(directory, files);
		
		return new Response(BasicServer.HTTP_OK, BasicServer.MIME_HTML, msg);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setHomeDirectory(File homeDirectory) {
		this.homeDirectory = homeDirectory;
	}

	public void setFile(File file) {
		this.directory = file;
	}

	public File getFile() {
		return directory;
	}

	public void setAllowDirectoryListing(boolean allowDirectoryListing) {
		this.allowDirectoryListing = allowDirectoryListing;
	}

}
