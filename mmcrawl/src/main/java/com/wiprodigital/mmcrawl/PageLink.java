package com.wiprodigital.mmcrawl;

/**
 * Class used to house the attributes of a Page
 * Also contains methods to detrmine whether a page is intenal/static content
 * 
 * @author Mike McFarlane
 *
 */

public class PageLink {

	// Regex used to identify static page content
	private final static String regexStatics = ".*\\.(bmp|gif|jpg|png|js|css)$";
		
	// Reference Page and Base URL 
	private String base;
	private String reference;
	
	// Site Internal / External Indicator
	private boolean internalContent = false;
	
	// Static Page Content Indicator
	private boolean staticContent = false;
	
	// HTTP Response Code (for non internal , non static content) 
	private int responseCode = 0;

	/**
	 * Construct PageLink object
	 * 
	 * @param root the base url of crawl
	 * @param url the href for this link
	 */
	public PageLink (String root, String url) {
		
		base = root;
		reference = url;
		
		// Set Internal Indicator
		if (url.startsWith(base)) {
			internalContent = true;
		}
			
		// Set Static Indicator
		if (url.matches(regexStatics)) {
			staticContent = true;
		}
	}
	
	/**
	 * Gets the base URL for the crawl operation
	 * used to determine internal vs. external status
	 * 
	 * @return the base
	 */
	
	public String getBase() {
		return base;
	}


	/**
	 * Returns host page URL
	 * 
	 * @return the page
	 */
	public String getPage() {
		return reference;
	}


	/**
	 * Identifies This as Local or External to Base URL
	 * 
	 * @return the internalContent
	 */
	public boolean isInternalContent() {
		return internalContent;
	}

	/**
	 * Static Content indicator
	 * 
	 * @return the staticContent indicator
	 */
	public boolean isStaticContent() {
		return staticContent;
	}

	/**
	 * HTTP Operation status code
	 * 
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * HTTP Operation status code
	 * 
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PageLink [base=" + base + ", reference=" + reference + ", internalContent=" + internalContent
				+ ", staticContent=" + staticContent + ", responseCode=" + responseCode + "]";
	}

	

}
