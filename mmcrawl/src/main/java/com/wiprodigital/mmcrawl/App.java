package com.wiprodigital.mmcrawl;

import java.io.IOException;
import java.util.*;


/**
 * Traverses and reports on links contains within the given URL  
 * 
 * Usage : App <BaseURL>
 * 
 * @author Mike McFarlane
 *
 */

public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	// Process Command line Entry
    	if (args.length != 1) {
    		System.err.println("Usage : App <BaseURL>");
    		System.exit(1);
    	}
    	
    	// Crawl Site From Given Base
    	WebCrawler siteCrawler = new WebCrawler(args[0]);
    	siteCrawler.crawl();
    			
    	// Process Results
    	System.out.printf("Results for BaseURL : %s \n\n\n", siteCrawler.getBaseURL());
    	List<PageLink> results = siteCrawler.getParseResults(); 
    	
        System.out.println(results);
        
        
    }
}
