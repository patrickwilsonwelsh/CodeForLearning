package fi.iki.elonen.example.fileserver;

import java.io.File;

import fi.iki.elonen.util.UriEncoder;

public class DirectoryContentsFormatter {
	private UriEncoder encoder;
	private String uri;
	
	public DirectoryContentsFormatter(String uri) {
		this.encoder = new UriEncoder();
		this.uri = uri;
	}
	
	public String format(File directory, String[] files) {
		String msg = addDirectoryLabelNameAndFormatting();
		if (weAreBelowRootDirectory()) msg = addParentDirectoryLink(msg);
		msg = addAllFileInformation(directory, files, msg);
		return msg;
	}

	private String addAllFileInformation(File directory, String[] files, String msg) {
		for (int i = 0; i < files.length; ++i) {
			msg = showFile(directory, msg, files, i);
		}
		return msg;
	}

	private String showFile(File directory, String msg, String[] files, int i) {
		File curFile = new File(directory, files[i]);
		
		if (curFile.isDirectory()) msg = prependSlashToFileName(msg, files, i);

		msg = addFilenameLink(msg, files, i);
		msg = addFileSize(msg, curFile);
		msg = msg + HtmlParts.NEW_LINE;
		if (curFile.isDirectory()) msg += HtmlParts.CLOSE_BOLD_TAG;
		return msg;
	}

	private String addDirectoryLabelNameAndFormatting() {
		String message;
		message = openHtmlBodyAndHeadingTagTo();
		message = addDirectoryLabelAndUriTo(message);
		message = addClosingHeadingTagTo(message);
		return message;
	}

	private String prependSlashToFileName(String msg, String[] files, int i) {
		msg = msg +  HtmlParts.OPEN_BOLD_TAG;
		files[i] += HtmlParts.FORWARD_SLASH;
		return msg;
	}

	private String addFilenameLink(String msg, String[] files, int i) {
		msg = msg +  HtmlParts.OPEN_LINK +
		HtmlParts.ESCAPED_QUOTE +
				encoder.encodeUri(uri + files[i]) + HtmlParts.ESCAPED_QUOTE +
				HtmlParts.CLOSING_ANGLE_BRACKET
				+ files[i] + HtmlParts.CLOSE_LINK;
		return msg;
	}

	private String addParentDirectoryLink(String msg) {
		String directoryName = uri.substring(0, uri.length() - 1);
		int slashIndex = directoryName.lastIndexOf(HtmlParts.SLASH_IN_SINGLE_QUOTES);
		if (weHaveAParentDirectory(directoryName, slashIndex)) {
			msg = addOpenHrefTag(msg, slashIndex);
			msg = addParentDirectoryLinkAndClosingHrefTag(msg);
		}
		return msg;
	}

	private boolean weAreBelowRootDirectory() {
		return uri.length() > 1;
	}

	private String addParentDirectoryLinkAndClosingHrefTag(String msg) {
		return msg + HtmlParts.PARENT_DIRECTORY_SYMBOL + HtmlParts.CLOSE_LINK +
		HtmlParts.CLOSE_BOLD_TAG + HtmlParts.NEW_LINE;
	}

	private String addOpenHrefTag(String msg, int slashIndex) {
		return msg + HtmlParts.OPEN_BOLD_TAG + HtmlParts.OPEN_LINK + HtmlParts.ESCAPED_QUOTE + uri.substring(0, slashIndex + 1) 
					+ HtmlParts.ESCAPED_QUOTE + HtmlParts.CLOSING_ANGLE_BRACKET;
	}

	private String addClosingHeadingTagTo(String msg) {
		return msg + HtmlParts.CLOSE_HEADING_TAG + HtmlParts.NEW_LINE;
	}

	private String addDirectoryLabelAndUriTo(String msg) {
		return msg + "Directory " + uri;
	}

	private String openHtmlBodyAndHeadingTagTo() {
		return HtmlParts.OPEN_HTML_BODY + HtmlParts.OPEN_HEADING_ONE_TAG;
	}

	private boolean weHaveAParentDirectory(String directoryName, int slashIndex) {
		return (slashIndex >= 0) && 
			(slashIndex < directoryName.length());
	}

	//REFACTOR Yoiks. Last fugly one. 
	private String addFileSize(String msg, File curFile) {
		if (curFile.isFile()) {
			long len = curFile.length();
			msg = msg + HtmlParts.NON_BREAKING_SPACE +
			HtmlParts.FONT_SIZE_TWO +
			HtmlParts.OPEN_PAREN;
			if (len < HtmlParts.ONE_KILOBYTE)
				msg = msg + curFile.length() + HtmlParts.BYTES;
			else if (len < HtmlParts.ONE_MEGABYTE)
				msg = msg + curFile.length() / HtmlParts.ONE_KILOBYTE + HtmlParts.CURRENT_DIRECTORY_SYMBOL
						+ (curFile.length() % HtmlParts.ONE_KILOBYTE / 10 % 100)
						+ HtmlParts.KILOBYTES_ABBREVIATION;
			else
				msg = msg + curFile.length() / HtmlParts.ONE_MEGABYTE + HtmlParts.CURRENT_DIRECTORY_SYMBOL
						+ curFile.length() % HtmlParts.ONE_MEGABYTE / 10
						% 100 + HtmlParts.MEGABYTES_ABBREVIATION;

			msg = msg + HtmlParts.CLOSE_PAREN +
			HtmlParts.CLOSE_FONT_TAG;
		}
		return msg;
	}


}
