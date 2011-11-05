package com.qait.gold.testframework.powersearch.search;

/**
 * Class encapsulating a search result
 * @author QAIT
 */
public class SearchResult {

	//document title
    private String title;
    //document publisher
    private String pub;
    //document details
    private String details;
    //document library links
    private String illLink;
    //document library links
    private String vs2Link;
    //document full text link
    private String fullTextLink;
    //document type
    private String documentType;

    public SearchResult() {
    }
    /**
     * 
     * @param title
     * @param pub
     * @param details
     */
    public SearchResult(String title, String pub, String details) {
        this(title, pub, details, null, null, null, null);
    }
    
    /**
     * 
     * @param title
     * @param pub
     * @param details
     * @param documentType
     * @param illLink
     * @param vs2Link
     * @param fullTextLink
     */
    public SearchResult(String title, String pub, String details, String documentType, String illLink, String vs2Link, String fullTextLink) {
        this.title = title;
        this.pub = pub;
        this.details = details;
        this.documentType = documentType;
        this.illLink = illLink;
        this.vs2Link = vs2Link;
        this.fullTextLink = fullTextLink;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("SearchResult[");
        sb.append(title);
        sb.append(", ");
        sb.append(pub);
        sb.append(", ");
        sb.append(details);
        sb.append(", ");
        sb.append(documentType);

        /*sb.append(", ");
        sb.append(illLink);
        sb.append(", ");
        sb.append(vs2Link);
        sb.append(", ");
        sb.append(fullTextLink);
         */

        sb.append("]");

        return sb.toString();
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the pub
     */
    public String getPub() {
        return pub;
    }

    /**
     * @param pub the pub to set
     */
    public void setPub(String pub) {
        this.pub = pub;
    }

    /**
     * @return the detail
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetails(String detail) {
        this.details = detail;
    }

    /**
     * @return the illLink
     */
    public String getIllLink() {
        return illLink;
    }

    /**
     * @param illLink the illLink to set
     */
    public void setIllLink(String illLink) {
        this.illLink = illLink;
    }

    /**
     * @return the vs2Link
     */
    public String getVs2Link() {
        return vs2Link;
    }

    /**
     * @param vs2Link the vs2Link to set
     */
    public void setVs2Link(String vs2Link) {
        this.vs2Link = vs2Link;
    }

    /**
     * @return the fullTextLink
     */
    public String getFullTextLink() {
        return fullTextLink;
    }

    /**
     * @param fullTextLink the fullTextLink to set
     */
    public void setFullTextLink(String fullTextLink) {
        this.fullTextLink = fullTextLink;
    }

    public String getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
}//end class SearchResult

