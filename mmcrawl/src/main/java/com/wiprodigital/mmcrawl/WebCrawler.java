package com.wiprodigital.mmcrawl;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Main Class which implements functionality to crawl given site
 * starting from a BaseURL location.
 * 
 * @author Mike McFarlane
 *
 */

public class WebCrawler {
	
	// Maintains Page Content/Crawl Results
	private List<PageLink> parseResults = new ArrayList<>();
	
	// Maintains the set of sites/links processed during traversal 
	private Set<String> visited = new HashSet<>();
	
	// The starting URL/Base of the traversal process
	private String baseURL;
	

	/**
	 * Constructs a site Crawler instance give the BaseURL as input
	 * 
	 * @param baseURL	position at which to start site traversal
	 * 
	 */
	
	WebCrawler(String baseURL) {
		this.baseURL = baseURL;
	}
	
	/**
	 * Method to be called by client code to invoke traversal
	 * of the given page/site.
	 * 
	 */
	
	public void crawl() throws IOException {
		
		parseResults.add(new PageLink(baseURL, baseURL));
		
		crawl(parseResults.get(0));
	}
	
	/**
	 * Internal method called recursively to visit each site 
	 * given by an link found within this page or child pages
	 * 
	 * Once completed results are available by calling the 
	 * method getParseResults()
	 * 
	 * @param PageLink 		The page to be inspected
	 * 
	 */
	private void crawl(PageLink page) throws IOException {
		
		// Avoid Pages we have already parsed
        if (!visited.contains(page.getPage())) {
            try {
                
            	visited.add(page.getPage());
            	
            	Document document = null;
            	File input = null;
            	
            	if (page.getPage().startsWith("http")) {
            		System.err.println("Performing HTTP GET for PageLink : " + page.getBase() + "/" + page.getPage());

            		// Establish resource connection
            		Connection.Response response = Jsoup.connect(page.getPage())
            				.timeout(100000)
            				.ignoreHttpErrors(true)
            				.execute();
                
            		page.setResponseCode(response.statusCode());
                
            		if (page.getResponseCode() == 200)
            			document = Jsoup.connect(page.getPage()).get();
            		else
            			System.err.println(
            					"HTTP Error Processing URL '" + page.getPage() + 
            					"', Response Code : " + page.getResponseCode());
            	} else {
            		System.err.println("Performing File Read for PageLink : " + page.getBase() + "/" + page.getPage());
            		
            		input = new File(page.getPage());
            		document = Jsoup.parse(input, "UTF-8");
            	}
            	
				if (document != null) {
					
					// Process Page
					Elements linksOnPage = document.select("a[href]");

					for (Element link : linksOnPage) {
						PageLink refPage = new PageLink(baseURL, link.attr("abs:href"));
						parseResults.add(refPage);
						
						if (refPage.isInternalContent() && !refPage.isStaticContent()) {
							// traverse page link
							crawl(refPage);
						} else {
							// Note reference / link for final reporting purposes only
							System.err.println("Reference to External/Static content found : " + refPage.getPage() );
						}
					}
				} 
				
            } catch (IOException e) {
                throw new IOException("Error getting URL '" + page.getPage() + "' : " + e.getMessage());
            }
        }
	}

	/**
	 * Get Results Of Crawl Operation
	 * 
	 * @return the parseResults
	 */
	public List<PageLink> getParseResults() {
		return parseResults;
	}
	
	/**
	 * Get Base For Crawler
	 * 
	 * @return the baseURL
	 */
	public String getBaseURL() {
		return baseURL;
	}
}
